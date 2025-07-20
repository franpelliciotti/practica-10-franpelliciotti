public class UndirectedEdge implements Comparable<UndirectedEdge>{
    protected final int v;
    protected final int w;
    protected final double weight;

    public UndirectedEdge(int v, int w, double weight){
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int other(int vertex){
        if(vertex == v) return w;
        else if(vertex == w) return v;
        else 
            throw new IllegalArgumentException("vertex must be either " + v + " or " + w);
    }

    @Override
    public String toString(){
        String s = "|" + this.v + " | " + this.w + " | " + this.weight + "|";
        return s;
    }

    public boolean equals(UndirectedEdge e){
        return this == e || ((this.v == e.w) && (this.w == e.v) && (this.weight == e.weight));
    }

    @Override
    public int compareTo(UndirectedEdge o) {
        if(this.weight < o.weight) return -1;
        else if(this.weight > o.weight) return 1;
        return 0;
    }
}
