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
        if (n <= 0) {
            return null;
        }

        int[][] result = new int[n][n];

        int row = 0;
        int col = 0;
        int height = n;
        int width = n;
        int num = 1;

        while (height > 1 && width > 1) {
            //traverse right
            for (int i = col; i < col + width - 1; i++) {
                result[row][i] = num;
                num++;
            }

            //traverse down
            for (int i = row; i < row + height - 1; i++) {
                result[i][col + width - 1] = num;
                num++;
            }

            //traverse left
            for (int i = col + width - 1; i > col; i--) {
                result[row + height - 1][i] = num;
                num++;
            }

            //traverse up
            for (int i = row + height - 1; i > row; i--) {
                result[i][col] = num;
                num++;
            }

            row++;
            col++;
            height -= 2;
            width -= 2;
        }
        //edge case
        if (height == 1) {
            for (int i = col; i < col + width; i++) {
                result[row][i] = num;
                num++;
            }
        }
        else { // width == 1
            for (int i = row; i < row + height; i++) {
                result[i][col] = num;
                num++;
            }
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}