//Given an array of non-negative integers, you are initially positioned at the f
//irst index of the array. 
//
// Each element in the array represents your maximum jump length at that positio
//n. 
//
// Your goal is to reach the last index in the minimum number of jumps. 
//
// Example: 
//
// 
//Input: [2,3,1,1,4]
//Output: 2
//Explanation: The minimum number of jumps to reach the last index is 2.
//    Jump 1 step from index 0 to 1, then 3 steps to the last index. 
//
// Note: 
//
// You can assume that you can always reach the last index. 
// Related Topics Array Greedy 
// 👍 2595 👎 138

package leetcode.editor.en;

// 2020-07-26 12:47:55
// Zeshi Yang
public class Leetcode0045JumpGameIi{
    // Java: jump-game-ii
    public static void main(String[] args) {
        Solution sol = new Leetcode0045JumpGameIi().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
    /**
     4  	5  	0  	3  	2     	1  	1  	10	3
     i
     preMax = 0	                       pm
     curMax = 0                               cm
     minStep = 0
     curMax always record the farthest place that previous elements can reach
     preMax always record the farthest place of the same step
     */
// first to think about corner cases: null, length = 1, or it can not reach the last elemenet
class Solution {
    // Zeshi Yang's Code
    public int jump(int[] nums) {
        // corner case
        if (nums == null || nums.length <=1) {
            return 0;
        }
        int length = nums.length;

        int preMax = 0;
        int curMax = 0;
        int minStep = 0;

        for (int i = 0; i < length; i++) {
            // corner case, if it can not reach the last element
            if (i > curMax) {
                return -1;
            }

            //edge case
            if (curMax >= length - 1) { // it has been far than the last element
                minStep++;
                return minStep;
            }
            if( i > preMax) { // to update the preMax
                minStep++;
                preMax = curMax;
            }
            curMax = Math.max(curMax, i + nums[i]);

        }
        return minStep;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}