import java.util.List;
import java.util.HashSet;
import java.util.LinkedList;

public class WeightedListIntGraph {
    private final int V;
    private int E;
    private List<UndirectedEdge>[] adj;
    private HashSet<UndirectedEdge> edges;

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
        edges = new HashSet<>();
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
        edges.add(e);
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

    public HashSet<UndirectedEdge> edges(){
        return edges;
    }

    public static void main(String[] args){
        WeightedListIntGraph g = new WeightedListIntGraph(8);
        UndirectedEdge e1 = new UndirectedEdge(0, 4, 9);        
        UndirectedEdge e2 = new UndirectedEdge(4, 2, 7);
        UndirectedEdge e3 = new UndirectedEdge(2, 1, 2);
        UndirectedEdge e4 = new UndirectedEdge(1, 3, 6);
        UndirectedEdge e5 = new UndirectedEdge(3, 5, 5);
        UndirectedEdge e6 = new UndirectedEdge(5, 7, 9);
        UndirectedEdge e7 = new UndirectedEdge(7, 6, 4);
        UndirectedEdge e8 = new UndirectedEdge(6, 2, 2);
        UndirectedEdge e9 = new UndirectedEdge(2, 5, 6);
        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);
        g.addEdge(e4);
        g.addEdge(e5);
        g.addEdge(e6);
        g.addEdge(e7);
        g.addEdge(e8);
        g.addEdge(e9);
        System.out.println(g.edges().toString());
    }
}
