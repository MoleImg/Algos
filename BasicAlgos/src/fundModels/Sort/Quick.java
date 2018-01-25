package fundModels.Sort;

import edu.princeton.cs.algs4.StdRandom;
import java.util.Comparator;

public class Quick extends SortAlgos {
    @Override
	public void sort(Comparable[] a) {
		sort(a, 0, a.length - 1);
		
	}
	
	@Override
	public void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        StdRandom.shuffle(a);
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);	
	}

	@Override
	public void sort(Object[] a, Comparator comparator) {
		sort(a, 0, a.length - 1, comparator);
		
	}

	@Override
	public void sort(Object[] a, int lo, int hi, Comparator comparator) {
		StdRandom.shuffle(a);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo; int j = hi + 1;
        while (true) {
            while(less(a[++i], a[lo]))
                if (i == hi) break;
            while(less(a[lo], a[--j]))
                if (j == lo) break;
            if (i > j) break;
            swap(a, i, j);
        }
        swap(a, lo, j);
        return j;
    }

<<<<<<< HEAD
    public void sortBy3Way(Comparable a[], int lo, int hi) {
        StdRandom.shuffle(a);
        if (hi <= lo) return;
        int i = lo; int lt = lo; int gt = hi;
        while (i <= gt) {
            if (less(a[lo], a[i])) swap(a, i++, lt++);
            else if (less(a[i], a[lo])) swap(a, i++, gt--);
            else i++;
        }

        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }

=======
>>>>>>> 077fb1ab8588af92db16dd0932e763f37ad964ca
    /**
     * select the kth smallest element from the array
     */
    public static Comparable select(Comparable[] a, int k) {
        StdRandom.shuffle(a);
        int lo = 0; int hi = a.length - 1;
        while(lo < hi) {
            int j = partition(a, lo, hi);
            if (j < k) lo = j + 1;
            else if (j > k) hi = j - 1;
            else return a[k];
        }
        return a[k];
    }
<<<<<<< HEAD

=======
>>>>>>> 077fb1ab8588af92db16dd0932e763f37ad964ca
    
    
}