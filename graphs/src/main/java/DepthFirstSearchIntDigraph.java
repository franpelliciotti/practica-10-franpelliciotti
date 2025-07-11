import java.util.LinkedList;
import java.util.List;

public class DepthFirstSearchIntDigraph<T extends IntDigraph> {
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
    public DepthFirstSearchIntDigraph(T G, int s){
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
        for(int w : G.adj(v)){
            if(!marked[w]){
                edgeTo[w] = v;
                dfs(G, w);
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
        LinkedList<Integer> path = new LinkedList<>();
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
    * @post Return true iff the source node is connected with 
    * any other node in G graph.
    */
    public boolean isSourceConnected(){
        return count == G.V();
    }
}
