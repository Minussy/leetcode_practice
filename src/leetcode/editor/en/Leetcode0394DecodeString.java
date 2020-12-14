//Given an encoded string, return its decoded string. 
//
// The encoding rule is: k[encoded_string], where the encoded_string inside the 
//square brackets is being repeated exactly k times. Note that k is guaranteed to 
//be a positive integer. 
//
// You may assume that the input string is always valid; No extra white spaces, 
//square brackets are well-formed, etc. 
//
// Furthermore, you may assume that the original data does not contain any digit
//s and that digits are only for those repeat numbers, k. For example, there won't
// be input like 3a or 2[4]. 
//
// 
// Example 1: 
// Input: s = "3[a]2[bc]"
//Output: "aaabcbc"
// Example 2: 
// Input: s = "3[a2[c]]"
//Output: "accaccacc"
// Example 3: 
// Input: s = "2[abc]3[cd]ef"
//Output: "abcabccdcdcdef"
// Example 4: 
// Input: s = "abc3[cd]xyz"
//Output: "abccdcdcdxyz"
// Related Topics Stack Depth-first Search 
// 👍 3276 👎 168

package leetcode.editor.en;

import java.util.Stack;

// 2020-08-04 12:34:12
// Zeshi Yang
public class Leetcode0394DecodeString {

	// Java: decode-string
	public static void main(String[] args) {
		Solution sol = new Leetcode0394DecodeString().new Solution();
		// TO TEST
		String str = "abc3[cd]xyz";
		String res = sol.decodeString(str);
		System.out.println(res);
	}

//leetcode submit region begin(Prohibit modification and deletion)
//	Solution 1: recursion
class Solution {

	public String decodeString(String s) {
		// corner case
		if (s == null || s.length() == 0) {
			return "";
		}
		// general case
		int[] pos = {0};
		return decodeString(s, pos);
	}

	private String decodeString(String s, int[] pos) {
		StringBuilder sb = new StringBuilder();
		String num = "";
		for (int i = pos[0]; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z') { // 字母
				sb.append(ch);
			} else if (Character.isDigit(ch)) { // 数字
				num += ch;
			} else if (ch == '[') { // 左括号
				pos[0] = i + 1;
				String next = decodeString(s, pos);
				for (int n = Integer.parseInt(num); n > 0; n--) {
					sb.append(next);
				}
				num = "";
				i = pos[0];
			} else if (s.charAt(i) == ']') { // 右括号
				pos[0] = i;
				return sb.toString();
			} else { // 其他
				throw new RuntimeException("come up with a unknown letter");
			}
		}
		return sb.toString();
	}
}
//leetcode submit region end(Prohibit modification and deletion)


// Solution 1: 4ms, recursion,老刘的做法
class Solution1 {

	public String decodeString(String s) {
		// corner case
		if (s == null || s.length() == 0) {
			return "";
		}
		// general case
		int[] pos = {0};
		return decodeString(s, pos);
	}

	private String decodeString(String s, int[] pos) {
		StringBuilder sb = new StringBuilder();
		String num = "";
		for (int i = pos[0]; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z') { // 字母
				sb.append(ch);
			} else if (Character.isDigit(ch)) { // 数字
				num += ch;
			} else if (ch == '[') { // 左括号
				pos[0] = i + 1;
				String next = decodeString(s, pos);
				for (int n = Integer.parseInt(num); n > 0; n--) {
					sb.append(next);
				}
				num = "";
				i = pos[0];
			} else if (s.charAt(i) == ']') { // 右括号
				pos[0] = i;
				return sb.toString();
			} else { // 其他
				throw new RuntimeException("come up with a unknown letter");
			}
		}
		return sb.toString();
	}
}

// Solution 2: 0ms, 2 stacks, numStack & strStack
/*
用两个stack, numStack和strStack,前者压数字,后者压string.
遇到括号的时候,strStack压空字符
 */
class Solution2 {

	public String decodeString(String s) {
		// corner case
		if (s == null || s.length() == 0) {
			return "";
		}
		// general case
		Stack<StringBuilder> strStack = new Stack<>();
		strStack.push(new StringBuilder());
		Stack<Integer> numStack = new Stack<>();
		int num = 0;
		for (char ch: s.toCharArray()) {
			if (Character.isDigit(ch)) { // 数字
				num = num * 10 + (ch - '0');
			} else if (ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z') { // 字母
				strStack.peek().append(ch);
			} else if (ch == '[') { // 左括号
				strStack.push(new StringBuilder());
				numStack.push(num);
				num = 0;
			} else if (ch == ']') { // 右括号
				int count = numStack.pop();
				StringBuilder str = strStack.pop();
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < count; i++) {
					sb.append(str);
				}
				// 把sb加到前一个StringBuilder的后面
				strStack.peek().append(sb);
			} else { // 其他
				throw new RuntimeException("come up with a unknown letter");
			}
		}
		return strStack.pop().toString();
	}
}
}