import edu.princeton.cs.algs4.StdOut;

public class TestRQueue {
    
    public void test_isEmpty() {
        RandomizedQueue<String> myRQueue1 = new RandomizedQueue<String>();
        RandomizedQueue<String> myRQueue2 = new RandomizedQueue<String>();
        String item = "tryit";
        myRQueue2.enqueue(item);
        
        if (myRQueue1.isEmpty() && !myRQueue2.isEmpty())
            StdOut.println("isEmpty() is OK");
        else
            StdOut.println("isEmpty() isn't OK");
    }
    
    
    public void test_size() {
        RandomizedQueue<String> myRQueue = new RandomizedQueue<String>();
        myRQueue.enqueue("1");
        myRQueue.enqueue("2");
        myRQueue.enqueue("3");
        myRQueue.enqueue("4");
        myRQueue.enqueue("5");
        myRQueue.enqueue("6");
        if (myRQueue.size() == 6)
            StdOut.println("size() is OK");
        else
            StdOut.println("size() isn't OK");

    }
    
    public static void main(String[] args) {
        TestRQueue myTestRQueue = new TestRQueue();
        myTestRQueue.test_isEmpty();
        myTestRQueue.test_size();
        // myTestRQueue.test_addRemove();
        // myTestRQueue.test_iterator();
    }
}