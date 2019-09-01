/* ********************************************************************
 *	Name:	Teng Bian
 * 
 *	Description:	Objects in this class will test the percolation limit
 *					of a square by many trials. For each trial, it randomly
 * 					opens sites in the square until the pattern percolates.
 * 					The it record the ratio of open sites. At last, it gives
 * 					the mean, stddev and 95% confidence interval of p limit
 * 					of the square.
 * 
 * 	Dependent class:	Percolation()
 * 
 *	APIs:
 *		PercolationStats
 *		---------------------------------
 *				PercolationStats(int, int)
 * 		double	mean()
 * 		double	stddev()
 * 		double	confidenceLo()
 * 		double	confidenceHi()
 * 
 * 	Written:		1/1/2019
 * 	Last update: 	1/2/2019
 * 
 * 	$ javac-algs4 PercolationStats.java
 * 	$ java-algs4 PercolationStats 200 1000
 * 
 * ***************************************************************** */



import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {

	private final double mean;
	private final double stddev;
	private final double confidenceLo;
	private final double confidenceHi;
	
	public PercolationStats(int n, int trials) {
	    
		if (n <= 0 || trials <= 0)
			throw new IllegalArgumentException("n and trials must be larger than 0\n");
		
		double CONFIDENCE_95 = 1.96;
		
	    int sl = n;        // size of the square
	    int tnum = trials; // # of experiments
		double[] prob = new double[tnum];

		for (int m = 0; m < tnum; m++) {
			int x, y;
			Percolation mymodel = new Percolation(sl);
			while (!mymodel.percolates()) {
				x = StdRandom.uniform(1, sl+1);
				y = StdRandom.uniform(1, sl+1);
				mymodel.open(x, y);
			}
			prob[m] = (1.0*mymodel.numberOfOpenSites())/(sl*sl);
		}
		mean = StdStats.mean(prob);
		stddev = StdStats.stddev(prob);
		confidenceLo = mean - CONFIDENCE_95*stddev/Math.sqrt(tnum);
		confidenceHi = mean + CONFIDENCE_95*stddev/Math.sqrt(tnum);
	}
	
	public double mean() {
		return mean;
	}
	public double stddev() {
		return stddev;
	}
	public double confidenceLo() {
		return confidenceLo;
	}
	public double confidenceHi() {
		return confidenceHi;
	}	
	
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int trials = Integer.parseInt(args[1]);
		Stopwatch myWatch = new Stopwatch();
		
		PercolationStats myStat = new PercolationStats(n, trials);
		StdOut.println("mean                    = " + myStat.mean());
		StdOut.println("sdtdev                  = " + myStat.stddev());
		StdOut.println("95% confidence interval = [" + myStat.confidenceLo() + ", " + myStat.confidenceHi() + "]");
		StdOut.println("Elapsed time            = " + myWatch.elapsedTime());
		
	}
	
}

