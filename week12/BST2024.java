package week12;

//todo: student 1 id & name, student 2 id & name
//todo: Write an algorithm in the findKeyInsertionSequence function that returns an insertion sequence of indices 
//that will minimize the height of the tree, which is floor(lg(n)) with n being the number of nodes. 
//DO NOT EDIT other functions NOR add global variables.

//BST1 is modified from https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/BST.java.html
//JavaDoc https://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/BST.html

import java.util.Arrays;
import java.util.NoSuchElementException;

public class BST2024<Key extends Comparable<Key>, Value> {
 private Node root;             // root of BST

 private class Node {
     private Key key;           // sorted by key
     private Value val;         // associated data
     private Node left, right;  // left and right subtrees
     private int size;          // number of nodes in subtree

     public Node(Key key, Value val, int size) {
         this.key = key;
         this.val = val;
         this.size = size;
     }
 }

 /**
  * Initializes an empty symbol table.
  */
 public BST2024() {
 }

 /**
  * Returns true if this symbol table is empty.
  * @return {@code true} if this symbol table is empty; {@code false} otherwise
  */
 public boolean isEmpty() {
     return size() == 0;
 }

 /**
  * Returns the number of key-value pairs in this symbol table.
  * @return the number of key-value pairs in this symbol table
  */
 public int size() {
     return size(root);
 }

 // return number of key-value pairs in BST rooted at x
 private int size(Node x) {
     if (x == null) return 0;
     else return x.size;
 }

 /**
  * Does this symbol table contain the given key?
  *
  * @param  key the key
  * @return {@code true} if this symbol table contains {@code key} and
  *         {@code false} otherwise
  * @throws IllegalArgumentException if {@code key} is {@code null}
  */
 public boolean contains(Key key) {
     if (key == null) throw new IllegalArgumentException("argument to contains() is null");
     return get(key) != null;
 }

 /**
  * Returns the value associated with the given key.
  *
  * @param  key the key
  * @return the value associated with the given key if the key is in the symbol table
  *         and {@code null} if the key is not in the symbol table
  * @throws IllegalArgumentException if {@code key} is {@code null}
  */
 public Value get(Key key) {
     return get(root, key);
 }

 private Value get(Node x, Key key) {
     if (key == null) throw new IllegalArgumentException("calls get() with a null key");
     if (x == null) return null;
     int cmp = key.compareTo(x.key);
     if      (cmp < 0) return get(x.left, key);
     else if (cmp > 0) return get(x.right, key);
     else              return x.val;
 }

 /**
  * Inserts the specified key-value pair into the symbol table, overwriting the old 
  * value with the new value if the symbol table already contains the specified key.
  * Deletes the specified key (and its associated value) from this symbol table
  * if the specified value is {@code null}.
  *
  * @param  key the key
  * @param  val the value
  * @throws IllegalArgumentException if {@code key} is {@code null}
  */
 public void put(Key key, Value val) {
     if (key == null) throw new IllegalArgumentException("calls put() with a null key");
     if (val == null) {
         delete(key);
         return;
     }
     root = put(root, key, val);
     assert check();
 }

 private Node put(Node x, Key key, Value val) {
     if (x == null) return new Node(key, val, 1);
     int cmp = key.compareTo(x.key);
     if      (cmp < 0) x.left  = put(x.left,  key, val);
     else if (cmp > 0) x.right = put(x.right, key, val);
     else              x.val   = val;
     x.size = 1 + size(x.left) + size(x.right);
     return x;
 }


 /**
  * Removes the smallest key and associated value from the symbol table.
  *
  * @throws NoSuchElementException if the symbol table is empty
  */
 public void deleteMin() {
     if (isEmpty()) throw new NoSuchElementException("Symbol table underflow");
     root = deleteMin(root);
     assert check();
 }

 private Node deleteMin(Node x) {
     if (x.left == null) return x.right;
     x.left = deleteMin(x.left);
     x.size = size(x.left) + size(x.right) + 1;
     return x;
 }

 /**
  * Removes the largest key and associated value from the symbol table.
  *
  * @throws NoSuchElementException if the symbol table is empty
  */
 public void deleteMax() {
     if (isEmpty()) throw new NoSuchElementException("Symbol table underflow");
     root = deleteMax(root);
     assert check();
 }

 private Node deleteMax(Node x) {
     if (x.right == null) return x.left;
     x.right = deleteMax(x.right);
     x.size = size(x.left) + size(x.right) + 1;
     return x;
 }

