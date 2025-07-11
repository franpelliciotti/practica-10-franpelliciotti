import java.util.List;
import java.util.LinkedList;
import java.util.TreeMap;

public class WeightedListDigraph<T extends Comparable<? super T>> {
    private int V;
    private int E;
    private TreeMap<T, Integer> map;
    private T[] keys;
    private List<DirectedEdge>[] adj;

    public WeightedListDigraph(int V){
        if(V < 0)
            throw new IllegalArgumentException("Number of vertices must be non-negative.");
        this.V = 0;
        this.E = 0;
        adj = new LinkedList[V];
        map = new TreeMap<>();
        keys = (T[]) new Comparable[V];
    }

    public int V() {
        return V;
    }

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

    public void addVertex(T v) {
        if(containsVertex(v))
            throw new IllegalArgumentException("Vertex already in the graph");
        int vid = V++;
        if(V >= keys.length)
            //resize(adj.length*2);
            throw new IllegalStateException("There's no more space for any other vertex");
        map.put(v, vid);
        adj[vid] = new LinkedList<>();
        keys[vid] = v;
    }

    public boolean containsVertex(T v) {
        return map.containsKey(v);
    }

    public void addEdge(DirectedEdge e) {
        if(e.from < 0 || e.from >= V)
            throw new IllegalArgumentException("vertex " + e.from + " is not between 0 and " + (V-1));
        if(e.to < 0 || e.to >= V)
            throw new IllegalArgumentException("vertex " + e.to + " is not between 0 and " + (V-1));
        E++;
        adj[e.from].add(e);
    }
    
    /*private void resize(int l){
        T[] auxT = (T[]) new Comparable[l];
        List<DirectedEdge>[] auxInt = new LinkedList[l];
        for(int i = 0; i < auxT.length; i++){
            auxT[i] = keys[i];
            auxInt[i] = adj[i];
        }
        keys = auxT;
        adj = auxInt;
    } */

    public String toString(){
        String s = "";
        for(int i = 0; i < V; i++){
            s += nameOf(i) + ": ";
            for(DirectedEdge v : adj[i]){
                s += "[" + v.toString() + "] - ";
            }
            s += "\n";
        }
        return s;
    }
}