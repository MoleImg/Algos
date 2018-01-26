package fundModels.PriorityQueue;

public class MaxPQ<key extends Comparable<key>> {
    private key[] PQ;
    private int N;

    public MaxPQ(int capacity) {
        PQ = (key[])new Comparable[capacity+1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void insert(key key) {
        PQ[++N] = key;
        swim(N);
    }

    public key delMax() {
        key max = PQ[1];
        swap(1, N--);
        sink(1);
        PQ[N+1] = null;
        return max;
    }

    private void swim(int k) {
        while (k/2 > 0 && less(k/2, k)) {
            swap(k, k/2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2*k <= N) {
            int j = 2*k;
            if (j < N && less(j, j+1)) j++;
            if (!less(k, j)) break;
            swap(k, j);
            k = j;
        }
    }

    private boolean less(int i, int j) {
        return PQ[i].compareTo(PQ[j]) < 0;
    }

    private void swap(int i, int j) {
        key tmp = PQ[i];
        PQ[i] = PQ[j];
        PQ[j] = tmp;
    }
}