 /**
  * Removes the specified key and its associated value from this symbol table     
  * (if the key is in this symbol table).    
  *
  * @param  key the key
  * @throws IllegalArgumentException if {@code key} is {@code null}
  */
 public void delete(Key key) {
     if (key == null) throw new IllegalArgumentException("calls delete() with a null key");
     root = delete(root, key);
     assert check();
 }

 private Node delete(Node x, Key key) {
     if (x == null) return null;

     int cmp = key.compareTo(x.key);
     if      (cmp < 0) x.left  = delete(x.left,  key);
     else if (cmp > 0) x.right = delete(x.right, key);
     else { 
         if (x.right == null) return x.left;
         if (x.left  == null) return x.right;
         Node t = x;
         x = min(t.right);
         x.right = deleteMin(t.right);
         x.left = t.left;
     } 
     x.size = size(x.left) + size(x.right) + 1;
     return x;
 } 


 /**
  * Returns the smallest key in the symbol table.
  *
  * @return the smallest key in the symbol table
  * @throws NoSuchElementException if the symbol table is empty
  */
 public Key min() {
     if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
     return min(root).key;
 } 

 private Node min(Node x) { 
     if (x.left == null) return x; 
     else                return min(x.left); 
 } 

 /**
  * Returns the largest key in the symbol table.
  *
  * @return the largest key in the symbol table
  * @throws NoSuchElementException if the symbol table is empty
  */
 public Key max() {
     if (isEmpty()) throw new NoSuchElementException("calls max() with empty symbol table");
     return max(root).key;
 } 

 private Node max(Node x) {
     if (x.right == null) return x; 
     else                 return max(x.right); 
 } 

 /**
  * Returns the largest key in the symbol table less than or equal to {@code key}.
  *
  * @param  key the key
  * @return the largest key in the symbol table less than or equal to {@code key}
  * @throws NoSuchElementException if there is no such key
  * @throws IllegalArgumentException if {@code key} is {@code null}
  */
 public Key floor(Key key) {
     if (key == null) throw new IllegalArgumentException("argument to floor() is null");
     if (isEmpty()) throw new NoSuchElementException("calls floor() with empty symbol table");
     Node x = floor(root, key);
     if (x == null) throw new NoSuchElementException("argument to floor() is too small");
     else return x.key;
 } 

 private Node floor(Node x, Key key) {
     if (x == null) return null;
     int cmp = key.compareTo(x.key);
     if (cmp == 0) return x;
     if (cmp <  0) return floor(x.left, key);
     Node t = floor(x.right, key); 
     if (t != null) return t;
     else return x; 
 } 

 public Key floor2(Key key) {
     Key x = floor2(root, key, null);
     if (x == null) throw new NoSuchElementException("argument to floor() is too small");
     else return x;

 }

 private Key floor2(Node x, Key key, Key best) {
     if (x == null) return best;
     int cmp = key.compareTo(x.key);
     if      (cmp  < 0) return floor2(x.left, key, best);
     else if (cmp  > 0) return floor2(x.right, key, x.key);
     else               return x.key;
 } 

 /**
  * Returns the smallest key in the symbol table greater than or equal to {@code key}.
  *
  * @param  key the key
  * @return the smallest key in the symbol table greater than or equal to {@code key}
  * @throws NoSuchElementException if there is no such key
  * @throws IllegalArgumentException if {@code key} is {@code null}
  */
 public Key ceiling(Key key) {
     if (key == null) throw new IllegalArgumentException("argument to ceiling() is null");
     if (isEmpty()) throw new NoSuchElementException("calls ceiling() with empty symbol table");
     Node x = ceiling(root, key);
     if (x == null) throw new NoSuchElementException("argument to floor() is too large");
     else return x.key;
 }

 private Node ceiling(Node x, Key key) {
     if (x == null) return null;
     int cmp = key.compareTo(x.key);
     if (cmp == 0) return x;
     if (cmp < 0) { 
         Node t = ceiling(x.left, key); 
         if (t != null) return t;
         else return x; 
     } 
     return ceiling(x.right, key); 
 } 

 /**
  * Return the key in the symbol table of a given {@code rank}.
  * This key has the property that there are {@code rank} keys in
  * the symbol table that are smaller. In other words, this key is the
  * ({@code rank}+1)st smallest key in the symbol table.
  *
  * @param  rank the order statistic
  * @return the key in the symbol table of given {@code rank}
  * @throws IllegalArgumentException unless {@code rank} is between 0 and
  *        <em>n</em>–1
  */
 public Key select(int rank) {
     if (rank < 0 || rank >= size()) {
         throw new IllegalArgumentException("argument to select() is invalid: " + rank);
     }
     return select(root, rank);
 }

