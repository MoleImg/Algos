package fundModels.Sort;

import java.util.Comparator;

public class Merge extends SortAlgos{

	private static final int CUTTOFF = 7;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sort(Comparable[] a) {
		sort(a, 0, a.length - 1);
		
	}
	
	@Override
	public void sort(Comparable[] a, int lo, int hi) {
		Comparable[] aux = new Comparable[hi - lo + 1];
		sort(a, aux, lo, hi);
		
	}

	@Override
	public void sort(Object[] a, Comparator comparator) {
		sort(a, 0, a.length - 1, comparator);
		
	}

	@Override
	public void sort(Object[] a, int lo, int hi, Comparator comparator) {
		Object[] aux = new Object[hi - lo + 1];
		sort(a, aux, lo, hi, comparator);
		
	}

	private void sort(Object[] a, Object[] aux, int lo, int hi,
			Comparator comparator) {
		if (hi <= lo) return;
		//
		if (hi <= lo + CUTTOFF - 1) {
			Insertion ins = new Insertion();
			ins.sort(a, lo, hi, comparator);
			return;
		}
		//
		int mid = lo + (hi - lo) / 2;
		sort(a, aux, lo, mid, comparator);
		sort(a, aux, mid+1, hi, comparator);
		if (!less(comparator, a[mid+1], a[mid])) return;
		merge(a, aux, lo, mid, hi, comparator);
		
		
	}

	private void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
		if (hi <= lo) return;
		//
		if (hi <= lo + CUTTOFF - 1) {
			Insertion ins = new Insertion();
			ins.sort(a, lo, hi);
			return;
		}
		//
		int mid = lo + (hi - lo) / 2;
		sort(a, aux, lo, mid);
		sort(a, aux, mid + 1, hi);
		if (!less(a[mid + 1], a[mid])) return;
		merge(a, aux, lo, mid, hi);
	}

	private void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
		assert isSorted(a, lo, mid);
		assert isSorted(a, mid + 1, hi);
		
		for (int k = lo; k <= hi; k++) {
			aux[k] = a[k];
		}
		
		int i = lo; int j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			if (i > mid) a[k] = aux[j++];
			else if (j > hi) a[k] = aux[i++];
			else if (less(aux[i], aux[j])) a[k] = aux[i++];
			else a[k] = aux[j++];
		}
		
		assert isSorted(a, lo, hi);
		
	}
	
	private void merge(Object[] a, Object[] aux, int lo, int mid, int hi,
			Comparator comparator) {
		assert isSorted(a, lo, mid, comparator);
		assert isSorted(a, mid+1, hi, comparator);

		for (int k = lo; k <= hi; k++) {
			aux[k] = a[k];
		}

		int i = lo; int j = mid + 1;
		for (int k = lo; k <= hi; k++) {
			if (i > mid) a[k] = aux[j++];
			else if (j > hi) a[k] = aux[i++];
			else if (less(comparator, aux[i], aux[j])) a[k] = aux[i++];
			else a[k] = aux[j++];
 		}
		
		
		
	}
	
	public void sortBU(Comparable[] a) {
		int N = a.length;
		Comparable[] aux = new Comparable[N];
		
		for(int sz = 1; sz < N; sz += sz) {
			for (int lo = 0; lo < N - sz; lo += sz + sz) {
				merge(a, aux, lo, lo + sz -1, Math.min(lo+2*sz-1, N-1));
			}
		}
	}
	
	public void sortBU(Object[] a, Comparator comparator) {
		int N = a.length;
		Object[] aux = new Object[N];
		
		for(int sz = 1; sz < N; sz += sz) {
			for (int lo = 0; lo < N - sz; lo += sz + sz) {
				merge(a, aux, lo, lo + sz -1, Math.min(lo+2*sz-1, N-1), comparator);
			}
		}
	}

}
