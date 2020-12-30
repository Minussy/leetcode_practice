//Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one
// sorted array. 
//
// Note: 
//
// 
// The number of elements initialized in nums1 and nums2 are m and n respectivel
//y. 
// You may assume that nums1 has enough space (size that is equal to m + n) to h
//old additional elements from nums2. 
// 
//
// Example: 
//
// 
//Input:
//nums1 = [1,2,3,0,0,0], m = 3
//nums2 = [2,5,6],       n = 3
//
//Output: [1,2,2,3,5,6]
// 
//
// 
// Constraints: 
//
// 
// -10^9 <= nums1[i], nums2[i] <= 10^9 
// nums1.length == m + n 
// nums2.length == n 
// 
// Related Topics Array Two Pointers 
// 👍 2301 👎 4223

package leetcode.editor.en;

// 2020-07-26 13:49:58
// Zeshi Yang
public class Leetcode0088MergeSortedArray{
    // Java: merge-sorted-array
    public static void main(String[] args) {
        Solution sol = new Leetcode0088MergeSortedArray().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
/*
两个指针，从后往前移动
 */
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int pos = m + n - 1;
        int i = m - 1;
        int j = n - 1;

        while(i >=0 && j >=0) {
            if(nums1[i] > nums2[j]) {
                nums1[pos--] = nums1[i--];
            }
            else {
                nums1[pos--] = nums2[j--];
            }
        }

        while (j >= 0) {
            nums1[pos--] = nums2[j--];
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}