

// M124020020 李培聖 & M124020020 黃冠綸
// todo: student 1 id & name, student 2 id & name
// todo: Complete the method shortestEdgesToAddToMakeAllNodesConnected in Graph2024.java (download from course website) that returns a list of shortest edges in the order of addition that will connect all the existing connected components. 
// Connected components that are larger need to be connected first with the shortest edge between them.  
// The length of an edge is the sum of the nodes' ids, which is also defined in the Edge class. 
// DO NOT EDIT other functions NOR add global variables.

import java.util.Iterator;
import java.util.*;
import java.util.NoSuchElementException;


//Graph2024 is modified from https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/Graph.java.html
//JavaDoc https://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/Graph.html
public class Graph2024 {
    private static final String NEWLINE = System.getProperty("line.separator");

    private final int V;
    private int E;
    private Bag<Integer>[] adj;
    
    /**
     * Initializes an empty graph with {@code V} vertices and 0 edges.
     * param V the number of vertices
     *
     * @param  V number of vertices
     * @throws IllegalArgumentException if {@code V < 0}
     */
    public Graph2024(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices must be non-negative");
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    /**
     * Returns the number of vertices in this graph.
     *
     * @return the number of vertices in this graph
     */
    public int V() {
        return V;
    }

    /**
     * Returns the number of edges in this graph.
     *
     * @return the number of edges in this graph
     */
    public int E() {
        return E;
    }
    
    // Sets the number of edges in this graph
    public void setE(int e) {
    	E = e;
    }
    
    // Returns a copy of the edges from in this graph
    public Bag<Integer>[] copyEdges(){
    	Bag<Integer>[] adj2 = (Bag<Integer>[]) new Bag[V];
    	for (int v = 0; v < V; v++) {
            adj2[v] = adj[v];
        }
    	return adj2;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    /**
     * Adds the undirected edge v-w to this graph.
     *
     * @param  v one vertex in the edge
     * @param  w the other vertex in the edge
     * @throws IllegalArgumentException unless both {@code 0 <= v < V} and {@code 0 <= w < V}
     */
    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        E++;
        
        for (int u : adj[v]) {
        	if(u == w) {
        		return;
        	}
        }
        
        for (int u : adj[w]) {
        	if(u == v) {
        		return;
        	}
        }
        
        adj[v].add(w);
        adj[w].add(v);
    }


    /**
     * Returns the vertices adjacent to vertex {@code v}.
     *
     * @param  v the vertex
     * @return the vertices adjacent to vertex {@code v}, as an iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    /**
     * Returns the degree of vertex {@code v}.
     *
     * @param  v the vertex
     * @return the degree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int degree(int v) {
        validateVertex(v);
        return adj[v].size();
    }
    
    // todo: complete this method
    public Queue<Edge> shortestEdgesToAddToMakeAllNodesConnected(){
        // Initialize connected components to identify existing components
        ConnectedComponent cc = new ConnectedComponent(this, 0);

        // Calculate the initial sizes of each component
        int[] componentSize = new int[V];
        for (int v = 0; v < V; v++) {
            componentSize[cc.id(v)]++;
        }

        // List to store all possible edges and their calculated priorities
        List<Edge> edges = new ArrayList<>();
        Map<Edge, Integer> edgePriority = new HashMap<>();

        // Iterate over all pairs of vertices to find potential edges between different components
        for (int i = 0; i < V; i++) {
            for (int j = i + 1; j < V; j++) {
                if (cc.id(i) != cc.id(j)) { // If vertices are in different components
                    Edge edge = new Edge(i, j);
                    // Calculate priority based on the sizes of the components the edge connects
                    int priority = componentSize[cc.id(i)] + componentSize[cc.id(j)];
                    edges.add(edge);
                    edgePriority.put(edge, priority);
                }
            }
        }

        // Sort edges by priority (size of components they connect), then by edge length if priorities are equal
        Collections.sort(edges, (e1, e2) -> {
            int comparePriority = Integer.compare(edgePriority.get(e2), edgePriority.get(e1));
            if (comparePriority == 0) {
                return Integer.compare(e1.length(), e2.length());
            }
            return comparePriority;
        });

        // Queue to store the result edges to be added
        Queue<Edge> result = new Queue<>();

        // Process edges until all components are connected
        while (!edges.isEmpty() && cc.count() > 1) {
            Edge e = edges.remove(0); // Get the highest priority edge
            if (cc.id(e.v) != cc.id(e.w)) { // Ensure it still connects different components
                result.enqueue(e); // Add the edge to the result queue
                addEdge(e.v, e.w); // Add the edge to the graph to merge components

                // Recompute connected components and update component sizes
                cc = new ConnectedComponent(this, 0);
                componentSize = new int[V];
                for (int v = 0; v < V; v++) {
                    componentSize[cc.id(v)]++;
                }

                // Recalculate priorities since component sizes have changed
                edgePriority.clear();
                for (Edge edge : edges) {
                    edgePriority.put(edge, componentSize[cc.id(edge.v)] + componentSize[cc.id(edge.w)]);
                }

                // Resort edges after updating priorities
                edges.sort((e1, e2) -> {
                    int comparePriority = Integer.compare(edgePriority.get(e2), edgePriority.get(e1));
                    if (comparePriority == 0) {
                        return Integer.compare(e1.length(), e2.length());
                    }
                    return comparePriority;
                });
            }
        }

        return result; // Return the queue of edges to be added
    }
    
    public Graph2024 replicateGraph(Graph2024 G) {
    	Graph2024 G2 = new Graph2024(G.V());
    	for (int v = 0; v < V; v++) {
            for (int w : adj[v]) {
                G2.addEdge(v, w);
            }
        }
    	return G2;
    }

    /**
     * Returns a string representation of this graph.
     *
     * @return the number of vertices <em>V</em>, followed by the number of edges <em>E</em>,
     *         followed by the <em>V</em> adjacency lists
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges ");
        return s.toString();
    }
    
    //Queue is modified from https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/Queue.java.html
    //JavaDoc https://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/Queue.html
    public class Queue<Item> implements Iterable<Item> {
        private Node<Item> first;    // beginning of queue
        private Node<Item> last;     // end of queue
        private int n;               // number of elements on queue

        // helper linked list class
        private static class Node<Item> {
            private Item item;
            private Node<Item> next;
        }

        /**
         * Initializes an empty queue.
         */
        public Queue() {
            first = null;
            last  = null;
            n = 0;
        }

        /**
         * Returns true if this queue is empty.
         *
         * @return {@code true} if this queue is empty; {@code false} otherwise
         */
        public boolean isEmpty() {
            return first == null;
        }

        /**
         * Returns the number of items in this queue.
         *
         * @return the number of items in this queue
         */
        public int size() {
            return n;
        }

        /**
         * Returns the item least recently added to this queue.
         *
         * @return the item least recently added to this queue
         * @throws NoSuchElementException if this queue is empty
         */
        public Item peek() {
            if (isEmpty()) throw new NoSuchElementException("Queue underflow");
            return first.item;
        }

        /**
         * Adds the item to this queue.
         *
         * @param  item the item to add
         */
        public void enqueue(Item item) {
            Node<Item> oldlast = last;
            last = new Node<Item>();
            last.item = item;
            last.next = null;
            if (isEmpty()) first = last;
            else           oldlast.next = last;
            n++;
        }

        /**
         * Removes and returns the item on this queue that was least recently added.
         *
         * @return the item on this queue that was least recently added
         * @throws NoSuchElementException if this queue is empty
         */
        public Item dequeue() {
            if (isEmpty()) throw new NoSuchElementException("Queue underflow");
            Item item = first.item;
            first = first.next;
            n--;
            if (isEmpty()) last = null;   // to avoid loitering
            return item;
        }

        /**
         * Returns a string representation of this queue.
         *
         * @return the sequence of items in FIFO order, separated by spaces
         */
        public String toString() {
            StringBuilder s = new StringBuilder();
            for (Item item : this) {
                s.append(item);
                s.append(' ');
            }
            return s.toString();
        }

        /**
         * Returns an iterator that iterates over the items in this queue in FIFO order.
         *
         * @return an iterator that iterates over the items in this queue in FIFO order
         */
        public Iterator<Item> iterator()  {
            return new LinkedIterator(first);
        }

        // a linked-list iterator
        private class LinkedIterator implements Iterator<Item> {
            private Node<Item> current;

            public LinkedIterator(Node<Item> first) {
                current = first;
            }

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

    }
    
    //Bag is modified from https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/Bag.java.html
    //JavaDoc https://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/Bag.html
    public class Bag<Item> implements Iterable<Item> {
        private Node<Item> first;    // beginning of bag
        private int n;               // number of elements in bag

        // helper linked list class
        private static class Node<Item> {
            private Item item;
            private Node<Item> next;
        }

        /**
         * Initializes an empty bag.
         */
        public Bag() {
            first = null;
            n = 0;
        }

        /**
         * Returns true if this bag is empty.
         *
         * @return {@code true} if this bag is empty;
         *         {@code false} otherwise
         */
        public boolean isEmpty() {
            return first == null;
        }

        /**
         * Returns the number of items in this bag.
         *
         * @return the number of items in this bag
         */
        public int size() {
            return n;
        }

        /**
         * Adds the item to this bag.
         *
         * @param  item the item to add to this bag
         */
        public void add(Item item) {
            Node<Item> oldfirst = first;
            first = new Node<Item>();
            first.item = item;
            first.next = oldfirst;
            n++;
        }
        
        // Removes the item from this bag
        public void remove(Item item) {
        	// if first
        	if(first.item == item) {
        		first = first.next;        		
        	}else{
        		// find item
        		Node<Item> current;
        		for(current = first; true; current = current.next) {
        			if(current.next.item == item) {
        				current.next = current.next.next;
        				return;
        			}
        		}
        	}
        }


        /**
         * Returns an iterator that iterates over the items in this bag in arbitrary order.
         *
         * @return an iterator that iterates over the items in this bag in arbitrary order
         */
        public Iterator<Item> iterator()  {
            return new LinkedIterator(first);  
        }

        // an iterator, doesn't implement remove() since it's optional
        private class LinkedIterator implements Iterator<Item> {
            private Node<Item> current;

            public LinkedIterator(Node<Item> first) {
                current = first;
            }

            public boolean hasNext()  { return current != null;                     }
            public void remove()      { throw new UnsupportedOperationException();  }

            public Item next() {
                if (!hasNext()) throw new NoSuchElementException();
                Item item = current.item;
                current = current.next; 
                return item;
            }
        }

    }
    
    //ConnectedComponent is modified from https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/DepthFirstSearch.java.html
    //JavaDoc https://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/DepthFirstSearch.html
    public static class ConnectedComponent {
        private boolean[] marked;    // marked[v] = is there an s-v path?
        private int count;
        private int[] id;

        /**
         * Computes the vertices in graph {@code G} that are
         * connected to the source vertex {@code s}.
         * @param G the graph
         * @param s the source vertex
         * @throws IllegalArgumentException unless {@code 0 <= s < V}
         */
        public ConnectedComponent(Graph2024 G, int s) {
        	marked = new boolean[G.V()];
            id = new int[G.V()];
            for (int v = 0; v < G.V(); v++)
            {	
               if(G.adj[v] == null) continue;
               if (!marked[v])
               {
                  dfs(G, v);
                  count++;
               }
            }
        }
        
        public int id(int v){
        	return id[v];
        }
        
        public boolean connected(int v, int w) { 
        	return id[v] == id[w];
        }

        // depth first search from v
        private void dfs(Graph2024 G, int v) {
            marked[v] = true;
            id[v] = count;
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    dfs(G, w);
                }
            }
        }

