import java.util.List;

public interface WeightedIntDigraph {
    public int V();

    public int E();

    public void addEdge(DirectedEdge e);

    public List<DirectedEdge> adj(int v);
}
