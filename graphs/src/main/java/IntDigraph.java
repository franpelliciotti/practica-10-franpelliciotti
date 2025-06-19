package src.main.java;
import java.util.List;

public interface IntDigraph {
    /**
     * @return el número de vértices del grafo.
     */
    public int V();

    /**
     * @return el número de aristas del grafo.
     */
    public int E();

    /**
     * @param v nodo del que sale la nueva arista.
     * @param w nodo al que llega la nueva arista.
     */
    public void addEdge(int v, int w);

    /**
     * 
     * @param v
     * @return
     */
    public List<Integer> adj(int v);
}
