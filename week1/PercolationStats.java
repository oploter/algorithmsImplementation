package week1;
import java.lang.IllegalArgumentException;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;


public class PercolationStats {
    // perform independent trials on an n-by-n grid
    private double mean;
    private double stddev;
    private int trials;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        this.trials = trials;
        double[] threshold = new double[trials];
        for (int t = 0; t < trials; ++t) {
            Percolation p = new Percolation(n);
            int[] seq = new int[n * n];
            for (int i = 0; i < n *n; ++i) {
                seq[i] = i;
            }
            StdRandom.shuffle(seq);
            //System.out.println(seq);
            int ind = 0;
            while (!p.percolates()) {
                p.open(1 + seq[ind] / n,1 + seq[ind] % n);
                ++ind;
                //System.out.println(ind + " " + seq[ind] + " " + p.percolates());
            }
            threshold[t] = (double) ind/(n*n);
        }
        mean = StdStats.mean(threshold);
        stddev = StdStats.stddev(threshold);
    }

    // sample mean of percolation threshold
    public double mean() {
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean - 1.96 * stddev / Math.sqrt(trials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean + 1.96 * stddev / Math.sqrt(trials);
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        try {
            PercolationStats ps = new PercolationStats(n, T);
            StdOut.printf("%-23s = %.15f%n", "mean", ps.mean());
            StdOut.printf("%-23s = %.15f%n", "stddev", ps.stddev());
            StdOut.printf("%-23s = [%.15f, %.15f]%n", "95% confidence interval", ps.confidenceLo(), ps.confidenceHi());
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }
}
