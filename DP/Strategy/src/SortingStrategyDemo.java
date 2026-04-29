import java.util.Random;

interface SortStrategy {
    void sort(int[] array);
}

class SortContext {
    private SortStrategy strategy;

    public void setStrategy(SortStrategy strategy) {
        this.strategy = strategy;
    }

    public void executeSort(int[] array) {
        strategy.sort(array);
    }
}

class BubbleSort implements SortStrategy {
    public void sort(int[] array) {
        int n = array.length;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }

            if (!swapped) break;
        }
    }
}

class MergeSort implements SortStrategy {
    public void sort(int[] array) {
        mergeSort(array, 0, array.length - 1);
    }

    private void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];

        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) temp[k++] = arr[i++];
            else temp[k++] = arr[j++];
        }

        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];

        for (i = 0; i < temp.length; i++) {
            arr[left + i] = temp[i];
        }
    }
}

class QuickSort implements SortStrategy {
    public void sort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }
}

public class SortingStrategyDemo {

    public static void main(String[] args) {

        SortContext context = new SortContext();

        int[] small = generateArray(30);
        int[] large = generateArray(100_000);

        System.out.println("SMALL DATASET (30)");

        test(context, new BubbleSort(), copy(small), "Bubble Sort");
        test(context, new MergeSort(), copy(small), "Merge Sort");
        test(context, new QuickSort(), copy(small), "Quick Sort");

        System.out.println("\nLARGE DATASET (100,000)");

        test(context, new BubbleSort(), copy(large), "Bubble Sort");
        test(context, new MergeSort(), copy(large), "Merge Sort");
        test(context, new QuickSort(), copy(large), "Quick Sort");
    }

    static void test(SortContext context, SortStrategy strategy, int[] data, String name) {
        context.setStrategy(strategy);

        long start = System.nanoTime();
        context.executeSort(data);
        long end = System.nanoTime();

        System.out.println(name + ": " + (end - start) / 1_000_000.0 + " ms");
    }

    static int[] generateArray(int size) {
        Random r = new Random();
        int[] arr = new int[size];

        for (int i = 0; i < size; i++) {
            arr[i] = r.nextInt(1_000_000);
        }

        return arr;
    }

    static int[] copy(int[] arr) {
        int[] copy = new int[arr.length];
        System.arraycopy(arr, 0, copy, 0, arr.length);
        return copy;
    }
}