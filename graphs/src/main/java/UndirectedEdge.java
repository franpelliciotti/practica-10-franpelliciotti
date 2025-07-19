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
}
