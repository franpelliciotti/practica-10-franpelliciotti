import java.util.TreeMap;

public class AdjacencyMatrixGraph<T extends Comparable<? super T>> implements Graph<T> {
    
    private int V;
    private int E;
    private TreeMap<T, Integer> map;
    private T[] keys;
    private int[][] adj;

    public AdjacencyMatrixGraph(int V){
        if(V < 0)
            throw new IllegalArgumentException("Number of vertices must be non-negative.");
        this.V = 0;
        this.E = 0;
        adj = new int[V][V];
        map = new TreeMap<>();
        keys = (T[]) new Comparable[V];
    }

    @Override
    public int V() {
        return V;
    }

    @Override
    public int E() {
        return E;
    }

    /**
     * @post Return the node information by giving its id.
     */
    public T nameOf(int v){
        return keys[v];
    }

    /**
     * @post Return the given node's int id.
     */
    public int indexOf(T v){
        return map.get(v);
    }

    @Override
    public void addVertex(T v) {
        if(containsVertex(v))
            throw new IllegalArgumentException("Vertex already in the graph");
        int newV = V++;
        if(V >= keys.length)
            resize(adj.length*2);
        map.put(v, newV);
        keys[newV] = v;
    }

    @Override
    public boolean containsVertex(T v) {
        return map.containsKey(v);
    }

    @Override
    public void addEdge(T v, T w) {
        if(!containsVertex(v))
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
        if(!containsVertex(w))
            throw new IllegalArgumentException("vertex " + w + " is not between 0 and " + (V-1));
        E++;
        int vid = indexOf(v);
        int wid = indexOf(w);
        adj[vid][wid] = adj[wid][vid] = 1;
    }
    
    private void resize(int l){
        int[][] aux = new int[l][l];
        for(int i = 0; i < aux.length; i++){
            for(int j = 0; j < aux.length; j++){
                aux[i][j] = adj[i][j];
            }
        }
        adj = aux;
    }

    public String toString(){
        String s = "";
        for(int i = 0; i < adj.length; i++){
            for(int j = 0; j < adj[i].length; j++){
                s += adj[i][j] + " ";
            }
            s += "\n";
        }
        return s;
    }
}
