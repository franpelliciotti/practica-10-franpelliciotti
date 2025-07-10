import java.util.List;
import java.util.LinkedList;
import java.util.TreeMap;

public class AdjacencyListDigraph<T extends Comparable<? super T>> implements Graph<T> {
    private int V;
    private int E;
    private TreeMap<T, Integer> map;
    private T[] keys;
    private List<Integer>[] adj;

    public AdjacencyListDigraph(int V){
        if(V < 0)
            throw new IllegalArgumentException("Number of vertices must be non-negative.");
        this.V = 0;
        this.E = 0;
        adj = new LinkedList[V];
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
        adj[newV] = new LinkedList<>();
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
        adj[vid].add(wid);
    }
    
    private void resize(int l){
        T[] auxT = (T[]) new Comparable[l];
        List<Integer>[] auxInt = new LinkedList[l];
        for(int i = 0; i < auxT.length; i++){
            auxT[i] = keys[i];
            auxInt[i] = adj[i];
        }
        keys = auxT;
        adj = auxInt;
    }

    public String toString(){
        String s = "";
        for(int i = 0; i < V; i++){
            s += nameOf(i) + ": ";
            for(int v : adj[i]){
                s += "[" + nameOf(v) + "] - ";
            }
            s += "\n";
        }
        return s;
    }
}

