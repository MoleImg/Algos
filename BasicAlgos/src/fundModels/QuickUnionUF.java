package fundModels;

public class QuickUnionUF {
	private int[] id; // father node
	
	public QuickUnionUF(int N){
		// initialization of the component set
		id = new int[N];
		for(int i:id){
			id[i] = i;
		}
	}
	
	private int findRoot(int i){
		/**
		 * Find the root node of the element
		 */
		while(i != id[i]) i = id[i];
		return i;
	}
	public void union(int p, int q){
		/**
		 * Union two elements
		 */
		int rootP = findRoot(p);
		int rootQ = findRoot(q);
		id[rootP] = rootQ;
	}
	
	public boolean connected(int p, int q){
		/**
		 * Check if two elements are connected
		 */
		return (findRoot(p) == findRoot(q));
	}
}
