import java.util.Random;

public class QuickSort {
    private static Random random = new Random();

    public static void sort(int[] arr) {
        shuffle(arr); // Randomly shuffle the array
        quicksort(arr, 0, arr.length - 1);
    }

    private static void quicksort(int[] arr, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(arr, lo, hi);
        quicksort(arr, lo, j - 1);
        quicksort(arr, j + 1, hi);
    }

    private static int partition(int[] arr, int lo, int hi) {
        int i = lo, j = hi + 1;
        int pivot = arr[lo];
        while (true) {
            while (arr[++i] < pivot) if (i == hi) break;
            while (pivot < arr[--j]) if (j == lo) break;
            if (i >= j) break;
            swap(arr, i, j);
        }
        swap(arr, lo, j);
        return j;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void shuffle(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            swap(arr, i, index);
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 2, 7, 3, 9, 1, 6, 4, 8};
        System.out.println("Before sorting:");
        printArray(arr);
        sort(arr);
        System.out.println("After sorting:");
        printArray(arr);
    }

    private static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
