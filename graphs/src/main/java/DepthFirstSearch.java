public class DepthFirstSearch {
    boolean[] marked;
    int count;

    public DepthFirstSearch(AdjacencyListIntGraph G, int s){
        marked = new boolean[G.V()];
        count = 0;
        dfs(G, s);
    }

    private void dfs(AdjacencyListIntGraph G, int v){
        count++;
        marked[v] = true;
        for(int w : G.adj(v)){
            if(!marked[w])
                dfs(G, w);
        }
    }
}
