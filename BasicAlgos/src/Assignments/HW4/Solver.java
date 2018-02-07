package Assignments.HW4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.MinPQ;

import java.util.LinkedList;
import java.util.Deque;

public class Solver {
    private boolean isSolvable;
    private MinPQ<SearchNode> snpq;
    private SearchNode solutionNode;

    public Solver(Board initial) {
        // find a solution to the initial board (using the A* algorithm)
        // Initialization
        solutionNode = null;
        snpq = new MinPQ<SearchNode>();
        snpq.insert(new SearchNode(initial, 0, null));

        while(true) {
            SearchNode currNode = snpq.delMin();
            Board currBoard = currNode.getBoard();
            if (currBoard.isGoal()) {
                isSolvable = true;
                solutionNode = currNode;
                break;
            }
            if (2 == currBoard.hamming() && currBoard.twin().isGoal()) {
                isSolvable = false;
                break;
            }
            // add each neighbor except the previous searchNode
            int moveStep = currNode.getMoveStep();
            Board prevBoard = (0 != moveStep ? currNode.getPrev().getBoard() : null);
            for (Board nextBoard : currBoard.neighbors()) {
                if (prevBoard != null && nextBoard == prevBoard)
                    continue;
                snpq.insert(new SearchNode(nextBoard, moveStep + 1, currNode));
            }

        }
        
    }

    public boolean isSolvable() {
        // is the initial board solvable?
        return this.isSolvable;
    }

    public int moves() {
        // min number of moves to solve initial board; -1 if unsolvable
        return isSolvable ? solutionNode.getMoveStep() : -1;
    }

    public Iterable<Board> solution() {
        // sequence of boards in a shortest solution; null if unsolvable
        if (!isSolvable) return null;
        Deque<Board> solution = new LinkedList<Board>();
        SearchNode sn = this.solutionNode;
        while (sn != null) {
            solution.addFirst(sn.getBoard());
            sn = sn.getPrev();
        }
        return solution;
    }

    private class SearchNode implements Comparable<SearchNode> {
        private final Board board;
        private final int moveStep;
        private final SearchNode prev;

        public SearchNode(Board board, int moveStep, SearchNode prev) {
            this.board = board;
            this.moveStep = moveStep;
            this.prev = prev;
        }

        @Override
        public int compareTo(SearchNode that) {
            return this.getPriority() - that.getPriority();
        }

        public Board getBoard() {
            return this.board;
        }

        public int getMoveStep() {
            return this.moveStep;
        }

        public SearchNode getPrev() {
            return this.prev;
        }

        private int getPriority() {
            return this.board.manhattan() + this.moveStep;
        }

        
    }

    public static void main(String[] args) {
        // solve a slider puzzle
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

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