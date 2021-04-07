package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int[][] grid;
    private WeightedQuickUnionUF percolateUf;
    private WeightedQuickUnionUF fullUf;
    private int[] dx = {-1, 0, 0 ,1};
    private int[] dy = {0, -1, 1, 0};
    private int number = 0;
    /* create N-by-N grid, with all sites initially blocked */
    public Percolation(int N)    {
        if (N <= 0) {
            throw new IllegalArgumentException("The N should be larger than 0");
        }
        grid = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = 0;
            }
        }
        percolateUf = new WeightedQuickUnionUF(N * N + 2);
        fullUf = new WeightedQuickUnionUF(N * N + 1);
    }

    private void checkIndex(int row, int col) {
        if (row < 0 || row >= grid.length) {
            throw new IndexOutOfBoundsException("The row index is out of bounds, The bounds is 0-" + (grid.length-1));
        } else if (col < 0 || col >= grid[0].length) {
            throw new IndexOutOfBoundsException("The column index is out of bounds, The bounds is 0-" + (grid.length-1));
        }
    }

    /* open the site (row, col) if it is not open already */
    public void open(int row, int col) {
        if (isOpen(row, col)) {
            return ;
        }
        grid[row][col] = 1;
        number++;
        if (row == 0) {
            percolateUf.union((int) Math.pow(grid.length, 2), row * grid.length + col);
            fullUf.union((int) Math.pow(grid.length, 2), row * grid.length + col);
        }
        if (row == grid.length-1) {
            percolateUf.union((int) Math.pow(grid.length, 2) + 1, row * grid.length + col);
        }
        for (int i = 0; i < 4; i++) {
            int newRow = row + dx[i];
            int newCol = col + dy[i];
            if (newRow >= 0 && newRow < grid.length && newCol >= 0 && newCol < grid.length && grid[newRow][newCol] > 0) {
                percolateUf.union(row * grid.length + col, newRow * grid.length + newCol);
                fullUf.union(row * grid.length + col, newRow * grid.length + newCol);
            }
        }
    }

    /* is the site (row, col) open? */
    public boolean isOpen(int row, int col) {
        checkIndex(row, col);
        return grid[row][col] == 1;
    }

    /* is the site (row, col) full? */
    public boolean isFull(int row, int col) {
        checkIndex(row, col);
        return fullUf.connected(row * grid.length + col, (int) Math.pow(grid.length, 2));
    }

    /* number of open sites */
    public int numberOfOpenSites() {
        return number;
    }

    /* does the system percolate? */
    public boolean percolates() {
        return percolateUf.connected((int) Math.pow(grid.length, 2) + 1, (int) Math.pow(grid.length, 2));
    }

    /* use for unit testing (not required, but keep this here for the autograder) */
    public static void main(String[] args) {
        Percolation p = new Percolation(3);
        p.open(0, 2);
        p.open(1, 2);
        p.open(2, 2);
        p.open(2, 0);
        System.out.println(p.isFull(2, 0));
    }
}