import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class radixCountingSort {
    static int getMax(int[] arr, int n) {
        int max = arr[0];
        for (int i = 1; i < n; i++)
            if (arr[i] > max)
                max = arr[i];
        return max;
    }

    static void countSort(int[] arr, int n, int exp) {
        int[] output = new int[n];
        int[] count = new int[10];
        Arrays.fill(count, 0);

        for (int i = 0; i < n; i++)
            count[(arr[i] / exp) % 10]++;

        for (int i = 1; i < 10; i++)
            count[i] += count[i - 1];

        for (int i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }
        System.arraycopy(output, 0, arr, 0, n);
    }

    static void radixSort(int[] arr, int n) {
        int max = getMax(arr, n);

        for (int exp = 1; max / exp > 0; exp *= 10)
            countSort(arr, n, exp);
    }

    public static void main(String[] args) {
        String filename = "large_sorted.txt";
        List<Integer> numbersList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                numbersList.add(Integer.parseInt(line.trim()));
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        long startTime = System.nanoTime();
        long startMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        int[] arr = Arrays.copyOf(numbersList.stream().mapToInt(Integer::intValue).toArray(), numbersList.size());
//        int[] arr = {170, 45, 75, 90, 802, 24, 2, 66};
        int n = arr.length;
        radixSort(arr, n);
//        System.out.println("Sorted Array: " + Arrays.toString(arr));

        long time = System.nanoTime() - startTime;
        long memory = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())- startMemory;

        System.out.println("Time: " + time / 1e6 + " ms");
        System.out.println("Memory: " + memory / 1024 + " KB");
        System.out.println("---------------------------------------------------");
    }
}
