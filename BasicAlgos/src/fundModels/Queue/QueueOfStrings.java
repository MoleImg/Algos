package fundModels.Queue;

public class QueueOfStrings {
	private Node first, last;
	public QueueOfStrings() {
		first = null;
		last = first;
	}
	
	public void enqueue(String item) {
		Node oldLast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		if (isEmpty()) first = last;
		else oldLast.next = last;
	}
	
	public String dequeue() {
		String oldItem = first.item;
		first = first.next;
		if (isEmpty()) last = null;
		return oldItem;
	}
	
	public boolean isEmpty() {
		return first == null;
	}
	
	private class Node {
		String item;
		Node next;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}