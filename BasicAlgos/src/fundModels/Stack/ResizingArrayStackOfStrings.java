/**
 * ResizingArrayStackOfStrings.java
 * Todo: Implementation of a string stack by resizing array
 */
package fundModels.Stack;

public class ResizingArrayStackOfStrings {
	private String[] s;
	private int N = 0;
	
	public ResizingArrayStackOfStrings() {
		s = new String[1];
	}

	public boolean isEmpty() {
		return N == 0;
	}
	
	public void push(String item) {
		if (N == s.length) resize(2 * s.length);
		s[N++] = item;
	}
	
	public String pop() {
		String item = s[--N];
		s[N] = null;
		if (N>0 && N == s.length/4) resize(s.length/2);
		return item;
	}
	
	private void resize(int capacity) {	// resize the capacity of the array
		String[] copy = new String[capacity];
		for (int i=0; i<N; i++)
			copy[i] = s[i];
		s = copy;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
