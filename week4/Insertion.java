package week4;
public class Insertion 
{ 
    @SuppressWarnings("rawtypes") 
    public static void sort(Comparable[] a) 
    { 
        int N = a.length; 
        for (int i = 0; i < N; i++) 
        { 
            // int min = i; 
            for (int j = i; j > 0; j--) 
                if (less(a[j], a[j-1])) {
                    exch(a, j, j-1);
                } 
                else {
                    break;
                }
        } 
    } 
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static boolean less(Comparable v, Comparable w) { 
        return v.compareTo(w) < 0; 
    }
    @SuppressWarnings("rawtypes")
    private static void exch(Comparable[] a, int i, int j) { 
        Comparable swap = a[i]; 
        a[i] = a[j]; 
        a[j] = swap; 
    }
}