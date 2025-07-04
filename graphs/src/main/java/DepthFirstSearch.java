public class DepthFirstSearch {
    AdjacencyListIntGraph G;
    boolean[] marked;
    int count;
    boolean isConnected;

    public DepthFirstSearch(AdjacencyListIntGraph G, int s){
        this.G = G;
        isConnected = false;
        marked = new boolean[G.V()];
        count = 0;
        dfs(G, s);
        if(count == G.V())
            isConnected = true;
    }

    private void dfs(AdjacencyListIntGraph G, int v){
        count++;
        marked[v] = true;
        for(int w : G.adj(v)){
            if(!marked[w])
                dfs(G, w);
        }
    }

    public boolean isConnected(){
        return isConnected;
    }

    public static void main(String[] args){
        AdjacencyListIntGraph g = new AdjacencyListIntGraph(4);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        DepthFirstSearch d = new DepthFirstSearch(g, 0);
        System.out.println(d.isConnected());
    }
}
