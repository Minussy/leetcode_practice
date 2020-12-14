//Given an m x n matrix of non-negative integers representing the height of each
// unit cell in a continent, the "Pacific ocean" touches the left and top edges of
// the matrix and the "Atlantic ocean" touches the right and bottom edges. 
//
// Water can only flow in four directions (up, down, left, or right) from a cell
// to another one with height equal or lower. 
//
// Find the list of grid coordinates where water can flow to both the Pacific an
//d Atlantic ocean. 
//
// Note: 
//
// 
// The order of returned grid coordinates does not matter. 
// Both m and n are less than 150. 
// 
//
// 
//
// Example: 
//
// 
//Given the following 5x5 matrix:
//
//  Pacific ~   ~   ~   ~   ~ 
//       ~  1   2   2   3  (5) *
//       ~  3   2   3  (4) (4) *
//       ~  2   4  (5)  3   1  *
//       ~ (6) (7)  1   4   5  *
//       ~ (5)  1   1   2   4  *
//          *   *   *   *   * Atlantic
//
//Return:
//
//[[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with paren
//theses in above matrix).
// 
//
// 
// Related Topics Depth-first Search Breadth-first Search

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Leetcode0417PacificAtlanticWaterFlow {
	
	// Java: pacific-atlantic-water-flow
	public static void main(String[] args) {
		Solution sol = new Leetcode0417PacificAtlanticWaterFlow().new Solution();
		// TO TEST
		int[][] matrix = new int[][]{
				{3, 3, 3, 3, 3, 3},
				{3, 0, 3, 3, 0, 3},
				{3, 3, 3, 3, 3, 3},
		};
		List<List<Integer>> result = sol.pacificAtlantic(matrix);
		System.out.println(Arrays.deepToString(result.toArray()));
	}
	
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
	
	private final int[][] DIRECTIONS = new int[][]{{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
	
	public List<List<Integer>> pacificAtlantic(int[][] matrix) {
		List<List<Integer>> result = new ArrayList<>();
		// corner case
		if (matrix == null || matrix.length == 0 || matrix[0] == null
				|| matrix[0].length == 0) {
			return result;
		}
		int rows = matrix.length;
		int cols = matrix[0].length;
		
		Queue<Integer> pacific = getOcean(matrix, true);
		Queue<Integer> atlantic = getOcean(matrix, false);
		
		boolean[][] reachPacific = bfs(matrix, pacific);
		boolean[][] reachAtlantic = bfs(matrix, atlantic);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (reachPacific[i][j] && reachAtlantic[i][j]) {
					result.add(new ArrayList<>(Arrays.asList(i, j)));
				}
			}
		}
		return result;
	}
	
	private boolean[][] bfs(int[][] matrix, Queue<Integer> queue) {
		int rows = matrix.length;
		int cols = matrix[0].length;
		boolean[][] result = new boolean[rows][cols];
		for (int index : queue) {
			int row = index / cols;
			int col = index % cols;
			result[row][col] = true;
		}
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			int row = cur / cols;
			int col = cur % cols;
			
			for (int[] dir : DIRECTIONS) {
				int i = row + dir[0];
				int j = col + dir[1];
				if (i >= 0 && i < rows && j >= 0 && j < cols && !result[i][j]
						&& matrix[i][j] >= matrix[row][col]) {
					queue.offer(i * cols + j);
					result[i][j] = true;
				}
			}
		}
		return result;
	}
	
	private Queue<Integer> getOcean(int[][] matrix, boolean isPacific) {
		Queue<Integer> ocean = new LinkedList<>();
		int rows = matrix.length;
		int cols = matrix[0].length;
		for (int j = 0; j < cols; j++) {
			if (isPacific) {
				ocean.offer(j);
			} else {
				ocean.offer(rows * cols - 1 - j);
			}
		}
		for (int i = 1; i < rows; i++) {
			if (isPacific) {
				ocean.offer(i * cols);
			} else {
				ocean.offer(rows * cols - 1 - i * cols);
			}
		}
		return ocean;
	}
}
//leetcode submit region end(Prohibit modification and deletion)

}