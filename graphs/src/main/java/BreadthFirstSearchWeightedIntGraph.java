import java.util.LinkedList;
import java.util.List;

public class BreadthFirstSearchWeightedIntGraph<T extends WeightedIntGraph> {
    T G;
    int s;
    int[] edgeTo;
    boolean[] marked;

    /**
     * @post Build a new BreadthFirstSearch object, in order to
     * apply breadth-first search algorithm to the given non-directed
     * graph G, beggining with node s.
     */
    public BreadthFirstSearchWeightedIntGraph(T G, int s){
        this.G = G;
        this.s = s;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        bfs(G, s);
    }

    /**
     * @pre 0 <= s < G.V().
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
            for(Edge w : G.adj(v)){
                if(!marked[w.to()]){
                    marked[w.to()] = true;
                    edgeTo[w.to()] = v;
                    queue.enqueue(w.to());
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
        BreadthFirstSearchWeightedIntGraph<WeightedListIntGraph> b = new BreadthFirstSearchWeightedIntGraph<WeightedListIntGraph>(g, 0);
        for(int i = 1; i < g.V(); i++){
            System.out.println(b.pathTo(i).toString());
        }
    }
}
