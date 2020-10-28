package week1;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
/* *****************************************************************************
 *  Name:    Alan Turing
 *  NetID:   aturing
 *  Precept: P00
 *
 *  Description:  Prints 'Hello, World' to the terminal window.
 *                By tradition, this is everyone's first program.
 *                Prof. Brian Kernighan initiated this tradition in 1974.
 *
 **************************************************************************** */

public class PercolationStats {
    private static final double CONFIDENCE_95 = 1.96;
    private final int thisTrials;
    private final int thisN;
    // private Percolation[] ufList;
    private double[] xt;
    private double mean;
    private double stddev;
    /**
     * @param   n   n-by-n grid
     * @param   trials  perform trials times
     * @return
     * @throws IllegalArgumentException if {@code n <= 0 || trials <= 0}
    * */
    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException("Illegal input!");
        thisTrials = trials;
        thisN = n;
        // ufList = new Percolation[thisTrials];
        xt = new double[thisTrials];
    };

    // sample mean of percolation threshold
    public double mean() {
        mean = StdStats.mean(xt);
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        stddev = StdStats.stddev(xt);
        return stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean - CONFIDENCE_95 *stddev/Math.sqrt(thisN);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean + CONFIDENCE_95 *stddev/Math.sqrt(thisN);
    }

    // ufSimulate
    private void ufsimulate() {
        for (int i = 0; i < thisTrials; i++) {
            Percolation percolation = new Percolation(thisN);
            int xti = 0;
            do {  // Open a site uniformly at random among all the blocked sites.
                int row = StdRandom.uniform(1, thisN+1);
                int col = StdRandom.uniform(1, thisN+1);
                if (!percolation.isOpen(row, col)) {
                    percolation.open(row, col);
                    xti++;
                }
            } while (!percolation.percolates());
            xt[i] = (double) xti/(thisN*thisN);
        }


        System.out.println("mean                    = "+mean());
        System.out.println("stddev                  = "+stddev());
        System.out.println("95% confidence interval = "+"["+confidenceLo()+", "+confidenceHi()+"]");
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats PS = new PercolationStats(n, trials);
        PS.ufsimulate();
    }
}