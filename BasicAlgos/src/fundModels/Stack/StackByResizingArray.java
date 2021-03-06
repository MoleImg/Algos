package fundModels.Stack;

import java.util.Iterator;

public class StackByResizingArray<Item> implements Iterable<Item>{
	private Item[] s;
	private int N = 0;
	
	public StackByResizingArray(int capacity) {
		s = (Item[]) new Object[capacity];
	}
	
	public boolean isEmpty() {
		return N == 0;
	}
	
	public void push(Item item) {
		if (N == s.length) resize(2 * s.length);
		s[N++] = item;	
	}
	
	public Item pop() {
		if (isEmpty()) return null;
		else {
			Item item = s[--N];
			if (N>0 && N == s.length / 4) resize(s.length / 2);
			return item;
		}
	}
	
	private void resize(int capacity) {
		Item[] copy = (Item[]) new Object[capacity];
		for (int i=0; i<N; i++)
			copy[i] = s[i];
		s = copy;
	}
	
	@Override
	public Iterator<Item> iterator() {
		return new ReverseArrayIterator();
	}
	
	private class ReverseArrayIterator implements Iterator<Item> {
		private int current = N;
		
		@Override
		public boolean hasNext() {
			return current > 0;
		}

		@Override
		public Item next() {
			return s[--current];
		}
		 
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}


}
