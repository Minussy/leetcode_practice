//Given an m x n matrix. If an element is 0, set its entire row and column to 0.
// Do it in-place. 
//
// Follow up: 
//
// 
// A straight forward solution using O(mn) space is probably a bad idea. 
// A simple improvement uses O(m + n) space, but still not the best solution. 
// Could you devise a constant space solution? 
// 
//
// 
// Example 1: 
//
// 
//Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
//Output: [[1,0,1],[0,0,0],[1,0,1]]
// 
//
// Example 2: 
//
// 
//Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
//Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
// 
//
// 
// Constraints: 
//
// 
// m == matrix.length 
// n == matrix[0].length 
// 1 <= m, n <= 200 
// -231 <= matrix[i][j] <= 231 - 1 
// 
// Related Topics Array 
// 👍 2963 👎 349

package leetcode.editor.en;

// 2021-01-19 12:10:44
// Zeshi Yang
public class Leetcode0073SetMatrixZeroes{
    // Java: set-matrix-zeroes
    public static void main(String[] args) {
        Solution sol = new Leetcode0073SetMatrixZeroes().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
/*
思路：
遍历所有点
如果这个点在第1行，或者第1列，把这个第1行（列）标记成本来有0
否则这个0所在的行的第一个数字标成0，也把这个0所在列的第一个数字标成0

然后遍历除了第1行和第1列之外的所有数字，如果它所在的行或者列的头是0的话，就把这个标记成0
遍历第1行，如果遇到0的话，就把这个0所在的列标成0
遍历第1列，如果遇到0的话，就把这个0所在的行变成0

最后看第一行和第一列的标记，看要不要第一行或者第一列全部变成0
 */
class Solution {
    
    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        
        boolean firstRow = false; // the first row has 0
        boolean firstCol = false; // the first column has 0
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 0) {
                    if (i == 0) {
                        firstRow = true;
                    }
                    if (j == 0) {
                        firstCol = true;
                    }
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        
        if (firstCol) {
            for (int i = 0; i < row; i++) {
                matrix[i][0] = 0;
            }
        }
        if (firstRow) {
            for (int j = 0; j < col; j++) {
                matrix[0][j] = 0;
            }
            
        }
        
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}
