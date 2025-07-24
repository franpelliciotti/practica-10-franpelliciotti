import java.util.List;
import java.util.LinkedList;

public class DepthFirstSearchWeightedIntGraph<T extends WeightedIntGraph> {
    T G;
    int s;
    boolean[] marked;
    int[] edgeTo;
    int count;

    public DepthFirstSearchWeightedIntGraph(T G, int s){
        this.G = G;
        this.s = s;
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        dfs(G, s);
    }

    private void dfs(T G, int v){
        count++;
        marked[v] = true;
        for(UndirectedEdge e : G.adj(v)){
            if(!marked[e.to()]){
                marked[e.to()] = true;
                edgeTo[e.to()] = v;
                dfs(G, e.to());
            }
        }
    }

    public boolean hasPathTo(int v){
        return marked[v];
    }

    public List<Integer> pathTo(int v){
        if(!hasPathTo(v)) return null;
        List<Integer> path = new LinkedList<>();
        for(int w = v; w != s; w = edgeTo[w]){
            path.addFirst(w);
        }
        path.addFirst(s);
        return path;
    }

    public static void main(String[] args){
        WeightedListIntGraph g = new WeightedListIntGraph(6);
        UndirectedEdge e1 = new UndirectedEdge(0, 1, 0);
        UndirectedEdge e2 = new UndirectedEdge(0, 2, 0);
        UndirectedEdge e3 = new UndirectedEdge(1, 3, 0);
        UndirectedEdge e4 = new UndirectedEdge(1, 4, 0);
        UndirectedEdge e5 = new UndirectedEdge(2, 5, 0);
        UndirectedEdge e6 = new UndirectedEdge(4, 5, 0);
        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);
        g.addEdge(e4);
        g.addEdge(e5);
        g.addEdge(e6);
        DepthFirstSearchWeightedIntGraph<WeightedListIntGraph> d = new DepthFirstSearchWeightedIntGraph<WeightedListIntGraph>(g, 0);
        for(int i = 1; i < g.V(); i++){
            System.out.println(d.pathTo(i));
        }
    }
}
