import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

public class PercolationTest {

    /**
     * Enum to represent the state of a cell in the grid. Use this enum to help you write tests.
     * <p>
     * (0) CLOSED: isOpen() returns true, isFull() return false
     * <p>
     * (1) OPEN: isOpen() returns true, isFull() returns false
     * <p>
     * (2) INVALID: isOpen() returns false, isFull() returns true
     *              (This should not happen! Only open cells should be full.)
     * <p>
     * (3) FULL: isOpen() returns true, isFull() returns true
     * <p>
     */
    private enum Cell {
        CLOSED, OPEN, INVALID, FULL
    }

    /**
     * Creates a Cell[][] based off of what Percolation p returns.
     * Use this method in your tests to see if isOpen and isFull are returning the
     * correct things.
     */
    private static Cell[][] getState(int N, Percolation p) {
        Cell[][] state = new Cell[N][N];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                int open = p.isOpen(r, c) ? 1 : 0;
                int full = p.isFull(r, c) ? 2 : 0;
                state[r][c] = Cell.values()[open + full];
            }
        }
        return state;
    }

    @Test
    public void basicTest() {
        int N = 5;
        Percolation p = new Percolation(N);
        // open sites at (r, c) = (0, 1), (2, 0), (3, 1), etc. (0, 0) is top-left
        int[][] openSites = {
                {0, 1},
                {2, 0},
                {3, 1},
                {4, 1},
                {1, 0},
                {1, 1}
        };
        Cell[][] expectedState = {
                {Cell.CLOSED, Cell.FULL, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED},
                {Cell.FULL, Cell.FULL, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED},
                {Cell.FULL, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED},
                {Cell.CLOSED, Cell.OPEN, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED},
                {Cell.CLOSED, Cell.OPEN, Cell.CLOSED, Cell.CLOSED, Cell.CLOSED}
        };
        for (int[] site : openSites) {
            p.open(site[0], site[1]);
        }
        assertThat(getState(N, p)).isEqualTo(expectedState);
        assertThat(p.percolates()).isFalse();
    }

    @Test
    public void oneByOneTest() {
        int N = 1;
        Percolation p = new Percolation(N);
        p.open(0, 0);
        Cell[][] expectedState = {
                {Cell.FULL}
        };
        assertThat(getState(N, p)).isEqualTo(expectedState);
        assertThat(p.percolates()).isTrue();
    }


    @Test
    public void TestOpen() {
        Percolation p = new Percolation(3);
        p.open(0, 2);
        p.open(1, 2);
        p.open(2, 1);
        p.open(1, 0);

        assertThat(p.isOpen(0, 2)).isTrue();
        assertThat(p.isOpen(2, 1)).isTrue();
        assertThat(p.isOpen(1, 0)).isTrue();
        assertThat(p.isOpen(2, 2)).isFalse();

        assertThat(p.isFull(1, 2)).isTrue();
    }

    @Test
    public void TestNumberofOpenSites() {
        Percolation p = new Percolation(3);
        p.open(0, 2);
        p.open(1, 2);
        p.open(2, 1);
        p.open(1, 0);

        assertThat(p.numberOfOpenSites()).isEqualTo(4);

    }

    @Test
    public void TestPercolation(){
        Percolation p1 = new Percolation(3);
        p1.open(0, 2);
        p1.open(1, 2);
        p1.open(2, 2);
        p1.open(2, 0);

        assertThat(p1.percolates()).isTrue();
        assertThat(p1.isFull(2,0)).isFalse();

        Percolation p2 = new Percolation(3);
        p2.open(0,0);
        p2.open(0,1);
        p2.open(1,1);
        p2.open(2,0);

        assertThat(p2.percolates()).isFalse();
        assertThat(p2.isFull(1,1)).isTrue();

    }


}
