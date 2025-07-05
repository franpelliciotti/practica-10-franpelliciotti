import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;

public class AdjacencyListIntGraphTest {
    
    @Test   
    public void test1(){
        AdjacencyListIntGraph g = new AdjacencyListIntGraph(6);
        String exp = "0: //\n1: //\n2: //\n3: //\n4: //\n5: //\n";
        assertEquals(exp, g.toString());
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
        String exp2 = "0: [1] - //\n1: [0] - [2] - //\n2: [1] - [3] - //\n3: [2] - [4] - //\n4: [3] - [5] - //\n5: [4] - //\n";
        assertEquals(exp2, g.toString());
    }

    @Test
    public void test2(){
        AdjacencyListIntGraph g = new AdjacencyListIntGraph(0);
        assertEquals("", g.toString());
        assertThrows(IllegalArgumentException.class, () -> g.addEdge(0, 1));
    }
}   
