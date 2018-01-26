package fundModels.Sort;

import java.util.Comparator;

public class Heap extends SortAlgos {
    @Override
    public void sort (Comparable[] a) {
        sort(a, 1, a.length);
    }

    @Override
    public void sort(Comparable[] a, int lo, int hi) {
        int N = a.length;
        // heap construction
        for (int k = N / 2; k >= 1; k--) {
            sink(a, k, N);
        }
        // del max
        while (N > 1) {
            swap(a, 1, N);
            sink(a, 1, --N);
        }
    }

    @Override
    public void sort(Object[] a, Comparator Comparator) {
        sort(a, 1, a.length, Comparator);
    }

    @Override
    public void sort(Object[] a, int lo, int hi, Comparator comparator) {
        int N = a.length;
        // heap construction
        for (int k = N / 2; k >= 1; k--) {
            sink(a, k, N, comparator);
        }
        // del max
        while (N > 1) {
            swap(a, 1, N);
            sink(a, 1, --N, comparator);
        }
    }

    private void sink(Comparable[] a, int k, int N) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(a, j, j + 1)) j++;
            if (!less(a, k, j)) break;
            swap(a, k, j);
            k = j;
        }
    }

    private void sink(Object[] a, int k, int N, Comparator com) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(a, j, j + 1, com)) j++;
            if (!less(a, k, j, com)) break;
            swap(a, k, j);
            k = j;
        }
    }

    private boolean less(Comparable[] a, int i, int j) {
        return a[i].compareTo(a[j]) < 0;
    }

    private boolean less(Object[] a, int i, int j, Comparator comparator) {
        return comparator.compare(a[i], a[j]) < 0;
    }
}