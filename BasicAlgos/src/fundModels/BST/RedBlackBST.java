package fundModels.BST;

import java.util.NoSuchElementException;

public class RedBlackBST<Key extends Comparable<Key>, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int size;
        private boolean color;

        public Node(Key key, Value val, int N, boolean color) {
            this.key = key;
            this.val = val;
            this.size = N;
            this.color = color; // color of parent link
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (null == node) return 0;
        else return node.size;
    }

    private boolean isRed(Node node) {
        if (null == node) return false;
        return RED == node.color;
    }

    public Value get(Key key) {
        if (null == key) throw new NoSuchElementException(
            "calls get() with a null key");
        return get(root, key);
    }

    private Value get(Node node, Key key) {
        while (node != null) {
            int cmp = node.key.compareTo(key);
            if (cmp > 0) node = node.left;
            if (cmp < 0) node = node.right;
            else return node.val;
        }

        return null;
    }

    public void put(Key key, Value val) {
        if (null == key) throw new IllegalArgumentException(
            "calls put() with a null key");
        root = put(root, key, val);
    }

    private Node put(Node node, Key key, Value val) {
        if (null == node) return new Node(key, val, 1, RED);
        int cmp = node.key.compareTo(key);
        if (cmp > 0) node.left = put(node.left, key, val);
        if (cmp < 0) node.right = put(node.right, key, val);
        else node.val = val;
        // maintein red-black tree
        if (isRed(node.right) && !isRed(node.left)) node = rotateLeft(node);
        if (isRed(node.left) && isRed(node.left.left)) node = rotateRight(node);
        if (isRed(node.left) && isRed(node.right)) flipColors(node);
        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

    private Node rotateLeft(Node node) {
        Node x = node;
        node = x.right;
        x.right = node.left;
        node.left = x;
        node.color = x.color;
        x.color = RED;
        return node;
    }

    private Node rotateRight(Node node) {
        Node x = node;
        node = x.left;
        x.left = node.right;
        node.right = x;
        node.color = x.color;
        x.color = RED;
        return node;
    }

    private void flipColors(Node node) {
        assert !isRed(node);
        assert isRed(node.left);
        assert isRed(node.right);
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }
}