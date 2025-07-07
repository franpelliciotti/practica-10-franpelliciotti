import java.util.LinkedList;
import java.util.List;

public class AdjacencyListIntDigraph implements IntDigraph {
    private final int V;
    private int E;
    private List<Integer>[] adj;

    /**
     * @pre v >= 0
     * @post Initializes a graph with v vertices and 0 edges.
     */
    @SuppressWarnings("unchecked")
    public AdjacencyListIntDigraph(int V){
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
        if(v < 0 || v >= V) throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
        if(w < 0 || w >= V) throw new IllegalArgumentException("vertex " + w + " is not between 0 and " + (V-1));
        adj[v].add(w);
        E++;
    }

    @Override
    public List<Integer> adj(int v) {
        if(v < 0 || v >= V) throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
        return adj[v];
    }

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

    public static void main(String[] args){
        AdjacencyListIntDigraph g = new AdjacencyListIntDigraph(6);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        g.addEdge(4, 5);

        System.out.println(g.toString());
        System.out.println(g.adj(0).toString());
        System.out.println(g.adj(1).toString());
        System.out.println(g.adj(2).toString());
        System.out.println(g.adj(3).toString());
        System.out.println(g.adj(4).toString());
        System.out.println(g.adj(5).toString());
    }

	/**
     * Provide a depth-first search algorithm, and
     * methods based on it, such as finding paths, 
     * or determining whether a given graph is connected.
     */
    protected class DepthFirstSearch {
        boolean[] marked;
        int[] edgeTo;
        int count;
        AdjacencyListIntDigraph G;
        int s; //Source vertex

		/**
		 * @post Build a new DepthFirstSearch object, in order to 
		 * apply depth-first search algorithm on the given digraph G,
		 * beginning at its node s.
		 */
		public DepthFirstSearch(AdjacencyListIntDigraph G, int s){
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
		private void dfs(AdjacencyListIntDigraph G, int v){
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
		 * @pre v is a valid vertex.
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

	/**
     * Provide a depth-first search algorithm, and
     * methods for finding shortest paths .
     */
	public class BreadthFirstSearch {
		AdjacencyListIntDigraph G;
		int s; //Source vertex.
		int[] edgeTo;
		boolean[] marked;

		/**
		 * @post Build a new BreadthFirstSearch object, in order to
		 * apply breadth-first search algorithm to the given digraph G, 
		 * beggining with node s.
		 */
		public BreadthFirstSearch(AdjacencyListIntDigraph G, int s){
			this.G = G;
			this.s = s;
			edgeTo = new int[G.V()];
			marked = new boolean[G.V()];
			bfs(G, s);
		}

		/**
		 * @pre 0 <= s < V
		 * @post Breadth-first search algorithm.
		 */
		private void bfs(AdjacencyListIntDigraph G, int s){
			if(!isValidVertex(s)) throw new IllegalArgumentException("vertex" + s + "isn't between 0 and " + (G.V()-1));
			Queue<Integer> queue = new Queue<>();
			marked[s] = true;
			queue.enqueue(s);
			while(!queue.isEmpty()){
				int v = queue.dequeue();
				for(int w : G.adj(v)){
					if(!marked[w]){
						marked[w] = true;
						edgeTo[w] = v;
						queue.enqueue(w);
					}
				}
			}
		}

		/**
		 * @post return whether a given vertex is
		 * between 0 and graph's number of vertices -1.
		 */
		private boolean isValidVertex(int v){
			return v >= 0 && v < G.V();
		}

		/**
		 * @pre 0 <= v < V
		 * @post return if there's a path from vertex s
		 * to vertex v.
		 */
		public boolean hasPathTo(int v){
			if(!isValidVertex(v)) throw new IllegalArgumentException("vertex" + v + "isn't between 0 and " + (G.V()-1));
			return marked[v];
		}

		/**
		 * @pre 0 <= v < V
		 * @post return a LinkedList that contains
		 * a path from vertex s to vertex v. If there
		 * isn't any path, return null.
		 */
		public List<Integer> pathTo(int v){
			if(!hasPathTo(v)) return null;
			List<Integer> path = new LinkedList<>();
			for(int w = v; w != s; w = edgeTo[w]){
				path.addFirst(w);
			}
			path.addFirst(s);
			return path;
		}
	}
}
