package week1;
import java.lang.IllegalArgumentException;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private WeightedQuickUnionUF uf1;
    private WeightedQuickUnionUF uf2;
    private int n;
    private int openSites = 0;
    private static final int[][] deltas = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Grid size invalid");
        }
        this.n = n;
        grid = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                grid[i][j] = false;
            }
        }
        uf1 = new WeightedQuickUnionUF(n * n + 2);
        uf2 = new WeightedQuickUnionUF(n * n + 1);
    }

    private boolean coordsNotInGrid(int row, int col) {
        return (row < 0 || n <= row || col < 0 || n <= col);
    }

    private int cellInUF(int row, int col) {
        return n * row + col;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        --row;
        --col;
        if (coordsNotInGrid(row, col)) {
            throw new IllegalArgumentException("Coordinate not in grid");
        }
        if (grid[row][col]) {
            return;
        }
        grid[row][col] = true;
        ++openSites;
        for (int[] delta : deltas) {
            int newRow = row + delta[0];
            int newCol = col + delta[1];
            if (coordsNotInGrid(newRow, newCol) || !grid[newRow][newCol]) {
                continue;
            }
            uf1.union(cellInUF(row, col), cellInUF(newRow, newCol));
            uf2.union(cellInUF(row, col), cellInUF(newRow, newCol));
        } 
        if (row == 0) {
            uf1.union(cellInUF(row, col), n * n);
            uf2.union(cellInUF(row, col), n * n);
        }
        if (row == n - 1) {
            uf1.union(cellInUF(row, col), n * n + 1);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        --row;
        --col;
        if (coordsNotInGrid(row, col)) {
            throw new IllegalArgumentException("Coordinate not in grid");
        }
        return grid[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        --row;
        --col;
        if (coordsNotInGrid(row, col)) {
            throw new IllegalArgumentException("Coordinate not in grid");
        }
        return (uf2.find(cellInUF(row, col)) == uf2.find(n * n));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return (uf1.find(n * n) == uf1.find(n * n + 1));
    }

}
