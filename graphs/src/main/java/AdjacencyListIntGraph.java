import java.util.LinkedList;
import java.util.List;


public class AdjacencyListIntGraph implements IntGraph {
    private final int V;
    private int E;
    private List<Integer>[] adj;

    /**
     * @pre v >= 0
     * @post Initializes a graph with v vertices and 0 edges.
     */
    @SuppressWarnings("unchecked")
    public AdjacencyListIntGraph(int V){
        if(V < 0) throw new IllegalArgumentException("v number must be greater or equal than zero.");
        this.V = V;
        E = 0;
        adj = new LinkedList[V];
        for(int v = 0; v < V; v++){
            adj[v] = new LinkedList<Integer>();
        }
    }

    @Override
    public int V() {
        return V;
    }

    @Override
    public int E() {
        return E;
    }

    /**
     * @pre 0 <= v < V && 0 <= w < V
     * @post Adds the undirected edge v-w to this graph.
     */
    @Override
    public void addEdge(int v, int w) {
        if(!isValidVertex(v)) throw new IllegalArgumentException("vertex" + v + "isn't between 0 and " + (V - 1));
        if(!isValidVertex(w)) throw new IllegalArgumentException("vertex" + w + "isn't between 0 and " + (V - 1));
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    @Override
    public List<Integer> adj(int v) {
        if(!isValidVertex(v)) throw new IllegalArgumentException("vertex" + v + "isn't between 0 and " + (V - 1));
        return adj[v];
    }

    @Override
    public String toString(){
        String s = "";
        for(int i = 0; i < adj.length; i++){
            s += i + ": ";
            for(int j = 0; j < adj[i].size(); j++){
                s += "[" + adj[i].get(j).toString() + "] - ";
            }
            s += "//\n";
        }
        return s;
    }

    /**
     * @post Return true if vertex v is a valid vertex
     * (0 <= v < V).
     */
    private boolean isValidVertex(int v){
        return v >= 0 && v < V();
    }

    /**
     * Provide a depth-first search algorithm, and
     * methods based on it, such as finding paths, 
     * or determining whether a given graph is connected.
     */
    protected class DepthFirstSearch{
        boolean[] marked;
        int[] edgeTo;
        int count;
        AdjacencyListIntGraph G;
        int s; //Source vertex

		/**
		 * @post Build a new DepthFirstSearch object, in order to 
		 * apply depth-first search algorithm on the given graph G,
		 * beginning at its node s.
		 */
		public DepthFirstSearch(AdjacencyListIntGraph G, int s){
			this.G = G;
			this.s = s;
			edgeTo = new int[G.V()];
			marked = new boolean[G.V()];
			count = 0;
			dfs(G, s);
		}

		/**
		 * Depth-first search algorithm.
		 */
		private void dfs(AdjacencyListIntGraph G, int v){
			count++;
			marked[v] = true;
			for(int w : G.adj(v)){
				if(!marked[w]){
					edgeTo[w] = v;
					dfs(G, w);
				}
			}
		}

		/**
		* @pre 0 <= v < V
		* @post Return true if there's a path from vertex s
     	* to vertex v (in the last analized graph).
		*/
		public boolean hasPathTo(int v){
			if(!isValidVertex(v)) throw new IllegalArgumentException("vertex" + v + "isn't between 0 and " + (G.V-1));
			return marked[v];
		}

		/**
		 * @pre 0 <= v < V.
		 * @post return a LinkedList that contains a path from 
		 * node s, to node v (in last analized graph). 
		 * In case there isn't any path, return null.
		 */
		public List<Integer> pathTo(int v){
			isValidVertex(v);
			if(!hasPathTo(v)) return null;
			LinkedList<Integer> path = new LinkedList<>();
			for(int w = v; w != s; w = edgeTo[w])
				path.addFirst(w);
			path.addFirst(s);
			return path;
		}

		/**
		 * @post Return true iff vertex v is between 0 
		 * and G's number of vertices -1. 
		 */
		private boolean isValidVertex(int v){
			return v >= 0 && v < G.V();
		}

		/**
     	* @post Return true iff the last analized graph is a connected graph.
     	*/
		public boolean isConnected(){
			return count == G.V();
		}
    }
}
