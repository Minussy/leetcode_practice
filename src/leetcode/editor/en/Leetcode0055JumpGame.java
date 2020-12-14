//Given an array of non-negative integers, you are initially positioned at the f
//irst index of the array. 
//
// Each element in the array represents your maximum jump length at that positio
//n. 
//
// Determine if you are able to reach the last index. 
//
// 
// Example 1: 
//
// 
//Input: nums = [2,3,1,1,4]
//Output: true
//Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
// 
//
// Example 2: 
//
// 
//Input: nums = [3,2,1,0,4]
//Output: false
//Explanation: You will always arrive at index 3 no matter what. Its maximum jum
//p length is 0, which makes it impossible to reach the last index.
// 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 3 * 10^4 
// 0 <= nums[i][j] <= 10^5 
// 
// Related Topics Array Greedy 
// 👍 4353 👎 329

package leetcode.editor.en;

// 2020-07-26 12:45:53
// Zeshi Yang
public class Leetcode0055JumpGame{
    // Java: jump-game
    public static void main(String[] args) {
        Solution sol = new Leetcode0055JumpGame().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
// Solution 3: greedy
class Solution {
    //S3 greedy
    //fill the dp table from right to left
    public boolean canJump(int[] nums) {
        //corner case
        if (nums == null || nums.length < 2) {
            return true;
        }

        int maxRange = 0;
        for (int i = 0; i <= maxRange; i++ ){
            maxRange = Math.max(maxRange, i + nums[i]);
            if (maxRange >= nums.length -1) return true;
        }
        return false;

    }
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution1: DFS recursion
class Solution1 {
    // S1: DFS recursion
    public boolean canJump(int[] nums) {
        int index = 0;
        boolean result =  canJump(nums, index);
        return result;
    }

    public boolean canJump(int[] nums, int index) {
        if (index >= nums.length - 1) return true; // base case
        int jump = nums[index];
        for (int i = 1; i <= jump; i++) { // 从远到近
            if (canJump(nums, index + i)) return true;
        }
        return false;
    }
}

// Solution 2: dynamic programming
//Solution 2_1 dynamic programming
//fill the dp table from right to left
class Solution2_1 {
    //S2.1 dynamic programming
    //fill the dp table from right to left
    public boolean canJump(int[] nums) {
        //corner case
        if (nums == null || nums.length < 2) {
            return true;
        }
        int n = nums.length;
        boolean[] dp = new boolean[n];

        dp[n - 1] = true;

        for (int i = n - 2; i >= 0; i--) {
            for (int j = 1; j <= nums[i]; j++) {
                if (i + j < n && dp[i + j]) { // i + j >= n-1 || dp[i+j]
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[0];
    }
}
//Solution 2_2 dynamic programming
//fill the dp table from right to left
class Solution2_2 {
    //S2.2 dynamic programming
    //fill the dp table from right to left
    public boolean canJump(int[] nums) {
        //corner case
        if (nums == null || nums.length < 2) {
            return true;
        }
        int n = nums.length;
        boolean[] dp = new boolean[n];
        dp[n - 1] = true;

        for (int i = n - 2; i >= 0; i--) {
            for (int j = nums[i]; j >= 1; j--) {
                if (i + j >= n - 1 || dp[i + j] == true) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[0] == true;
    }
}

// Solution 3: greedy
class Solution3 {
    //S3 greedy
    //fill the dp table from right to left
    public boolean canJump(int[] nums) {
        //corner case
        if (nums == null || nums.length < 2) {
            return true;
        }

        int maxRange = 0;
        for (int i = 0; i <= maxRange; i++ ){
            maxRange = Math.max(maxRange, i + nums[i]);
            if (maxRange >= nums.length -1) return true;
        }
        return false;

    }
}
}