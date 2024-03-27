public class MergeSort {
    public void sort(int[] elements) {
      int length = elements.length;
      int[] sorted = mergeSort(elements, 0, length - 1);
      System.arraycopy(sorted, 0, elements, 0, length);
    }

  
    private int[] mergeSort(int[] elements, int left, int right) {
      // End of recursion reached?
      if (left == right) return new int[]{elements[left]};
  
      int middle = left + (right - left) / 2;
      int[] leftArray = mergeSort(elements, left, middle);
      int[] rightArray = mergeSort(elements, middle + 1, right);
      return merge(leftArray, rightArray);
    }
  
    int[] merge(int[] leftArray, int[] rightArray) {
      int leftLen = leftArray.length;
      int rightLen = rightArray.length;
  
      int[] target = new int[leftLen + rightLen];
      int targetPos = 0;
      int leftPos = 0;
      int rightPos = 0;
  
      // As long as both arrays contain elements...
      while (leftPos < leftLen && rightPos < rightLen) {
        // Which one is smaller?
        int leftValue = leftArray[leftPos];
        int rightValue = rightArray[rightPos];
        if (leftValue <= rightValue) {
          target[targetPos++] = leftValue;
          leftPos++;
        } else {
          target[targetPos++] = rightValue;
          rightPos++;
        }
      }
      // Copy the rest
      while (leftPos < leftLen) {
        target[targetPos++] = leftArray[leftPos++];
      }
      while (rightPos < rightLen) {
        target[targetPos++] = rightArray[rightPos++];
      }
      return target;
    }

    private static void printArray(int[] array) {
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();

        // Example 1: Sorting an array of integers
        int[] array1 = {5, 2, 9, 1, 5, 6};
        System.out.println("Original array:");
        printArray(array1);
        mergeSort.sort(array1);
        System.out.println("Sorted array:");
        printArray(array1);
    }
}