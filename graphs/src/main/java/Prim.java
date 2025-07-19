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

    @Override
    public String toString(){
        String s = "Minimum Spanning Tree:\n";
        List<UndirectedEdge> mst = edges();
        s += mst.toString() + "\nWeight: " + weight();
        return s;
    }

    public static void main(String[] args){
        WeightedListIntGraph g = new WeightedListIntGraph(8);
        UndirectedEdge e1 = new UndirectedEdge(0, 4, 9);
        UndirectedEdge e2 = new UndirectedEdge(4, 2, 7);
        UndirectedEdge e3 = new UndirectedEdge(2, 1, 2);
        UndirectedEdge e4 = new UndirectedEdge(1, 3, 6);
        UndirectedEdge e5 = new UndirectedEdge(3, 5, 5);
        UndirectedEdge e6 = new UndirectedEdge(5, 7, 9);
        UndirectedEdge e7 = new UndirectedEdge(7, 6, 4);
        UndirectedEdge e8 = new UndirectedEdge(6, 2, 2);
        UndirectedEdge e9 = new UndirectedEdge(2, 5, 6);
        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);
        g.addEdge(e4);
        g.addEdge(e5);
        g.addEdge(e6);
        g.addEdge(e7);
        g.addEdge(e8);
        g.addEdge(e9);
        Prim p = new Prim(g, 0);
        System.out.println(p.toString());
    }
}
