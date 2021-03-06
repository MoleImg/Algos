/**
 * StackOfStrings.java
 * Todo: Implementation of a string stack by linkedlist
 */
package fundModels.Stack;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class StackOfStrings {
	private Node first = null;
	public StackOfStrings() {	// create an empty stack
		
	}
	
	public void push(String item) {	// insert a new string onto stack
		Node oldFirst = first;
		first= new Node();
		first.item = item;
		first.next = oldFirst;	
	}
	
	public String pop() {	// remove and return the string most recently added
		String oldItem = first.item;
		first = first.next;
		return oldItem;
	}
	
	public boolean isEmpty() {	// is the stack empty
		return first == null;
	}
	
//	public int size() {	// number of strings on the stack
//		
//	}
	
	private class Node {	// inner class of linked list node
		String item;
		Node next;
	}
	
	/*
	 * Client
	 */
	
	public static void main(String[] args) {
		StackOfStrings stack = new StackOfStrings();
		while(!StdIn.isEmpty()) {
			String s = StdIn.readString();
			if (s.equals("-")) StdOut.print(stack.pop());
			else stack.push(s);
		}
	}
}
