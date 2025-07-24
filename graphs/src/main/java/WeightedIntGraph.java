import java.util.List;

public interface WeightedIntGraph {
    public int V();

    public int E();

    public void addEdge(UndirectedEdge e);

    public List<UndirectedEdge> adj(int v);
}
