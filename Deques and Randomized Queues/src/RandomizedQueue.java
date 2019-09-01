/* *********************************************************
 *  Name:   Teng Bian
 *  
 *  Description:    This is a class used to store and output
 *                  data. It is close to a queue but the
 *                  difference is that it output an item in
 *                  random place when dequeue() is called.
 *                  Also it support a method sample() to
 *                  give a random item in the queue (but does
 *                  not delete it in the RandomizedQueue).
 * 
 *  API:
 *      RandomizedQueue<Item>
 *      ----------------------------------
 *                      RandomizedQueue()
 *      boolean         isEmpty()
 *      int             size()
 *      void            enqueue(Item)
 *      Item            dequeue()
 *      Item            sample()
 *      Iterator<Item>  iterator()
 *      
 *      
 *  Written:        01/04/2019
 *  Update date:    01/05/2019
 * 
 * ****************************************************** */




// import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
// import java.lang.UnsupportedOperationException;
// import java.lang.IllegalArgumentException;
import java.util.NoSuchElementException;
import java.util.Iterator;


public class RandomizedQueue<Item> implements Iterable<Item> {
    
    private Item[] s;
    private int size;
    private int capacity;
    
    public RandomizedQueue() {
        s = (Item[]) new Object[1];
        size = 0;
        capacity = 1;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public int size() {
        return size;
    }
    
    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Item can not be null!");
            
        if (size == capacity)   resize(2*capacity);
        s[size] = item;
        size++;
    }
    
    public Item dequeue() {
        if (size == 0) 
            throw new NoSuchElementException("The RandomizedQueue is already empty");
        
        if (size == capacity/4) resize(capacity/2);
        int select = StdRandom.uniform(size);
        Item item = s[select];
        s[select] = s[--size];
        s[size] = null;
        return item;
    }
    
    public Item sample() {
        if (size == 0) 
            throw new NoSuchElementException("The RandomizedQueue is already empty");

        int select = StdRandom.uniform(size);
        return s[select];
    }    
    
    private void resize(int newCapacity) {
        Item[] newS =  (Item[]) new Object[newCapacity];
        for (int i = 0; i < Math.min(capacity, newCapacity); i++)
            newS[i] = s[i];
        s = newS;  
        capacity = newCapacity;
    }
    
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }
    
    private class RandomizedQueueIterator implements Iterator<Item> {
        
        private Item[] copyQueue;
        private int copySize;
        
        public RandomizedQueueIterator() {
            copyQueue = (Item[]) new Object[size];
            for (int i = 0; i < size; i++)
                copyQueue[i] = s[i];
            copySize = size;
        }
        
        public boolean hasNext() {
            return copySize > 0;
        }
        public Item next() {
            if (copySize == 0)
                throw new NoSuchElementException("The RandomizedQueue Iterator is already empty");
            int select = StdRandom.uniform(copySize);
            Item item = copyQueue[select];
            copyQueue[select] = copyQueue[--copySize];
            copyQueue[copySize] = null;
            return item;
        }
        
        public void remove() {
            throw new UnsupportedOperationException("Remove is not supported yet.");
        }
        
    }
    
    public static void main(String[] args) {
        /* nothing */
    }
    
    
}