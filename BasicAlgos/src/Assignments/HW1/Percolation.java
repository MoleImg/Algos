/**
 * Percolations.java
 * Todo: operations for sites
 */
package Assignments.HW1;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private final boolean[][] site;	// all the sites
	private int openNum;	// number of open sites
	private final int size;
	private final WeightedQuickUnionUF wuf;
	public Percolation(int n) {                
		// create n-by-n grid, with all sites blocked
		if (n <= 0) throw new IllegalArgumentException();
		
		site = new boolean[n][n];	
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				site[i][j] = false;	// initialization: all the sites are blocked
			}
		}
		wuf = new WeightedQuickUnionUF(n * n + 2);
		size = n;
		openNum = 0;
		// virtual sites
		for(int i = 0; i < size; i++) {
			wuf.union(size * size, i);	// top
			wuf.union(size * size + 1, size * size - 1 - i);	// bottom
		}
	   }
	
	public void open(int row, int col) {
		   // open site (row, col) if it is not open already
		if ((row-1) < 0 || (row-1) >= size || (col-1) < 0 || (col-1) >= size) 
			throw new IllegalArgumentException();
		else {
			// open the chosen site
			site[row-1][col-1] = true;
			openNum++;
			if (size == 1) return;
			// then check if there are adjacent open sites
			// up
			if ((row-1) > 0) {
				if (isOpen(row - 1, col)) wuf.union(size * (row - 1) + (col - 1), size * (row - 2) + (col - 1));
			}
			//down
			if ((row+1)<=size) {
				if (isOpen(row + 1, col)) wuf.union(size * (row - 1) + (col - 1), size * (row) + (col - 1));
			}
			//left
			if ((col-1) > 0) {
				if (isOpen(row, col - 1)) wuf.union(size * (row - 1) + (col - 1), size * (row - 1) + col - 2);
			}
			//right
			if ((col + 1) <= size) {
				if (isOpen(row, col + 1)) wuf.union(size * (row - 1) + (col - 1), size * (row - 1) + col);
			}
		}
	   }
	
	public boolean isOpen(int row, int col) {
		   // is site (row, col) open?
		if ((row - 1) < 0 || (row - 1) >= size || (col - 1) < 0 || (col - 1) >= size) 
			throw new IllegalArgumentException();
		return (site[row - 1][col - 1] == true);
	   }
	
	public boolean isFull(int row, int col) {
		// is site (row, col) full?
		// A full site is an open site that can be connected to an open site in the top row 
		// via a chain of neighboring (left, right, up, down) open site
		if ((row - 1) < 0 || (row - 1) >= size || (col - 1) < 0 || (col - 1) >= size) 
			throw new IllegalArgumentException();
		if (isOpen(row, col))
			return wuf.connected(size * (row - 1) + (col - 1), size * size);
		else
			return false;
	   }
	
	public int numberOfOpenSites() {
		   // number of open sites
		   return openNum;
	   }
	
	public boolean percolates() {
		// does the system percolate?
		if (size == 1) {
			if (isOpen(1, 1))
				return true;
			else
				return false;
		} else {
			return wuf.connected(size * size, size * size + 1);
		}
		
	   }
	
	public static void main(String[] args) {
//		   Percolation pc = new Percolation(6);
//		   System.out.println("Open num:"+pc.numberOfOpenSites());
////		   System.out.println("Is percolated:" + pc.percolates());
////		   System.out.println("isOpen(1,1):" + pc.isOpen(1, 1));
////		   System.out.println("isFull(1,1):" + pc.isFull(1, 1));
//		   pc.open(1, 6);
//		   System.out.println("Open num:"+pc.numberOfOpenSites());
////		   System.out.println("Is percolated:" + pc.percolates());
//		   System.out.println("isOpen(1,6):" + pc.isOpen(1, 6));
//		   System.out.println("isFull(1,6):" + pc.isFull(1, 6));
	   }
}
