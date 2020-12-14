//
//Given a list of sorted characters letters containing only lowercase letters, a
//nd given a target letter target, find the smallest element in the list that is l
//arger than the given target.
// 
//Letters also wrap around. For example, if the target is target = 'z' and lette
//rs = ['a', 'b'], the answer is 'a'.
// 
//
// Examples: 
// 
//Input:
//letters = ["c", "f", "j"]
//target = "a"
//Output: "c"
//
//Input:
//letters = ["c", "f", "j"]
//target = "c"
//Output: "f"
//
//Input:
//letters = ["c", "f", "j"]
//target = "d"
//Output: "f"
//
//Input:
//letters = ["c", "f", "j"]
//target = "g"
//Output: "j"
//
//Input:
//letters = ["c", "f", "j"]
//target = "j"
//Output: "c"
//
//Input:
//letters = ["c", "f", "j"]
//target = "k"
//Output: "c"
// 
// 
//
// Note: 
// 
// letters has a length in range [2, 10000]. 
// letters consists of lowercase letters, and contains at least 2 unique letters
//. 
// target is a lowercase letter. 
// 
// Related Topics Binary Search 
// 👍 463 👎 568

package leetcode.editor.en;

// 2020-09-24 15:32:44
// Zeshi Yang
public class Leetcode0744FindSmallestLetterGreaterThanTarget{
    // Java: find-smallest-letter-greater-than-target
    public static void main(String[] args) {
        Solution sol = new Leetcode0744FindSmallestLetterGreaterThanTarget().new Solution();
        // TO TEST
        char[] letters = {'e','e','e','e','e','e','n','n','n','n'};
        char target = 'e';
        char res = sol.nextGreatestLetter(letters, target);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        int len = letters.length;
        // corner case
        if (letters[len - 1] <= target) {
            return letters[0];
        }
        
        int left = 0;
        int right = len - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (letters[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return letters[left];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}