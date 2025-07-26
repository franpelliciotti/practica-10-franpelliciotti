import java.util.List;
import java.util.LinkedList;

public class BellmanFord<T extends WeightedIntDigraph> {
    double[] distTo;
    DirectedEdge[] edgeTo;
    T G;
    boolean valid;

    public BellmanFord(T G, int s){
        if(s < 0 || s >= G.V())
            throw new IllegalArgumentException("vertex " + s + "isn't between 0 and " + (G.V() -1));
        
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        this.G = G;

        for(int v = 0; v < G.V(); v++){
            if(v != s){
                distTo[v] = Double.POSITIVE_INFINITY;
            }
        }
        distTo[s] = 0.0;
         
        valid = bellmanFord();
    }

    private boolean bellmanFord(){
        for(int i = 1; i < G.V(); i++){
            for(int v = 0; v < G.V(); v++){
                for(DirectedEdge e : G.adj(v)){
                    relax(e);
                }
            }
        }
        for(int i = 0; i < G.V(); i++){
            for(DirectedEdge e : G.adj(i)){
                int v = e.from;
                int w = e.to;
                if(distTo[w] > distTo[v] + e.weight)
                    return false;
            }
        }
        return true;
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
        BellmanFord<WeightedListIntDigraph> b1 = new BellmanFord<>(g1, 0);
        System.out.println(b1.valid);

        WeightedMatrixIntDigraph gM = new WeightedMatrixIntDigraph(4);
        gM.addEdge(ed1);
        gM.addEdge(ed2);
        gM.addEdge(ed3);
        gM.addEdge(ed4);
        BellmanFord<WeightedMatrixIntDigraph> b2 = new BellmanFord<>(gM, 0);
        System.out.println(b2.valid);

        WeightedListIntDigraph g = new WeightedListIntDigraph(4);
        DirectedEdge e1 = new DirectedEdge(0, 1, 3);
        DirectedEdge e2 = new DirectedEdge(0, 2, 5);
        DirectedEdge e3 = new DirectedEdge(2, 3, 3);
        DirectedEdge e4 = new DirectedEdge(3, 2, -6);
        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);
        g.addEdge(e4);
        BellmanFord<WeightedListIntDigraph> b = new BellmanFord<>(g, 0);
        System.out.println(b.valid);

        WeightedMatrixIntDigraph gM1 = new WeightedMatrixIntDigraph(4);
        gM1.addEdge(e1);
        gM1.addEdge(e2);
        gM1.addEdge(e3);
        gM1.addEdge(e4);
        BellmanFord<WeightedMatrixIntDigraph> bM = new BellmanFord<>(gM1, 0);
        System.out.println(bM.valid);
    }
}
