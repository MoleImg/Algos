/**
 * Foundations of sort algorithms
 */
package fundModels.Sort;

import java.util.Comparator;

public abstract class SortAlgos {
	
	public abstract void sort(Comparable a[]); // sort machine
	public abstract void sort(Comparable a[], int lo, int hi); // piecewise sort machine
	public abstract void sort(Object[] a, Comparator comparator);
	public abstract void sort(Object[] a, int lo, int hi, Comparator comparator);
	
	public static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}
	
	public static boolean less(Comparator com, Object v, Object w) {
		return (com.compare(v, w) < 0);
	}
	
	public static void swap(Comparable a[], int i, int j) {
		Comparable tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}
	
	public static void swap(Object[] a, int i, int j) {
		Object tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}
	
	public static boolean isSorted(Comparable[] a, int lo, int hi) {
		for (int i = lo; i < hi; i++)
			if (less(a[i + 1], a[i])) return false;
		return true;
	}
	
	public static boolean isSorted(Object[] a, int lo, int hi, Comparator com) {
		for (int i = lo; i <= hi; i++)
			if(less(com, a[i + 1], a[i])) return false;
		return true;
	}

}
