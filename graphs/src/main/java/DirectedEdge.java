public class DirectedEdge {
    protected final int from;
    protected final int to;
    final double weight;

    public DirectedEdge(int from, int to, double weight){
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public String toString(){
        String s = "" + this.from + " | " + this.to + " | " + this.weight;
        return s;
    }
}