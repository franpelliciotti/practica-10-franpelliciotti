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

        for(int i = 0; i < G.V(); i++){
            for(DirectedEdge e: G.adj(i))
                relax(e);
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

    public String pathTo(int v){
        if(v < 0 || v >= G.V())
            throw new IllegalArgumentException("vertex " + v + " isn't between 0 and " + (G.V()-1));
        String s = "";

        return s;
    }
 
}
