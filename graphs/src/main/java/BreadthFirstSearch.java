public class BreadthFirstSearch {
    
    AdjacencyListIntGraph G;
    AdjacencyListIntGraph G1;
    int[] edgeTo;
    boolean[] marked;

    /**
     * @post Build a new BreadthFirstSearch object, in order to
     * apply breadth-first search algorithm to the given non-directed
     * graph G, beggining with node s.
     */
    public BreadthFirstSearch(AdjacencyListIntGraph G, int s){
        this.G = G;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        bfs(G, s);
    }

    /**
     * @post Build a new BreadthFirstSearch object, in order to
     * apply breadth-first search algorithm to the given digraph G, 
     * beggining with node s.
     */
    public BreadthFirstSearch(AdjacencyListIntDigraph G, int s){
        this.G = G1;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        bfs(G, s);
    }

    /**
     * @pre 0 <= s < V
     * @post Apply breadth-first search algorithm to the given 
     * graph G, beggining with node s.
     */
    private void bfs(AdjacencyListIntGraph G, int s){
        if(s < 0 && s >= G.V()) throw new IllegalArgumentException("vertex" + s + "isn't between 0 and " + (G.V()-1));
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
     * @pre 0 <= s < V
     * @post Apply breadth-first search algorithm to the given 
     * digraph G, beggining with node s.
     */
    private void bfs(AdjacencyListIntDigraph G , int s){
        if(s < 0 && s >= G.V()) throw new IllegalArgumentException("vertex" + s + "isn't between 0 and " + (G.V()-1));
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
}
