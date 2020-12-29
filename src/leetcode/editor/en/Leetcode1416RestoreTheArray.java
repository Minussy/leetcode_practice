//A program was supposed to print an array of integers. The program forgot to pr
//int whitespaces and the array is printed as a string of digits and all we know i
//s that all integers in the array were in the range [1, k] and there are no leadi
//ng zeros in the array. 
//
// Given the string s and the integer k. There can be multiple ways to restore t
//he array. 
//
// Return the number of possible array that can be printed as a string s using t
//he mentioned program. 
//
// The number of ways could be very large so return it modulo 10^9 + 7 
//
// 
// Example 1: 
//
// 
//Input: s = "1000", k = 10000
//Output: 1
//Explanation: The only possible array is [1000]
// 
//
// Example 2: 
//
// 
//Input: s = "1000", k = 10
//Output: 0
//Explanation: There cannot be an array that was printed this way and has all in
//teger >= 1 and <= 10.
// 
//
// Example 3: 
//
// 
//Input: s = "1317", k = 2000
//Output: 8
//Explanation: Possible arrays are [1317],[131,7],[13,17],[1,317],[13,1,7],[1,31
//,7],[1,3,17],[1,3,1,7]
// 
//
// Example 4: 
//
// 
//Input: s = "2020", k = 30
//Output: 1
//Explanation: The only possible array is [20,20]. [2020] is invalid because 202
//0 > 30. [2,020] is ivalid because 020 contains leading zeros.
// 
//
// Example 5: 
//
// 
//Input: s = "1234567890", k = 90
//Output: 34
// 
//
// 
// Constraints: 
//
// 
// 1 <= s.length <= 10^5. 
// s consists of only digits and doesn't contain leading zeros. 
// 1 <= k <= 10^9. 
// Related Topics Dynamic Programming 
// 👍 194 👎 5

package leetcode.editor.en;

import java.util.*;
// 2020-12-28 16:27:34
// Zeshi Yang
public class Leetcode1416RestoreTheArray{
    // Java: restore-the-array
    public static void main(String[] args) {
        Solution sol = new Leetcode1416RestoreTheArray().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int numberOfArrays(String s, int k) {
        
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
