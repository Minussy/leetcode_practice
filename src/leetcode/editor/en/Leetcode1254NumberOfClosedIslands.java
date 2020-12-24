//Given a 2D grid consists of 0s (land) and 1s (water). An island is a maximal 4
//-directionally connected group of 0s and a closed island is an island totally (a
//ll left, top, right, bottom) surrounded by 1s. 
//
// Return the number of closed islands. 
//
// 
// Example 1: 
//
// 
//
// 
//Input: grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,
//0,1,0,1],[1,1,1,1,1,1,1,0]]
//Output: 2
//Explanation: 
//Islands in gray are closed because they are completely surrounded by water (gr
//oup of 1s). 
//
// Example 2: 
//
// 
//
// 
//Input: grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
//Output: 1
// 
//
// Example 3: 
//
// 
//Input: grid = [[1,1,1,1,1,1,1],
//               [1,0,0,0,0,0,1],
//               [1,0,1,1,1,0,1],
//               [1,0,1,0,1,0,1],
//               [1,0,1,1,1,0,1],
//               [1,0,0,0,0,0,1],
//               [1,1,1,1,1,1,1]]
//Output: 2
// 
//
// 
// Constraints: 
//
// 
// 1 <= grid.length, grid[0].length <= 100 
// 0 <= grid[i][j] <=1 
// 
// Related Topics Depth-first Search 
// 👍 603 👎 21

package leetcode.editor.en;

// 2020-12-23 23:42:39
// Zeshi Yang
public class Leetcode1254NumberOfClosedIslands{
    // Java: number-of-closed-islands
    public static void main(String[] args) {
        Solution sol = new Leetcode1254NumberOfClosedIslands().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
// 0 is land, 1 is water, island need to be closely surrounded by water.
// DFS, T(m, n) = O(m* n), S(m, n) = O(m * n);
/*
turn circumference land into and their connected land into 2, no count change
then traverse the grid, if we meet the land (0), turn its connected land to 2 and count++
 */
class Solution {
    
    private final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    
    public int closedIsland(int[][] grid) {
        // corner case
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        
        int rows = grid.length;
        int cols = grid[0].length;
        traverseCircumference(grid);
        int count = 0;
        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < cols - 1; j++) {
                if (grid[i][j] == 0) {
                    dfsSearch(i, j, grid);
                    count++;
                }
            }
        }
        restoreLand(grid); // can be deleted if interviewer does not require
        return count;
    }
    
    private void traverseCircumference(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        for (int j = 0; j < cols; j++) {
            if (grid[0][j] == 0) {
                dfsSearch(0, j, grid);
            }
            if (grid[rows - 1][j] == 0) {
                dfsSearch(rows - 1, j, grid);
            }
        }
        for (int i = 0; i < rows; i++) {
            if (grid[i][0] == 0) {
                dfsSearch(i, 0, grid);
            }
            if (grid[i][cols - 1] == 0) {
                dfsSearch(i, cols - 1, grid);
            }
        }
    }
    
    private void dfsSearch(int row, int col, int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        if (row < 0 || row >= rows || col < 0 || col >= cols || grid[row][col] == 2 || grid[row][col] == 1) {
            return;
        }
        grid[row][col] = 2; // mark 2 as visited land
        for (int[] dir: DIRECTIONS) {
            int i = row + dir[0];
            int j = col + dir[1];
            dfsSearch(i, j, grid);
        }
    }
    
    private void restoreLand(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) {
                    grid[i][j] = 0;
                }
            }
        }
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}
