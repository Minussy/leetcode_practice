//Implement a basic calculator to evaluate a simple expression string. 
//
// The expression string may contain open ( and closing parentheses ), the plus 
//+ or minus sign -, non-negative integers and empty spaces . 
//
// The expression string contains only non-negative integers, +, -, *, / operato
//rs , open ( and closing parentheses ) and empty spaces . The integer division sh
//ould truncate toward zero. 
//
// You may assume that the given expression is always valid. All intermediate re
//sults will be in the range of [-2147483648, 2147483647]. 
//
// Some examples: 
//
// 
//"1 + 1" = 2
//" 6-4 / 2 " = 4
//"2*(5+5*2)/3+(6/2+8)" = 21
//"(2+6* 3+5- (3*14/7+2)*5)+3"=-12
// 
//
// 
//
// Note: Do not use the eval built-in library function. 
// Related Topics String Stack 
// 👍 457 👎 199

package leetcode.editor.en;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

// 2020-07-16 22:47:55
// Zeshi Yang
public class Leetcode0772BasicCalculatorIii {

	// Java: basic-calculator-iii
	public static void main(String[] args) {
		Solution sol = new Leetcode0772BasicCalculatorIii().new Solution();
		// TO TEST

		System.out.println();
	}

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
	
	Map<Character, Integer> priorityMap;
	Stack<Integer> nums;
	Stack<Character> ops;
	
	public int calculate(String s) {
		if (s == null | s.length() == 0) {
			return 0;
		}
		int num;
		initialize();
		
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == ' ') {
				continue;
			}
			if (Character.isDigit(c)) {
				num = c - '0';
				while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
					num = num * 10 + (s.charAt(i + 1) - '0');
					i++;
				}
				nums.push(num);
			} else if (isOperand(c)) {
				// - 表示负数的时候，前面补0，比如-1 * (-3)
				if (c == '-' && (i == 0 || s.charAt(i - 1) == '(')) {
					nums.push(0);
				}
				while (!ops.isEmpty() && calculatePreviousOper(c, ops.peek())) {
					nums.push(operate(ops.pop(), nums.pop(), nums.pop()));
				}
				ops.push(c);
			} else if (c == '(') {
				ops.push(c);
			} else if (c == ')') {
				while (!ops.isEmpty() && ops.peek() != '(') {
					nums.push(operate(ops.pop(), nums.pop(), nums.pop()));
				}
				ops.pop();
			}
		}
		while (!ops.isEmpty()) {
			nums.push(operate(ops.pop(), nums.pop(), nums.pop()));
		}
		return nums.pop();
	}
	
	private void initialize() {
		priorityMap = new HashMap<>();
		priorityMap.put('(', 3);
		priorityMap.put(')', 3);
		priorityMap.put('*', 2);
		priorityMap.put('/', 2);
		priorityMap.put('+', 1);
		priorityMap.put('-', 1);
		nums = new Stack<>();
		ops = new Stack<>();
	}
	
	private boolean isOperand(char c) {
		return (c == '+' || c == '-' || c == '*' || c == '/');
	}
	
	/** return true; iff the rank of current operator is higher than the previous one
	 * false;
	 * else return true; (we need to calculate the previous operator)
	 * (, rank 3
	 * * / rank 2
	 * + - rank 1
	 */
	private boolean calculatePreviousOper(char c1, char c2) {
		// return priorityMap.get(c1) <= priorityMap.get(c2);
		if (c2 == '(') {
			return false;
		} else {
			return (c1 != '*' && c1 != '/') || (c2 != '+' && c2 != '-');
		}
	}

	private int operate(char c, int i1, int i2) {
		switch (c) {
			case '+':
				return i2 + i1;
			case '-':
				return i2 - i1;
			case '*':
				return i2 * i1;
			case '/':
				return i2 / i1;
		}
		return 0;
	}
}

