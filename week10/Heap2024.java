package week10;

// todo: student 1 id & name, student 2 id & name
// todo: complete the remove function where a key can be provided and deleted from the heap. 
// The reconstructed heap without the removed item will be returned with the smaller size without the removed item.   
// DO NOT EDIT other functions NOR add global variables.

public class Heap2024 {

	//Heap is modified from Heap at https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/Heap.java.html
	//JavaDoc https://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/Heap.html
	public class Heap {

	    // This class should not be instantiated.
	    private Heap() { }

	    /**
	     * Creates the heap in the array
	     * @param pq the array to be sorted
	     */
	    
	    public static void heapify(Comparable[] pq) {
	    	int n = pq.length;
	    	
	    	for (int k = n/2; k >= 1; k--) {
	    		sink(pq, k, n);
	    	}
	    }
	    
	    // todo: complete this method and return the restructured heap without the removed item 
	    public static Comparable[] remove(Comparable[] pq, int key) {
	    	int n = pq.length;	    	
	    	Integer[] pq2 = new Integer[n-1];
			// boolean bool = false;
			// for (int i = 1; i <= n; i++) {
			// 	if (key == (int)pq[i]){
			// 		bool = true;
			// 	}
			// }
			// if (bool == false){
			// 	return pq;
			// }

			// for (int i = 1; i <= n; i++) {

			// }
			
			// for (Comparable element: pq) {
			// 	System.out.println(element);
			// }

			// for (int element: pq2) {
			// 	System.out.println(element);
			// }
            for (int i = 1; i <= n; i++) {
                if (pq[i - 1] != null && pq[i - 1].equals(key)) {
                    exch(pq, i, n); // Swap with the last element
                    // pq[n - 1] = null; // Nullify the last element
					break;
                }
            }

			// for (int i = 1; i <= n; i++) {
			// 	pq2[i] = (Integer)pq[i];
            // }

			// heapify(pq2);


			// n--;
			// heapify(pq);
	    	return pq;
	    }

	   /***************************************************************************
	    * Helper functions to restore the heap invariant.
	    ***************************************************************************/

	    private static void sink(Comparable[] pq, int k, int n) {
	        while (2*k <= n) {
	            int j = 2*k;
	            if (j < n && less(pq, j, j+1)) j++;
	            if (!less(pq, k, j)) break;
	            exch(pq, k, j);
	            k = j;
	        }
	    }

	   /***************************************************************************
	    * Helper functions for comparisons and swaps.
	    * Indices are "off-by-one" to support 1-based indexing.
	    ***************************************************************************/
	    private static boolean less(Comparable[] pq, int i, int j) {
	        return pq[i-1].compareTo(pq[j-1]) < 0;
	    }

	    private static void exch(Object[] pq, int i, int j) {
	        Object swap = pq[i-1];
	        pq[i-1] = pq[j-1];
	        pq[j-1] = swap;
	    }

	    // print array to standard output
	    private static void show(Comparable[] a) {
	        for (int i = 0; i < a.length; i++) {
	        	if(a[i] == null) return;
	        	if(i < a.length-1) {
	        		System.out.print(a[i]+", ");	        		
	        	}else {
	        		System.out.print(a[i]);
	        	}
	        }
	    }
	}
	
	public static void main(String[] args) {
		Integer[] a = {15,5,3,2,7,9,10,19,12,16,20};
    	Heap.heapify(a);
    	System.out.print("Heap constructed: ");
    	Heap.show(a);
    	System.out.println();
    	
    	a = (Integer[]) Heap.remove(a,1);
    	System.out.print("Heap reconstructed after removing 1: ");
    	Heap.show(a);
    	System.out.println();
    	
    	a = (Integer[]) Heap.remove(a,16);
    	System.out.print("Heap reconstructed after removing 16: ");
    	Heap.show(a);
    	System.out.println();

    	a = (Integer[]) Heap.remove(a,10);
    	System.out.print("Heap reconstructed after removing 10: ");
    	Heap.show(a);
    	System.out.println();
    	
    	a = (Integer[]) Heap.remove(a,19);
    	System.out.print("Heap reconstructed after removing 19: ");
    	Heap.show(a);
    	
	}

}
