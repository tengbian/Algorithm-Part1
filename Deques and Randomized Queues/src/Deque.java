/* **************************************************************
 * Name:    Teng Bian
 * 
 * Description: This class provides a data structure to store
 *              and output data. It is more flexible than stacks
 *              and queues and is equipped with iterator() method.
 * 
 * 
 *  API
 *      Deque
 *      -----------------------------
 *                      Deque()
 *      boolean         isEmpty()
 *      int             size() 
 *      void            addFirst(Item)
 *      void            addLast(Item)
 *      Item            removeFirst()
 *      Item            removeLast()
 *      Iterator<Item>  iterator()     
 *    
 *             
 * Written:     01/04/2019
 * Update data: 01/06/2019        
 * 
 * $ javac-algs4 Deque.java
 * $ java-algs4 Deque
 * 
 * ************************************************************* */

// import java.lang.UnsupportedOperationException;
// import java.lang.IllegalArgumentException;
import java.util.NoSuchElementException;
import java.util.Iterator;


public class Deque<Item> implements Iterable<Item> {
    
    private Node first, last;
    private int size;
    
    private class Node {
        Item item;
        Node prev;
        Node next;
    }
    
    public Deque() {
        first = new Node();
        last = first;
        size = 0;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public int size() {
        return size;
    }
    
    public void addFirst(Item item) {

        if (item == null)
            throw new IllegalArgumentException("Item can not be null!");
        
        if (size == 0) {
            first.item = item;
            first.prev = null;
            first.next = null;
            size++;
        }
        else {
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.prev = null;
            first.next = oldFirst;
            oldFirst.prev = first;
            size++;
        }
    }
    
    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Item can not be null!");

        if (size == 0) {
            last.item = item;
            last.prev = null;
            last.next = null;
            size++;
        }
        else {
            Node oldLast = last;
            last = new Node();
            last.item = item;
            last.prev = oldLast;
            last.next = null;
            oldLast.next = last;
            size++;
        }
    }
    
    public Item removeFirst() {
        if (size == 0)
            throw new NoSuchElementException("No elements in the deque!");

        Item item = first.item;
        
        if (size == 1) {
            first.item = null; // also last; like a new Node
        }
        else {
            first = first.next;
            first.prev = null;
        }
        size--;
        return item;
    }
    
    public Item removeLast() {
        if (size == 0)
            throw new NoSuchElementException("No elements in the deque!");

        Item item = last.item;
        if (size == 1) {
            last.item = null; // also first; like a new Node
        }
        else {
            last = last.prev;
            last.next = null;
        }
        size--;
        return item;
    }
    
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }
    
    private class DequeIterator implements Iterator<Item> {
        private Node current = first;
        private int currentSize = size;
        public boolean hasNext() {
            return currentSize > 0;
        }
        public void remove() {
            throw new UnsupportedOperationException("Remove is not supported yet.");
        }
        
        public Item next() {
            if (currentSize == 0) {
                throw new NoSuchElementException("The Iterator is already empty");
            }
            Item item = current.item;
            current = current.next;
            currentSize--;
            return item;
        }
    }
    
    public static void main(String[] args) {
        Deque<String> myDeque = new Deque<String>();
        String item = "tryit";
        myDeque.addFirst(item);
    }
    
}