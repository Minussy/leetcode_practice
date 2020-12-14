//Suppose an array sorted in ascending order is rotated at some pivot unknown to
// you beforehand. 
//
// (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]). 
//
// Find the minimum element. 
//
// The array may contain duplicates. 
//
// Example 1: 
//
// 
//Input: [1,3,5]
//Output: 1 
//
// Example 2: 
//
// 
//Input: [2,2,2,0,1]
//Output: 0 
//
// Note: 
//
// 
// This is a follow up problem to Find Minimum in Rotated Sorted Array. 
// Would allow duplicates affect the run-time complexity? How and why? 
// 
// Related Topics Array Binary Search 
// 👍 1095 👎 234

package leetcode.editor.en;

// 2020-08-04 11:40:03
// Zeshi Yang
public class Leetcode0154FindMinimumInRotatedSortedArrayIi{
    // Java: find-minimum-in-rotated-sorted-array-ii
    public static void main(String[] args) {
        Solution sol = new Leetcode0154FindMinimumInRotatedSortedArrayIi().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findMin(int[] nums) {
        //corner case
        if(nums == null || nums.length ==0) return -1;

        //edge case
        int left = 0, right = nums.length - 1, mid = 0;
        // if(nums.length == 1) return nums[0];
        // if(nums[left] < nums[right]) return nums[left];

        //general case
        while(left + 1 < right){
            mid = left + (right - left) / 2;
            if(nums[mid] < nums[right]) right = mid;
            else if(nums[mid] > nums[right]) left = mid;
            else /*(nums[mid] == nums[left])*/ right--;
            // else right--;
        }
        return nums[right] < nums[left] ? nums[right] : nums[left];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}