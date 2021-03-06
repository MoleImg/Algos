/**
 * PercolationStats.java
 * Todo: perform the Monte-Carlo experiments for finding the percolation threshold
 */
package Assignments.HW1;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	private final double[] perThresh;	// percolation threshold of all the experiments
	private static final double CONFIDENCE_95 = 1.96;	// hold the 95% confident intervals
	
	public PercolationStats(int n, int trials) {
		// perform trials independent experiments on an n-by-n grid
		if (n <= 0 || trials <= 0) throw new IllegalArgumentException();
		
		perThresh = new double[trials];
		for (int i = 0; i < trials; i++) {
			Percolation per = new Percolation(n);
			while (true) {
				int select;
				while (per.isOpen((select = StdRandom.uniform(0, n * n)) / n + 1, select % n + 1)) {
					select = StdRandom.uniform(0, n * n);
				}
				int row = select / n + 1;
				int col = select % n + 1;
				per.open(row, col);
				if (per.percolates()) {
					break;
				}
			}
				perThresh[i] = (double)(per.numberOfOpenSites()) / (n * n);
		}
		
	}
	
	public double mean() {
		// sample mean of percolation threshold
		return StdStats.mean(perThresh);
	}
	
	public double stddev() {
		// sample standard deviation of percolation threshold
		return StdStats.stddev(perThresh);
	}
	
	public double confidenceLo() {
		// low  endpoint of 95% confidence interval
		return mean() - CONFIDENCE_95 * stddev() / Math.sqrt(perThresh.length);
	}
	
	public double confidenceHi() {
		// high endpoint of 95% confidence interval
		return mean() + CONFIDENCE_95 * stddev() / Math.sqrt(perThresh.length);
	}
	
	public static void main(String args[]) {
		// test client
		int n = Integer.parseInt(args[0]);
		int trials = Integer.parseInt(args[1]);
		PercolationStats ps = new PercolationStats(n, trials);
		System.out.println("mean                    =" + ps.mean());
		System.out.println("stddev                  =" + ps.stddev());
		System.out.println("95% confidence interval = [" + ps.confidenceLo() + "," + ps.confidenceHi() + "]");
		
	   }
}
