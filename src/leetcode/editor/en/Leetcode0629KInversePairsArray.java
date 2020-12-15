//Given two integers n and k, find how many different arrays consist of numbers 
//from 1 to n such that there are exactly k inverse pairs. 
//
// We define an inverse pair as following: For ith and jth element in the array,
// if i < j and a[i] > a[j] then it's an inverse pair; Otherwise, it's not. 
//
// Since the answer may be very large, the answer should be modulo 109 + 7. 
//
// Example 1: 
//
// 
//Input: n = 3, k = 0
//Output: 1
//Explanation: 
//Only the array [1,2,3] which consists of numbers from 1 to 3 has exactly 0 inv
//erse pair.
// 
//
// 
//
// Example 2: 
//
// 
//Input: n = 3, k = 1
//Output: 2
//Explanation: 
//The array [1,3,2] and [2,1,3] have exactly 1 inverse pair.
// 
//
// 
//
// Note: 
//
// 
// The integer n is in the range [1, 1000] and k is in the range [0, 1000]. 
// 
//
// 
// Related Topics Dynamic Programming 
// 👍 361 👎 76

package leetcode.editor.en;

// 2020-12-14 21:01:17
// Zeshi Yang
public class Leetcode0629KInversePairsArray{
    // Java: k-inverse-pairs-array
    public static void main(String[] args) {
        Solution sol = new Leetcode0629KInversePairsArray().new Solution();
        // TO TEST
        int n = 1000;
        int k = 1000;
        int res = sol.kInversePairs(n, k);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
/*
状态转移公式dp[n][k] = dp[n - 1][k] + dp[n - 1][k-1] + ... + dp[n - 1][k - n + 1]
dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1] + dp[i - 1][j - 2] + ... + dp[i - 1][j - (i - 1)]
j - (n - 1) >= 0
所以
 */
class Solution {
    
    public int kInversePairs(int n, int k) {
        long[][] dp = new long[n + 1][k + 1];
        if (k > n * (n - 1) / 2 || k < 0) {
            return 0;
        }
        if (k == 0 || k == n * (n - 1) / 2) {
            return 1;
        }
        
        int module = (int) Math.pow(10, 9) + 7;
        dp[2][0] = 1;
        dp[2][1] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= Math.min(k, n * (n - 1) / 2); j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                if (j >= i) {
                    dp[i][j] -= dp[i - 1][j - i];
                }
                dp[i][j] = (dp[i][j] + module) % module;
            }
        }
        return (int) dp[n][k];
    }
}
//leetcode submit region end(Prohibit modification and deletion)


// Solution 1: DP T(n,k） = O(n^2 * k), S(n, k) = O(n * k)
// 超时
/**
 * 当xxxx为1-4的排列的时候
 * xxxx5，多0个逆序
 * xxx5x，多1个逆序
 * xx5xx，多2个逆序
 * x5xxx，多3个逆序
 * 5xxxx，多4个逆序
 * 所以f(n,k) = f(n-1,k)+f(n-1,k-1) + f(n-1,k-2) + f(n-1,k-3) + ... + f(n-1,k-n+1)
 * 其中k - n + 1 >= 0
 */
class Solution1 {
    
    public int kInversePairs(int n, int k) {
        // corner case
        if (k > (n - 1) * n / 2) {
            return 0;
        }
        
        int module = (int) Math.pow(10, 9) + 7;
        long[][] dp = new long[n + 1][k + 1];
        // initialize
        dp[0][0] = 1;
        
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= k && j <= (i - 1) * i / 2; j++) {
                for (int l = Math.max(j - i + 1, 0); l <= j; l++) { // l + i - 1 == j
                    dp[i][j] += dp[i - 1][l];
                    dp[i][j] %= module;
                }
            }
        }
        return (int) (dp[n][k] % module);
    }
}

// Solution 2: DP T(n,k） = O(n * k), S(n, k) = O(n * k)
/*
f(n,k) = f(n-1,k)+f(n-1,k-1) + f(n-1,k-2) + f(n-1,k-3) + ... + f(n-1,k-n+2) + f(n-1,k-n+1)
f(n,k+1) = f(n-1,k+1) + f(n-1,k) + f(n-1,k-1) + f(n-1,k-2) + ... + f(n-1,k-n+3) + f(n-1,k-n+2)
上面两个式子相减得到f(n,k+1) - f(n,k) = f(n-1,k+1) - f(n-1,k-n+1);
所以f(n,k+1) = f(n-1,k+1) - f(n-1,k-n+1) + f(n,k);
将k替换成k-1得到f(n,k) = f(n-1,k) - f(n-1,k-n) + f(n,k-1);
对于f(x, y), x >= 0, 0 <= y <= (x-1)*x/2,否则f(x, y)的值为0

 */
class Solution2 {
    
    public int kInversePairs(int n, int k) {
        long[][] dp = new long[n + 1][k + 1];
        if (k > n * (n - 1) / 2 || k < 0) {
            return 0;
        }
        if (k == 0 || k == n * (n - 1) / 2) {
            return 1;
        }
        
        int module = (int) Math.pow(10, 9) + 7;
        dp[2][0] = 1;
        dp[2][1] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= Math.min(k, n * (n - 1) / 2); j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                if (j >= i) {
                    dp[i][j] -= dp[i - 1][j - i];
                }
                dp[i][j] = (dp[i][j] + module) % module;
            }
        }
        return (int) dp[n][k];
    }
}
}