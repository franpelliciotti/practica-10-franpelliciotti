public class UndirectedEdge {
    protected final int v;
    protected final int w;
    protected final double weight;

    public UndirectedEdge(int v, int w, double weight){
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int other(int vertex){
        if(vertex != v || vertex != w)
            throw new IllegalArgumentException("vertex must be either " + v + " or " + w);
        if(vertex == v) return w;
        return v;
    }

    @Override
    public String toString(){
        String s = "|" + this.v + " | " + this.w + " | " + this.weight + "|";
        return s;
    }
}
