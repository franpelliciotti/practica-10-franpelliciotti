import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class WeightedListIntDigraph implements WeightedIntDigraph{
    private final int V;
    private int E;
    private List<DirectedEdge>[] adj;

    @SuppressWarnings("unchecked")
    public WeightedListIntDigraph(int V){
        if(V < 0)
            throw new IllegalArgumentException("V must be non-negative");
        this.V = V;
        E = 0;
        adj = new LinkedList[V];
        for(int i = 0; i < V; i++){
            adj[i] = new LinkedList<DirectedEdge>();
        }
    }

    public int V(){
        return V;
    }

    public int E(){
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
        adj[e.from].add(e);
        E++;
    }

    /**
    * @pre 0 <= v < V
    * @post Returns the list of edges going out from vertex v. 
    */
    public List<DirectedEdge> adj(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
        return adj[v];
    }

    public String toString(){
        String s = "";
        for(int i = 0; i < V; i++){
            s += i + ": ";
            for(DirectedEdge v : adj[i]){
                s += "[" + v.toString() + "] - ";
            }
            s += "\n";
        }
        return s;
    }

    public boolean hasNegativeCycle(){
        BellmanFord<WeightedListIntDigraph> b = new BellmanFord<>(this, 0);
        return !b.valid;
    }

    public boolean isConnected(){
        for(int i = 0; i < V; i++){
            DepthFirstSearchWeightedIntDigraph<WeightedListIntDigraph> d = 
            new DepthFirstSearchWeightedIntDigraph<WeightedListIntDigraph>(this, i);
            if(!d.isSourceConnected())
                return false;
        }
        return true;
    }

    public List<Integer> shortestPath(int v, int w){
        DijkstraDigraph d = new DijkstraDigraph(this, v);
        List<Integer> path = new LinkedList<>();
        path = d.shortestPath(w);
        return path;
    }

    public static void main(String[] args){
        WeightedListIntDigraph g1 = new WeightedListIntDigraph(8);
        DirectedEdge e1 = new DirectedEdge(0, 4, 9);        
        DirectedEdge e2 = new DirectedEdge(4, 2, 7);
        DirectedEdge e3 = new DirectedEdge(2, 1, 2);
        DirectedEdge e4 = new DirectedEdge(1, 3, 6);
        DirectedEdge e5 = new DirectedEdge(3, 5, 5);
        DirectedEdge e6 = new DirectedEdge(5, 7, 9);
        DirectedEdge e7 = new DirectedEdge(7, 6, 4);
        DirectedEdge e8 = new DirectedEdge(2, 6, 1);
        DirectedEdge e9 = new DirectedEdge(5, 2, 6);
        DirectedEdge e10 = new DirectedEdge(5, 6, 2);
        g1.addEdge(e1);
        g1.addEdge(e2);
        g1.addEdge(e3);
        g1.addEdge(e4);
        g1.addEdge(e5);
        g1.addEdge(e6);
        g1.addEdge(e7);
        g1.addEdge(e8);
        g1.addEdge(e9);
        g1.addEdge(e10);
        DijkstraDigraph d = new DijkstraDigraph(g1, 0);
        System.out.println(Arrays.toString(d.distTo));
        System.out.println(Arrays.toString(d.edgeTo));

        for(int i = 1; i < g1.V(); i++){
            System.out.println(g1.shortestPath(0, i).toString() + " Weight: " + d.distTo[i]);
        }

        /*
         * WeightedListIntDigraph g = new WeightedListIntDigraph(3);
        DirectedEdge d1 = new DirectedEdge(0, 1, 2);
        g.addEdge(d1);
        System.out.println(g.toString());
        DirectedEdge d2 = new DirectedEdge(1, 2, 3);
        g.addEdge(d2);
        System.out.println(g.toString());
        DirectedEdge d3 = new DirectedEdge(2, 1, -6);
        g.addEdge(d3);
        System.out.println(g.toString());
        System.out.println(g.hasNegativeCycle());
         */
    }   
}
