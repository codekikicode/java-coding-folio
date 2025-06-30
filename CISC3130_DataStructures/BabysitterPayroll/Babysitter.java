public class Babysitter {
    private String employeeNumber;
    private String lastName;
    private String firstName;
    private double rateBefore9;
    private double rate9toMidnight;
    private double rateAfterMidnight;

    public Babysitter(String employeeNumber, String lastName, String firstName,
                      double rateBefore9, double rate9toMidnight, double rateAfterMidnight) {
        this.employeeNumber = employeeNumber;
        this.lastName = lastName;
        this.firstName = firstName;
        this.rateBefore9 = rateBefore9;
        this.rate9toMidnight = rate9toMidnight;
        this.rateAfterMidnight = rateAfterMidnight;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public double getRateBefore9() {
        return rateBefore9;
    }

    public double getRate9toMidnight() {
        return rate9toMidnight;
    }

    public double getRateAfterMidnight() {
        return rateAfterMidnight;
    }
}