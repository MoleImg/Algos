package fundModels.Sort;

import java.util.Comparator;

public class Insertion extends SortAlgos{

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sort(Comparable[] a) {
		sort(a, 0, a.length - 1);
	}

	@Override
	public void sort(Comparable[] a, int lo, int hi) {
		for (int i = lo; i <=hi; i++) {
			for (int j = i; j > lo; j--) {
				if (less(a[j], a[j-1])) swap(a, j, j-1);
				else break;
			}
		}
		
	}

	@Override
	public void sort(Object[] a, Comparator comparator) {
		sort(a, 0, a.length - 1, comparator);
		
	}

	@Override
	public void sort(Object[] a, int lo, int hi, Comparator comparator) {
		for (int i = lo; i <= hi; i++) {
			for (int j = i; j > lo; j--) {
				if (less(comparator, a[j], a[j-1])) swap(a, j, j-1);
				else break;
			}
		}
		
	}

}
