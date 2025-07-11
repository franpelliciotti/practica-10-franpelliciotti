import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class AdjacencyListIntDigraphTest {
    
    @Test   
    public void test1(){
        AdjacencyListIntDigraph g = new AdjacencyListIntDigraph(6);
        g.addEdge(0, 1);
        assertEquals("[1]", g.adj(0).toString());
        g.addEdge(0, 2);
        assertEquals("[1, 2]", g.adj(0).toString());
        g.addEdge(0, 3);
        assertEquals("[1, 2, 3]", g.adj(0).toString());
        g.addEdge(1, 2);
        assertEquals("[2]", g.adj(1).toString());
        g.addEdge(2, 3);
        assertEquals("[3]", g.adj(2).toString());
        g.addEdge(3, 4);
        assertEquals("[4]", g.adj(3).toString());
        g.addEdge(4, 5);
        assertEquals("[5]", g.adj(4).toString());
        assertEquals("[]", g.adj(5).toString());
    }

    @Test
    public void test2(){
        AdjacencyListIntDigraph g = new AdjacencyListIntDigraph(0);
        assertEquals("", g.toString());
        assertThrows(IllegalArgumentException.class, () -> g.addEdge(0, 1));
    }

    @Test
    public void test3(){
        AdjacencyListIntDigraph g = new AdjacencyListIntDigraph(5);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 4);
        g.addEdge(2, 3);
        g.addEdge(0, 3);
        g.addEdge(0, 4);
        BreadthFirstSearch<AdjacencyListIntDigraph> b = new BreadthFirstSearch<>(g, 0);
        DepthFirstSearchIntDigraph<AdjacencyListIntDigraph> d = new DepthFirstSearchIntDigraph<>(g, 0);
        boolean isConnected = true;
        for(int i = 0; i < g.V() && isConnected; i++){
            DepthFirstSearchIntDigraph<AdjacencyListIntDigraph> d1 = new DepthFirstSearchIntDigraph<>(g, i);
            if(!(d1.isSourceConnected()))
                isConnected = false;
        }
        assertFalse(isConnected);

        for(int i = 1; i < g.V(); i++){
            assertTrue(b.hasPathTo(i));
        }
        assertEquals("[0, 1]", d.pathTo(1).toString());
        assertEquals("[0, 1, 2]", d.pathTo(2).toString());
        assertEquals("[0, 1, 2, 3]", d.pathTo(3).toString());
        assertEquals("[0, 1, 2, 4]", d.pathTo(4).toString());
        assertEquals("[0, 1]", b.pathTo(1).toString());
        assertEquals("[0, 1, 2]", b.pathTo(2).toString());
        assertEquals("[0, 3]", b.pathTo(3).toString());
        assertEquals("[0, 4]", b.pathTo(4).toString());
    }

    @Test
    public void test4(){
        AdjacencyListIntDigraph g = new AdjacencyListIntDigraph(5);
        g.addEdge(0, 1);
        g.addEdge(1, 0);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 3);
        g.addEdge(2, 4);
        g.addEdge(3, 0);
        g.addEdge(4, 1);
        BreadthFirstSearch<AdjacencyListIntDigraph> b = new BreadthFirstSearch<>(g, 0);
        DepthFirstSearchIntDigraph<AdjacencyListIntDigraph> d = new DepthFirstSearchIntDigraph<>(g, 0);
        boolean isConnected = true;
        for(int i = 0; i < g.V() && isConnected; i++){
            DepthFirstSearchIntDigraph<AdjacencyListIntDigraph> d1 = new DepthFirstSearchIntDigraph<>(g, i);
            if(!(d1.isSourceConnected()))
                isConnected = false;
        }
        assertTrue(isConnected);

        for(int i = 1; i < g.V(); i++){
            assertTrue(b.hasPathTo(i));
        }
    }
}