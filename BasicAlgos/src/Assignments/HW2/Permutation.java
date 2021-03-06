/**
 * Permutation.java
 * Todo: client program
 * that takes an integer k as a command-line argument
 * reads in a sequence of strings from standard input
 * prints exactly k of them, uniformly at random
 * Print each item from the sequence at most once
 */
package Assignments.HW2;

import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {

	public static void main(String[] args) {
		// test client
		int n = Integer.parseInt(args[0]);
		RandomizedQueue<String> rq = new RandomizedQueue<String>();
		while (!StdIn.isEmpty()) {
			String s = StdIn.readString();
			rq.enqueue(s);
		}
		Iterator<String> iter = rq.iterator();
		for (int i = 0; i < n; i++)
			StdOut.println(iter.next());
	}

}
