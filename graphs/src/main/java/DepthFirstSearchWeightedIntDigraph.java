import java.util.LinkedList;
import java.util.List;

public class DepthFirstSearchWeightedIntDigraph<T extends WeightedIntDigraph> {
    T G;
    int s; //Source node
    int[] edgeTo;
    boolean[] marked;
    int count;

    /**
     * @post Build a new DepthFirstSearch object, in order to 
     * apply depth-first search algorithm on the given graph G,
     * beginning at its node s.
     */
    public DepthFirstSearchWeightedIntDigraph(T G, int s){
        this.G = G;
        this.s = s;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        count = 0;
        dfs(G, s);
    }

    /**
     * @pre 0 <= v < V.
     * Depth-first search algorithm.
     */
    private void dfs(T G, int v){
        count++;
        marked[v] = true;
        for(DirectedEdge w : G.adj(v)){
            if(!marked[w.to()]){
                edgeTo[w.to()] = v;
                marked[w.to()] = true;
                dfs(G, w.to());
            }
        }
    }

    /**
    * @pre 0 <= v < G.V().
    * @post Return true if there's a path from vertex s
    * to vertex v (in the last analized graph).
    */
    public boolean hasPathTo(int v){
        if(!isValidVertex(v)) 
            throw new IllegalArgumentException("vertex" + v + "isn't between 0 and " + (G.V()-1));
        return marked[v];
    }

    /**
     * @pre v is a valid vertex.
     * @post return a LinkedList that contains a path from 
     * node s, to node v. In case there isn't any path, return null.
     */
    public List<Integer> pathTo(int v){
        isValidVertex(v);
        if(!hasPathTo(v)) return null;
        List<Integer> path = new LinkedList<>();
        for(int w = v; w != s; w = edgeTo[w])
            path.addFirst(w);
        path.addFirst(s);
        return path;
    }

    /**
     * @post Return true iff vertex v is between 0 and G.V() -1. 
     */
    private boolean isValidVertex(int v){
        return v >= 0 && v < G.V();
    }

    /**
    * @post Return true iff the source vertex is connected with the rest of vertices.
    */
    public boolean isSourceConnected(){
        return count == G.V();
    }

    public static void main(String[] args){
        WeightedListIntDigraph g = new WeightedListIntDigraph(6);
        DirectedEdge e1 = new DirectedEdge(0, 2, 0);
        DirectedEdge e2 = new DirectedEdge(0, 1, 0);
        DirectedEdge e3 = new DirectedEdge(1, 5, 0);
        DirectedEdge e4 = new DirectedEdge(2, 3, 0);
        DirectedEdge e5 = new DirectedEdge(2, 0, 0);
        DirectedEdge e6 = new DirectedEdge(2, 1, 0);
        DirectedEdge e7 = new DirectedEdge(3, 4, 0);
        DirectedEdge e8 = new DirectedEdge(4, 2, 0);
        DirectedEdge e9 = new DirectedEdge(5, 3, 0);
        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);
        g.addEdge(e4);
        g.addEdge(e5);
        g.addEdge(e6);
        g.addEdge(e7);
        g.addEdge(e8);
        g.addEdge(e9);
        DepthFirstSearchWeightedIntDigraph<WeightedListIntDigraph> d = new DepthFirstSearchWeightedIntDigraph<>(g, 0);
        for(int i = 1; i < g.V(); i++){
            System.out.println(d.pathTo(i));
        }
    }
}