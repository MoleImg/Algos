package fundModels;

public class QuickFindUF {
	private int[] id;
	
	public QuickFindUF(int N){
		// initialization of the component set
		id = new int[N];
		for(int i:id){
			id[i] = i;
		}
	}
	
	public void union(int p, int q){
		/**
		 * Union two elements
		 */
		int pid = id[p]; int qid = id[q];
		for(int i=0; i<id.length; i++){
			if(id[i] == pid) id[i] = qid;
		}
		
	}
	
	public boolean connected(int p, int q){
		/**
		 * Check if two elements are connected
		 */
		return (id[p] == id[q]);
	}
}
