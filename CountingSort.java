import java.util.ArrayList;

public class CountingSort implements SortingAlgorithm{
    
    @Override
    public void sort(ArrayList<Integer> array) {
        int n = array.size();
        
        // Find the maximum value in the array
        int max = array.get(0);
        for (int i = 1; i < n; i++) {
            if (array.get(i) > max) {
                max = array.get(i);
            }
        }
    
        int[] output = new int[n];
        int[] counter = new int[max + 1];
    
        for (int i = 0; i <= max; i++) {
            counter[i] = 0;
        }
    
        // Count the occurrences of each element in the input array
        for (int i = 0; i < n; i++) {
            counter[array.get(i)]++;
        }
    
        // Calculate the cumulative count of elements
        for (int i = 1; i <= max; i++) {
            counter[i] += counter[i - 1];
        }
    
        // Build the output array based on the cumulative count
        for (int i = n - 1; i >= 0; i--) {
            output[counter[array.get(i)] - 1] = array.get(i);
            counter[array.get(i)]--;
        }
    
        // Copy the sorted elements back to the input array
        for (int i = 0; i < n; i++) {
            array.set(i, output[i]);
        }
    }
    
    @Override
    public String toString() {
      return "Counting Sort";
    }
}
