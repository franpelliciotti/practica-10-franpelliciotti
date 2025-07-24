public class BellmanFordUndirected {
    double[] distTo;
    UndirectedEdge[] edgeTo;
    WeightedListIntGraph G;

    public BellmanFordUndirected(WeightedListIntGraph G, int s){
        if(s < 0 || s >= G.V())
            throw new IllegalArgumentException("vertex " + s + "isn't between 0 and " + (G.V() -1));
        if(G.hasNegativeCycle())
            throw new IllegalArgumentException("This graph has negative cycles.");
        
        distTo = new double[G.V()];
        edgeTo = new UndirectedEdge[G.V()];
        this.G = G;

        for(int v = 0; v < G.V(); v++){
            if(v != s){
                distTo[v] = Double.POSITIVE_INFINITY;
            }
        }
        distTo[s] = 0.0;

        for(int i = 1; i < G.V(); i++){
            for(UndirectedEdge e: G.edges())
                relax(e);
        }
    }

    private void relax(UndirectedEdge e){
        int v = e.from;
        int w = e.to;
        if(distTo[w] > distTo[v] + e.weight){
            distTo[w] = distTo[v] + e.weight;
            edgeTo[w] = e;
        }
    }
}
