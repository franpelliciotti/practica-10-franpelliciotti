import java.util.List;
import java.util.LinkedList;

public class WeightedListIntGraph {
    private final int V;
    private int E;
    private List<UndirectedEdge>[] adj;

    @SuppressWarnings("unchecked")
    public WeightedListIntGraph(int V){
        if(V < 0)
            throw new IllegalArgumentException("V must be non-negative");
        this. V = V;
        E = 0;
        adj = new LinkedList[V];
        for(int i = 0; i < V; i++){
            adj[i] = new LinkedList<UndirectedEdge>();
        }
    }

    public int V(){
        return V;
    }

    public int E(){
        return E;
    }

    /**
    * @pre 0 <= e.from < V && 0 <= e.to < V
    * @post Add the undirected edge e this edge-weighted graph. 
    */
    public void addEdge(UndirectedEdge e) {
        if(e.v < 0 || e.v >= V)
            throw new IllegalArgumentException("vertex " + e.v + " is not between 0 and " + (V-1));
        if(e.w < 0 || e.w >= V)
            throw new IllegalArgumentException("vertex " + e.w + " is not between 0 and " + (V-1));
        UndirectedEdge rev = new UndirectedEdge(e.w, e.v, e.weight);
        adj[e.v].add(e);
        adj[e.w].add(rev);
        E++;
    }

    /**
    * @pre 0 <= v < V
    * @post Returns the list of edges going out from vertex v. 
    */
    public List<UndirectedEdge> adj(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
        return adj[v];
    }

    public String toString(){
        String s = "";
        for(int i = 0; i < V; i++){
            s += i + ": ";
            for(UndirectedEdge v : adj[i]){
                s += "[" + v.toString() + "] - ";
            }
            s += "\n";
        }
        return s;
    }
}
