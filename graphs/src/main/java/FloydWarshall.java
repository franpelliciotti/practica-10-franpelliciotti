public class FloydWarshall {
    WeightedListIntDigraph G;
    double[][] distTo;
    DirectedEdge[][] edgeTo;

    public FloydWarshall(WeightedListIntDigraph g){
        this.G = g;
        int V = G.V();
        distTo = new double[V][V];
        edgeTo = new DirectedEdge[V][V];
        //Inicialización de las distancias en infinito:
        for(int v = 0; v < V; v++){
            distTo[v][v] = 0.0;
            for(int w = 0; w < V; w++){
                if(v != w)
                    distTo[v][w] = Double.POSITIVE_INFINITY;
            }
        }
        //Inicialización de las distancias según el grafo:
        for(int v = 0; v < G.V(); v++){
            for(DirectedEdge e : G.adj(v)){
                distTo[e.from][e.to] = e.weight;
                edgeTo[e.from][e.to] = e;
            }
        }
        //Algoritmo:
        for(int k = 0; k < V; k++){
            for(int i = 0; i < V; i++){
                for(int j = 0; j < V; j++){
                    if(distTo[i][j] > distTo[i][k] + distTo[k][j]){
                        distTo[i][j] = distTo[i][k] + distTo[k][j];
                        edgeTo[i][j] = edgeTo[k][j];
                    }
                }
            }
        }
    }
}
