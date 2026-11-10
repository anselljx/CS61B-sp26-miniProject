import edu.princeton.cs.algs4.UF;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;



public class Percolation {

    private boolean [][] grid;
    private int size ;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF percolationsite;
    private int top;
    private int bottom;
    private int openNum;

    public Percolation(int N) {
        grid = new boolean[N][N];
        size = N;
        uf = new WeightedQuickUnionUF(N*N + 1);//这里有错误，没有调整大小
        percolationsite = new WeightedQuickUnionUF(N*N + 2);
        top = N*N;
        bottom = N*N+1;
        openNum = 0;
    }

    public int index(int row,int col){return row * size + col;}

    public void openHelper(int row1,int col1,int row2,int col2){
        if(grid[row1][col1] && grid[row2][col2]){
            uf.union(index(row1,col1),index(row2,col2));
            percolationsite.union(index(row1,col1),index(row2,col2));
        }
    }

    public void open(int row, int col) {
        grid[row][col] = true;
        openNum++;
        if (row == 0) {
            uf.union(index(row,col),top);
            percolationsite.union(index(row,col),top);
            if (col == 0) {
                openHelper(row, col, row + 1, col);
                openHelper(row, col, row, col + 1);
            } else if (col == size - 1) {
                openHelper(row, col, row + 1, col);
                openHelper(row, col, row, col - 1);
            } else {
                openHelper(row, col, row + 1, col);
                openHelper(row, col, row, col + 1);
                openHelper(row, col, row, col - 1);
            }
        } else if (row == size - 1) {
            percolationsite.union(index(row,col),bottom);
            if (col == 0) {
                openHelper(row, col, row - 1, col);
                openHelper(row, col, row, col + 1);
            } else if (col == size - 1) {
                openHelper(row, col, row - 1, col);
                openHelper(row, col, row, col - 1);
            } else {
                openHelper(row, col, row - 1, col);
                openHelper(row, col, row, col + 1);
                openHelper(row, col, row, col - 1);
            }

        } else {
            if (col == 0) {
                openHelper(row, col, row + 1, col);
                openHelper(row, col, row - 1, col);
                openHelper(row, col, row, col + 1);
            } else if (col == size - 1) {
                openHelper(row, col, row + 1, col);
                openHelper(row, col, row - 1, col);
                openHelper(row, col, row, col - 1);
            } else {
                openHelper(row, col, row + 1, col);
                openHelper(row, col, row - 1, col);
                openHelper(row, col, row, col + 1);
                openHelper(row, col, row, col - 1);
            }
        }
    }

    public boolean isOpen(int row, int col) {return grid[row][col];}

    public boolean isFull(int row, int col) {return uf.connected(index(row,col),top);}

    public int numberOfOpenSites() {return openNum;}

    public int size(){return size;}

    public boolean percolates() {return percolationsite.connected(top,bottom);}



}