 // Return key in BST rooted at x of given rank.
 // Precondition: rank is in legal range.
 private Key select(Node x, int rank) {
     if (x == null) return null;
     int leftSize = size(x.left);
     if      (leftSize > rank) return select(x.left,  rank);
     else if (leftSize < rank) return select(x.right, rank - leftSize - 1); 
     else                      return x.key;
 }

 /**
  * Return the number of keys in the symbol table strictly less than {@code key}.
  *
  * @param  key the key
  * @return the number of keys in the symbol table strictly less than {@code key}
  * @throws IllegalArgumentException if {@code key} is {@code null}
  */
 public int rank(Key key) {
     if (key == null) throw new IllegalArgumentException("argument to rank() is null");
     return rank(key, root);
 } 

 // Number of keys in the subtree less than key.
 private int rank(Key key, Node x) {
     if (x == null) return 0; 
     int cmp = key.compareTo(x.key); 
     if      (cmp < 0) return rank(key, x.left); 
     else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right); 
     else              return size(x.left); 
 } 


 /**
  * Returns the number of keys in the symbol table in the given range.
  *
  * @param  lo minimum endpoint
  * @param  hi maximum endpoint
  * @return the number of keys in the symbol table between {@code lo} 
  *         (inclusive) and {@code hi} (inclusive)
  * @throws IllegalArgumentException if either {@code lo} or {@code hi}
  *         is {@code null}
  */
 public int size(Key lo, Key hi) {
     if (lo == null) throw new IllegalArgumentException("first argument to size() is null");
     if (hi == null) throw new IllegalArgumentException("second argument to size() is null");

     if (lo.compareTo(hi) > 0) return 0;
     if (contains(hi)) return rank(hi) - rank(lo) + 1;
     else              return rank(hi) - rank(lo);
 }

 /**
  * Returns the height of the BST (for debugging).
  *
  * @return the height of the BST (a 1-node tree has height 0)
  */
 public int height() {
     return height(root);
 }
 private int height(Node x) {
     if (x == null) return -1;
     return 1 + Math.max(height(x.left), height(x.right));
 }

