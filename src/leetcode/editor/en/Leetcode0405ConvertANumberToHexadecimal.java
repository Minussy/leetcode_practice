//
//Given an integer, write an algorithm to convert it to hexadecimal. For negativ
//e integer, two’s complement method is used.
// 
//
// Note:
// 
// All letters in hexadecimal (a-f) must be in lowercase. 
// The hexadecimal string must not contain extra leading 0s. If the number is ze
//ro, it is represented by a single zero character '0'; otherwise, the first chara
//cter in the hexadecimal string will not be the zero character. 
// The given number is guaranteed to fit within the range of a 32-bit signed int
//eger. 
// You must not use any method provided by the library which converts/formats th
//e number to hex directly. 
// 
// 
//
// Example 1:
// 
//Input:
//26
//
//Output:
//"1a"
// 
// 
//
// Example 2:
// 
//Input:
//-1
//
//Output:
//"ffffffff"
// 
// Related Topics Bit Manipulation 
// 👍 527 👎 124

package leetcode.editor.en;

// 2020-12-15 15:29:38
// Zeshi Yang
public class Leetcode0405ConvertANumberToHexadecimal{
    // Java: convert-a-number-to-hexadecimal
    public static void main(String[] args) {
        Solution sol = new Leetcode0405ConvertANumberToHexadecimal().new Solution();
        // TO TEST
        int num = -2;
        String res = sol.toHex(num);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public String toHex(int num) {
        // corner case
        if (num == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        int hex = 16;
        boolean isNegative = false;
        if (num < 0) {
            isNegative = true;
            num = -num - 1;
        }
        while (num != 0) {
            int remainder = num % hex;
            char hexRemainder = getHexChar(remainder, hex);
            num /= hex;
            sb.append(hexRemainder);
        }
        if (isNegative) {
            char[] chars = sb.toString().toCharArray();
            sb.setLength(0);
            for (int i = 0; i < 8; i++) {
                int minuend = 15;
                if (i < chars.length) {
                    int subtrahend =
                    ch -= (chars[i] - '0');
                }
                sb.append(ch);
            }
        }
        sb.reverse();
        return sb.toString();
    }
    
    private char getHexChar(int num, int hex) {
        switch (num) {
            case 10:
                return 'a';
            case 11:
                return 'b';
            case 12:
                return 'c';
            case 13:
                return 'd';
            case 14:
                return 'e';
            case 15:
                return 'f';
            default:
                return (char) (num + '0');
        }
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}