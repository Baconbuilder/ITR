package week12;

// import java.util.ArrayList;
// import java.util.List;

// public class BalancedBSTInsertion {

//     public static List<Integer> findKeyInsertionSequence(Character[] keys) {
//         List<Integer> indices = new ArrayList<>();
//         int n = keys.length;
//         // Create an array of indices
//         Integer[] indexArray = new Integer[n];
//         for (int i = 0; i < n; i++) {
//             indexArray[i] = i;
//         }

//         // Sort indices based on the values at those indices
//         java.util.Arrays.sort(indexArray, (a, b) -> keys[a].compareTo(keys[b]));

//         // Generate the balanced insertion sequence
//         List<Integer> result = new ArrayList<>();
//         generateBalancedSequence(indexArray, 0, n - 1, result);
//         return result;
//     }

//     private static void generateBalancedSequence(Integer[] indexArray, int start, int end, List<Integer> result) {
//         if (start > end) {
//             return;
//         }
//         int mid = start + (end - start) / 2;
//         result.add(indexArray[mid]);
//         generateBalancedSequence(indexArray, start, mid - 1, result);
//         generateBalancedSequence(indexArray, mid + 1, end, result);
//     }

//     public static void main(String[] args) {
//         // Test cases
//         Character[] keys1 = {'J', 'K', 'Z'};
//         Character[] keys2 = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
//         Character[] keys3 = {'A', 'C', 'D', 'F', 'J', 'K', 'L', 'M', 'N', 'O'};
//         Character[] keys4 = {'A', 'B', 'C', 'D', 'E', 'F', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'V', 'W', 'X', 'Y'};

//         System.out.println("BST New Insert Order: " + findKeyInsertionSequence(keys1));
//         System.out.println("BST New Insert Order: " + findKeyInsertionSequence(keys2));
//         System.out.println("BST New Insert Order: " + findKeyInsertionSequence(keys3));
//         System.out.println("BST New Insert Order: " + findKeyInsertionSequence(keys4));
//     }
// }

// import java.util.ArrayList;
// import java.util.List;

// public class BalancedBSTInsertion<T extends Comparable<T>> {

//     public static void main(String[] args) {
//         // Test cases using Character array
//         Character[] keys1 = {'J', 'K', 'Z'};
//         Character[] keys2 = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
//         Character[] keys3 = {'A', 'C', 'D', 'F', 'J', 'K', 'L', 'M', 'N', 'O'};
//         Character[] keys4 = {'A', 'B', 'C', 'D', 'E', 'F', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'V', 'W', 'X', 'Y'};

//         System.out.println("BST New Insert Order: " + findKeyInsertionSequence(keys1));
//         System.out.println("BST New Insert Order: " + findKeyInsertionSequence(keys2));
//         System.out.println("BST New Insert Order: " + findKeyInsertionSequence(keys3));
//         System.out.println("BST New Insert Order: " + findKeyInsertionSequence(keys4));

//         // // Example using Integer array
//         // Integer[] numbers = {10, 6, 15, 3, 8, 20};
//         // System.out.println("BST New Insert Order: " + findKeyInsertionSequence(numbers));
//     }

//     public static <T extends Comparable<T>> List<Integer> findKeyInsertionSequence(T[] keys) {
//         List<Integer> indices = new ArrayList<>();
//         int n = keys.length;
//         // Create an array of indices
//         Integer[] indexArray = new Integer[n];
//         for (int i = 0; i < n; i++) {
//             indexArray[i] = i;
//         }

//         // Sort indices based on the values at those indices using their natural ordering
//         java.util.Arrays.sort(indexArray, (a, b) -> keys[a].compareTo(keys[b]));

//         // Generate the balanced insertion sequence
//         List<Integer> result = new ArrayList<>();
//         generateBalancedSequence(indexArray, 0, n - 1, result);
//         return result;
//     }

//     private static void generateBalancedSequence(Integer[] indexArray, int start, int end, List<Integer> result) {
//         if (start > end) {
//             return;
//         }
//         int mid = start + (end - start) / 2;
//         result.add(indexArray[mid]);
//         generateBalancedSequence(indexArray, start, mid - 1, result);
//         generateBalancedSequence(indexArray, mid + 1, end, result);
//     }
// }

// import java.util.ArrayList;
// import java.util.List;

// public class BalancedBSTInsertion {

//     public static void main(String[] args) {
//         // Test cases
//         Character[] keys1 = {'J', 'K', 'Z'};
//         Character[] keys2 = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
//         Character[] keys3 = {'A', 'C', 'D', 'F', 'J', 'K', 'L', 'M', 'N', 'O'};
//         Character[] keys4 = {'A', 'B', 'C', 'D', 'E', 'F', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'V', 'W', 'X', 'Y'};

//         handleKeys(keys1);
//         handleKeys(keys2);
//         handleKeys(keys3);
//         handleKeys(keys4);
//     }

