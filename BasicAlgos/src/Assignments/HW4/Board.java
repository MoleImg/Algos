

import java.util.LinkedList;
import java.util.List;

public class Board {
    private final int[][] board;
    private final int N;
    private int blankRow, blankCol; // position of the blank

    public Board(int[][] blocks) {
        // construct a board from an n-by-n array of blocks
        // (where blocks[i][j] = block in row i, column j)
        if (null == blocks) throw new NullPointerException();
        // copy the initial status
        board = copyOf(blocks);
        N = blocks.length;
        blankRow = -1; blankCol = -1;
        // find the position of the blank
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (0 == blocks[i][j]) {
                    blankRow = i;
                    blankCol = j;
                    return;
                }
            }
        }
    }
                                           
    public int dimension() {
        // board dimension n
        return N;
    } 

    public int hamming() {
        // number of blocks out of place
        int result = 0;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (blankRow == row && blankCol == col)
                    continue;
                if (manhattan(row, col) != 0)
                    result++;
            }
        }
        return result;
    } 

    public int manhattan() {
        // sum of Manhattan distances between blocks and goal
        int result = 0;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (blankRow == row && blankCol == col)
                    continue;
                result += manhattan(row, col);
            }
        }
        return result;
    }  

    private int manhattan(int row, int col) {
        int destVal = board[row][col];
        int destRow = destVal / N;
        int destCol = destVal % N - 1;
        return Math.abs(destRow - row) + Math.abs(destCol - col);
    }

    public boolean isGoal() {
        // is this board the goal board?
        return 0 == hamming();
    }

    public Board twin() {
        // a board that is obtained by exchanging any pair of blocks
        int[][] cloned = copyOf(board);
        if (blankRow != 0) {
            swap(cloned, 0, 0, 0, 1);
        } else {
            swap(cloned, 1, 0, 1, 1);
        }
        return new Board(cloned);
    }

    private void swap(int[][] mat, int rowA, int colA, int rowB, int colB) {
        int tmp = mat[rowA][colA];
        mat[rowA][colA] = mat[rowB][colB];
        mat[rowB][colB] = tmp;
    }

    public boolean equals(Object y) {
        // does this board equal y?
        if (this == y) return true;
        if (null == y) return false;
        if (y.getClass() != this.getClass()) return false;
        Board that = (Board)y;
        if (this.N != that.N) return false;
        if(this.blankRow != that.blankRow) return false;
        if(this.blankCol != that.blankCol) return false;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (this.board[row][col] != that.board[row][col]) return false;
            }
        }
        return true;

    }     

    public Iterable<Board> neighbors() {
        // all neighboring boards
        List<Board> neighbors = new LinkedList<Board>();
        // moving up
        if (blankRow > 0) {
            swap(board, blankRow, blankCol, blankRow - 1, blankCol);
            neighbors.add(new Board(board));
        }
        // moving down
        if (blankRow < N - 1) {
            swap(board, blankRow, blankCol, blankRow + 1, blankCol);
            neighbors.add(new Board(board));
        }
        // moving left
        if (blankCol > 0) {
            swap(board, blankRow, blankCol, blankRow, blankCol - 1);
            neighbors.add(new Board(board));
        }
        // moving right
        if (blankCol < N - 1) {
            swap(board, blankRow, blankCol, blankRow, blankCol + 1);
            neighbors.add(new Board(board));
        }

        return neighbors;
    }

    public String toString() {
        // string representation of this board (in the output format specified below)
        StringBuilder sb = new StringBuilder();
        sb.append(N);
        sb.append("\n");
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                sb.append(String.format("%2d", board[row][col]));
            }
            sb.append("\n");
        }
        return sb.toString();

    }  
    
    private int[][] copyOf(int[][] mat) {
        int[][] copyMat = new int[mat.length][];
        for (int i = 0; i < mat.length; i++)
            copyMat[i] = mat[i].clone();
            
        return copyMat;
    }

    public static void main(String[] args) {
        // unit tests (not graded)
    }
}