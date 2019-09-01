/* ******************************************************
 *  Name:   Teng Bian
 *  
 *  Description:    This object in this class takes in
 *                  n strings and randomly output k of them,
 *                  in which k is obtained from standard input.
 *  
 *  
 *  Written:        01/04/2019
 *  Update date:    01/05/2019
 * 
 * 
 *  $ javac-algs4 Permutation.java
 *  $ java-algs4 Permutation < data.txt
 * 
 * *************************************************** */




import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int numString = Integer.parseInt(args[0]);
        
        RandomizedQueue<String> myQueue = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String myString = StdIn.readString();
            myQueue.enqueue(myString);
        }
        for (String item: myQueue) {
            if (numString > 0) {
                StdOut.println(item);
                numString--;
            }
            else break;
        }
    }
}