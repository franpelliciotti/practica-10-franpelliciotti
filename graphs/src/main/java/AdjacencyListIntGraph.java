import java.util.LinkedList;
import java.util.List;


public class AdjacencyListIntGraph implements IntGraph {
    private final int V;
    private int E;
    private List<Integer>[] adj;

    /**
     * @pre v >= 0
     * @post Initializes a graph with v vertices and 0 edges.
     */
    @SuppressWarnings("unchecked")
    public AdjacencyListIntGraph(int V){
        if(V < 0) throw new IllegalArgumentException("v number must be greater or equal than zero.");
        this.V = V;
        E = 0;
        adj = new LinkedList[V];
        for(int v = 0; v < V; v++){
            adj[v] = new LinkedList<Integer>();
        }
    }

    @Override
    public int V() {
        return V;
    }

    @Override
    public int E() {
        return E;
    }

    /**
     * @pre 0 <= v < V && 0 <= w < V
     * @post Adds the undirected edge v-w to this graph.
     */
    @Override
    public void addEdge(int v, int w) {
        if(!isValidVertex(v)) throw new IllegalArgumentException("vertex" + v + "isn't between 0 and " + (V - 1));
        if(!isValidVertex(w)) throw new IllegalArgumentException("vertex" + w + "isn't between 0 and " + (V - 1));
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    @Override
    public List<Integer> adj(int v) {
        if(!isValidVertex(v)) throw new IllegalArgumentException("vertex" + v + "isn't between 0 and " + (V - 1));
        return adj[v];
    }

    @Override
    public String toString(){
        String s = "";
        for(int i = 0; i < adj.length; i++){
            s += i + ": ";
            for(int j = 0; j < adj[i].size(); j++){
                s += "[" + adj[i].get(j).toString() + "] - ";
            }
            s += "//\n";
        }
        return s;
    }

    /**
     * @post Return true if vertex v is a valid vertex
     * (0 <= v < V).
     */
    private boolean isValidVertex(int v){
        return v >= 0 && v > V();
    }
}
