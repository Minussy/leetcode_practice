//Given an integer array nums, find the contiguous subarray within an array (con
//taining at least one number) which has the largest product. 
//
// Example 1: 
//
// 
//Input: [2,3,-2,4]
//Output: 6
//Explanation: [2,3] has the largest product 6.
// 
//
// Example 2: 
//
// 
//Input: [-2,0,-1]
//Output: 0
//Explanation: The result cannot be 2, because [-2,-1] is not a subarray. 
// Related Topics Array Dynamic Programming 
// 👍 4257 👎 157

package leetcode.editor.en;

// 2020-07-26 12:44:23
// Zeshi Yang
public class Leetcode0152MaximumProductSubarray{
    // Java: maximum-product-subarray
    public static void main(String[] args) {
        Solution sol = new Leetcode0152MaximumProductSubarray().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxProduct(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int preMax = nums[0];
        int preMin = nums[0];
        int max = nums[0];
        int curMax;
        int curMin;

        for (int i = 1; i < nums.length; i++) {
            int temp = nums[i];
            curMax = Math.max(Math.max(preMax * temp, preMin * temp), temp);
            curMin = Math.min(Math.min(preMax * temp, preMin * temp), temp);
            max = Math.max(curMax, max);
            preMax = curMax;
            preMin = curMin;
        }
        return max;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}