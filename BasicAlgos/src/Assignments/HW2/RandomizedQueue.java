/**
 * RandomizedQueue.java
 * Todo: A randomized queue is similar to a stack or queue, 
 * except that the item removed is chosen uniformly at random from items in the data structure
 */

package Assignments.HW2;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private Item[] s;
	private int num = 0;
	
	public RandomizedQueue() {
		// construct an empty randomized queue
		s = (Item[]) new Object[1];
	}
	
	public boolean isEmpty() {
		// is the randomized queue empty?
		return 0 == num;
	}
	
	public int size() {
		// return the number of items on the randomized queue
		return num;
	}
	
	public void enqueue(Item item) {
		// add the item
		if (null == item) throw new IllegalArgumentException();
		if (num == s.length) resize(2 * s.length);
		s[num++] = item;
	}
	
	public Item dequeue() {
		// remove and return a random item
		if (isEmpty()) throw new NoSuchElementException();
		int randomIndex = StdRandom.uniform(0, num);
		Item item = s[randomIndex];
		if (--num > 0) {
			for (int i = randomIndex; i < num; i++) {
				s[i] = s[i+1];
			}
		}
		if (num > 0 && num == s.length / 4) resize(s.length / 2);
		return item;
	}
	
	public Item sample()  {
		// return a random item (but do not remove it)
		if (isEmpty()) throw new NoSuchElementException();
		int randomIndex = StdRandom.uniform(0, num);
		return s[randomIndex];
	}
	
	public Iterator<Item> iterator() {
		// return an independent iterator over items in random order
		return new RandomizedQueueIterator();
	}
	
	private void resize(int capacity) {
		Item[] copy = (Item[]) new Object[capacity];
		for (int i = 0; i < num; i++)
			copy[i] = s[i];
		s = copy;
	}
	
	private class RandomizedQueueIterator implements Iterator<Item> {
		private int currentNum = num;
		private Item[] currentS = s;
		
		@Override
		public boolean hasNext() {
			return currentNum > 0;
		}

		@Override
		public Item next() {
			if (!hasNext()) throw new NoSuchElementException();
			int randomIndex = StdRandom.uniform(0, currentNum);
			Item item = currentS[randomIndex];
			if (--currentNum > 0) {
				for (int i = randomIndex; i < currentNum; i++) {
					currentS[i] = currentS[i+1];
				}
			}
			if (currentNum > 0 && currentNum == currentS.length / 4) {
				Item[] copy = (Item[]) new Object[currentS.length / 2];
				for (int i = 0; i < currentNum; i++)
					copy[i] = currentS[i];
				currentS = copy;
			}
			return item;
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	
	public static void main(String[] args) {
		// unit testing (optional)
		
//		/*
//		 * enqueue & dequeue tests 
//		 */
//		RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
//		System.out.println("IsEmpty:" + rq.isEmpty());
//		System.out.println("Size:" + rq.size());
//		rq.enqueue(100);
//		rq.enqueue(110);
//		rq.enqueue(120);
//		rq.enqueue(130);
//		rq.enqueue(140);
//		rq.enqueue(150);
//		rq.enqueue(160);
//		rq.dequeue();
//		System.out.println("IsEmpty:" + rq.isEmpty());
//		System.out.println("Size:" + rq.size());
//		
//		/*
//		 * iterator tests
//		 */
//		System.out.println("=======Iterator 1======");
//		Iterator<Integer> iter1 = rq.iterator();
//		while (iter1.hasNext())
//			System.out.println(iter1.next());
//		System.out.println("=======Iterator 2======");
//		Iterator<Integer> iter2 = rq.iterator();
//		while (iter2.hasNext())
//			System.out.println(iter2.next());
		
	}

}
