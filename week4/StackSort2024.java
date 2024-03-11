package week4;
// todo: student 1 id & name, student 2 id & name
// M124020020 李培聖 & M124020023 黃冠綸
// todo: complete the method sort in Selection2024, see details in slide for how to sort 
// DO NOT EDIT other functions NOR add global variables.

// import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackSort2024 {
	
	// private LinkedStack<Swap> swaps;
	
	// public class Swap{
	// 	private int index1 = -1;
	// 	private int index2 = -1;
	// 	private Swap(int index1, int index2) {
	// 		this.index1 = index1;
	// 		this.index2 = index2;
	// 	}
	// }
	
	// public StackSort2024() {
	// 	swaps = new LinkedStack<Swap>();
	// }
	
	// Selection2024 is modified from Selection from https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/Selection.java.html
	// JavaDoc of Selection can be found at https://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/Selection.html
	public static class Selection2024 {
	
	    // This class should not be instantiated.
	    private Selection2024() {}
	    
	    // todo: complete this method
		@SuppressWarnings({"rawtypes"})
	    public static void sort(Comparable[] a, LinkedStack<Boolean> s) {

			int N = a.length; 
			for (int i = 0; i < N; i++) 
			{ 
				boolean bool = s.pop(); // Gets the boolean value from the linked stack
				int min = i; // Temp value
				for (int j = i+1; j < N; j++) 
				if(bool){
					// If the boolean is true, which indicates getting max value, will set to temp to j from inner loop
					if (!less(a[j], a[min])) {
						min = j;
					} 
				}
				else{
					// If the boolean is false, which indicates getting min value, will set to temp to j from inner loop
					if (less(a[j], a[min])) {
						min = j;
					} 
				}

				exch(a, i, min); // swap two variables
			} 
	    }

	   /***************************************************************************
	    *  Helper sorting functions.
	    ***************************************************************************/

	    // is v < w ?
		@SuppressWarnings({ "rawtypes", "unchecked" })
	    private static boolean less(Comparable v, Comparable w) {
	        return v.compareTo(w) < 0;
	    }

	    // exchange a[i] and a[j]
	    private static void exch(Object[] a, int i, int j) {
	        Object swap = a[i];
	        a[i] = a[j];
	        a[j] = swap;
	    }

	}
	
	// LinkedStack is modified from https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/LinkedStack.java.html
	// JavaDoc of LinkedStack can be found at https://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/LinkedStack.html
	public static class LinkedStack<Item> implements Iterable<Item> {
	    private int n;          // size of the stack
	    private Node first;     // top of stack

	    // helper linked list class
	    private class Node {
	        private Item item;
	        private Node next;
	    }

	    /**
	     * Initializes an empty stack.
	     */
	    public LinkedStack() {
	        first = null;
	        n = 0;
	        assert check();
	    }

	    /**
	     * Is this stack empty?
	     * @return true if this stack is empty; false otherwise
	     */
	    public boolean isEmpty() {
	        return first == null;
	    }

	    /**
	     * Returns the number of items in the stack.
	     * @return the number of items in the stack
	     */
	    public int size() {
	        return n;
	    }

	    /**
	     * Adds the item to this stack.
	     * @param item the item to add
	     */
	    public void push(Item item) {
	        Node oldfirst = first;
	        first = new Node();
	        first.item = item;
	        first.next = oldfirst;
	        n++;
	        assert check();
	    }

	    /**
	     * Removes and returns the item most recently added to this stack.
	     * @return the item most recently added
	     * @throws java.util.NoSuchElementException if this stack is empty
	     */
	    public Item pop() {
	        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
	        Item item = first.item;        // save item to return
	        first = first.next;            // delete first node
	        n--;
	        assert check();
	        return item;                   // return the saved item
	    }


	    /**
	     * Returns (but does not remove) the item most recently added to this stack.
	     * @return the item most recently added to this stack
	     * @throws java.util.NoSuchElementException if this stack is empty
	     */
	    public Item peek() {
	        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
	        return first.item;
	    }

	    /**
	     * Returns a string representation of this stack.
	     * @return the sequence of items in the stack in LIFO order, separated by spaces
	     */
	    public String toString() {
	        StringBuilder s = new StringBuilder();
	        for (Item item : this)
	            s.append(item + " ");
	        return s.toString();
	    }

	    /**
	     * Returns an iterator to this stack that iterates through the items in LIFO order.
	     * @return an iterator to this stack that iterates through the items in LIFO order.
	     */
	    public Iterator<Item> iterator() {
	        return new LinkedIterator();
	    }

	    // a linked-list iterator
	    private class LinkedIterator implements Iterator<Item> {
	        private Node current = first;

	        public boolean hasNext() {
	            return current != null;
	        }

	        public Item next() {
	            if (!hasNext()) throw new NoSuchElementException();
	            Item item = current.item;
	            current = current.next;
	            return item;
	        }
	    }


	    // check internal invariants
	    private boolean check() {

	        // check a few properties of instance variable 'first'
	        if (n < 0) {
	            return false;
	        }
	        if (n == 0) {
	            if (first != null) return false;
	        }
	        else if (n == 1) {
	            if (first == null)      return false;
	            if (first.next != null) return false;
	        }
	        else {
	            if (first == null)      return false;
	            if (first.next == null) return false;
	        }

	        // check internal consistency of instance variable n
	        int numberOfNodes = 0;
	        for (Node x = first; x != null && numberOfNodes <= n; x = x.next) {
	            numberOfNodes++;
	        }
	        if (numberOfNodes != n) return false;

	        return true;
	    }
	}
	@SuppressWarnings({"rawtypes"})
	public static void printOrder(Comparable[] a) {
		for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
	}

    public static void main(String[] args) {

    	LinkedStack<Boolean> maxOrder = new LinkedStack<Boolean>();
    	
        maxOrder.push(true);
    	maxOrder.push(true);
    	maxOrder.push(true);
    	maxOrder.push(true);
    	maxOrder.push(true);
    	
    	Integer[] a1 = {1,2,3,4,5};
    	Selection2024.sort(a1,maxOrder);
    	StackSort2024.printOrder(a1); // 5 4 3 2 1
    	System.out.println();
    	
    	maxOrder.push(true);
    	maxOrder.push(true);
    	maxOrder.push(true);
    	maxOrder.push(false);
    	maxOrder.push(false);
    	
    	Selection2024.sort(a1,maxOrder);
    	StackSort2024.printOrder(a1); // 1 2 5 4 3
    	System.out.println();
    	
    	maxOrder.push(false);
    	maxOrder.push(false);
    	maxOrder.push(true);
    	maxOrder.push(true);
    	maxOrder.push(true);
    	maxOrder.push(false);
    	maxOrder.push(false);
    	maxOrder.push(true);
    	maxOrder.push(false);
    	
    	Integer[] a2 = {10,3,5,11,7,9,2,12,1};
    	
        Selection2024.sort(a2,maxOrder);
        StackSort2024.printOrder(a2); // 1 12 2 3 11 10 9 5 7 
        System.out.println();
        
        maxOrder.push(true);
    	maxOrder.push(true);
    	maxOrder.push(true);
    	maxOrder.push(false);
    	maxOrder.push(true);
    	maxOrder.push(false);
    	maxOrder.push(false);
    	maxOrder.push(true);
    	maxOrder.push(true);
    	
    	Selection2024.sort(a2,maxOrder);
        StackSort2024.printOrder(a2); // 12 11 1 2 10 3 9 7 5
        System.out.println();
        
    }
}
