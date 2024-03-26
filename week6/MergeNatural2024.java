//todo: student 1 id & name, student 2 id & name
//todo: modify sort and findNextRun functions to implement a mergesort that finds natural ordered items 
//with "runs" and merges them using a bottom up approach.   
//DO NOT EDIT other functions NOR add global variables.

//MergeNatural2024 is modified from MergeBU from https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/MergeBU.java.html
//JavaDoc https://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/MergeBU.html
public class MergeNatural2024 {

    // This class should not be instantiated.
    private MergeNatural2024() { }

    // stably merge a[lo..mid] with a[mid+1..hi] using aux[lo..hi]
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {

        // copy to aux[]
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k]; 
        }

        // merge back to a[]
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)              a[k] = aux[j++];  // this copying is unnecessary
            else if (j > hi)               a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else                           a[k] = aux[i++];
        }

    }
    
    // todo: write code in this function
    // finds next run in the input array with the given starting index
    // returns end index of the run
    public int findNextRun(Comparable[]a, int startIndex) {
    	int endIndex = a.length -1;
    	// add your code here

        int i = startIndex + 1;
        if (startIndex >= a.length-1) { // prevent index from going out of bound
            endIndex = startIndex;
            return endIndex;
        }

        for(i =startIndex+1; i<a.length; i++) 
        {
            if (less(a[i],a[i-1])) { // compare the 2 values from the array, if not in order, break
                break;
            }
        }
        endIndex =i-1;

    	// do not modify the following lines
    	System.out.println("Run start: " + startIndex + ", end: " + endIndex);
    	show(a,startIndex,endIndex);
    	return endIndex;
    }
    
    // todo: write code in this function to sort the array with mergesort using natural runs
    // use findNextRun function in this function to identify runs
    public static void sort(Comparable[] a) 
    {
        // add your code here
        Comparable aux[] = new Comparable[a.length];
        MergeNatural2024 natural = new MergeNatural2024();
        

        while (!isSorted(a)) // while the array is not sorted
        {
            int mid =0;
            int hi =0;
            for(int i=0; i<a.length; i = hi+1)
            {
                mid = natural.findNextRun(a,i) ; // find first run, i initially set to 0
                if(mid == a.length-1) // prevent index from going out bound
                    break;
                hi = natural.findNextRun(a, mid+1);
                merge(a, aux,i,mid,hi); // merges the subarrays
            }
            
        }
        natural.findNextRun(a, 0);
    }

  /***********************************************************************
    *  Helper sorting functions.
    ***************************************************************************/
    
    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }


   /***************************************************************************
    *  Check if array is sorted - useful for debugging.
    ***************************************************************************/
    private static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    // print array to standard output
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
    
    // print array between indices to standard output
    private static void show(Comparable[] a, int startIndex, int endIndex) {
        for (int i = startIndex; i <= endIndex; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        Integer[] a = {3,7,5,9,12,20,10,15,11,6,17,200,1};
        MergeNatural2024.sort(a);
        
        System.out.println();
        System.out.print("Sorted: ");
        show(a); 
    }
}
