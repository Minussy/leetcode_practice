//Given a positive integer n, generate a square matrix filled with elements from
// 1 to n2 in spiral order. 
//
// Example: 
//
// 
//Input: 3
//Output:
//[
// [ 1, 2, 3 ],
// [ 8, 9, 4 ],
// [ 7, 6, 5 ]
//]
// 
// Related Topics Array 
// 👍 991 👎 111

package leetcode.editor.en;

// 2020-07-26 13:01:03
// Zeshi Yang
public class Leetcode0059SpiralMatrixIi{
    // Java: spiral-matrix-ii
    public static void main(String[] args) {
        Solution sol = new Leetcode0059SpiralMatrixIi().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        
        int row = 0; // 正方形边框的左上角横坐标
        int col = 0; // 正方形边框的左上角纵坐标
        int height = n;
        int width = n;
        int num = 1;
        
        while (height > 1 && width > 1) {
            //traverse right
            for (int i = col; i < col + width - 1; i++) {
                matrix[row][i] = num++;
            }
            //traverse down
            for (int i = row; i < row + height - 1; i++) {
                matrix[i][col + width - 1] = num++;
            }
            //traverse left
            for (int i = col + width - 1; i > col; i--) {
                matrix[row + height - 1][i] = num++;
            }
            //traverse up
            for (int i = row + height - 1; i > row; i--) {
                matrix[i][col] = num++;
            }
            row++;
            col++;
            height -= 2;
            width -= 2;
        }
        //edge case
        if (height == 1) {
            for (int i = col; i < col + width; i++) {
                matrix[row][i] = num;
                num++;
            }
        } else { // width == 1
            for (int i = row; i < row + height; i++) {
                matrix[i][col] = num;
                num++;
            }
        }
        return matrix;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1：从外面往里面走，设置好初始的height和width
class Solution1 {
    
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        
        int row = 0; // 正方形边框的左上角横坐标
        int col = 0; // 正方形边框的左上角纵坐标
        int height = n;
        int width = n;
        int num = 1;
        
        while (height > 1 && width > 1) {
            //traverse right
            for (int i = col; i < col + width - 1; i++) {
                matrix[row][i] = num++;
            }
            //traverse down
            for (int i = row; i < row + height - 1; i++) {
                matrix[i][col + width - 1] = num++;
            }
            //traverse left
            for (int i = col + width - 1; i > col; i--) {
                matrix[row + height - 1][i] = num++;
            }
            //traverse up
            for (int i = row + height - 1; i > row; i--) {
                matrix[i][col] = num++;
            }
            row++;
            col++;
            height -= 2;
            width -= 2;
        }
        //edge case
        if (height == 1) {
            for (int i = col; i < col + width; i++) {
                matrix[row][i] = num;
                num++;
            }
        } else { // width == 1
            for (int i = row; i < row + height; i++) {
                matrix[i][col] = num;
                num++;
            }
        }
        return matrix;
    }
    
}

// Solution 2:从外面往里面走，设置好初始的layer就行了，两个思路一样
class Solution2 {
    
    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int cnt = 1;
        for (int layer = 0; layer < (n + 1) / 2; layer++) {
            // direction 1 - traverse from left to right
            for (int ptr = layer; ptr < n - layer; ptr++) {
                result[layer][ptr] = cnt++;
            }
            // direction 2 - traverse from top to bottom
            for (int ptr = layer + 1; ptr < n - layer; ptr++) {
                result[ptr][n - layer - 1] = cnt++;
            }
            // direction 3 - traverse from right to left
            for (int ptr = layer + 1; ptr < n - layer; ptr++) {
                result[n - layer - 1][n - ptr - 1] = cnt++;
            }
            // direction 4 - traverse from bottom to top
            for (int ptr = layer + 1; ptr < n - layer - 1; ptr++) {
                result[n - ptr - 1][layer] = cnt++;
            }
        }
        return result;
    }
    
}
}