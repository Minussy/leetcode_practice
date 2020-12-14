//Given an array nums, write a function to move all 0's to the end of it while m
//aintaining the relative order of the non-zero elements. 
//
// Example: 
//
// 
//Input: [0,1,0,3,12]
//Output: [1,3,12,0,0] 
//
// Note: 
//
// 
// You must do this in-place without making a copy of the array. 
// Minimize the total number of operations. 
// Related Topics Array Two Pointers 
// 👍 3889 👎 127

package leetcode.editor.en;

// 2020-07-26 13:38:50
// Zeshi Yang
public class Leetcode0283MoveZeroes {

	// Java: move-zeroes
	public static void main(String[] args) {
		Solution sol = new Leetcode0283MoveZeroes().new Solution();
		// TO TEST

		System.out.println();
	}

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        int slow = 0;
        int fast = slow + 1;
        while (fast < nums.length) {
            if (nums[slow] != 0) {
                slow++;
                fast++;
            } else if (nums[fast] == 0) {
                fast++;
            } else {
                swap(nums, slow++, fast++);
            }
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
/*
    not 0 in [0,slow)
    0 in [slow, fast)
    to be checked in [fast, right]
*/

// Solution 1: 动fast指针，最后的位置补0
class Solution1 {

    public int[] moveZeroKeepOrder(int[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        int slow = 0;
        for (int fast = 0; fast < array.length; fast++) {
            if (array[fast] != 0) {
                array[slow++] = array[fast];
            }
        }
        while (slow < array.length) {
            array[slow++] = 0;
        }
        return array;
    }
}

// Solution 2, 动的是slow指针
class Solution2 {
    public int[] moveZero(int[] array){
        return null;
    }
}

// Solution 3:  双指针，0的位置交换值
class Solution3 {
    
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        int slow = 0;
        int fast = slow + 1;
        while (fast < nums.length) {
            if (nums[slow] != 0) {
                slow++;
                fast++;
            } else if (nums[fast] == 0) {
                fast++;
            } else {
                swap(nums, slow++, fast++);
            }
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

}