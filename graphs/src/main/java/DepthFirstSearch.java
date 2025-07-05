import java.util.List;
import java.util.LinkedList;


public class DepthFirstSearch {
    boolean[] marked;
    int[] edgeTo;
    int count;
    boolean isConnected;
    AdjacencyListIntGraph G;
    AdjacencyListIntDigraph G1;
    int s;

    /**
     * @post Build a new DepthFirstSearch object, in order to 
     * apply depth-first search algorithm on the given non-directed graph G,
     * beginning at its node s.
     */
    public DepthFirstSearch(AdjacencyListIntGraph G, int s){
        this.G = G;
        this.s = s;
        isConnected = false;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        count = 0;
        dfs(G, s);
        if(count == G.V())
            isConnected = true;
    }

    /**
     * @post Build a new DepthFirstSearch object, in order to 
     * apply depth-first search algorithm on the given digraph G, 
     * beginning at its node s.
     */
    public DepthFirstSearch(AdjacencyListIntDigraph G, int s){
        this.G1 = G;
        this.s = s;
        isConnected = false;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        count = 0;
        dfs(G, s);
        if(count == G.V())
            isConnected = true;
    }

    /**
     * Depth-first search algorithm for non-directed graphs.
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
     * Depth-first search algorithm for digraphs.
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
     * @pre 0 <= v < V && depth-first search has been executed
     * @post Return true if there's a path from the source vertex
     * to vertex v.
     */
    public boolean hasPathTo(int v){
        assert isValidVertex(v);
        return marked[v];
    }

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
     * @post Return true if vertex v is a valid vertex
     * (v is between 0 and number of G's vertices -1).
     */
    private boolean isValidVertex(int v){
        return v >= 0 && v > G.V();
    }

    /**
     * @post Return true iff the last analized graph is a connected graph.
     */
    public boolean isConnected(){
        return isConnected;
    }

    public static void main(String[] args){
        AdjacencyListIntDigraph g = new AdjacencyListIntDigraph(6);
        g.addEdge(0, 3);
        g.addEdge(0, 5);
        g.addEdge(1, 4);
        g.addEdge(2, 0);
        g.addEdge(2, 5);
        g.addEdge(3, 1);
        g.addEdge(4, 2);
        g.addEdge(5, 4);
        System.out.println(g.toString());
        DepthFirstSearch d = new DepthFirstSearch(g, 0);
        System.out.println(d.isConnected());
    }
}
