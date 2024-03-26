//todo: student 1 id & name, student 2 id & name
//todo: modify sort and findNextRun functions to implement a mergesort that finds natural ordered items 
//with "runs" and merges them using a bottom up approach.   
//DO NOT EDIT other functions NOR add global variables.

//MergeNatural2024 is modified from MergeBU from https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/MergeBU.java.html
//JavaDoc https://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/MergeBU.html
public class MergeNatural2024_ {

    // This class should not be instantiated.
    private MergeNatural2024_() { }

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
        // int temp = 0;
        // for (int i = startIndex+1; i < a.length; i++){
        //     temp = i;
        //     if (!less(a[i-1], a[i])){
        //         temp -= 1;
        //         // System.out.println(temp);
        //         break;
        //     }
        // }
        // endIndex = temp;
        int i = startIndex + 1;
        for (i = startIndex+1; i<a.length;i++){
            if (less(a[i], a[i-1])){
                break;
            }
        }
        endIndex = i-1;

    	// do not modify the following lines
    	System.out.println("Run start: " + startIndex + ", end: " + endIndex);
    	show(a,startIndex,endIndex);
    	return endIndex;
    }
    
    // todo: write code in this function to sort the array with mergesort using natural runs
    // use findNextRun function in this function to identify runs
    public static void sort(Comparable[] a) {
        // add your code here
        MergeNatural2024_ natural = new MergeNatural2024_();
        // Comparable temp = natural.findNextRun(a, 0);
        Comparable aux[] = new Comparable[a.length];
        int n = a.length;
        int startIndex = 0;
        
        // int temp_int = (int) temp;
        // temp_int++;
        // int endIndex = a.length-1;
        // while(!isSorted(a)){
        //     Comparable lo = natural.findNextRun(a, startIndex);
        //     int lo_int = (int) lo;
        //     lo_int++;
        //     Comparable high = natural.findNextRun(a, lo_int);
        //     int high_int = (int) high;
        //     high_int++;
        //     // int mid = 
        //     merge(a,aux,startIndex,lo_int-1,high_int-1);
        //     startIndex = 0;
            
        // }
        // natural.findNextRun(a, 0);

        while (!isSorted(a)) {
            int i = 0;
            while (i < a.length) {
                Comparable lo = natural.findNextRun(a, i);
                int lo_int = (int) lo;
                int mid = lo_int; // Set mid to the beginning of the current run
                if (mid == a.length - 1) // Prevent index from going out of bounds
                    break;
                Comparable high = natural.findNextRun(a, mid + 1);
                int high_int = (int) high;
                int hi = high_int;
                merge(a, aux, i, mid, hi); // Merges the subarrays
                i = hi + 1; // Move to the next unmerged segment
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
        MergeNatural2024_.sort(a);
        // System.out.println("Bruh");
        
        System.out.println();
        System.out.print("Sorted: ");
        show(a); 
    }
}
