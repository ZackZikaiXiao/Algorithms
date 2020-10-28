package week1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final WeightedQuickUnionUF uf;
    private boolean[] nodeStatusList;
    private final int lengthOfSide;  // length of side
    private final int head;
    private final int tail;
    private int openingNunber;
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        // (a,b) = n*(a-1)+b-1
        // Resize the (n, n) mat to (n*n,1).
        // Assuming inverse the list, first node checks the top while second node checks the bottom.
        if (n <= 0) throw new IllegalArgumentException("Illegal input!");
        lengthOfSide = n;
        head = n*n;
        tail = n*n+1;
        nodeStatusList = new boolean[n*n+2]; // default zero, set 1 when opening
        uf = new WeightedQuickUnionUF(n*n+2);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row > lengthOfSide || row < 1 || col > lengthOfSide
                || col < 1) throw new IllegalArgumentException("Out of range!");
        if (isOpen(row, col)) return; // check Opened
        if (row == 1) uf.union(lengthOfSide*(row-1)+col-1, head); // connect with head and tail
        if (row == lengthOfSide) uf.union(lengthOfSide*(row-1)+col-1, tail);
        openingNunber++;
        nodeStatusList[lengthOfSide *(row-1)+col-1] = true;
        if (row-1 > 0 && isOpen(row-1, col)) uf.union(lengthOfSide *(row-1)+col-1, lengthOfSide *(row-1-1)+col-1);
        if (row+1 < lengthOfSide +1 && isOpen(row+1, col)) uf.union(lengthOfSide *(row-1)+col-1, lengthOfSide
                *(row+1-1)+col-1);
        if (col-1 > 0 && isOpen(row, col-1)) uf.union(lengthOfSide *(row-1)+col-1, lengthOfSide *(row-1)+col-1-1);
        if (col+1 < lengthOfSide +1 && isOpen(row, col+1)) uf.union(lengthOfSide *(row-1)+col-1, lengthOfSide
                *(row-1)+col+1-1);
    }

    //  is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row > lengthOfSide || row < 1 || col > lengthOfSide || col < 1) {
            // System.out.println("row: "+row);
            // System.out.println("col: "+col);
            throw new IllegalArgumentException("Out of range!");
        }
        return nodeStatusList[lengthOfSide*(row-1)+col-1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        // row++; col++;
        if (row > lengthOfSide || row < 1 || col > lengthOfSide
                || col < 1) throw new IllegalArgumentException("Out of range!");
        return uf.find(head) == uf.find(lengthOfSide *(row-1)+col-1);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openingNunber;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.find(head) == uf.find(tail);
    }


    // test client (optional)
    public static void main(String[] args) {
        Percolation pclt = new Percolation(6);
        // pclt.open();
    }
}