//leetcode submit region end(Prohibit modification and deletion)
// Solution 2:
// time = O(n), space = O(n)
/*
1. 计算器3这个题，主要借助1个optrMap来存除括号以外的所有操作运算符极其有限权值(分别用int来表示)，
    以及2个stack分别来存取数字和非数字的字符(运算符，括号以及空格)。
2. 为了保证最后一步运算能得以进行，我们手动在整个表达式的前后分别加上左括号和右括号。
3. 为了处理一上来就是个‘-’的情况，我们手动在这种情况前面添加1个0，这样就可以把-5 → 0 - 5。
4. 在处理运算方面，我们分为3种情况，分别是空格，左右括号和运算符，以及数字。而对于处理addOptr这个method来说，
    我们又分为左括号，右括号和其他运算符3种case，而其中左括号下我们又分别处理空格和‘-’的情况。
5. 我们在符号栈里只需要压入左括号和运算符，右括号是不需要压入的。遇到右括号，我们只需要弹栈，
       然后拿出2个数字栈里的数字进行计算，直到遇到弹出的是左括号为止。
6. 遇到运算符时，我们首先看符号栈里是否有运算符，没有的话就没有必要从数字栈里拿数据来计算，
    因为所有的计算都是靠符号栈里的运算符来进行的，只有当符号栈顶的运算符优先级大于当前的optr才会拿出来能算就算，
    因此如果符号栈为空，则完全没有计算的必要，直接只需要将当前的optr压入符号栈即可。
 */
class Solution2 {
    public int calculate(String s) {
        // corner case
        if (s == null) {
            throw new IllegalArgumentException();
        }

        // initialization
        HashMap<Character, Integer> optrMap = new HashMap<>();
        optrMap.put('+', 1);
        optrMap.put('-', 1);
        optrMap.put('*', 2);
        optrMap.put('/', 2);

        Stack<Integer> numStack = new Stack<>();
        Stack<Character> optrStack = new Stack<>();

        // traversal
        int i = 0;
        addOptr(numStack, optrStack, optrMap, '(', s, i - 1);

        while (i < s.length()) {
            char ch = s.charAt(i);
            // case 1: ' '
            if (ch == ' ') {
                i++;
            } else if (ch == '(' || ch == ')' || optrMap.containsKey(ch)) { // case 2: ( ) + - * /
                addOptr(numStack, optrStack, optrMap, ch, s, i);
                i++;
            } else if (ch >= '0' && ch <= '9') { // case 3: numbers
                // 拼数
                int val = 0;
                while (i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                    val = val * 10 + s.charAt(i) - '0';
                    i++;
                }
                numStack.push(val);
            } else {
                throw new IllegalArgumentException();
            }
        }

        addOptr(numStack, optrStack, optrMap, ')', s, i); // i = s.length()
        return numStack.pop();
    }

    private void addOptr(Stack<Integer> numStack, Stack<Character> optrStack,
            HashMap<Character, Integer> optrMap, char optr, String s, int i) {
        // case 1: (
        if (optr == '(') {
            optrStack.push(optr);
            int idx = i + 1;
            while (idx < s.length()) {
                char ch = s.charAt(idx);
                if (ch == ' ') {
                    idx++;
                    continue;
                } else if (ch == '-') {
                    numStack.push(0);
                    break;
                } else {
                    break;
                }
            }
        } else if (optr == ')') { // case 2: )
            while (true) {
                char top = optrStack.pop();
                if (top == '(') {
                    break;
                }
                int num2 = numStack.pop();
                int num1 = numStack.pop();
                numStack.push(cal(top, num1, num2));
            }
        } else { // case 3: + - * /
            while (true) {
                if (optrStack.isEmpty()) {
                    break; // 无运算符，不用计算
                }
                char top = optrStack.peek(); // 先peek出来比较优先级，能算再pop()
                Integer topWeight = optrMap.get(top); // top为 ‘(’时，topWeight == null
                if (topWeight == null || topWeight < optrMap.get(optr)) {
                    break; // 优先级比现在进来的optr要低，不用计算
                }
                int num2 = numStack.pop();
                int num1 = numStack.pop();
                numStack.push(cal(optrStack.pop(), num1, num2));
            }
            optrStack.push(optr); // + / - / * / /和左括号一样，都要压入optrStack,右括号则不用！
        }
    }

    private int cal(char optr, int num1, int num2) {
        switch (optr) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                return num1 / num2;
            default:
                throw new IllegalArgumentException();
        }
    }
}

}