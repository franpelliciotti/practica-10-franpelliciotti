import java.util.LinkedList;
import java.util.List;

public class WeightListIntDigraph {
    private final int V;
    private int E;
    private List<DirectedEdge>[] adj;

    @SuppressWarnings("unchecked")
    public WeightListIntDigraph(int V){
        if(V < 0)
            throw new IllegalArgumentException("V must be non-negative");
        this. V = V;
        E = 0;
        adj = new LinkedList[V];
        for(int i = 0; i < V; i++){
            adj[i] = new LinkedList<DirectedEdge>();
        }
    }
}
