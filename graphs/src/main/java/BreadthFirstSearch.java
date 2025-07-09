import java.util.LinkedList;
import java.util.List;

public class BreadthFirstSearch<T extends IntGraph> {
    T G;
    int s;
    int[] edgeTo;
    boolean[] marked;

    /**
     * @post Build a new BreadthFirstSearch object, in order to
     * apply breadth-first search algorithm to the given non-directed
     * graph G, beggining with node s.
     */
    public BreadthFirstSearch(T G, int s){
        this.G = G;
        this.s = s;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        bfs(G, s);
    }

    /**
     {* @pre 0 <= s < G.V().
     * Breadth-first search algorithm.
     */
    private void bfs(T G, int s){
        if(!isValidVertex(s)) 
            throw new IllegalArgumentException("vertex" + s + "isn't between 0 and " + (G.V()-1));
        Queue<Integer> queue = new Queue<>();
        marked[s] = true;
        queue.enqueue(s);
        while(!queue.isEmpty()){
            int v = queue.dequeue();
            for(int w : G.adj(v)){
                if(!marked[w]){
                    marked[w] = true;
                    edgeTo[w] = v;
                    queue.enqueue(w);
                }
            }
        }
    }

    /**
     * @post return whether a given vertex is
     * between 0 and graph's number of vertices -1.
     */
    private boolean isValidVertex(int v){
        return v >= 0 && v < G.V();
    }

    /**
     * @pre 0 <= v < G.V()
     * @post return if there's a path from vertex s to vertex v.
     */
    public boolean hasPathTo(int v){
        if(!isValidVertex(v)) 
            throw new IllegalArgumentException("vertex" + v + "isn't between 0 and " + (G.V()-1));
        return marked[v];
    }

    /**
     * @pre 0 <= v < G.V()
     * @post return a LinkedList that contains
     * a path from vertex s to vertex v. If there
     * isn't any path, return null.
     */
    public List<Integer> pathTo(int v){
        if(!hasPathTo(v)) return null;
        List<Integer> path = new LinkedList<>();
        for(int w = v; w != s; w = edgeTo[w]){
            path.addFirst(w);
        }
        path.addFirst(s);
        return path;
    }
}
