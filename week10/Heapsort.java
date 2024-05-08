package week10;

public class Heapsort {

    private static class Heap {

        private Heap() { }

        public static void sort(Comparable[] pq) {
            int n = pq.length;

            // Build a max heap
            for (int k = n / 2; k >= 1; k--) {
                sink(pq, k, n);
            }

            // Sortdown phase
            while (n > 1) {
                exch(pq, 1, n);
                // n = remove(pq, n, 6); // example of attempting to remove the number 6 during sortdown
                sink(pq, 1, n);
            }
        }

        // Example removal method: removes an item by setting it to null
        private static int remove(Comparable[] pq, int n, int itemToRemove) {
            for (int i = 1; i <= n; i++) {
                if (pq[i - 1] != null && pq[i - 1].equals(itemToRemove)) {
                    exch(pq, i, n); // Swap with the last element
                    pq[n - 1] = null; // Nullify the last element
                    return n - 1; // Reduce the size of the heap by one
                }
            }
            return n; // No item was removed
        }

        private static void sink(Comparable[] pq, int k, int n) {
            while (2 * k <= n) {
                int j = 2 * k;
                if (j < n && less(pq, j, j + 1)) j++;
                if (!less(pq, k, j)) break;
                exch(pq, k, j);
                k = j;
            }
        }

        private static boolean less(Comparable[] pq, int i, int j) {
            return pq[i - 1].compareTo(pq[j - 1]) < 0;
        }

        private static void exch(Object[] pq, int i, int j) {
            Object temp = pq[i - 1];
            pq[i - 1] = pq[j - 1];
            pq[j - 1] = temp;
        }

        public static void show(Comparable[] a) {
            for (Comparable item : a) {
                if (item != null) System.out.print(item + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Integer[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.print("Before Sorting: ");
        Heap.show(a);
        Heap.sort(a);
        System.out.print("After Sorting: ");
        Heap.show(a);
    }
}

