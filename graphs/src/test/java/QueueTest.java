import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;

public class QueueTest {

    @Test
    public void test1(){
        Queue<Integer> q = new Queue<>();
        assertThrows(NoSuchElementException.class, () -> q.dequeue());
        assertTrue(q.isEmpty());
        assertEquals(0, q.size());
    }

    @Test
    public void test2(){
        Queue<Integer> q = new Queue<>();
        q.enqueue(3);
        assertFalse(q.isEmpty());
        assertEquals(1, q.size());
        assertEquals(3, q.peek());
        q.enqueue(2);
        assertFalse(q.isEmpty());
        assertEquals(2, q.size());
        assertEquals(3, q.peek());
        q.dequeue();
        assertFalse(q.isEmpty());
        assertEquals(1, q.size());
        assertEquals(2, q.peek());
        q.dequeue();
        assertTrue(q.isEmpty());
        assertEquals(0, q.size());
        assertThrows(NoSuchElementException.class, () -> q.dequeue());
    }
}
