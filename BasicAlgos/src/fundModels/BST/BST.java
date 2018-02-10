package fundModels.BST;


public class BST<Key extends Comparable<Key>, Value> {

	private Node root;

	public BST() {}

	public boolean isEmpty() {
		return 0 == size();
	}

	public int size() {
		return size(root);
	}

	private int size(Node node) {
		if (null == node) return 0;
		else return node.size;
	}

	public boolean contains(Key key) {
		if (null == key) throw new IllegalArgumentException(
			"argument to contains() is null");
		return get(key) != null;
	}

	public void put(Key key, Value val) {
		if (null == key) throw new IllegalArgumentException(
			"calls put() with a null key");
		if (null == val) {
			delete(key); return;
		}
		root = put(root, key, val);
	}

	private Node put(Node node, Key key, Value val) {
		if (null == node) return new Node(key, val, 1);
		int cmp = node.key.compareTo(key);
		if (cmp > 0) node.left = put(node.left, key, val);
		else if (cmp < 0) node.right = put(node.right, key, val);
		else node.val = val;
		node.size = 1 + size(node.left) + size(node.right);
		return node;
	}

	public Value get(Key key) {
		return get(root, key);
	}

	private Value get(Node node, Key key) {
		if (null == key) throw new IllegalArgumentException(
			"calls get() with a null key");
		if (null == node) return null;
		int cmp = node.key.compareTo(key);
		if (cmp > 0) return get(node.left, key);
		else if (cmp < 0) return get(node.right, key);
		else return node.val;
	}

	public void deleteMin() {
		if (isEmpty()) throw new IllegalArgumentException(
			"Symbol table underflow");
		root = deleteMin(root);
	}

	private Node deleteMin(Node node) {
		if (null == node.left) return node.right;
		node.left = deleteMin(node.left);
		node.size = 1 + size(node.left) + size(node.right);
		return node;
	}

	public void deleteMax() {
		if (isEmpty()) throw new IllegalArgumentException(
			"Symbol table underflow");
		root = deleteMax(root);
	}

	private Node deleteMax(Node node) {
		if (null == node.right) return node.left;
		node.right = deleteMax(node.right);
		node.size = 1 + size(node.left) + size(node.right);
		return node;
	}

	public void delete(Key key) {
		if (null == key) throw new IllegalArgumentException(
			"calls delete() with a null key");
		if (isEmpty()) throw new IllegalArgumentException(
			"Symbol table underflow");
		root = delete(root, key);
	}

	private Node delete(Node node, Key key) {
		if (null == node) return null;
		int cmp = node.key.compareTo(key);
		if (cmp > 0) node.left = delete(node.left, key);
		else if (cmp < 0) node.right = delete(node.right, key);
		else {
			if (null == node.left) return node.right;
			if (null == node.right) return node.left;
			Node tmp = node;
			node = min(tmp.right);
			node.right = deleteMin(tmp.right);
			node.left = tmp.left;
		}
		node.size = 1 + size(node.left) + size(node.right);
		return node;

	}

	public Key max() {
		if (isEmpty()) throw new IllegalArgumentException(
			"calls max() with empty symbol table");
		return max(root).key;
	}

	private Node max(Node node) {
		if (null == node.right) return node;
		else return max(node.right);
	}

	public Key min() {
		if (isEmpty()) throw new IllegalArgumentException(
			"calls min() with empty symbol table");
		return min(root).key;
	}

	private Node min(Node node) {
		if (null == node.left) return node;
		else return min(node.left);
	}

	public Iterable<Key> keys() {
		
	}


	private class Node {
		private Key key;
		private Value val;
		private Node left, right;
		private int size;	// number of nodes in subtree

		public Node(Key key, Value val, int size) {
			this.key = key;
			this.val = val;
			this.size = size;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
