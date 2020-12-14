//Given a matrix of m x n elements (m rows, n columns), return all elements of t
//he matrix in spiral order. 
//
// Example 1: 
//
// 
//Input:
//[
// [ 1, 2, 3 ],
// [ 4, 5, 6 ],
// [ 7, 8, 9 ]
//]
//Output: [1,2,3,6,9,8,7,4,5]
// 
//
// Example 2: 
// 
//Input:
//[
//  [1, 2, 3, 4],
//  [5, 6, 7, 8],
//  [9,10,11,12]
//]
//Output: [1,2,3,4,8,12,11,10,9,5,6,7]
// Related Topics Array 
// 👍 2435 👎 574

package leetcode.editor.en;

import java.util.*;
// 2020-07-26 13:00:43
// Zeshi Yang
public class Leetcode0054SpiralMatrix{
    // Java: spiral-matrix
    public static void main(String[] args) {
        Solution sol = new Leetcode0054SpiralMatrix().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return result;
        }
        int row = 0;
        int col = 0;
        int height = matrix.length;
        int width = matrix[0].length;

        while (height > 1 && width > 1) {
            //traverse right
            for (int i = col; i < col + width - 1; i++) {
                result.add(matrix[row][i]);
            }

            //traverse down
            for (int i = row; i < row + height - 1; i++) {
                result.add(matrix[i][col + width - 1]);
            }

            //traverse left
            for (int i = col + width - 1; i > col; i--) {
                result.add(matrix[row + height - 1][i]);
            }

            //traverse up
            for (int i = row + height - 1; i > row; i--) {
                result.add(matrix[i][col]);
            }

            row++;
            col++;
            height -= 2;
            width -= 2;
        }

        if (height == 1) {
            for (int i = col; i < col + width; i++) {
                result.add(matrix[row][i]);
            }
        }
        else if (width == 1) {
            for (int i = row; i < row + height; i++) {
                result.add(matrix[i][col]);
            }
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}