import java.util.NoSuchElementException;

/**
 * Doubly-Linked List implementation for using
 * it on Breadth-first search.
 */
public class Queue<T> {
    protected class Node{
        T elem;
        Node next;
        Node last;

        protected Node(T elem){
            this.elem = elem;
        }
    }

    private int size;
    private Node head;
    private Node tail;

    public void enqueue(T item){
        Node n = new Node(item);
        if(isEmpty()){
            head = tail = n;
        } else {
            head.last = n;
            n.next = head;
            head = n;
        }
        size++;
    }

    public T dequeue(){
        if(isEmpty()) throw new NoSuchElementException();
        T deq = tail.elem;
        if(size == 1){
            head = tail = null;
        } else {
            tail = tail.last;
            tail.next = null;
        }
        size--;
        return deq;
    }

    public T peek(){
        if(isEmpty()) throw new NoSuchElementException();
        return tail.elem;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return head == null;
    }
}
