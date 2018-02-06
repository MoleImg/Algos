import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * ST.java
 * @todo: Implementation of symbol table
 */

 public class ST<Key extends Comparable<Key>, Value> {
     private Key[] keys;
     private Value[] vals;
     private int N;

     public ST() {

     }

     public void put(Key key, Value value) {

     }

     public Value get(Key key) {
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < N && keys[i] == key) return vals[i];
        else return null;
     }

     /**
      * Binary search
      */
     private int rank(Key key) {
        int lo = 0, hi = N - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp > 0) lo = mid + 1;
            else if (cmp < 0) hi = mid - 1;
            else return mid;
        }
        return lo;
     }

     public boolean contains(Key key) {
         return get(key) != null;
     }

     public void delete(Key key) {
        put(key, null);
     }

     public boolean isEmpty() {
         return 0 == N;
     }

     public int size() {
        return N;
     }

     public Iterable<Key> keys() {

     }

     public static void main(String[] args) {
         // frequency counter
         int minLen = Integer.parseInt(args[0]);
         ST<String, Integer> st = new ST<String, Integer>();
         while(!StdIn.isEmpty()) {
             String word = StdIn.readString();
             if (word.length() < minLen) continue;
             if (!st.contains(word)) st.put(word, 1);
             else st.put(word, st.get(word) + 1);
         }
         String maxFreqWord = "";
         st.put(maxFreqWord, 0);
         for (String word : st.keys())
            if (st.get(word) > st.get(maxFreqWord))
                maxFreqWord = word;
         StdOut.println(maxFreqWord + " " + st.get(maxFreqWord));
     }
 }