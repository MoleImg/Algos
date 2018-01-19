package fundModels.Queue;


public class QueueByLinkedList<Item> {
	private Node first, last;
	
	public QueueByLinkedList() {
		first = null;
		last = first;
	}
	
	public boolean isEmpty() {
		return first == null;
	}
	
	public void enqueue(Item item) {
		Node oldLast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		if (isEmpty()) first = last;
		else oldLast.next = last;
	}
	
	public Item dequeue() {
		Item oldItem = first.item;
		first = first.next;
		if (isEmpty()) last = null;
		return oldItem;
	}

	
	private class Node {
		Item item;
		Node next;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
