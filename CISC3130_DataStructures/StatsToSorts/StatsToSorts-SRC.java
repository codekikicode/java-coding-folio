import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;


public class Sorts {
    static int bubbleComparisons = 0;
    static int quickComparisons = 0;
    static int mergeComparisons = 0;

    public static void main(String[] args) {
        try {
            File file = new File("src/sort_test_input");
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String heading = sc.nextLine().trim();
                if (heading.isEmpty()) continue;

                System.out.println("Heading: " + heading);

                if (!sc.hasNextLine()) break;
                String[] numsStr = sc.nextLine().split(" ");
                int[] original = new int[numsStr.length];
                for (int i = 0; i < numsStr.length; i++) {
                    original[i] = Integer.parseInt(numsStr[i]);
                }

                System.out.println("Original: " + Arrays.toString(original));
                runSorts(original);
                System.out.println();
            }

            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    public static void runSorts(int[] data) {
        int[] bubbleData = Arrays.copyOf(data, data.length);
        int[] quickData = Arrays.copyOf(data, data.length);
        int[] mergeData = Arrays.copyOf(data, data.length);

        bubbleComparisons = 0;
        quickComparisons = 0;
        mergeComparisons = 0;

        bubbleSort(bubbleData);
        System.out.println("Bubble Sort: " + Arrays.toString(bubbleData));
        System.out.println("Comparisons: " + bubbleComparisons);

        quickSort(quickData, 0, quickData.length - 1);
        System.out.println("Quick Sort: " + Arrays.toString(quickData));
        System.out.println("Comparisons: " + quickComparisons);

        mergeSort(mergeData, 0, mergeData.length - 1);
        System.out.println("Merge Sort: " + Arrays.toString(mergeData));
        System.out.println("Comparisons: " + mergeComparisons);
    }

    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                bubbleComparisons++;
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = quickPartition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    public static int quickPartition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            quickComparisons++;
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
            }
        }
        int temp = arr[i+1]; arr[i+1] = arr[high]; arr[high] = temp;
        return i+1;
    }

    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    public static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            mergeComparisons++;
            if (arr[i] <= arr[j]) temp[k++] = arr[i++];
            else temp[k++] = arr[j++];
        }
        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];

        System.arraycopy(temp, 0, arr, left, temp.length);
    }
}