//Given a 2D board and a word, find if the word exists in the grid. 
//
// The word can be constructed from letters of sequentially adjacent cell, where
// "adjacent" cells are those horizontally or vertically neighboring. The same let
//ter cell may not be used more than once. 
//
// Example: 
//
// 
//board =
//[
//  ['A','B','C','E'],
//  ['S','F','C','S'],
//  ['A','D','E','E']
//]
//
//Given word = "ABCCED", return true.
//Given word = "SEE", return true.
//Given word = "ABCB", return false.
// 
//
// 
// Constraints: 
//
// 
// board and word consists only of lowercase and uppercase English letters. 
// 1 <= board.length <= 200 
// 1 <= board[i].length <= 200 
// 1 <= word.length <= 10^3 
// 
// Related Topics Array Backtracking
/**
 * @ClassName: Leetcode79WordSearch
 * @Description:
 * @Author: Zeshi(Jesse) Yang
 * @Date: 2020/06/23 周二 18:36
 */
package leetcode.editor.en;

public class Leetcode0079WordSearch {
	
	// Java: word-search
	public static void main(String[] args) {
		
		Solution sol = new Leetcode0079WordSearch().new Solution();
		// TO TEST
		
		char[][] board = new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'},
				{'A', 'D', 'E', 'E'}};
		String word = "ABCB";
		boolean res = sol.exist(board, word);
		System.out.println(res);
	}
	
//leetcode submit region begin(Prohibit modification and deletion)
// DFS
class Solution {
	
	private final int[][] DIRECTIONS = new int[][]{{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
	
	public boolean exist(char[][] board, String word) {
		// corner case
		if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
			return false;
		}
		int rows = board.length;
		int cols = board[0].length;
		boolean[][] visited = new boolean[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (dfs(board, i, j, word, 0, visited)) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean dfs(char[][] matrix, int i, int j, String word, int idx,
			boolean[][] visited) {
		
		int rows = matrix.length;
		int cols = matrix[0].length;
		int len = word.length();
		// base case
		// success case
		if (idx == len) {
			return true;
		}
		// failture case
		if (i < 0 || i >= rows || j < 0 || j >= cols || word.charAt(idx) != matrix[i][j]
				|| visited[i][j]) {
			return false;
		}
		visited[i][j] = true;
		// Solution 1:
		 boolean res = false;
		 for (int[] dir : DIRECTIONS) {
		 	int row = i + dir[0];
		 	int col = j + dir[1];
		 	res = dfs(matrix, row, col, word, idx + 1, visited);
		 	if (res) {
		 		break;
		 	}
		 }
		// Solution 2: 或者改成
//		boolean res = dfs(matrix, i - 1, j, word, idx + 1, visited)
//				|| dfs(matrix, i + 1, j, word, idx + 1, visited)
//				|| dfs(matrix, i, j - 1, word, idx + 1, visited)
//				|| dfs(matrix, i, j + 1, word, idx + 1, visited);
		visited[i][j] = false;
		return res;
	}
	
}
//leetcode submit region end(Prohibit modification and deletion)

}