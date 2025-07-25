import java.util.List;

public interface Graph<T extends Comparable<? super T>> {
    public int V();

    public int E();

    public void addVertex(T v);

    public boolean containsVertex(T v);

    public void addEdge(T v, T w);

    public List<Integer> adj(int v);
}
