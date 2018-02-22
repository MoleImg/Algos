/**
 * @Todo: Implementation of Binary Search Tree
 */
package fundModels.BST;

import java.util.List;

import fundModels.Queue.*;

public class BST<Key extends Comparable<Key>, Value> {

	private Node root;

	public BST() {}

	public boolean isEmpty() {
		return 0 == size();
	}

	public int size() {
		return size(root);
	}

	/**
	 * 1D range count
	 */
	public int size(Key lo, Key hi) {
		if (lo == null) throw new IllegalArgumentException("first argument to size() is null");
		if (hi == null) throw new IllegalArgumentException("second argument to size() is null");
		if (lo.compareTo(hi) > 0) return 0;
		if (contains(hi)) return rank(hi) - rank(lo) + 1;
		else return rank(hi) - rank(lo);
	}

	private int size(Node node) {
		if (null == node) return 0;
		else return node.size;
	}

	/**
	 * 1D range search
	 */
	public List<Key> find(Key lo, Key hi) {
		if (lo == null) throw new IllegalArgumentException("first argument to size() is null");
		if (hi == null) throw new IllegalArgumentException("second argument to size() is null");
		return find(root, lo, hi);
	}

	private List<Key> find(BST<Key, Value>.Node root2, Key lo, Key hi) {
		// TODO Auto-generated method stub
		return null;
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
		if (cmp < 0) node.right = delete(node.right, key);
		else {
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

	public Key floor(Key key) {
		Node x = floor(root, key);
		return null == x ? null : x.key;
	}

	private Node floor(Node node, Key key) {
		if (null == node) return null;
		int cmp = node.key.compareTo(key);
		if (0 == cmp) return node;
		if (cmp > 0) return floor(node.left, key);
		Node t = floor(node.right, key);
		return null == t ? node : t;
	}

	public Key ceiling(Key key) {
		if (key == null) throw new IllegalArgumentException(
			"argument to ceiling() is null");
		if (isEmpty()) throw new IllegalArgumentException(
			"calls ceiling() with empty symbol table");
		Node x = ceiling(root, key);
		return null == x ? null : x.key;
	}

	private Node ceiling(Node node, Key key) {
		if (null == node) return null;
		int cmp = node.key.compareTo(key);
		if (0 == cmp) return node;
		if (cmp < 0) return ceiling(node.right, key);
		Node t = ceiling(node.left, key);
		return null == t ? node : t;

	}


	public int rank(Key key) {
		return rank(root, key);
	}

	private int rank(Node node, Key key) {
		if (null == node) return 0;
		int cmp = node.key.compareTo(key);
		if (cmp > 0) return rank(node.left, key);
		if (cmp < 0) return 1 + size(node.left) + rank(node.right, key);
		else return size(node.left);
	}

	/**
	 * select the key of node with the rank of k
	 */
	public Key select(int k) {
		if (k < 0 || k > size()) throw new IllegalArgumentException(
			"argument to select() is invalid: " + k);
		Node x = select(root, k);
		return x.key;
	}

	private Node select(Node node, int k) {
		if (null == node) return null;
		int t = size(node.left);
		if (t > k) return select(node.left, k);
		if (t < k) return select(node.right, k - t - 1);
		else return node;
	}

	public int height() {
		return height(root);
	}

	public int height(Node node) {
		if (null == node) return -1;
		return 1 + Math.max(height(node.left), height(node.right));
	}

	public Iterable<Key> keys() {
		QueueByLinkedList<Key> queue = new QueueByLinkedList<Key>();
		inorder(root, queue);
		return queue;
	}

	/**
	 * in-order traverse
	 */
	private void inorder(Node node, QueueByLinkedList q) {
		if (null == node) return;
		inorder(node.left, q);
		q.enqueue(node);
		inorder(node.right, q);
	}

	/**
	 * level-order traverse
	 */
	private void levelOrder(Node node, QueueByLinkedList q) {
		if (null == node) return;
		q.enqueue(node);
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
