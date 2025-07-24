public class UndirectedEdge implements Comparable<UndirectedEdge>, Edge{
    protected final int from;
    protected final int to;
    protected final double weight;

    public UndirectedEdge(int from, int to, double weight){
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public int other(int vertex){
        if(vertex == from) return to;
        else if(vertex == to) return from;
        else 
            throw new IllegalArgumentException("vertex must be either " + from + " or " + to);
    }

    @Override
    public String toString(){
        String s = "|" + this.from + " | " + this.to + " | " + this.weight + "|";
        return s;
    }

    public boolean equals(UndirectedEdge e){
        return this == e || ((this.from == e.to) && (this.to == e.from) && (this.weight == e.weight));
    }

    @Override
    public int compareTo(UndirectedEdge o) {
        if(this.weight < o.weight) return -1;
        else if(this.weight > o.weight) return 1;
        return 0;
    }

    @Override
    public int from() {
        return from;
    }

    @Override
    public int to() {
        return to;
    }
}
