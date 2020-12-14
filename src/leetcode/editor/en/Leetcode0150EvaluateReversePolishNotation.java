//Evaluate the value of an arithmetic expression in Reverse Polish Notation. 
//
// Valid operators are +, -, *, /. Each operand may be an integer or another exp
//ression. 
//
// Note: 
//
// 
// Division between two integers should truncate toward zero. 
// The given RPN expression is always valid. That means the expression would alw
//ays evaluate to a result and there won't be any divide by zero operation. 
// 
//
// Example 1: 
//
// 
//Input: ["2", "1", "+", "3", "*"]
//Output: 9
//Explanation: ((2 + 1) * 3) = 9
// 
//
// Example 2: 
//
// 
//Input: ["4", "13", "5", "/", "+"]
//Output: 6
//Explanation: (4 + (13 / 5)) = 6
// 
//
// Example 3: 
//
// 
//Input: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
//Output: 22
//Explanation: 
//  ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
//= ((10 * (6 / (12 * -11))) + 17) + 5
//= ((10 * (6 / -132)) + 17) + 5
//= ((10 * 0) + 17) + 5
//= (0 + 17) + 5
//= 17 + 5
//= 22
// 
// Related Topics Stack 
// 👍 1074 👎 474

package leetcode.editor.en;

import java.util.*;
// 2020-08-04 11:36:08
// Zeshi Yang
public class Leetcode0150EvaluateReversePolishNotation{
    // Java: evaluate-reverse-polish-notation
    public static void main(String[] args) {
        Solution sol = new Leetcode0150EvaluateReversePolishNotation().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        int result = 0;
        for (String str: tokens) {
            if (isNumber(str)) {
                stack.push(Integer.parseInt(str));
            }
            else if (isOperand(str)) {
                stack.push(operate(str, stack.pop(), stack.pop()));
            }
        }
        return stack.pop();
    }

    private boolean isNumber(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        char c = str.charAt(0);

        if (!Character.isDigit(c)) { // when the first note does not equesl to digit
            if (!(c == '+' || c == '-')) {
                return false;
            }
            else if (str.length() == 1) {
                return false;
            }
        }

        for (int i = 1; i < str.length(); i++) {
            if(!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private boolean isOperand(String str) {
        if (str == null || str.length() != 1) {
            return false;
        }
        char c = str.charAt(0);

        return ( c =='+' || c == '-' || c == '*' || c == '/');
    }

    private int operate(String str, int i1, int i2) {
        char c = str.charAt(0);
        switch(c) {
            case '+': return i2 + i1;
            case '-': return i2 - i1;
            case '*': return i2 * i1;
            case '/': return i2 / i1;
        }
        return 0;
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}