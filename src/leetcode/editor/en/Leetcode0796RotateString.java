//We are given two strings, A and B. 
//
// A shift on A consists of taking string A and moving the leftmost character to
// the rightmost position. For example, if A = 'abcde', then it will be 'bcdea' af
//ter one shift on A. Return True if and only if A can become B after some number 
//of shifts on A. 
//
// 
//Example 1:
//Input: A = 'abcde', B = 'cdeab'
//Output: true
//
//Example 2:
//Input: A = 'abcde', B = 'abced'
//Output: false
// 
//
// Note: 
//
// 
// A and B will have length at most 100. 
// 
// 👍 732 👎 52

package leetcode.editor.en;

// 2020-07-26 12:30:34
// Zeshi Yang
public class Leetcode0796RotateString{
    // Java: rotate-string
    public static void main(String[] args) {
        Solution sol = new Leetcode0796RotateString().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean rotateString(String A, String B) {
        //S2: concatenate 2 A into a new String, and see whether it has B
        return A.length() == B.length() && (A + A).contains(B);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: check by cycle
class Solution1 {
    public boolean rotateString(String A, String B) {
        // S1: compare char by char by circle
        if(A.length() != B.length()) {
            return false;
        }
        if (A.length() == 0) {
            return true;
        }

        int length = A.length();
        search:
        for (int s = 0; s < length; s++) {
            for (int i = 0; i < length; i++) {
                if (A.charAt((s + i) % length) != B.charAt(i)) {
                    continue search;
                }
            }
            return true;
        }
        return false;
    }
}
// Solution 2: check by synthesis 2 A
class Solution2 {
    public boolean rotateString(String A, String B) {
        //S2: concatenate 2 A into a new String, and see whether it has B
        return A.length() == B.length() && (A + A).contains(B);
    }
}
}