//Given an array of size n, find the majority element. The majority element is t
//he element that appears more than ⌊ n/2 ⌋ times. 
//
// You may assume that the array is non-empty and the majority element always ex
//ist in the array. 
//
// Example 1: 
//
// 
//Input: [3,2,3]
//Output: 3 
//
// Example 2: 
//
// 
//Input: [2,2,1,1,1,2,2]
//Output: 2
// 
// Related Topics Array Divide and Conquer Bit Manipulation 
// 👍 3326 👎 224

package leetcode.editor.en;

// 2020-07-29 21:49:44
// Zeshi Yang
public class Leetcode0169MajorityElement {

	// Java: majority-element
	public static void main(String[] args) {
		Solution sol = new Leetcode0169MajorityElement().new Solution();
		// TO TEST

		int[] nums = {2, 2, 1, 1, 1, 2, 2, 1, 1};
		int res = sol.majorityElement(nums);
		System.out.println(res);
	}

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public int majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new RuntimeException("should never happen");
        }
        int INT_BIT_NUMBER = 32;
        int[] count = new int[INT_BIT_NUMBER];
        for (int num : nums) {
            for (int i = 0; i < INT_BIT_NUMBER; i++) {
                count[i] += num & 1;
                num >>= 1;
            }
        }
        int res = 0;
        int len = nums.length;
        int halfLen = len / 2;
        for (int i = 0; i < INT_BIT_NUMBER; i++) {
            if (count[i] > halfLen) {
                res += (1 << i);
            }
        }
        return res;
    }


}
//leetcode submit region end(Prohibit modification and deletion)

}