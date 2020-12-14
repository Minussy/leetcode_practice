//Given a string s, find the longest palindromic substring in s. You may assume 
//that the maximum length of s is 1000. 
//
// Example 1: 
//
// 
//Input: "babad"
//Output: "bab"
//Note: "aba" is also a valid answer.
// 
//
// Example 2: 
//
// 
//Input: "cbbd"
//Output: "bb"
// 
// Related Topics String Dynamic Programming 
// 👍 7185 👎 543

package leetcode.editor.en;

// 2020-07-26 12:36:56
// Zeshi Yang
public class Leetcode0005LongestPalindromicSubstring{
    // Java: longest-palindromic-substring
    public static void main(String[] args) {
        Solution sol = new Leetcode0005LongestPalindromicSubstring().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String longestPalindrome(String s) {
        // Solution 4: Expand Around Center
        // corner case
        if (s== null || s.length() <= 1) {
            return s;
        }
        int length = s.length();
        int[] max = new int[1];
        int[] result = new int[2];

        for (int i = 0; i < length; i++) {
            //当剩下的subString的最大长度比max[0]小的时候，就不需要比较了
            if (2 * Math.min(i + 1, length - i) < max[0] && 2 * Math.min(i + 1, length - i - 1) < max[0]) {
                break;
            }
            findMax(s, i, i, max, result);
            findMax(s, i, i + 1, max, result);
        }
        // subString的区间是[left, right)
        return s.substring(result[0], result[1] + 1);
    }

    private void findMax(String s, int left, int right, int[] max, int[] result) {
        int length = s.length();
        while(left >= 0 && right < length) {
            if(s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            }
            else {
                break;
            }
        }

        int dist = right - left - 1;

        if(dist > max[0]) {
            max[0] = dist;
            result[0] = left + 1;
            result[1] = right - 1;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}