import java.util.LinkedList;
import java.util.List;

public class AdjacencyMatrixIntDigraph implements IntDigraph {
    private final int V;
    private int E;
    private int[][] adj;

    /**
     * @pre v >= 0
     * @post Initializes a graph with v vertices and 0 edges.
     */
    public AdjacencyMatrixIntDigraph(int V){
        if(V < 0) throw new IllegalArgumentException("v number must be greater or equal than zero.");
        this.V = V;
        E = 0;
        adj = new int[V][V];
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    /**
     * @pre 0 <= v < V && 0 <= w < V
     * @post Adds the undirected edge v-w to this graph.
     */
    public void addEdge(int v, int w) {
        if(v < 0 || v >= V) throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
        if(w < 0 || w >= V) throw new IllegalArgumentException("vertex " + w + " is not between 0 and " + (V-1));
        E++;
        adj[v][w] = 1;
    }

    public List<Integer> adj(int v) {
        if(v < 0 || v >= V) throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
        List<Integer> adjList = new LinkedList<>();
        for(int i = 0; i < adj.length; i++){
            if(adj[v][i] == 1)
                adjList.add(i);
        }
        return adjList;
    }

    public String toString(){
        String s = "";
        for(int i = 0; i < adj.length; i++){
            for(int j = 0; j < adj[i].length; j++){
                s += adj[i][j] + " ";
            }
            s += "\n";
        }
        return s;
    }

    public static void main(String[] args){
        AdjacencyMatrixIntDigraph g = new AdjacencyMatrixIntDigraph(6);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(0, 3);
        g.addEdge(0, 4);
        g.addEdge(0, 5);

        System.out.println(g.toString());
        System.out.println(g.adj(0).toString());
        System.out.println(g.adj(1).toString());
        System.out.println(g.adj(2).toString());
        System.out.println(g.adj(3).toString());
        System.out.println(g.adj(4).toString());
        System.out.println(g.adj(5).toString());
    }
}