//     private static <T extends Comparable<T>> void handleKeys(T[] keys) {
//         int originalHeight = calculateHeightForSequentialInsertion(keys.length);
//         List<Integer> newOrder = findKeyInsertionSequence(keys);
//         List<T> sortedKeys = getSortedKeys(keys, newOrder);
//         int minimalHeight = calculateMinimalHeight(keys.length);

//         System.out.println("Original height: " + originalHeight);
//         System.out.print("BST New Insert Order: ");
//         newOrder.forEach(index -> System.out.print(index + " "));
//         System.out.println();
//         System.out.println("Keys in Tree (inorder): " + sortedKeys);
//         System.out.println("Minimal height: " + minimalHeight);
//         System.out.println();
//     }

//     public static <T extends Comparable<T>> List<Integer> findKeyInsertionSequence(T[] keys) {
//         Integer[] indexArray = new Integer[keys.length];
//         for (int i = 0; i < keys.length; i++) {
//             indexArray[i] = i;
//         }
//         java.util.Arrays.sort(indexArray, (a, b) -> keys[a].compareTo(keys[b]));

//         List<Integer> result = new ArrayList<>();
//         generateBalancedSequence(indexArray, 0, keys.length - 1, result);
//         return result;
//     }

//     private static void generateBalancedSequence(Integer[] indexArray, int start, int end, List<Integer> result) {
//         if (start > end) return;
//         int mid = start + (end - start) / 2;
//         result.add(indexArray[mid]);
//         generateBalancedSequence(indexArray, start, mid - 1, result);
//         generateBalancedSequence(indexArray, mid + 1, end, result);
//     }

//     private static <T> List<T> getSortedKeys(T[] keys, List<Integer> order) {
//         List<T> sortedKeys = new ArrayList<>();
//         for (int index : order) {
//             sortedKeys.add(keys[index]);
//         }
//         return sortedKeys;
//     }

//     private static int calculateHeightForSequentialInsertion(int n) {
//         return n - 1;  // Worst case, linear insertion forms a skew tree
//     }

//     private static int calculateMinimalHeight(int n) {
//         return (int) (Math.log(n) / Math.log(2));  // Log base 2 of n gives the height of a balanced binary tree
//     }
// }

import java.util.ArrayList;
import java.util.List;

public class BalancedBSTInsertion {

    public static void main(String[] args) {
        // Test cases
        Character[] keys1 = {'J', 'K', 'Z'};
        Character[] keys2 = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        Character[] keys3 = {'A', 'C', 'D', 'F', 'J', 'K', 'L', 'M', 'N', 'O'};
        Character[] keys4 = {'A', 'B', 'C', 'D', 'E', 'F', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'V', 'W', 'X', 'Y'};

        handleKeys(keys1);
        handleKeys(keys2);
        handleKeys(keys3);
        handleKeys(keys4);
    }

    private static <T extends Comparable<T>> void handleKeys(T[] keys) {
        int originalHeight = calculateHeightForSequentialInsertion(keys.length);
        List<Integer> newOrder = findKeyInsertionSequence(keys);
        List<T> sortedKeys = getSortedKeys(keys, newOrder);
        int minimalHeight = calculateMinimalHeight(keys.length);

        System.out.println("Original height: " + originalHeight);
        System.out.print("BST New Insert Order: ");
        newOrder.forEach(index -> System.out.print(index + " "));
        System.out.println();
        System.out.println("Keys in Tree (inorder): " + sortedKeys);
        System.out.println("Minimal height: " + minimalHeight);
        System.out.println();
    }

    public static <T extends Comparable<T>> List<Integer> findKeyInsertionSequence(T[] keys) {
        Integer[] indexArray = new Integer[keys.length];
        for (int i = 0; i < keys.length; i++) {
            indexArray[i] = i;
        }
        // Manually sort the indices based on the values at those indices using insertion sort
        for (int i = 1; i < indexArray.length; i++) {
            int key = indexArray[i];
            int j = i - 1;
            while (j >= 0 && keys[indexArray[j]].compareTo(keys[key]) > 0) {
                indexArray[j + 1] = indexArray[j];
                j = j - 1;
            }
            indexArray[j + 1] = key;
        }

        List<Integer> result = new ArrayList<>();
        generateBalancedSequence(indexArray, 0, keys.length - 1, result);
        return result;
    }

    private static void generateBalancedSequence(Integer[] indexArray, int start, int end, List<Integer> result) {
        if (start > end) return;
        int mid = start + (end - start) / 2;
        result.add(indexArray[mid]);
        generateBalancedSequence(indexArray, start, mid - 1, result);
        generateBalancedSequence(indexArray, mid + 1, end, result);
    }

    private static <T> List<T> getSortedKeys(T[] keys, List<Integer> order) {
        List<T> sortedKeys = new ArrayList<>();
        for (int index : order) {
            sortedKeys.add(keys[index]);
        }
        return sortedKeys;
    }

    private static int calculateHeightForSequentialInsertion(int n) {
        return n - 1;  // Worst case, linear insertion forms a skew tree
    }

    private static int calculateMinimalHeight(int n) {
        return (int) (Math.log(n) / Math.log(2));  // Log base 2 of n gives the height of a balanced binary tree
    }
}
