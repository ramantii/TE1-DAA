import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomDataset {
    private static Random rng = new Random();

    public static void main(String[] args) {
        generateAndWriteRandomDataset("random500.txt", 500, 1, 500);
        generateAndWriteSortedDataset("sorted500.txt", 500, 1, 500);
        generateAndWriteReversedDataset("reversed500.txt", 500, 1, 500);

        generateAndWriteRandomDataset("random5000.txt", 5000, 1, 5000);
        generateAndWriteSortedDataset("sorted5000.txt", 5000, 1, 5000);
        generateAndWriteReversedDataset("reversed5000.txt", 5000, 1, 5000);

        generateAndWriteRandomDataset("random50000.txt", 50000, 1, 50000);
        generateAndWriteSortedDataset("sorted50000.txt", 50000, 1, 50000);
        generateAndWriteReversedDataset("reversed50000.txt", 50000, 1, 50000);
    }

    public static void generateAndWriteRandomDataset(String filename, int size, int rangeMin, int rangeMax) {
        List<Integer> data = generateRandom(size, rangeMin, rangeMax);
        writeToFile(filename, data);
    }

    public static void generateAndWriteSortedDataset(String filename, int size, int rangeMin, int rangeMax) {
        List<Integer> data = generateSorted(size, rangeMin);
        writeToFile(filename, data);
    }

    public static void generateAndWriteReversedDataset(String filename, int size, int rangeMin, int rangeMax) {
        List<Integer> data = generateReversed(size, rangeMax);
        writeToFile(filename, data);
    }

    public static List<Integer> generateRandom(int n, int rangeMin, int rangeMax) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(rng.nextInt(rangeMax - rangeMin + 1) + rangeMin);
        }
        return list;
    }

    public static List<Integer> generateSorted(int n, int rangeMin) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }
        return list;
    }

    public static List<Integer> generateReversed(int n, int rangeMax) {
        List<Integer> list = generateSorted(n, rangeMax - n + 1);
        Collections.reverse(list);
        return list;
    }

    public static void writeToFile(String filename, List<Integer> data) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (Integer value : data) {
                writer.write(value.toString());
                writer.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
