/**
 * FixedCapacityStackOfStrings.java
 * Todo: Implementation of a string stack by an array of fixed capacity
 */
package fundModels.Stack;

public class FixedCapacityStackOfStrings {
	private String[] s;
	private int N = 0;	// size of the stack
	
	public FixedCapacityStackOfStrings(int capacity) {
		s = new String[capacity];
	}
	
	public boolean isEmpty() {
		return N == 0;
	}
	
	public void push(String item) {
		s[N++] = item;
	}
	
	public String pop() {
		String item = s[--N];
		s[N] = null;
		return item;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
