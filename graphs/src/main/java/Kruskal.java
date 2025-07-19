import java.util.List;
import java.util.LinkedList;
import java.util.Arrays;

public class Kruskal {
    UF set;
    WeightedListIntGraph G;
    UndirectedEdge[] edges;
    double weight;
    List<UndirectedEdge> mst;

    public Kruskal(WeightedListIntGraph G){
        set = new UF(G.V());
        this.G = G;
        edges = new UndirectedEdge[G.E()];
        mst = new LinkedList<>();

        int t = 0;
        for(UndirectedEdge e : G.edges()){
            edges[t++] = e;
        }
        Arrays.sort(edges);

        for(int i = 0; i < G.E() && mst.size() < G.V() - 1; i++){
            UndirectedEdge e = edges[i];
            int v = e.v;
            int w = e.other(v);

            if(set.find(v) != set.find(w)){
                set.union(v, w);
                mst.add(e);
                weight += e.weight;
            }
        }
    }

    public List<UndirectedEdge> edges() {
        return mst;
    }

    public double weight(){
        return weight;
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
        Kruskal k = new Kruskal(g);
        System.out.println(k.edges().toString());
    }
}
