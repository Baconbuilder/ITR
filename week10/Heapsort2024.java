package week10;

public class Heapsort2024 {

    public static class Heap {

        private Comparable[] heap; // This will store the heap elements
        private int size;          // This will keep track of the size of the heap

        public Heap(int capacity) {
            heap = new Comparable[capacity];
            size = 0;
        }

        public void insert(Comparable item) {
            if (size == heap.length) {
                throw new IllegalStateException("Heap is full");
            }
            heap[size] = item;
            size++;
            swim(size);
        }

        public Comparable remove(Comparable item) {
            int index = -1;
            for (int i = 1; i <= size; i++) {
                if (heap[i - 1].equals(item)) {
                    index = i - 1;
                    break;
                }
            }

            if (index == -1) return null; // item not found

            Comparable removedItem = heap[index];
            exch(heap, index + 1, size); // swap with the last item
            size--;
            sink(index + 1);
            heap[size] = null; // prevent loitering
            return removedItem;
        }

        private void sink(int k) {
            while (2 * k <= size) {
                int j = 2 * k;
                if (j < size && less(heap, j, j + 1)) j++;
                if (!less(heap, k, j)) break;
                exch(heap, k, j);
                k = j;
            }
        }

        private void swim(int k) {
            while (k > 1 && less(heap, k / 2, k)) {
                exch(heap, k, k / 2);
                k = k / 2;
            }
        }

        private static boolean less(Comparable[] pq, int i, int j) {
            return pq[i - 1].compareTo(pq[j - 1]) < 0;
        }

        private static void exch(Object[] pq, int i, int j) {
            Object swap = pq[i - 1];
            pq[i - 1] = pq[j - 1];
            pq[j - 1] = swap;
        }

        // Method to print the heap
        public void showHeap() {
            for (int i = 0; i < size; i++) {
                System.out.print(heap[i] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Heap heap = new Heap(10);

        // Insert elements into the heap
        heap.insert(5);
        heap.insert(3);
        heap.insert(17);
        heap.insert(10);
        heap.insert(84);
        heap.insert(19);
        heap.insert(6);
        heap.insert(22);
        heap.insert(9);

        System.out.print("Heap before removal: ");
        heap.showHeap();

        // Removing specific elements
        Comparable removed = heap.remove(17); // Remove element 17
        // if (removed != null) {
        //     System.out.println("Removed element: " + removed);
        // }

        // System.out.print("Heap after removal of 17: ");
        heap.showHeap();

        // Remove another element
        removed = heap.remove(5);
        // if (removed != null) {
        //     System.out.println("Removed element: " + removed);
        // } else {
        //     System.out.println("Element not found or not removed.");
        // }

        // System.out.print("Heap after removal of 5: ");
        heap.showHeap();
    }
}
