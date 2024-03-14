package week3;


//todo: student 1 id & name, student 2 id & name
//todo: complete the method nodesWithDepthOf to return a list of nodes in an array that have the depth specified in the argument. 
//the returned nodes from the method nodesWithDepthOf need to be in ascending order (small to large).
//(optional) todo: complete the method componentSize to return the size of the connected component with item p.
//DO NOT EDIT other functions NOR add global variables.

public class UnionFind2024 {
	
	// QuickUnionUF2024 is modified from QuickUnionUF from https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/QuickUnionUF.java.html
	// JavaDoc of QuickUnionUF can be found at https://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/QuickUnionUF.html
	public static class QuickUnionUF2024 {
	    private int[] parent;  // parent[i] = parent of i
	    private int count;     // number of components

	    /**
	     * Initializes an empty union-find data structure with
	     * {@code n} elements {@code 0} through {@code n-1}.
	     * Initially, each element is in its own set.
	     *
	     * @param  n the number of elements
	     * @throws IllegalArgumentException if {@code n < 0}
	     */
	    public QuickUnionUF2024(int n) {
	        parent = new int[n];
	        count = n;
	        for (int i = 0; i < n; i++) {
	            parent[i] = i;
	        }
	    }

	    /**
	     * Returns the number of sets.
	     *
	     * @return the number of sets (between {@code 1} and {@code n})
	     */
	    public int count() {
	        return count;
	    }

	    /**
	     * Returns the canonical element of the set containing element {@code p}.
	     *
	     * @param  p an element
	     * @return the canonical element of the set containing {@code p}
	     * @throws IllegalArgumentException unless {@code 0 <= p < n}
	     */
	    public int find(int p) {
	        validate(p);
	        while (p != parent[p])
	            p = parent[p];
	        return p;
	    }

	    // validate that p is a valid index
	    private void validate(int p) {
	        int n = parent.length;
	        if (p < 0 || p >= n) {
	            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n-1));
	        }
	    }

	    /**
	     * Returns true if the two elements are in the same set.
	     *
	     * @param  p one element
	     * @param  q the other element
	     * @return {@code true} if {@code p} and {@code q} are in the same set;
	     *         {@code false} otherwise
	     * @throws IllegalArgumentException unless
	     *         both {@code 0 <= p < n} and {@code 0 <= q < n}
	     * @deprecated Replace with two calls to {@link #find(int)}.
	     */
	    @Deprecated
	    public boolean connected(int p, int q) {
	        return find(p) == find(q);
	    }

	    /**
	     * Merges the set containing element {@code p} with the set
	     * containing element {@code q}.
	     *
	     * @param  p one element
	     * @param  q the other element
	     * @throws IllegalArgumentException unless
	     *         both {@code 0 <= p < n} and {@code 0 <= q < n}
	     */
	    public void union(int p, int q) {
	        int rootP = find(p);
	        int rootQ = find(q);
	        if (rootP == rootQ) return;
	        parent[rootP] = rootQ;
	        count--;
	    }
	    
	    // todo: complete this method to return a list of nodes in an array that have the depth specified in the argument. 
	    // the returned nodes need to be in ascending order (small to large).
	    public int[] nodesWithDepthOf(int depth) {
	    	
	    	
	    	int[] nodes = new int[] {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
	    	for (int i = 0; i < parent.length; i++) {
	    		int c = 0;
	    		int p = i;
		        while (p != parent[p]) {
		            p = parent[p];
		        	c++;
		        }
		        if (c == depth) {
		        	nodes[i] = i;
		        }
		        
	    	}

	    	return nodes;
	    }
	    
	    // (optional) todo: complete the method to return the size of the connected component with item p.
	    public int componentSize(int p) {
	    	int root = find(p);
	    	int [] depth_array = new int [parent.length];
	    	
	    	//initialize the depth array
	    	for(int i=0; i<parent.length ;i++) {
	    		depth_array[i] = -1;
	    	}
	    	
	    	for(int i=0; i<parent.length ;i++) {
	    		if(find(i) == root) {
	    			depth_array[i] = 1;
	    		}
	    	}
	    	
	    	int count=0;
	    	
	    	for(int i=0; i<parent.length ;i++) {
	    		if(depth_array[i] == 1)
	    			count++;
	    	}
	    	
	    	
	    	return count;
	    }
	    
	    public void printNodes(int[] nodes, int depth) {
	    	int i;
	    	String output = "";
	    	for (i=0; i<nodes.length; i++) {
	    		if(nodes[i] != -1) {
	    			output += nodes[i] + ",";
	    		}
	    	}
	    	if (output != ""){
	    		output = output.substring(0, output.length() - 1);
	    	}
	    	System.out.println("Nodes with depth of " + depth + ": " + output);
	    }
	}
	
	public static void main(String[] args) {
		QuickUnionUF2024 uf = new QuickUnionUF2024(12);
		uf.union(1, 2);
		uf.union(3, 2);
		uf.union(5, 4);
		uf.union(4, 0);
		uf.union(2, 0);
		uf.union(7, 6);
		uf.union(8, 6);
		uf.union(9, 6);
		uf.union(6, 10);

		// int[] a = new int[] {0,10,11,3,2,5,-1,-1};
//		uf.printNodes(a, 2);
		uf.printNodes(uf.nodesWithDepthOf(0), 0);
		uf.printNodes(uf.nodesWithDepthOf(1), 1);
		uf.printNodes(uf.nodesWithDepthOf(2), 2);
		uf.printNodes(uf.nodesWithDepthOf(3), 3);
//		
//		System.out.println("Component size of 1 = " + uf.componentSize(1));
//		System.out.println("Component size of 6 = " + uf.componentSize(6));
	}
}
