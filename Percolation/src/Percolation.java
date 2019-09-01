/*	***************************************************************************
 * 	Name:	Teng Bian
 * 	
 * 	Description:	By given a data.txt, which include the size of a square and
 * 					the positions of opened sites, this algorithm can construct
 * 					a Percolation class. It would automatically decide whether
 * 					an open site is full or empty. At last, you can use a method
 * 					percolate() to determine if this pattern percolates or not.
 * 
 *	Dependent class: WeighedQuickUnionUF()
 * 
 * 	API for Percolation:
 * 		public class Percolation
 * 		-------------------------------
 * 				Percolation()
 * 	    void 	open(int, int)
 * 		boolean isOpen(int, int)
 * 		boolean isFull(int, int)
 * 		int 	numberofOpenSites()
 * 		boolean percolates()
 * 
 * 	Written:		1/1/2019
 * 	Last updated:	1/1/2019
 * 
 * 	$ javac-algs4 Percolation.java
 * 	$ java-algs4 Percolation < data.txt
 ************************************************************************	*/


import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

	private final int sl;		// size of square
	private boolean[] state;	// false: blocked, true: open
	private int numOpen;		// number of open sites
	private final WeightedQuickUnionUF myWQUF;
	
	private final int idTop;
	private final int idDown;

	public Percolation(int n) {
	    
	   if (n <= 0)
	       throw new IllegalArgumentException("n must be larger than 0\n");
	        
	    
		sl = n;
		myWQUF = new WeightedQuickUnionUF(sl*sl+2);	// sl^2 for top, sl^2+1 for down
		state  = new boolean[sl*sl];				    // state of top and down are nonsense
		
		idTop = sl*sl;
		idDown = sl*sl + 1;
		numOpen = 0;
		
		for (int i = 1; i <= sl; i++)
			for (int j = 1; j <= sl; j++)
				state[idSite(i, j)] = false;
	}
	
	private int idSite(int row, int col) {
	    return (row-1)*sl + (col-1);
	}
	
	public void open(int row, int col) {
		if (row <= 0 || row > sl || col <= 0 || col > sl)
			throw new IllegalArgumentException("Row and Col must be between 1 and sl \n");
		if (!isOpen(row, col)) {					// this site is closed
			state[idSite(row, col)] = true;			// this site becomes open
			numOpen++;

			
			// connect to Top and Down
			if (row == 1)    myWQUF.union(idSite(row, col), idTop);
			if (row == sl)   myWQUF.union(idSite(row, col), idDown);			

			// connect to neighbors
			if (row-1 > 0 && isOpen(row-1, col))
				myWQUF.union(idSite(row, col), idSite(row-1, col));
			if (row+1 <= sl && isOpen(row+1, col))
				myWQUF.union(idSite(row, col), idSite(row+1, col));
			if (col-1 > 0 && isOpen(row, col-1))
				myWQUF.union(idSite(row, col), idSite(row, col-1));
			if (col+1 <= sl && isOpen(row, col+1))
				myWQUF.union(idSite(row, col), idSite(row, col+1));
		}
	}
	
	public boolean isOpen(int row, int col) {
		if (row <= 0 || row > sl || col <= 0 || col > sl)
			throw new IllegalArgumentException("Row and Col must be between 1 and sl\n");
		return state[idSite(row, col)];
	}
	
	public boolean isFull(int row, int col) {
		if (row <= 0 || row > sl || col <= 0 || col > sl)
			throw new IllegalArgumentException("Row and Col must be between 1 and sl\n");
		return (myWQUF.connected(idSite(row, col), idTop));
	}
	
	public int numberOfOpenSites() {
		return numOpen;
	}
	
	public boolean percolates() {
		return myWQUF.connected(idTop, idDown);
	}
	
	public static void main(String[] args) {
		Percolation myExample = new Percolation(StdIn.readInt());
		while (!StdIn.isEmpty()) {
			myExample.open(StdIn.readInt(), StdIn.readInt());
		}	
		StdOut.println(myExample.percolates());
	}
	
	/*
	private void checkFull() {
		StdOut.println();
		for (int i = 1; i <= sl; i++) {
			for (int j = 1; j <= sl; j++) {
				StdOut.print(isFull(i,j));
				StdOut.print(" ");
			}
			StdOut.println("");
		}
		StdOut.println();
	}
	*/
	
	
}