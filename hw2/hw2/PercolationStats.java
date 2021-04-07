package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {

    private double[] thresholds;

    /* perform T independent experiments on an N-by-N grid */
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("N/T should be larger than 0");
        }
        thresholds = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation percolation = pf.make(N);
            while (!percolation.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                if (percolation.isOpen(row, col)) {
                    continue;
                }
                percolation.open(row, col);
            }
            int number = percolation.numberOfOpenSites();
            thresholds[i] = (double) number / (N * N);
        }
    }

    /* sample mean of percolation threshold */
    public double mean() {
        double dividend = 0.0;
        for (int i = 0; i < thresholds.length; i++) {
            dividend += thresholds[i];
        }
        return dividend / thresholds.length;
    }

    /* sample standard deviation of percolation threshold */
    public double stddev() {
        double sampleMean = mean();
        double dividend = 0.0;
        for (int i = 0; i < thresholds.length; i++) {
            dividend += Math.pow(thresholds[i] - sampleMean, 2);
        }
        return Math.sqrt(dividend / (thresholds.length - 1));
    }

    /* low endpoint of 95% confidence interval */
    public double confidenceLow() {
        return mean() - (1.96 * stddev()) / Math.sqrt(thresholds.length);
    }

    /* high endpoint of 95% confidence interval */
    public double confidenceHigh() {
        return mean() + (1.96 * stddev()) / Math.sqrt(thresholds.length);
    }
}
