import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Board {
    private static final int[][] DCOORDS = new int[][] { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    private final int n;
    private final int[][] tiles;
    private final int[] swapCoords;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        this.tiles = new int[tiles.length][];
        for (int r = 0; r < this.tiles.length; ++r) {
            this.tiles[r] = tiles[r].clone();
        }
        this.n = tiles.length;

        int r1 = StdRandom.uniformInt(n);
        int c1 = StdRandom.uniformInt(n);
        int r2 = StdRandom.uniformInt(n);
        int c2 = StdRandom.uniformInt(n);
        while ((r1 == r2 && c1 == c2) || (this.tiles[r1][c1] == 0) || (this.tiles[r2][c2] == 0)) {
            r1 = StdRandom.uniformInt(n);
            c1 = StdRandom.uniformInt(n);
            r2 = StdRandom.uniformInt(n);
            c2 = StdRandom.uniformInt(n);
        }
        swapCoords = new int[]{r1, c1, r2, c2};
        
    }

    // string representation of this board
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(n + "\n");
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                sb.append(" " + tiles[i][j]);
            }
            if (i < n - 1) {
                sb.append('\n');
            }
        }
        return sb.toString();
    }

    // board dimension n
    public int dimension() {
        return n;
    }

    // number of tiles out of place
    public int hamming() {
        int k = 1;
        int cnt = 0;
        for (int i = 0; i < n; ++i) {
            int lastRow = (i == (n - 1) ? 1 : 0);
            for (int j = 0; j < (n - lastRow); ++j) {
                if (tiles[i][j] != k++) {
                    ++cnt;
                }
            }
        }
        return cnt;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int s = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (tiles[i][j] == 0) {
                    continue;
                }
                s += Math.abs(((tiles[i][j] - 1) / n) - i) + Math.abs(((tiles[i][j] - 1) % n) - j);
            }
        }
        return s;
    }

    // is this board the goal board?
    public boolean isGoal() {
        int k = 1;
        boolean isGoal = true;
        for (int i = 0; i < n; ++i) {
            int lastRow = ((i == n - 1) ? 1 : 0);
            for (int j = 0; j < n - lastRow; ++j) {
                if (tiles[i][j] != k++) {
                    isGoal = false;
                    break;
                }
            }
            if (!isGoal) {
                break;
            }
        }
        return isGoal;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == null) {
            return false;
        }
        if (y.getClass() != this.getClass()) {
            return false;
        }
        final Board other = (Board) y;
        if (n != other.n) {
            return false;
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (tiles[i][j] != other.tiles[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        int[] prevZero = null;
        for (int i = 0; i < dimension(); ++i) {
            for (int j = 0; j < dimension(); ++j) {
                if (tiles[i][j] == 0) {
                    prevZero = new int[] { i, j };
                    break;
                }
            }
            if (prevZero != null) {
                break;
            }
        }

        Stack<Board> newBoards = new Stack<>();
        for (int[] dc : DCOORDS) {
            int newZeroR = prevZero[0] + dc[0];
            int newZeroC = prevZero[1] + dc[1];
            if (0 <= newZeroR && newZeroR < dimension() && 0 <= newZeroC && newZeroC < dimension()) {
                int[][] newTiles = new int[tiles.length][];
                for (int i = 0; i < tiles.length; i++) {
                    newTiles[i] = tiles[i].clone();
                }
                newTiles[prevZero[0]][prevZero[1]] = newTiles[newZeroR][newZeroC];
                newTiles[newZeroR][newZeroC] = 0;
                newBoards.push(new Board(newTiles));
            }
        }
        return newBoards;

    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int[][] twinTiles = new int[tiles.length][];
        for (int r = 0; r < tiles.length; ++r) {
            twinTiles[r] = tiles[r].clone();
        }
        int tmp = twinTiles[swapCoords[0]][swapCoords[1]];
        twinTiles[swapCoords[0]][swapCoords[1]] = twinTiles[swapCoords[2]][swapCoords[3]];
        twinTiles[swapCoords[2]][swapCoords[3]] = tmp;
        return new Board(twinTiles);
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        Board b1 = new Board(new int[][] {
                { 0, 1, 3 },
                { 4, 2, 5 },
                { 7, 8, 6 }
        });
        StdOut.println(b1.dimension());
        StdOut.println(b1.toString());

        Board b2 = new Board(new int[][] {
                { 8, 1, 3 },
                { 4, 0, 2 },
                { 7, 6, 5 }
        });
        StdOut.println(b2.hamming());
        StdOut.println(b2.manhattan());

        Board b3 = new Board(new int[][] {
                { 1, 2, 3, 4 },
                { 5, 6, 7, 8 },
                { 9, 10, 11, 12 },
                { 13, 14, 15, 0 }
        });
        StdOut.println(b3.isGoal());

        Board b4 = new Board(new int[][] {
                { 1, 0, 3, 6 },
                { 5, 4, 7, 8 },
                { 9, 11, 10, 12 },
                { 13, 14, 15, 2 }
        });
        StdOut.println(b4.isGoal());

        Board b5 = new Board(new int[][] {
                { 14, 7, 21, 8, 13 },
                { 12, 6, 18, 11, 16 },
                { 10, 5, 15, 20, 17 },
                { 9, 3, 24, 23, 0 },
                { 2, 1, 4, 22, 19 }
        });
        Board b6 = new Board(new int[][] {
                { 14, 7, 21, 8, 13 },
                { 12, 6, 18, 11, 16 },
                { 10, 5, 15, 20, 17 },
                { 9, 3, 24, 23, 0 },
                { 2, 1, 4, 22, 19 }
        });
        Board b7 = new Board(new int[][] {
                { 14, 4, 21, 8, 13 },
                { 12, 6, 18, 1, 16 },
                { 10, 5, 15, 19, 17 },
                { 9, 3, 24, 23, 0 },
                { 2, 11, 7, 22, 20 }
        });
        StdOut.println(b5.equals(b6));
        StdOut.println(b5.equals(b7));

        Board b8 = new Board(new int[][] {
                { 1, 0, 3 },
                { 4, 2, 5 },
                { 7, 8, 6 }
        });
        for (Board nBoard : b8.neighbors()) {
            StdOut.println(nBoard.toString());
        }

        Board b9 = new Board(new int[][] {
                { 1, 2, 3, 4 },
                { 5, 6, 7, 8 },
                { 9, 10, 11, 12 },
                { 13, 14, 15, 0 }
        });
        StdOut.println(b9.toString());
        Board b10 = b9.twin();
        Board b11 = b9.twin();
        StdOut.println(b10.toString());
        StdOut.println(b11.toString());
    }

}