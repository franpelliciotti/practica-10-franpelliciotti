import java.util.List;
import java.util.LinkedList;

public class BellmanFord {
    double[] distTo;
    DirectedEdge[] edgeTo;
    WeightedListIntDigraph G;
    int s;

    public BellmanFord(WeightedListIntDigraph G, int s){
        if(s < 0 || s >= G.V())
            throw new IllegalArgumentException("vertex " + s + "isn't between 0 and " + (G.V() -1));
        
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        this.G = G;
        this.s = s;

        for(int v = 0; v < G.V(); v++){
            if(v != s){
                distTo[v] = Double.POSITIVE_INFINITY;
            }
        }
        distTo[s] = 0.0;

        for(int i = 1; i < G.V(); i++){
            for(int v = 0; v < G.V(); v++){
                for(DirectedEdge e: G.adj(v))
                    relax(e);
            }
        }

        for(int i = 0; i < G.V(); i++){
            for(DirectedEdge e: G.adj(i)){
                int v = e.from;
                int w = e.to;
                if(distTo[w] > distTo[v] + e.weight)
                    throw new IllegalStateException("There's a negative cycle in this graph.");
            }   
        }
    }

    private void relax(DirectedEdge e){
        int v = e.from;
        int w = e.to;
        if(distTo[w] > distTo[v] + e.weight){
            distTo[w] = distTo[v] + e.weight;
            edgeTo[w] = e;
        }
    }
    
    public static void main(String[] args){
        WeightedListIntDigraph g1 = new WeightedListIntDigraph(4);
        DirectedEdge ed1 = new DirectedEdge(0, 1, 3);
        DirectedEdge ed2 = new DirectedEdge(0, 2, 5);
        DirectedEdge ed3 = new DirectedEdge(2, 3, 6);
        DirectedEdge ed4 = new DirectedEdge(3, 2, -3);
        g1.addEdge(ed1);
        g1.addEdge(ed2);
        g1.addEdge(ed3);
        g1.addEdge(ed4);
        BellmanFord b1 = new BellmanFord(g1, 0);
        
        WeightedListIntDigraph g = new WeightedListIntDigraph(4);
        DirectedEdge e1 = new DirectedEdge(0, 1, 3);
        DirectedEdge e2 = new DirectedEdge(0, 2, 5);
        DirectedEdge e3 = new DirectedEdge(2, 3, 3);
        DirectedEdge e4 = new DirectedEdge(3, 2, -6);
        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);
        g.addEdge(e4);
        BellmanFord b = new BellmanFord(g, 0);
    }
}
