/**
 * Deque.java
 * Todo: A double-ended queue or deque is a generalization of a stack and a queue 
 * that supports adding and removing items from either the front or the back of the data structure.
 */
package Assignments.HW2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	private Node first;
	private Node last;
	
	public Deque() {
		 first = null;
		 last = first;
	}
	
	public boolean isEmpty() {
		return null == first;
	}
	
	public int size() {
		Iterator<Item> dequeIterator = iterator();
		int n = 0;
		while (dequeIterator.hasNext()) {
			dequeIterator.next();
			n++;
		}
		return n;
	}
	
	public void addFirst(Item item) {
		if (null == item)
			throw new IllegalArgumentException();
		Node oldFirst = first;
		first = new Node();
		first.item = item;
		if (oldFirst != null) {
			first.next = oldFirst;
			first.precursor = null;
			oldFirst.precursor = first;
		} else {	// single node after addFirst
			first.next = null;
			first.precursor = null;
			last = first;
		}
			
	}
	
	public void addLast(Item item) {
		if (null == item)
			throw new IllegalArgumentException();
		Node oldLast = last;
		last = new Node();
		last.item = item;
		if (oldLast != null) {
			last.precursor = oldLast;
			last.next = null;
			oldLast.next = last;
		} else {	// single node after addLast
			last.precursor = null;
			last.next = null;
			first = last;
		}
			
	}
	
	public Item removeFirst() {
		if (isEmpty()) throw new NoSuchElementException();
		Item oldItem = first.item;
		first = first.next;
		first.precursor = null;
		if (isEmpty()) last = first;
		return oldItem;
	}
	
	public Item removeLast() {
		if (isEmpty()) throw new NoSuchElementException();
		Item oldItem = last.item;
		last = last.precursor;
		last.next = null;
		if (isEmpty()) first = last;
		return oldItem;
	}
	
	public Iterator<Item> iterator() {
		return new DequeIterator();
	}
	
	private class Node {
		Item item;
		Node precursor;
		Node next;
	}
	
	private class DequeIterator implements Iterator<Item> {
		private Node current = first;
		
		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public Item next() {
			if (!hasNext()) throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
	}
	
	public static void main(String[] args) {
		// unit testing (optional)
		
		/*
		 * add element tests
		 */
		Deque<Integer> d = new Deque<Integer>();
		System.out.println("IsEmpty:" + d.isEmpty());
		System.out.println("Size:" + d.size());
		d.addFirst(100);
		d.addFirst(110);
		d.addLast(120);
		System.out.println("IsEmpty:" + d.isEmpty());
		System.out.println("Size:" + d.size());
		
		/*
		 * remove element tests
		 */
		d.removeFirst();
		d.removeLast();
		System.out.println("IsEmpty:" + d.isEmpty());
		System.out.println("Size:" + d.size());
	}

}
