//Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2]
// < nums[3].... 
//
// Example 1: 
//
// 
//Input: nums = [1, 5, 1, 1, 6, 4]
//Output: One possible answer is [1, 4, 1, 5, 1, 6]. 
//
// Example 2: 
//
// 
//Input: nums = [1, 3, 2, 2, 3, 1]
//Output: One possible answer is [2, 3, 1, 3, 1, 2]. 
//
// Note: 
//You may assume all input has valid answer. 
//
// Follow Up: 
//Can you do it in O(n) time and/or in-place with O(1) extra space? 
// Related Topics Sort 
// 👍 1238 👎 618

package leetcode.editor.en;

import java.util.Arrays;
import java.util.Random;

// 2020-12-30 18:54:20
// Zeshi Yang
public class Leetcode0324WiggleSortIi{
    // Java: wiggle-sort-ii
    public static void main(String[] args) {
        Solution1 sol1 = new Leetcode0324WiggleSortIi().new Solution1();
        Solution2 sol2 = new Leetcode0324WiggleSortIi().new Solution2();
    
        // TO TEST
        int len = 10000000;
        Random random = new Random();
        int[] nums1 = new int[len];
        int[] nums2 = new int[len];
        for (int i = 0; i < len; i++) {
            nums1[i] = random.nextInt();
            nums2[i] = nums1[i];
        }
        long time0 = System.currentTimeMillis();
        sol1.wiggleSort(nums2);
        long time1 = System.currentTimeMillis();
        sol2.wiggleSort(nums2);
        long time2 = System.currentTimeMillis();
        System.out.println(time1 - time0 + "ms");
        System.out.println(time2 - time1 + "ms");
        
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public void wiggleSort(int[] nums) {
        // corner case
        if (nums == null || nums.length <= 1) {
            return;
        }
        
        Arrays.sort(nums);// O(nlg(n))
        int len = nums.length;
        int mid = (len - 1) / 2;
        int index = 0;
        int[] temp = new int[len];
        for (int i = 0; i <= mid; i++) {
            temp[index] = nums[mid - i];
            if (index + 1 < len) {
                temp[index + 1] = nums[len - 1 - i];
            }
            index += 2;
        }
        // nums = Arrays.copyOf(temp, temp.length);
        System.arraycopy(temp, 0, nums, 0, nums.length);
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

// Solution 1: Average T(n) = O(n), worst case T(n) = O(n^2), S(n) = O(lg(n)), worst S(n) = O(n)
// 34 ms,击败了22.34% 的Java用户, 43.6 MB,击败了11.28% 的Java用户
/*
使用quick selection，中位数对应的两个数字middleLeft和middleRight,
把所有比中位数小的数字从前往后放到奇数位置上
把所有比中位数大的数字，从后往前放到偶数位置上
 */
class Solution1 {
    
    public void wiggleSort(int[] nums) {
        // corner case
        if (nums == null || nums.length <= 1) {
            return;
        }
        int len = nums.length;
        int k = (len + 1) / 2;
        int middleLeft = findPosPartition(k, 0, len - 1, nums); // kth smallest value
        int middleRight = findMiddleRight(k, nums);
        /*
        将偶数index数字都设置成 <= middleLeft
            == middleLeft的部分都尽可能放在左边
        将奇数index数字都设置成 >= middleRight
            == middleRight的尽可能放在右边
         */
        int left = 0; // [0, left] is middleLeft, even pointer
        int right = (len % 2 == 0 ? len - 1 : len - 2); //[right, end] is middleRight, pdd pointer
        
        swap(nums, k - 1, left);
        swap(nums, k, right);
        
        int evenP = (len % 2 == 0 ? len - 2 : len - 1); // even pointer, <-
        int oddP = 1; // odd pointer, ->
        
        for (int i = 0; i < len; i++) {
            if (i > evenP && (i - evenP) % 2 == 0) {
                continue;
            }
            if (nums[i] < middleLeft) { // 比middleLeft小的数字，从右边往左边放
                swap(nums, i, evenP);
                evenP -= 2;
                i--;
            }
        }
        for (int i = 0; i < len; i++) {
            if (oddP > i && (oddP - i) % 2 == 0) {
                continue;
            }
            if (nums[i] > middleRight) { // 比middleRight大的数字，从左往右边放
                swap(nums, i, oddP);
                oddP += 2;
                i--;
            }
        }
        
        for (int i = oddP; i <= right; i += 2) {
            nums[i] = middleRight;
        }
        for (int i = left; i <= evenP; i += 2) {
            nums[i] = middleLeft;
        }
    }
    
    // using quick selection to find kth smallest value in nums and put it the right place
    // average T(n) = O(n), worst T(n) = O(n^2), average S(n) = O(lg(n)), worst S(n) = O(n)
    private int findPosPartition(int k, int left, int right, int[] nums) {
        int pivotIndex = left + (int) (Math.random() * (right - left + 1));
        int pivot = nums[pivotIndex];
        swap(nums, pivotIndex, right);
        
        // S2: use slow and fast pointers, two pointers forward, stable
        /*
         * [0, slow) < pivot
         * [slow, fast) >= pivot
         * [fast, length - 2) to check
         */
        int slow = left;
        int fast;
        for (fast = left; fast < right; fast++) {
            if (nums[fast] < pivot) {
                swap(nums, slow, fast);
                slow++;
            }
        }
        swap(nums, slow, right); // move the pivot from right to the real place
        
        // After operation, the target(pivot) 's index is slow;
        if (k == slow + 1) {
            return nums[slow];
        } else if (k > slow + 1) {
            return findPosPartition(k, slow + 1, right, nums);
        } else {
            return findPosPartition(k, left, slow - 1, nums);
        }
    }
    
    // find the smallest number that >= target
    private int findMiddleRight(int k, int[] nums) {
        int res = Integer.MAX_VALUE;
        for (int i = k; i < nums.length; i++) {
            res = Math.min(res, nums[i]);
        }
        return res;
    }
    
    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}

// Solution 2:T(n) = O(nlg(n)), S(n) = O(n)
// 3 ms,击败了86.77% 的Java用户, 42.1 MB,击败了41.11% 的Java用户
class Solution2 {
    
    public void wiggleSort(int[] nums) {
        // corner case
        if (nums == null || nums.length <= 1) {
            return;
        }
        
        Arrays.sort(nums);// O(nlg(n))
        int len = nums.length;
        int mid = (len - 1) / 2;
        int index = 0;
        int[] temp = new int[len];
        for (int i = 0; i <= mid; i++) {
            temp[index] = nums[mid - i];
            if (index + 1 < len) {
                temp[index + 1] = nums[len - 1 - i];
            }
            index += 2;
        }
        System.arraycopy(temp, 0, nums, 0, nums.length);
    }
    
}
}
