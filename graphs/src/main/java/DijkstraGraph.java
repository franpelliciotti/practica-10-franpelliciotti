import java.util.List;
import java.util.LinkedList;

public class DijkstraGraph {
    double[] distTo;
    UndirectedEdge[] edgeTo;
    IndexMinPQ<Double> pq;

    public DijkstraGraph(WeightedListIntGraph G, int s){
        if(s < 0 || s >= G.V())
            throw new IllegalArgumentException("vertex " + s + "isn't between 0 and " + (G.V() -1));
        
        distTo = new double[G.V()];
        edgeTo = new UndirectedEdge[G.V()];
        pq = new IndexMinPQ<>(G.V());
        for(int v = 0; v < G.V(); v++){
            if(v != s){
                distTo[v] = Double.POSITIVE_INFINITY;
                pq.insert(v, distTo[v]);
            }
        }
        distTo[s] = 0.0;
        pq.insert(s, distTo[s]);

        while(!pq.isEmpty()){
            int v = pq.delMin();
            for(UndirectedEdge e: G.adj(v))
                relax(e);
        }
    }

    private void relax(UndirectedEdge e){
        int v = e.from;
        int w = e.to;
        if(distTo[w] > distTo[v] + e.weight){
            distTo[w] = distTo[v] + e.weight;
            edgeTo[w] = e;
            pq.decreaseKey(w, distTo[w]);
        }
    }

    private boolean hasPathto(int w){
        return edgeTo[w] != null;
    }

    public List<Integer> shortestPath(int v, int w){
        if(!hasPathto(w)) return null;
        List<Integer> path = new LinkedList<>();
        for(int i = w; i != v; w = edgeTo[w].from()){
            path.addFirst(w);
        }
        path.addFirst(v);
        return path;
    }
}
