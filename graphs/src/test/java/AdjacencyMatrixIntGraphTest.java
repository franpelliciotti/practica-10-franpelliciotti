import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class AdjacencyMatrixIntGraphTest {
    
    @Test   
    public void test1(){
        AdjacencyMatrixIntGraph g = new AdjacencyMatrixIntGraph(6);
        g.addEdge(0, 1);
        assertEquals("[1]", g.adj(0).toString());
        g.addEdge(1, 2);
        assertEquals("[0, 2]", g.adj(1).toString());
        g.addEdge(2, 3);
        assertEquals("[1, 3]", g.adj(2).toString());
        g.addEdge(3, 4);
        assertEquals("[2, 4]", g.adj(3).toString());
        g.addEdge(4, 5);
        assertEquals("[3, 5]", g.adj(4).toString());
        assertEquals("[4]", g.adj(5).toString());
    }

    @Test
    public void test2(){
        AdjacencyMatrixIntGraph g = new AdjacencyMatrixIntGraph(0);
        assertEquals("", g.toString());
        assertThrows(IllegalArgumentException.class, () -> g.addEdge(0, 1));
    }

    @Test
    public void test3(){
        AdjacencyMatrixIntGraph g = new AdjacencyMatrixIntGraph(5);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 4);
        g.addEdge(2, 3);
        g.addEdge(0, 3);
        g.addEdge(0, 4);
        BreadthFirstSearch<AdjacencyMatrixIntGraph> b = new BreadthFirstSearch<>(g, 0);       
        DepthFirstSearchIntGraph<AdjacencyMatrixIntGraph> d = new DepthFirstSearchIntGraph<>(g, 0);
        for(int i = 1; i < g.V(); i++){
            assertTrue(b.hasPathTo(i));
        }
        assertTrue(d.isConnected());
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
        AdjacencyMatrixIntGraph g = new AdjacencyMatrixIntGraph(5);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 3);
        BreadthFirstSearch<AdjacencyMatrixIntGraph> b = new BreadthFirstSearch<>(g, 0);
        DepthFirstSearchIntGraph<AdjacencyMatrixIntGraph> d = new DepthFirstSearchIntGraph<>(g, 0);
        assertFalse(b.hasPathTo(4));
        assertFalse(d.hasPathTo(4));
        assertFalse(d.isConnected());
        assertEquals("[0, 1]", d.pathTo(1).toString());
        assertEquals("[0, 1, 2]", d.pathTo(2).toString());
        assertEquals("[0, 1, 2, 3]", d.pathTo(3).toString());
        assertEquals("[0, 1]", b.pathTo(1).toString());
        assertEquals("[0, 1, 2]", b.pathTo(2).toString());
        assertEquals("[0, 1, 3]", b.pathTo(3).toString());
    }

    @Test
    public void test5(){
        AdjacencyMatrixIntGraph g = new AdjacencyMatrixIntGraph(5);
        g.addEdge(0, 4);
        g.addEdge(2, 3);
        g.addEdge(1, 4);
        g.addEdge(0, 3);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        BreadthFirstSearch<AdjacencyMatrixIntGraph> b = new BreadthFirstSearch<>(g, 0);
        DepthFirstSearchIntGraph<AdjacencyMatrixIntGraph> d = new DepthFirstSearchIntGraph<>(g, 0);
        assertTrue(d.isConnected());
        for(int i = 1; i < g.V(); i++){
            assertTrue(b.hasPathTo(i));
        }
        assertEquals("[0, 3, 1]", d.pathTo(1).toString());
        assertEquals("[0, 3, 1, 2]", d.pathTo(2).toString());
        assertEquals("[0, 3]", d.pathTo(3).toString());
        assertEquals("[0, 3, 1, 4]", d.pathTo(4).toString());
        assertEquals("[0, 3, 1]", b.pathTo(1).toString());
        assertEquals("[0, 3, 2]", b.pathTo(2).toString());
        assertEquals("[0, 3]", b.pathTo(3).toString());
        assertEquals("[0, 4]", b.pathTo(4).toString());
    }
}   
