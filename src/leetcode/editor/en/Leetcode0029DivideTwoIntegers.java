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
        int dividend = 20;
        int divisor = 3;
        int res = sol.divide(dividend, divisor);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public int divide(int dividend, int divisor) {
        // corner case
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        boolean isPositive = true;
        long dividendCur = dividend;
        long divisorCur = divisor;
        if (dividend < 0) {
            isPositive = !isPositive;
            dividendCur = -dividendCur;
        }
        if (divisor < 0) {
            isPositive = !isPositive;
            divisorCur = -divisorCur;
        }
        int quotient = 0;
        long divisorOrigin = divisorCur;
        while (dividendCur >= divisorCur) {
            int i = 1;
            
            while (dividendCur >= divisorCur + divisorCur) {
                divisorCur += divisorCur;
                i += i;
            }
            dividendCur -= divisorCur;
            quotient += i;
            divisorCur = divisorOrigin;
        }
        return isPositive ? quotient : -quotient;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: T(n) = O(log(n) * log(n))
// 1 ms,击败了100.00% 的Java用户, 36.5 MB,击败了16.45% 的Java用户
/*
每次把除数一直*2，乘到大于被除数的时候， 被除数把除数减掉，记录减去了多少个除数。除数复原，然后重复这个过程

 */
class Solution1 {
    
    public int divide(int dividend, int divisor) {
        // corner case
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        boolean isPositive = true;
        long dividendCur = dividend;
        long divisorCur = divisor;
        if (dividend < 0) {
            isPositive = !isPositive;
            dividendCur = -dividendCur;
        }
        if (divisor < 0) {
            isPositive = !isPositive;
            divisorCur = -divisorCur;
        }
        int quotient = 0;
        long divisorOrigin = divisorCur;
        while (dividendCur >= divisorCur) {
            int i = 1;
            
            while (dividendCur >= divisorCur + divisorCur) {
                divisorCur += divisorCur;
                i += i;
            }
            dividendCur -= divisorCur;
            quotient += i;
            divisorCur = divisorOrigin;
        }
        return isPositive ? quotient : -quotient;
    }
}

// Solution 2: T(n) = O(log(n) * log(n)
//1 ms,击败了100.00% 的Java用户,36 MB,击败了82.63% 的Java用户
/*
bit操作，把divisor从低位开始一直往前移，移动到比被除数小的最大值，让被除数把这个数字减掉
然后把divisor复原，重复这个操作，原理同上
 */
class Solution2 {
    
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

// Solution 3: T(n) = O(log(n))
// 1 ms,击败了100.00% 的Java用户, 36.5 MB,击败了9.60% 的Java用户
/*
bit操作，把divisor从低位开始一直往前移，移动到比被除数小的最大值，让被除数把这个数字减掉
这个时候，把divisor一步步往低位移，移到比dividend小的时候，就减掉这个数字，加上相应的指数结果
 */
class Solution3 {
    
    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        boolean isPositive = true;
        if (dividend < 0) {
            isPositive =!isPositive;
        }
        if (divisor < 0) {
            isPositive =! isPositive;
        }
        long p = Math.abs((long) dividend);
        long q = Math.abs((long) divisor);
        
        int result = 0;
        int shift = 0;
        if (p >= q) {
            while (p >= (q << shift)) {
                shift++;
            }
            result += 1 << (shift - 1);
            p -= q << (shift - 1);
        }
        shift--;
        while (p >= q) {
            while (p < (q << shift)) {
                shift--;
            }
            result += 1 << shift;
            p -= q << shift;
        }
        return isPositive ? result : -result;
    }
    
}
}