import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BCIS implements SortingAlgorithm{
    
    @Override
    public void sort(ArrayList<Integer> array) {
        int leftIndex = 0;
        int rightIndex = array.size() - 1;
        int startLeft = leftIndex;
        int startRight = rightIndex;

        while (startLeft < startRight) {
            swapElements(array, startRight, startLeft + (startRight - startLeft) / 2);

            if (array.get(startLeft).equals(array.get(startRight)) && isEqual(array, startLeft, startRight) == -1) {
                return;
            }

            if (array.get(startLeft) > array.get(startRight)) {
                swapElements(array, startLeft, startRight);
            }

            int i = startLeft + 1;
            if (startRight - startLeft >= 100) {
                for (; i <= Math.sqrt(1.0 * startRight - startLeft); i++) {
                    if (array.get(startRight) < array.get(i)) {
                        swapElements(array, startRight, i);
                    } else if (array.get(startLeft) > array.get(i)) {
                        swapElements(array, startLeft, i);
                    }
                }
            }

            int leftCompare = array.get(startLeft);
            int rightCompare = array.get(startRight);

            while (i < startRight) {
                int current = array.get(i);
                if (current >= rightCompare) {
                    array.set(i, array.get(startRight - 1));
                    insertRight(array, current, startRight, rightIndex);
                    startRight--;
                } else if (current <= leftCompare) {
                    array.set(i, array.get(startLeft + 1));
                    insertLeft(array, current, startLeft, leftIndex);
                    startLeft++;
                    i++;
                } else {
                    i++;
                }
            }
            startLeft++;
            startRight--;
        }
    }

    private int isEqual(List<Integer> array, int startLeft, int startRight) {
        for (int k = startLeft + 1; k < startRight; k++) {
            if (!array.get(k).equals(array.get(startLeft))) {
                swapElements(array, k, startLeft);
                return k;
            }
        }
        return -1;
    }

    private void insertRight(List<Integer> array, int current, int startRight, int right) {
        int j = startRight;
        while (j <= right && current > array.get(j)) {
            array.set(j - 1, array.get(j));
            j++;
        }
        array.set(j - 1, current);
    }

    private void insertLeft(List<Integer> array, int current, int startLeft, int left) {
        int j = startLeft;
        while (j >= left && current < array.get(j)) {
            array.set(j + 1, array.get(j));
            j--;
        }
        array.set(j + 1, current);
    }

    private void swapElements(List<Integer> array, int i, int j) {
        Collections.swap(array, i, j);
    }

    @Override
    public String toString() {
      return "BCIS Sort";
    }
}
