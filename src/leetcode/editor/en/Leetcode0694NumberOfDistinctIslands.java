//Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (r
//epresenting land) connected 4-directionally (horizontal or vertical.) You may as
//sume all four edges of the grid are surrounded by water. 
//
// Count the number of distinct islands. An island is considered to be the same 
//as another if and only if one island can be translated (and not rotated or refle
//cted) to equal the other. 
//
// Example 1: 
// 
//11000
//11000
//00011
//00011
// 
//Given the above grid map, return 1.
// 
//
// Example 2: 
// 11011
//10000
//00001
//11011 
//Given the above grid map, return 3. 
//Notice that:
// 
//11
//1
// 
//and
// 
// 1
//11
// 
//are considered different island shapes, because we do not consider reflection 
/// rotation.
// 
//
// Note:
//The length of each dimension in the given grid does not exceed 50.
// Related Topics Hash Table Depth-first Search 
// 👍 870 👎 56

package leetcode.editor.en;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// 2020-08-27 20:48:52
// Zeshi Yang
public class Leetcode0694NumberOfDistinctIslands {

	// Java: number-of-distinct-islands
	public static void main(String[] args) {
		Solution sol = new Leetcode0694NumberOfDistinctIslands().new Solution();
		// TO TEST
        int[][] grid = {{1,1,0},{0,1,1},{0,0,0},{1,1,1},{0,1,0}};
        for (int[] row: grid) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("number of distinct islands is");
        int res = sol.numDistinctIslands(grid);
		System.out.println(res);
	}

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int numDistinctIslands(int[][] grid) {
        // corner case
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        int rows = grid.length;
        int cols = grid[0].length;
        Set<String> shapes = new HashSet<>();
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!visited.contains(i * cols + j) && grid[i][j] == 1) {
                    StringBuilder path = new StringBuilder();
                    dfs(grid, i, j, path, "start", visited);
                    shapes.add(path.toString());
                }
            }
        }
        return shapes.size();
    }

    private void dfs(int[][] board, int i, int j, StringBuilder path, String dir,
            Set<Integer> visited) {
        int rows = board.length;
        int cols = board[0].length;
        // no success case

        // base case - fail
        if (i < 0 || i >= rows || j < 0 || j >= cols || board[i][j] == 0 || visited.contains(i * cols + j)) {
            return;
        }
        visited.add(i * cols + j);
        path.append(dir);
        dfs(board, i - 1, j, path, "u", visited); // go upper
        dfs(board, i + 1, j, path, "d", visited); // go down
        dfs(board, i, j - 1, path, "l", visited); // go left
        dfs(board, i, j + 1, path, "r", visited); // go right
        path.append("end");
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}