package fundModels.Stack;

import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class StackByLinekedList<Item> implements Iterable<Item> {
	private Node first;
	
	public void push(Item item) {
		Node oldFirst = first;
		first = new Node();
		first.item = item;
		first.next = oldFirst;
	}
	
	public Item pop() {
		if (isEmpty()) return null;
		else {
			Item oldItem = first.item;
			first = first.next;
			return oldItem;
		}
	}
	
	public boolean isEmpty() {
		return first == null;
	}
	
	private class Node {
		Item item;
		Node next;
	}
	
	public StackByLinekedList() {
		first = null;
	}
	
	@Override
	public Iterator<Item> iterator() {
		return new LinkedListIterator();
	}
	
	private class LinkedListIterator implements Iterator<Item> {
		private Node current = first;

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public Item next() {
			Item item = current.item;
			current = current.next;
			return item;
		}
		
		
	}

	public static void main(String[] args) {
		StackByLinekedList<String> stack = new StackByLinekedList<String>();
		while(!StdIn.isEmpty()) {
			String s = StdIn.readString();
			if (s.equals("-")) StdOut.print(stack.pop());
			else stack.push(s);
		}

	}

}
