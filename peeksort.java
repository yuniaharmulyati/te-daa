import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class peeksort {

    public static void merge(int[] A, int l, int m, int r, int[] B) {
        int i, j;

        for (i = m; i > l; --i) {
            B[i - 1] = A[i - 1];
        }
        for (j = m-1; j < r; ++j) { // j = 2
            B[r + m - 1 - j] = A[j + 1];
        }
        for (int k = l; k <= r; ++k){
            if (B[j] < B[i]){
                A[k] =  B[j--];
            }else {
                A[k] = B[i++];
            }
        }
    }
    public static void reverseRange(int[] a, int lo, int hi) {
        while (lo < hi) {
            int t = a[lo];
            a[lo++] = a[hi];
            a[hi--] = t;
        }
    }
    public static int extendWeaklyIncreasingRunLeft(final int[] A, int i, final int l) {
        while (i > l && A[i-1] <= A[i]){
            --i;
        }
        return i;
    }

    public static int extendWeaklyIncreasingRunRight(final int[] A, int i, final int r) {
        while (i < r && A[i+1] >= A[i]){
            ++i;
        }
        return i;
    }

    public static int extendStrictlyDecreasingRunLeft(final int[] A, int i, final int l) {
        while (i > l && A[i-1] > A[i]){
            --i;
        }
        return i;
    }

    public static int extendStrictlyDecreasingRunRight(final int[] A, int i, final int r) {
        while (i < r && A[i+1] < A[i]){
            ++i;
        }
        return i;
    }
    public static void peeksort(int[] A, int l, int r, int e, int s, final int[] B) {
        if (e == r || s == l) return;

        int m = l + ((r - l)/ 2);

        if (m <= e) {
            peeksort(A, e+1, r, e+1,s, B);
            merge(A, l, e+1, r, B);
        } else if (m >= s) {
            peeksort(A, l, s-1, e, s-1, B);
            merge(A, l, s, r, B);
        } else {
            final int i, j;
            if (A[m] <= A[m+1]) {
                i = extendWeaklyIncreasingRunLeft(A, m, l == e ? l : e+1);
                j = m+1 == s ? m : extendWeaklyIncreasingRunRight(A, m+1, r == s ? r : s-1);
            } else {
                i = extendStrictlyDecreasingRunLeft(A, m, l == e ? l : e+1);
                j = m+1 == s ? m : extendStrictlyDecreasingRunRight(A, m+1,r == s ? r : s-1);
                reverseRange(A, i, j);
            }
            if (i == l && j == r) return;
            if (m - i < j - m) {
                peeksort(A, l, i-1, e, i-1, B);
                peeksort(A, i, r, j, s, B);
                merge(A,l, i, r, B);
            } else {
                peeksort(A, l, j, e, i, B);
                peeksort(A, j+1, r, j+1, s, B);
                merge(A,l, j+1, r, B);
            }
        }
    }
    public static void main(String[] args) {
        String filename = "large_inverse.txt";
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

//        int[] arr = Arrays.copyOf(numbersList.stream().mapToInt(Integer::intValue).toArray(), numbersList.size());
        int[] arr = {2,5,4,1,3};
        int[] temp = Arrays.copyOf(arr, arr.length);
//        System.out.println("Original Array: "+ Arrays.toString(arr));
        peeksort(arr, 0, arr.length-1, 0, arr.length-1, temp);
//        System.out.println("Sorted Array: " + Arrays.toString(arr));

        long time = System.nanoTime() - startTime;
        long memory = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())- startMemory;

        System.out.println("Time: " + time / 1e6 + " ms");
        System.out.println("Memory: " + memory / 1024 + " KB");
        System.out.println("---------------------------------------------------");
    }
}

// referensi : https://github.com/sebawild/nearly-optimal-mergesort-code/blob/master/src/wildinter/net/mergesort/MergesAndRuns.java