import java.util.Comparator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
    private static class SearchNode {
        public final Board board;
        public final int moves;
        public final SearchNode prevNode;
        // public final int hamming_priority;
        public final int manhattanPriority;
    
        public SearchNode(Board board, int moves, SearchNode prevNode) {
            this.board = board;
            this.moves = moves;
            this.prevNode = prevNode;
            // hammingPriority = this.board.hamming() + moves;
            manhattanPriority = this.board.manhattan() + moves;
        }
    }
    private Stack<Board> boardStack;

    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException();
        }

        SearchNode initialNode2 = new SearchNode(initial, 0, null);
        boardStack = findPath(initialNode2, new Comparator<SearchNode>() {

            @Override
            public int compare(SearchNode arg0, SearchNode arg1) {
                return Integer.compare(arg0.manhattanPriority, arg1.manhattanPriority);
            }

        });
    }

    private Stack<Board> findPath(SearchNode initialNode1, Comparator<SearchNode> comp) {
        MinPQ<SearchNode> q1 = new MinPQ<>(comp);
        MinPQ<SearchNode> q2 = new MinPQ<>(comp);

        Board twin = initialNode1.board.twin();
        SearchNode initialNode2 = new SearchNode(twin, 0, null);

        q1.insert(initialNode1);
        q2.insert(initialNode2);

        SearchNode minNode1 = q1.delMin();
        SearchNode minNode2 = q2.delMin();

        while (!minNode1.board.isGoal() && !minNode2.board.isGoal()) {
            for (Board neighBoard : minNode1.board.neighbors()) {
                if (minNode1.prevNode != null && minNode1.prevNode.board.equals(neighBoard)) {
                    continue;
                }
                q1.insert(new SearchNode(neighBoard, minNode1.moves + 1, minNode1));
            }
            for (Board neighBoard : minNode2.board.neighbors()) {
                if (minNode2.prevNode != null && minNode2.prevNode.board.equals(neighBoard)) {
                    continue;
                }
                q2.insert(new SearchNode(neighBoard, minNode2.moves + 1, minNode2));
            }
            minNode1 = q1.delMin();
            minNode2 = q2.delMin();
        }
        // StdOut.println("Done while ");
        // StdOut.println(minNode1.board.toString() + "\n" + minNode1.board.isGoal());
        // StdOut.println(minNode2.board.toString() + "\n" + minNode2.board.isGoal());

        if (minNode1.board.isGoal()) {
            Stack<Board> bStack = new Stack<>();
            SearchNode currNode = minNode1;
            while (currNode != null) {
                bStack.push(currNode.board);
                currNode = currNode.prevNode;
            }
            return bStack;
        } else {
            return null;
        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return !(boardStack == null);
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return (boardStack == null ? -1 : boardStack.size() - 1);
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        return boardStack;
    }

    // test client (see below)
    public static void main(String[] args) {
        // create initial board from file
        In in = new In("unsolv_p_1.txt");
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);
        // StdOut.println("solver done");
        // StdOut.println("++++++++++++++++++++++++++=");
        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

}