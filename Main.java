import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int[] datasetSizes = {500, 5000, 50000};

        SortingAlgorithm[] algorithms = {new BCIS(), new CountingSort()}; // Add more sorting algorithms as needed

        for (int size : datasetSizes) {
            for (SortingAlgorithm algorithm : algorithms) {
                sortDataset("random" + size + ".txt", algorithm);
                sortDataset("sorted" + size + ".txt", algorithm);
                sortDataset("reversed" + size + ".txt", algorithm);
            }
        }

        System.out.println("\nAll datasets have been successfully sorted with different algorithms.");
    }

    private static void sortDataset(String filename, SortingAlgorithm sortingAlgorithm) {
        ArrayList<Integer> dataset = loadDataset(filename);
        Runtime.getRuntime().gc();
        long startTime = System.nanoTime();
        long startMemory= getUsedMemory();
        sortingAlgorithm.sort(dataset);
        long endMemory = getUsedMemory();
        long endTime = System.nanoTime();

        System.out.println("**************** Sorting Report ****************");
        System.out.println("[Algorithm]: " + sortingAlgorithm.getClass().getSimpleName());
        System.out.println("[Dataset]: " + filename);
        System.out.println("[Time Elapsed]: " + (endTime - startTime) * 1e-6 + " ms");
        System.out.println("[Memory Usage]: " +(endMemory - startMemory) +"bytes");
    }

    private static long getUsedMemory() {
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }


    public static ArrayList<Integer> loadDataset(String filename) {
        ArrayList<Integer> dataset = new ArrayList<>();
    
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int value = Integer.parseInt(line);
                dataset.add(value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        return dataset;
    }
}