        /**
         * Is there a path between the source vertex {@code s} and vertex {@code v}?
         * @param v the vertex
         * @return {@code true} if there is a path, {@code false} otherwise
         * @throws IllegalArgumentException unless {@code 0 <= v < V}
         */
        public boolean marked(int v) {
            validateVertex(v);
            return marked[v];
        }

        /**
         * Returns component count
         */
        public int count() {
            return count;
        }

        // throw an IllegalArgumentException unless {@code 0 <= v < V}
        private void validateVertex(int v) {
            int V = marked.length;
            if (v < 0 || v >= V)
                throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
        }

    }

    // Class for representing an edge
    public class Edge{
    	int v;
    	int w;
    	public Edge(int v, int w) {
    		this.v = v;
    		this.w = w;
    	}
    	
    	// calculate length of edge
    	public int length() {
    		return v+w;
    	}
    }
    
    /**
     * Unit tests the {@code Graph} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
    	
    	Graph2024 dg0 = new Graph2024(3);
    	dg0.addEdge(0, 2);

    	System.out.println(dg0);
    	
    	Queue<Edge> eg0 = dg0.shortestEdgesToAddToMakeAllNodesConnected(); 
    	
    	int i=0;
    	System.out.print("Added Shortest Edges: ");
    	for(Edge e: eg0) {
    		if (i==eg0.size()-1) {
    			System.out.print(e.v + "-" + e.w);
    		}else {
    			System.out.print(e.v + "-" + e.w + ", ");
        		i++;    			
    		}
    	}
    	
    	System.out.println();
    	System.out.println();
    	
    	Graph2024 dg1 = new Graph2024(6);
    	dg1.addEdge(0, 4);
    	dg1.addEdge(1, 2);
    	dg1.addEdge(1, 3);
    	
    	System.out.println(dg1);
    	
    	eg0 = dg1.shortestEdgesToAddToMakeAllNodesConnected(); 
    	
    	i=0;
    	System.out.print("Added Shortest Edges: ");
    	for(Edge e: eg0) {
    		if (i==eg0.size()-1) {
    			System.out.print(e.v + "-" + e.w);
    		}else {
    			System.out.print(e.v + "-" + e.w + ", ");
        		i++;    			
    		}
    	}
    	
    	System.out.println();
    	System.out.println();
        
    	Graph2024 dg2 = new Graph2024(10);
    	dg2.addEdge(0, 5);
    	dg2.addEdge(1, 3);
    	dg2.addEdge(1, 4);
    	dg2.addEdge(3, 4);
    	dg2.addEdge(3, 6);
    	dg2.addEdge(2, 9);
    	dg2.addEdge(2, 7);
    	
    	System.out.println(dg2);
    	eg0 = dg2.shortestEdgesToAddToMakeAllNodesConnected(); 
    	
    	i=0;
    	System.out.print("Added Shortest Edges: ");
    	for(Edge e: eg0) {
    		if (i==eg0.size()-1) {
    			System.out.print(e.v + "-" + e.w);
    		}else {
    			System.out.print(e.v + "-" + e.w + ", ");
        		i++;    			
    		}
    	}

    	System.out.println();
    	System.out.println();
    	
    	Graph2024 dg3 = new Graph2024(15);
    	dg3.addEdge(10, 2);
    	dg3.addEdge(10, 3);
    	dg3.addEdge(2, 4);
    	dg3.addEdge(3, 4);
    	dg3.addEdge(5, 6);
    	dg3.addEdge(7, 8);
    	dg3.addEdge(7, 9);
    	dg3.addEdge(8, 9);
    	dg3.addEdge(1, 11);
    	dg3.addEdge(11, 12);
    	dg3.addEdge(12, 13);
    	dg3.addEdge(11, 14);
    	
    	System.out.println(dg3);
    	
    	eg0 = dg3.shortestEdgesToAddToMakeAllNodesConnected(); 
    	
    	i=0;
    	System.out.print("Added Shortest Edges: ");
    	for(Edge e: eg0) {
    		if (i==eg0.size()-1) {
    			System.out.print(e.v + "-" + e.w);
    		}else {
    			System.out.print(e.v + "-" + e.w + ", ");
        		i++;    			
    		}
    	}
    }
}

