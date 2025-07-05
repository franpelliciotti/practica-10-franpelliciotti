import java.util.LinkedList;
import java.util.List;

public class AdjacencyListIntDigraph implements IntDigraph {
    private final int V;
    private int E;
    private List<Integer>[] adj;

    /**
     * @pre v >= 0
     * @post Initializes a graph with v vertices and 0 edges.
     */
    @SuppressWarnings("unchecked")
    public AdjacencyListIntDigraph(int V){
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
        if(v < 0 || v >= V) throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
        if(w < 0 || w >= V) throw new IllegalArgumentException("vertex " + w + " is not between 0 and " + (V-1));
        adj[v].add(w);
        E++;
    }

    @Override
    public List<Integer> adj(int v) {
        if(v < 0 || v >= V) throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
        return adj[v];
    }

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

    public static void main(String[] args){
        AdjacencyListIntDigraph g = new AdjacencyListIntDigraph(6);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        g.addEdge(4, 5);

        System.out.println(g.toString());
        System.out.println(g.adj(0).toString());
        System.out.println(g.adj(1).toString());
        System.out.println(g.adj(2).toString());
        System.out.println(g.adj(3).toString());
        System.out.println(g.adj(4).toString());
        System.out.println(g.adj(5).toString());
    }
}