/*************************************************************************
 *  Check integrity of BST data structure.
 ***************************************************************************/
 private boolean check() {
     if (!isBST())            System.out.println("Not in symmetric order");
     if (!isSizeConsistent()) System.out.println("Subtree counts not consistent");
     return isBST() && isSizeConsistent();
 }

 // does this binary tree satisfy symmetric order?
 // Note: this test also ensures that data structure is a binary tree since order is strict
 private boolean isBST() {
     return isBST(root, null, null);
 }

 // is the tree rooted at x a BST with all keys strictly between min and max
 // (if min or max is null, treat as empty constraint)
 // Credit: Bob Dondero's elegant solution
 private boolean isBST(Node x, Key min, Key max) {
     if (x == null) return true;
     if (min != null && x.key.compareTo(min) <= 0) return false;
     if (max != null && x.key.compareTo(max) >= 0) return false;
     return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
 } 

 // are the size fields correct?
 private boolean isSizeConsistent() { return isSizeConsistent(root); }
 private boolean isSizeConsistent(Node x) {
     if (x == null) return true;
     if (x.size != size(x.left) + size(x.right) + 1) return false;
     return isSizeConsistent(x.left) && isSizeConsistent(x.right);
 } 
 
 int index = 0;
 private void printKeysInorder() {
 	index = 0;
 	String[] store = new String[size(root)];
 	keysInorder(root, store);
 	System.out.println("Keys in Tree (inorder): " + Arrays.toString(store));
 }
 
 // put keys in order into store
 private void keysInorder(Node x, String[] store) {
 	if(x == null) return;
 	keysInorder(x.left, store);
 	store[index++] = (String)x.key;
 	keysInorder(x.right, store);
 }
 
 // attempts to reduce height of tree with a different insertion sequence
 private void reduceHeight() {
 	index = 0;
 	String[] store = new String[size(root)];
 	keysInorder(root, store);
 	
 	// find insertion sequence
 	int[] sequence = findKeyInsertionSequence(new int[size(root)], 0, store.length-1);
 	// System.out.println(sequence[sequence.length-1]);

 	root = null;
 	
 	for (int i: sequence) {
 		put((Key)store[i],(Value)Integer.valueOf(1));
 	}
 }

 int seqIndex = 0;
 
 // todo: write your code in this methods
 // returns a sequences of indices from the ordered keys that can be used to create a new binary search tree
 private int[] findKeyInsertionSequence(int[] sequence, int lo, int hi) {
    Integer[] indexArray = new Integer[sequence.length];
    for (int i = 0; i < sequence.length; i++) {
        indexArray[i] = i;
    }

    Integer[] int_sequence = new Integer[sequence.length];
    for (int i = 0; i < sequence.length; i++) {
        int_sequence[i] = sequence[i];
    }

    // Manually sort the indices based on the values at those indices using insertion sort
    for (int i = 1; i < indexArray.length; i++) {
        int key = indexArray[i];
        int j = i - 1;
        while (j >= 0 && int_sequence[indexArray[j]].compareTo(int_sequence[key]) > 0) {
            indexArray[j + 1] = indexArray[j];
            j = j - 1;
        }
        indexArray[j + 1] = key;
    }

    Integer[] result = new Integer[sequence.length];
    for (int i = 0; i < sequence.length; i++) {
        result[i] = 0;
    }

    int mid = lo +(hi - lo) / 2;
    System.out.println(seqIndex);
    System.out.println(mid);
    System.out.println(lo);
    System.out.println(hi);
    System.out.println("---");
    result[seqIndex] = indexArray[mid];
    seqIndex++;

    if (lo > hi){
        for (int i = 0; i < sequence.length; i++) {
            sequence[i] = result[i];
        }
        return sequence;
    }
    findKeyInsertionSequence(sequence, lo, mid - 1);
    findKeyInsertionSequence(sequence, mid+1, hi);
    for (int i = 0; i < sequence.length; i++) {
        sequence[i] = indexArray[i];
    }
    return sequence;
	// return sequence;
 }

 public static void main(String[] args) { 
	 BST2024<String, Integer> st = new BST2024<String, Integer>();
    //  st.put("Z", 1);
    //  st.put("K", 1);
    //  st.put("J", 1);
     
     int originalHeight = st.height();
    //  System.out.println("Original height: " + originalHeight);
    //  st.reduceHeight();
     int newHeight = st.height();
    //  st.printKeysInorder();
    //  System.out.println("Minimal height: " + newHeight);
     
    //  System.out.println();
     
     BST2024<String, Integer> st1 = new BST2024<String, Integer>();
     st1.put("A", 1);
     st1.put("B", 1);
     st1.put("C", 1);
     st1.put("D", 1);
     st1.put("E", 1);
     st1.put("F", 1);
     st1.put("G", 1);
     
     originalHeight = st1.height();
     System.out.println("Original height: " + originalHeight);
     st1.reduceHeight();
     newHeight = st1.height();
     st1.printKeysInorder();
     System.out.println("Minimal height: " + newHeight);
     
     System.out.println();
     
     BST2024<String, Integer> st2 = new BST2024<String, Integer>();
     st2.put("J", 1);
     st2.put("M", 1);
     st2.put("F", 1);
     st2.put("D", 1);
     st2.put("K", 1);
     st2.put("C", 1);
     st2.put("O", 1);
     st2.put("N", 1);
     st2.put("L", 1);
     st2.put("A", 1);    
     
     originalHeight = st2.height();
     System.out.println("Original height: " + originalHeight);
     st2.reduceHeight();
     newHeight = st2.height();
     st2.printKeysInorder();
     System.out.println("Minimal height: " + newHeight);
     
     System.out.println();
     
     BST2024<String, Integer> st3 = new BST2024<String, Integer>();
     st3.put("A", 1);
     st3.put("H", 1);
     st3.put("I", 1);
     st3.put("J", 1);
     st3.put("P", 1);
     st3.put("Q", 1);
     st3.put("R", 1);
     st3.put("K", 1);
     st2.put("G", 1);
     st3.put("L", 1);
     st3.put("M", 1);
     st2.put("G", 1);
     st3.put("C", 1);
     st3.put("B", 1);
     st3.put("D", 1);
     st3.put("N", 1);
     st2.put("O", 1);
     st3.put("S", 1);
     st3.put("E", 1);
     st3.put("F", 1);
     st2.put("Z", 1);
     st3.put("Y", 1);
     st3.put("X", 1);
     st3.put("W", 1);
     st3.put("V", 1);
     
     originalHeight = st3.height();
     System.out.println("Original height: " + originalHeight);
     st3.reduceHeight();
     newHeight = st3.height();
     st3.printKeysInorder();
     System.out.println("Minimal height: " + newHeight);
 }
}