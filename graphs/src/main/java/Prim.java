import java.util.List;
import java.util.LinkedList;

public class Prim {
    WeightedListIntGraph G;
    IndexMinPQ<Double> pq;
    double[] distTo;
    UndirectedEdge[] edgeTo;
    boolean[] marked;

    public Prim(WeightedListIntGraph G, int s){
        if(s < 0 || s >= G.V())
            throw new IllegalArgumentException("vertex " + s + "isn't between 0 and " + (G.V()-1));
        this.G = G;
        pq = new IndexMinPQ<>(G.V());
        distTo = new double[G.V()];
        edgeTo = new UndirectedEdge[G.V()];
        marked = new boolean[G.V()];
        for(int v = 0; v < G.V(); v++){
            if(v != s){
                pq.insert(v, Double.POSITIVE_INFINITY);
                distTo[v] = Double.POSITIVE_INFINITY;
            }
        }
        distTo[s] = 0.0;
        pq.insert(s, distTo[s]);

        while(!pq.isEmpty()){
            int v = pq.delMin();
            marked[v] = true;
            for(UndirectedEdge e : G.adj(v)){
                int w = e.other(v);
                if(marked[w]) continue;
                if(e.weight < distTo[w]){
                    distTo[w] = e.weight;
                    edgeTo[w] = e;
                    pq.decreaseKey(w, distTo[w]);
                }
            }
        }
    }

    public List<UndirectedEdge> edges(){
        List<UndirectedEdge> mst = new LinkedList<>();
        for(int v = 0; v < edgeTo.length; v++){
            UndirectedEdge e = edgeTo[v];
            if(e != null)
                mst.add(e);
        }
        return mst;
    }

    public double weight(){
        double weight = 0.0;
        for(UndirectedEdge e : edges())
            weight += e.weight;
        return weight;
    }
}
