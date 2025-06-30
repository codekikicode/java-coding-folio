import java.io.*;
import java.util.*;

public class BabysitterPayroll {

    public static void main(String[] args) {
        List<Babysitter> babysitters = new ArrayList<>();
        Map<String, List<String>> payrollData = new HashMap<>();

        loadPersonnelData(babysitters);
        loadPayrollData(payrollData);
        generatePayrollReport(babysitters, payrollData);
    }

    private static void loadPersonnelData(List<Babysitter> babysitters) {
        try (BufferedReader br = new BufferedReader(new FileReader("personnel.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String employeeNumber = line.trim();
                String[] name = br.readLine().split(", ");
                br.readLine();
                br.readLine();
                String[] rates = br.readLine().split("\\s+");

                babysitters.add(new Babysitter(employeeNumber, name[0], name[1],
                        Double.parseDouble(rates[0]), Double.parseDouble(rates[1]), Double.parseDouble(rates[2])));

                br.readLine();
            }
        } catch (IOException e) {
            System.err.println("Error reading personnel.txt: " + e.getMessage());
        }
    }

    private static void loadPayrollData(Map<String, List<String>> payrollData) {
        try (BufferedReader br = new BufferedReader(new FileReader("payroll.txt"))) {
            String line;
            String currentEmployee = null;
            while ((line = br.readLine()) != null) {
                line = line.trim();

                if (line.isEmpty()) continue;
                if (line.matches("\\d{4}")) {
                    currentEmployee = line;
                    payrollData.put(currentEmployee, new ArrayList<>());
                } else if (currentEmployee != null) {
                    payrollData.get(currentEmployee).add(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading payroll data: " + e.getMessage());
        }
    }


    private static void generatePayrollReport(List<Babysitter> babysitters, Map<String, List<String>> payrollData) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("payroll_report.txt"))) {

            babysitters.sort(Comparator.comparing(Babysitter::getFirstName).reversed());

            for (Babysitter b : babysitters) {
                bw.write("Babysitter: " + b.getFirstName() + " " + b.getLastName() + " (" + b.getEmployeeNumber() + ")\n");

                List<String> shifts = payrollData.get(b.getEmployeeNumber());
                double totalPay = 0;

                if (shifts != null) {
                    for (String shift : shifts) {
                        String[] times = shift.split("\\s+");
                        if (times.length == 2) {
                            totalPay += calculatePay(times[0], times[1], b);
                        }
                    }
                }

                bw.write("Club Pay Total: $" + String.format("%.2f", totalPay) + "\n\n");
            }
            System.out.println("Payroll report generated successfully!");
        } catch (IOException e) {
            System.err.println("Error writing payroll report: " + e.getMessage());
        }
    }

    private static double calculatePay(String start, String end, Babysitter b) {
        int startHour = Integer.parseInt(start.split(":")[0]);
        int endHour = Integer.parseInt(end.split(":")[0]);
        double total = 0;

        while (startHour != endHour) {
            if (startHour < 21) {
                total += b.getRateBefore9();
            } else if (startHour < 24) {
                total += b.getRate9toMidnight();
            } else {
                total += b.getRateAfterMidnight();
            }

            startHour = (startHour + 1) % 24;
        }

        return total;
    }
}