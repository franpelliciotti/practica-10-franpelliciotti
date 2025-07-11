import java.util.List;
/**
 * IntGraph represents a directed graph, where vertices are labeled
 * with integers.
 * Formally, a graph G=(V,E) consists of a set of vertices V, and a
 * relation E in VxV that describes the edges of the graph.
 */

public interface IntDigraph {
    /**
     * @post The number of vertices in this graph.
     */
    public int V();

    /**
     * @post The number of edges in this graph.
     */
    public int E();

    /**
     * @pre 0 <= v < V && 0 <= w < V
     * @post Adds the directed edge v-w to this graph.
     */
    public void addEdge(int v, int w);

    /**
     * @pre 0 <= v < V
     * @post Returns the list of vertices adjacent to vertex v
     */
    public List<Integer> adj(int v);
}
