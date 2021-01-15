package leetcode.editor.en;

/*
 Program: leetcode_practice
 ClassName: temp
 Description:
 Author: Zeshi(Jesse) Yang
 Date: 2020-08-06 15:25
 */

import java.util.HashMap;
import java.util.Stack;

public class Foo {
	
	public static void main(String[] args) {
		int num = (int) 2e5;
		String[] strs = {"(", "1", "+", "2", ")", "*", "3", "-", "8", "/", "4", "+", "5"};
		int res = new Foo(). new Solution().calculate(strs);
		System.out.println(res);
	}
	
class Solution {
	public int calculate(String[] s) {
		// corner case
		if (s == null) {
			throw new IllegalArgumentException();
		}
		
		// initialization
		HashMap<String, Integer> optrMap = new HashMap<>();
		optrMap.put("+", 1);
		optrMap.put("-", 1);
		optrMap.put("*", 2);
		optrMap.put("/", 2);
		
		Stack<Integer> numStack = new Stack<>();
		Stack<String> optrStack = new Stack<>();
		
		// traversal
		int i = 0;
		addOptr(numStack, optrStack, optrMap, "(", s, i - 1);
		
		while (i < s.length) {
			String ch = s[i];;
			if (ch.equals(" ")) { // case 1: ' '
				continue;
			} else if (ch.equals("(") || ch.equals(")") || optrMap.containsKey(ch)) {
				// case 2: ( ) + - * /
				addOptr(numStack, optrStack, optrMap, ch, s, i);
			} else if (ch.charAt(0) >= '0' && ch.charAt(0) <= '9') { // case 3: numbers
				// 拼数
				int val = Integer.parseInt(ch);
				numStack.push(val);
			} else { // case 4: exception
				throw new IllegalArgumentException();
			}
			i++;
		}
		
		addOptr(numStack, optrStack, optrMap, ")", s, i); // i = s.length()
		return numStack.pop();
	}
	
	private void addOptr(Stack<Integer> numStack, Stack<String> optrStack,
			HashMap<String, Integer> optrMap, String optr, String[] s, int i) {
		// case 1: (
		if (optr.equals("(")) {
			optrStack.push(optr);
			int idx = i + 1;
			while (idx < s.length) {
				String ch = s[idx];
				if (ch.equals(" ")) {
					idx++;
					continue;
				} else if (ch.equals("-")) {
					numStack.push(0);
					break;
				} else {
					break;
				}
			}
		} else if (optr.equals(")")) { // case 2: )
			while (true) {
				String top = optrStack.pop();
				if (top.equals("(")) {
					break;
				}
				int num2 = numStack.pop();
				int num1 = numStack.pop();
				numStack.push(cal(top, num1, num2));
			}
		} else { // case 3: + - * /
			while (!optrStack.isEmpty()) {
				/*if (optrStack.isEmpty()) {
					break; // 无运算符，不用计算
				}*/
				String top = optrStack.peek(); // 先peek出来比较优先级，能算再pop()
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
	
	private int cal(String optr, int num1, int num2) {
		switch (optr) {
			case "+":
				return num1 + num2;
			case "-":
				return num1 - num2;
			case "*":
				return num1 * num2;
			case "/":
				return num1 / num2;
			default:
				throw new IllegalArgumentException();
		}
	}
}
}