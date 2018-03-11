package fundModels.HashTable;

public class SeparateChainingHashST<Key, Value> {
    private int N;  // number of key-value pairs
    private int M; // hash table size, number of chains
    private Node[] st = new Node[M];    // array of chains

    private class Node {
        private Object key;
        private Object value;
        private Node nextNode;

        public Node(Object key, Object value, Node nextNode) {
            this.key = key;
            this.value = value;
            this.nextNode = nextNode;
        }
    }

    // hash function
    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public Value get(Key key) {
        int hashCode = hash(key);
        for (Node iNode = st[hashCode]; iNode != null; iNode = iNode.nextNode)
            if (key.equals(iNode.key)) return iNode.value;
        return null;
    }

    public void put(Key key, Value value) {
        int hashCode = hash(key);
        for (Node iNode = st[hashCode]; iNode != null; iNode = iNode.next)
            if (key.equals(iNode.key)) iNode.value = value;
        st[hashCode] = new Node(key, value, st[hashCode]);
    }

}