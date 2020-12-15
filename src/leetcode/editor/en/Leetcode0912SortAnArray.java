//Given an array of integers nums, sort the array in ascending order. 
//
// 
// Example 1: 
// Input: nums = [5,2,3,1]
//Output: [1,2,3,5]
// Example 2: 
// Input: nums = [5,1,1,2,0,0]
//Output: [0,0,1,1,2,5]
// 
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 50000 
// -50000 <= nums[i] <= 50000 
// 
// 👍 563 👎 310

package leetcode.editor.en;

import java.util.Arrays;

// 2020-09-26 16:06:34
// Zeshi Yang
public class Leetcode0912SortAnArray{
    // Java: sort-an-array
    public static void main(String[] args) {
        Solution sol = new Leetcode0912SortAnArray().new Solution();
        // TO TEST
        int[] nums = {5, 2, 3, 1};
        sol.sortArray(nums);
        System.out.println(Arrays.toString(nums));
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public int[] sortArray(int[] nums) {
        // corner case
        if (nums == null || nums.length <= 1) {
            return nums;
        }
        int len = nums.length;
        int[] helper = new int[len];
        mergeSort(0, len - 1, nums, helper); //TODO
        return nums;
    }
    
    // merge sort the nums[start] to nums[end]
    private void mergeSort(int start, int end, int[] nums, int[] helper) {
        if (start == end) {
            return;
        }
        int mid = start + (end - start) / 2;
        mergeSort(start, mid, nums, helper);
        mergeSort(mid + 1, end, nums, helper);
        merge(start, mid, end, nums, helper);
    }
    
    // merge robFrom nums[start]- nums[mid], and nums[mid + 1] to nums[end]
    private void merge(int start, int mid, int end, int[] nums, int[] helper) {
        int index = start;
        int left = start;
        int right = mid + 1;
        while (left <= mid && right <= end) {
            if (nums[left] < nums[right]) {
                helper[index++] = nums[left++];
            } else {
                helper[index++] = nums[right++];
            }
        }
        while (left <= mid) {
            helper[index++] = nums[left++];
        }
        // while (right <= end) {
        //     robFrom[index++] = nums[right++];
        // }
        System.arraycopy(helper, start, nums, start, right - start);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: merge sort with robFrom array
class Solution1 {
    
    public int[] sortArray(int[] nums) {
        // corner case
        if (nums == null || nums.length <= 1) {
            return nums;
        }
        int len = nums.length;
        int[] helper = new int[len];
        mergeSort(0, len - 1, nums, helper); //TODO
        return nums;
    }
    
    // merge sort the nums[start] to nums[end]
    private void mergeSort(int start, int end, int[] nums, int[] helper) {
        if (start == end) {
            return;
        }
        int mid = start + (end - start) / 2;
        mergeSort(start, mid, nums, helper);
        mergeSort(mid + 1, end, nums, helper);
        merge(start, mid, end, nums, helper);
    }
    
    // merge robFrom nums[start]- nums[mid], and nums[mid + 1] to nums[end]
    private void merge(int start, int mid, int end, int[] nums, int[] helper) {
        int index = start;
        int left = start;
        int right = mid + 1;
        while (left <= mid && right <= end) {
            if (nums[left] < nums[right]) {
                helper[index++] = nums[left++];
            } else {
                helper[index++] = nums[right++];
            }
        }
        while (left <= mid) {
            helper[index++] = nums[left++];
        }
        while (right <= end) {
            helper[index++] = nums[right++];
        }
        System.arraycopy(helper, start, nums, start, end - start + 1);
    }
}
}