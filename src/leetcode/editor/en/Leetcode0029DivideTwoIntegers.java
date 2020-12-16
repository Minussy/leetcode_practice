//Given two integers dividend and divisor, divide two integers without using mul
//tiplication, division, and mod operator. 
//
// Return the quotient after dividing dividend by divisor. 
//
// The integer division should truncate toward zero, which means losing its frac
//tional part. For example, truncate(8.345) = 8 and truncate(-2.7335) = -2. 
//
// Note: 
//
// 
// Assume we are dealing with an environment that could only store integers with
//in the 32-bit signed integer range: [−231, 231 − 1]. For this problem, assume th
//at your function returns 231 − 1 when the division result overflows. 
// 
//
// 
// Example 1: 
//
// 
//Input: dividend = 10, divisor = 3
//Output: 3
//Explanation: 10/3 = truncate(3.33333..) = 3.
// 
//
// Example 2: 
//
// 
//Input: dividend = 7, divisor = -3
//Output: -2
//Explanation: 7/-3 = truncate(-2.33333..) = -2.
// 
//
// Example 3: 
//
// 
//Input: dividend = 0, divisor = 1
//Output: 0
// 
//
// Example 4: 
//
// 
//Input: dividend = 1, divisor = 1
//Output: 1
// 
//
// 
// Constraints: 
//
// 
// -231 <= dividend, divisor <= 231 - 1 
// divisor != 0 
// 
// Related Topics Math Binary Search 
// 👍 1456 👎 5985

package leetcode.editor.en;

// 2020-12-15 16:47:21
// Zeshi Yang
public class Leetcode0029DivideTwoIntegers{
    // Java: divide-two-integers
    public static void main(String[] args) {
        Solution sol = new Leetcode0029DivideTwoIntegers().new Solution();
        // TO TEST
        int dividend = 2147483647;
        int divisor = 1;
        int res = sol.divide(dividend, divisor);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        
        long p = Math.abs((long) dividend);
        long q = Math.abs((long) divisor);
        
        int result = 0;
        while (p >= q) {
            int shift = 0;
            while (p >= (q << shift)) {
                shift++;
            }
            result += 1 << (shift - 1);
            p -= q << (shift - 1);
        }
        if ((dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0)) {
            return result;
        } else {
            return -result;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}