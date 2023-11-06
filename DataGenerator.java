import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class DataGenerator {
    public static void generateSorted(int x, String filename) {
        int[] sortedArray = new int[x];
        for (int i = 0; i < x; i++) {
            sortedArray[i] = i + 1; // Generating sequential numbers
        }
        exportToFile(sortedArray, filename);
    }
    public static void generateRandom(int x, String filename) {
        Set<Integer> randomArray = new LinkedHashSet<>();
        Random random = new Random();

        while (randomArray.size() < x) {
            randomArray.add(random.nextInt(x)+1);
        }
        exportToFile(randomArray, filename);
    }
    public static void generateInversed(int x, String filename) {
        int[] inversedArray = new int[x];
        for (int i = 0; i < x; i++) {
            inversedArray[i] = x - i;
        }
        exportToFile(inversedArray, filename);
    }

    public static void exportToFile(int[] array, String filename) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            for (int number : array) {
                writer.write(Integer.toString(number));
                writer.newLine();
            }
            writer.close();
            System.out.println("Data exported to " + filename);
        } catch (IOException e) {
            System.err.println("Error exporting data to file: " + e.getMessage());
        }
    }
    public static void exportToFile(Set<Integer> set, String filename) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            for (int number : set) {
                writer.write(Integer.toString(number));
                writer.newLine();
            }
            writer.close();
            System.out.println("Data exported to " + filename);
        } catch (IOException e) {
            System.err.println("Error exporting data to file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
//        generateSorted(1000, "small_sorted.txt");
//        generateRandom(1000, "small_random.txt");
//        generateInversed(1000, "small_inverse.txt");
//
//        generateSorted(10000, "medium_sorted.txt");
//        generateRandom(10000, "medium_random.txt");
//        generateInversed(10000, "medium_inverse.txt");
//
//        generateSorted(100000, "large_sorted.txt");
//        generateRandom(100000, "large_random.txt");
//        generateInversed(100000, "large_inverse.txt");
        generateInversed(100000000, "superlarge.txt");
    }
}

