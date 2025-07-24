import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class WeightedMatrixIntDigraph implements WeightedIntDigraph {
    private final int V;
    private int E;
    private DirectedEdge[][] adj;
    private HashSet<DirectedEdge> edges;

    public WeightedMatrixIntDigraph(int V){
        if(V < 0)
            throw new IllegalArgumentException("V must be non-negative");
        this. V = V;
        this.E = 0;
        adj = new DirectedEdge[V][V];
        edges = new HashSet<>();
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
    * @pre 0 <= e.from < V && 0 <= e.to < V
    * @post Adds the directed edge e (e.from->e.weight->e.to)
    * to this edge-weighted digraph. 
    */
    public void addEdge(DirectedEdge e) {
        if(e.from < 0 || e.from >= V)
            throw new IllegalArgumentException("vertex " + e.from + " is not between 0 and " + (V-1));
        if(e.to < 0 || e.to >= V)
            throw new IllegalArgumentException("vertex " + e.to + " is not between 0 and " + (V-1));
        adj[e.from][e.to] = e;
        edges.add(e);
        E++;
    }

    /**
    * @pre 0 <= v < V
    * @post Returns the list of edges going out from vertex v. 
    */
    public List<DirectedEdge> adj(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
        List<DirectedEdge> adjList = new LinkedList<>();
        for(int i = 0; i < adj.length; i++){
            if((adj[v][i]) != null)
                adjList.add(adj[v][i]);
        }
        return adjList;
    }

    public String toString(){
        String s = "";
        for(int i = 0; i < adj.length; i++){
            for(int j = 0; j < adj[i].length; j++){
                if(adj[i][j] != null)
                    s += adj[i][j].weight + "  ";
                else
                    s += adj[i][j] + "  ";
            }
            s += "\n";
        }
        return s;
    }

    public HashSet<DirectedEdge> edges(){
        return edges;
    }

    public static void main(String[] args){
        WeightedMatrixIntDigraph g = new WeightedMatrixIntDigraph(5);
        DirectedEdge e1 = new DirectedEdge(0, 1, 2.0);
        DirectedEdge e2 = new DirectedEdge(1, 2, 1.5);
        DirectedEdge e3 = new DirectedEdge(1, 3, 0.3);
        DirectedEdge e4 = new DirectedEdge(0, 3, 7.1);
        DirectedEdge e5 = new DirectedEdge(3, 0, 4.2);
        DirectedEdge e6 = new DirectedEdge(3, 2, 8.0);
        DirectedEdge e7 = new DirectedEdge(4, 2, 3.7);
        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);
        g.addEdge(e4);
        g.addEdge(e5);
        g.addEdge(e6);
        g.addEdge(e7);
        System.out.println(g.toString());
    }
}
