//Write a program to solve a Sudoku puzzle by filling the empty cells. 
//
// A sudoku solution must satisfy all of the following rules: 
//
// 
// Each of the digits 1-9 must occur exactly once in each row. 
// Each of the digits 1-9 must occur exactly once in each column. 
// Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-b
//oxes of the grid. 
// 
//
// Empty cells are indicated by the character '.'. 
//
// 
//A sudoku puzzle... 
//
// 
//...and its solution numbers marked in red. 
//
// Note: 
//
// 
// The given board contain only digits 1-9 and the character '.'. 
// You may assume that the given Sudoku puzzle will have a single unique solutio
//n. 
// The given board size is always 9x9. 
// 
// Related Topics Hash Table Backtracking 
// 👍 1846 👎 91

package leetcode.editor.en;

// 2020-07-26 13:00:15
// Zeshi Yang
public class Leetcode0037SudokuSolver{
    // Java: sudoku-solver
    public static void main(String[] args) {
        Solution sol = new Leetcode0037SudokuSolver().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public void solveSudoku(char[][] board) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
            return;
        }
        solveBoard(board);
    }

    private boolean solveBoard(char[][] board) {
        int rows = board.length;
        int cols = rows;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == '.') {
                    for (char c = '1'; c <= '9'; c++) {
                        if (isValid(board, i, j, c)) {
                            board[i][j] = c;
                            if (solveBoard(board)) {
                                return true;
                            }
                            else {
                                board[i][j] = '.'; // backtracking
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(char[][] board, int row, int col, char c) {
        for (int i = 0; i < 9; i++) {
            char rowChar = board[row][i];
            char colChar = board[i][col];
            int boxRow = row / 3 * 3 + i / 3;
            int boxCol = col / 3 * 3 + i % 3;
            char boxChar = board[boxRow][boxCol];
            if (rowChar != '.' && rowChar == c) {
                return false;
            }
            if (colChar != '.' && colChar == c) {
                return false;
            }
            if (boxChar != '.' && boxChar == c) {
                return false;
            }
        }
        return true;
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}