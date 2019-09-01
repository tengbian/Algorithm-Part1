
import edu.princeton.cs.algs4.StdOut;

public class TestDeque {
    public void test_isEmpty() {
        Deque<String> myDeque1 = new Deque<String>();
        Deque<String> myDeque2 = new Deque<String>();
        String item = "tryit";
        myDeque2.addFirst(item);
        
        if (myDeque1.isEmpty() && !myDeque2.isEmpty())
            StdOut.println("isEmpty() is OK");
        else
            StdOut.println("isEmpty() isn't OK");
    }
    
    public void test_size() {
        Deque<String> myDeque = new Deque<String>();
        myDeque.addFirst("1");
        myDeque.addFirst("2");
        myDeque.addFirst("3");
        myDeque.addFirst("4");
        myDeque.addFirst("5");
        myDeque.addFirst("6");
        if (myDeque.size() == 6)
            StdOut.println("size() is OK");
        else
            StdOut.println("size() isn't OK");
    }
    
    public void test_addRemove() {
        Deque<String> myDeque = new Deque<String>();
        myDeque.addFirst("1");
        myDeque.addFirst("2");
        myDeque.addLast("3");
        myDeque.addLast("4");
        
        String item1 = myDeque.removeLast();
        String item2 = myDeque.removeLast();
        String item3 = myDeque.removeFirst();
        String item4 = myDeque.removeFirst();
        
        if (item1 == "4" && item2 == "3" && item3 == "2" && item4 == "1")
            StdOut.println("add* and remove* is OK");
        else
            StdOut.println("add* or remove* isn't OK");
    }
    
    public void test_iterator() {
        Deque<String> myDeque = new Deque<String>();        
        myDeque.addFirst("1");
        myDeque.addFirst("2");
        myDeque.addLast("3");
        myDeque.addLast("4");
        
        int k = 0;
        boolean result = true;
        for (String item: myDeque) {
            if (k == 0 && item != "2")  result = false;
            if (k == 1 && item != "1")  result = false;
            if (k == 2 && item != "3")  result = false;
            if (k == 3 && item != "4")  result = false;
            k++;
        }
        
        if (result)
            StdOut.println("iterator() is OK");
        else
            StdOut.println("iterator() isn't OK");
        
    }
    
    public static void main(String[] args) {
        TestDeque myTestDeque = new TestDeque();
        myTestDeque.test_isEmpty();
        myTestDeque.test_size();
        myTestDeque.test_addRemove();
        myTestDeque.test_iterator();
    }
    
    
    
}