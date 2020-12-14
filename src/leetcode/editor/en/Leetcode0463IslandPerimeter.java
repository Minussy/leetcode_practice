//You are given row x col grid representing a map where grid[i][j] = 1 represent
//s land and grid[i][j] = 0 represents water. 
//
// Grid cells are connected horizontally/vertically (not diagonally). The grid i
//s completely surrounded by water, and there is exactly one island (i.e., one or 
//more connected land cells). 
//
// The island doesn't have "lakes", meaning the water inside isn't connected to 
//the water around the island. One cell is a square with side length 1. The grid i
//s rectangular, width and height don't exceed 100. Determine the perimeter of the
// island. 
//
// 
// Example 1: 
//
// 
//Input: grid = [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
//Output: 16
//Explanation: The perimeter is the 16 yellow stripes in the image above.
// 
//
// Example 2: 
//
// 
//Input: grid = [[1]]
//Output: 4
// 
//
// Example 3: 
//
// 
//Input: grid = [[1,0]]
//Output: 4
// 
//
// 
// Constraints: 
//
// 
// row == grid.length 
// col == grid[i].length 
// 1 <= row, col <= 100 
// grid[i][j] is 0 or 1. 
// 
// Related Topics Hash Table 
// 👍 2220 👎 125

package leetcode.editor.en;

import java.util.LinkedList;
import java.util.Queue;

// 2020-09-12 14:05:59
// Zeshi Yang
public class Leetcode0463IslandPerimeter{
    // Java: island-perimeter
    public static void main(String[] args) {
        Solution sol = new Leetcode0463IslandPerimeter().new Solution();
        // TO TEST
        int[][] grid = {{0,1,0,0},{1,1,1,0},{0,1,0,0},{1,1,0,0}};
        int perimeter = sol.islandPerimeter(grid);
        System.out.println(perimeter);
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    private int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int islandPerimeter(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    return bfs(i, j, grid);
                }
            }
        }
        return 0;
    }

    private int bfs(int row, int col, int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        visited[row][col] = true;
        Queue<Integer> queue = new LinkedList<>();
        int index = row * cols + col;
        queue.offer(index);
        int perimeter = 0;
        while (!queue.isEmpty()) {
            index = queue.poll();
            row = index / cols;
            col = index % cols;
            for (int[] dir: DIRECTIONS) {
                int r = row + dir[0]; // neighbor row
                int c = col + dir[1]; // neighbor col
                if (r >= 0 && r < rows && c >= 0 && c < cols && grid[r][c] == 1) {
                    if (!visited[r][c]) {
                        visited[r][c] = true;
                        int idx = r * cols + c;
                        queue.offer(idx);
                    }
                } else {
                    perimeter++;
                }
            }
        }
        return perimeter;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}