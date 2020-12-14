//Given n non-negative integers a1, a2, ..., an , where each represents a point 
//at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of
// line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis for
//ms a container, such that the container contains the most water. 
//
// Note: You may not slant the container and n is at least 2. 
//
// 
//
// 
//
// The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In thi
//s case, the max area of water (blue section) the container can contain is 49. 
//
// 
//
// Example: 
//
// 
//Input: [1,8,6,2,5,4,8,3,7]
//Output: 49 Related Topics Array Two Pointers 
// 👍 6904 👎 607

package leetcode.editor.en;

// 2020-09-09 18:22:49
// Zeshi Yang
public class Leetcode0011ContainerWithMostWater{
    // Java: container-with-most-water
    public static void main(String[] args) {
        Solution sol = new Leetcode0011ContainerWithMostWater().new Solution();
        // TO TEST
        
        System.out.println();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
// 2 pointers
class Solution {
    public int maxArea(int[] height) {
        int maxArea = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            maxArea = Math.max(maxArea, (right - left) * Math.min(height[left], height[right]));
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}