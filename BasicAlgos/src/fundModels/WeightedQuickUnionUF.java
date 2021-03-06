package fundModels;

public class WeightedQuickUnionUF {
	private int[] id;	// father node
	private int[] sz;	// number of objects in the tree with single root
	
	public WeightedQuickUnionUF(int N){
		id = new int[N];
		sz = new int[N];
		for(int i:id){
			id[i] = i;
			sz[i] = 1;
		}
	}
	
	private int findRoot(int i){
		while(i != id[i]) {
			id[i] = id[id[i]];
			i = id[i];
		}
		return i;
	}
	
	public void union(int p, int q){
		int rootP = findRoot(p);
		int rootQ = findRoot(q);
		if (rootP == rootQ) return;
		if(sz[rootP] < sz[rootQ]) {
			id[rootP] = rootQ; sz[rootQ] += sz[rootP];
			}
		else	{id[rootQ] = rootP; sz[rootP] += sz[rootQ];}
	}
	
	public boolean connected(int p, int q){
		return (findRoot(p) == findRoot(q));
	}
	
	
}
