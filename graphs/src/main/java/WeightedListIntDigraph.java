import java.util.LinkedList;
import java.util.List;

public class WeightedListIntDigraph {
    private final int V;
    private int E;
    private List<DirectedEdge>[] adj;

    @SuppressWarnings("unchecked")
    public WeightedListIntDigraph(int V){
        if(V < 0)
            throw new IllegalArgumentException("V must be non-negative");
        this. V = V;
        E = 0;
        adj = new LinkedList[V];
        for(int i = 0; i < V; i++){
            adj[i] = new LinkedList<DirectedEdge>();
        }
    }

    /**
    * @pre 0 <= e.from < V && 0 <= e.to < V
    * @post Adds the directed edge e (e.from->e.weight->e.to)
    * to this edge-weighted digraph. 
    */
    public void addEdge(DirectedEdge e) {
        if(e.from < 0 || e.from >= V)
            throw new IllegalArgumentException("vertex " + e.from + " is not between 0 and " + (V-1));
        if(e.to < 0 || e.to >= V)
            throw new IllegalArgumentException("vertex " + e.to + " is not between 0 and " + (V-1));
        adj[e.from].add(e);
        E++;
    }

    /**
    * @pre 0 <= v < V
    * @post Returns the list of edges going out from vertex v. 
    */
    public List<DirectedEdge> adj(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
        return adj[v];
    }
}
