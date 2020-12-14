//Given an input string (s) and a pattern (p), implement regular expression matc
//hing with support for '.' and '*'. 
//
// 
//'.' Matches any single character.
//'*' Matches zero or more of the preceding element.
// 
//
// The matching should cover the entire input string (not partial). 
//
// Note: 
//
// 
// s could be empty and contains only lowercase letters a-z. 
// p could be empty and contains only lowercase letters a-z, and characters like
// . or *. 
// 
//
// Example 1: 
//
// 
//Input:
//s = "aa"
//p = "a"
//Output: false
//Explanation: "a" does not match the entire string "aa".
// 
//
// Example 2: 
//
// 
//Input:
//s = "aa"
//p = "a*"
//Output: true
//Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, 
//by repeating 'a' once, it becomes "aa".
// 
//
// Example 3: 
//
// 
//Input:
//s = "ab"
//p = ".*"
//Output: true
//Explanation: ".*" means "zero or more (*) of any character (.)".
// 
//
// Example 4: 
//
// 
//Input:
//s = "aab"
//p = "c*a*b"
//Output: true
//Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, i
//t matches "aab".
// 
//
// Example 5: 
//
// 
//Input:
//s = "mississippi"
//p = "mis*is*p*."
//Output: false
// 
// Related Topics String Dynamic Programming Backtracking

package leetcode.editor.en;

public class Leetcode0010RegularExpressionMatching {
	
	// Java: regular-expression-matching
	public static void main(String[] args) {
		
		Solution sol = new Leetcode0010RegularExpressionMatching().new Solution();
		// TO TEST
		String s = "aab";
		String p = "c*a*b";
		boolean res = sol.isMatch(s, p);
		System.out.println(res);
	}
	
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    // Solution 1: DFS without pruning
    public boolean isMatch(String s, String p) {
        // corner case
        if (s == null || p == null) {
            return false;
        }
        return dfs(s, 0, p, 0);
        
    }
    
    /**
     * 因为 * 可以匹配0到任意一个 * 前面的字符，所以检测的是p[idxP + 1] 是不是 *
     *
     * @param s:    target String
     * @param idxS: current idx in the String s
     * @param p:    given regular expression
     * @param idxP: current idx in the String p
     * @return: whether matched
     */
    private boolean dfs(String s, int idxS, String p, int idxP) {
        /*
         * base case
         * 这里的corner case是从idxP == lenP角度去出发的
         * 如果这个时候s也走到了最后，就return true, 否则return false
         *
         * 否则如果从idxS == lenS角度去出发会很麻烦，如果idxS == lenS
         * 如果idxP == lenP，return true;
         * 但是如果idxP != lenP, 结果也有可能是true，比如说P的结尾是 * 的情况下
         *
         */
        int lenS = s.length();
        int lenP = p.length();
        // base case
        if (idxP == lenP) {
            return idxS == lenS;
        }
    /*
    正常的逻辑是
    if (idxP == lenP - 1) { // p[idxP]是最后一个字符，后面没有字符了
        // part A
        
    } else {
        if (p.charAt(idxP + 1) != '*') { // p[idxP]不是最后一个字符，而且后面跟的字符不是 *
            // part B
        } else { // p[idxP]不是最后一个字符，而且后面跟的字符是 *
            // part C
        }
    }
    分析过程的本身代码应该是这样子的
    然后发现part A 和 B是一样的，可以合并
     */
        if (idxP == lenP - 1) { // p[idxP]是最后一个字符，后面没有字符了
            // part A
            if (idxS < lenS) {
                if (p.charAt(idxP) == '.' || p.charAt(idxP) == s.charAt(idxS)) {
                    return dfs(s, idxS + 1, p, idxP + 1);
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else { // p[idxP]不是最后一个字符
            if (p.charAt(idxP + 1) != '*') { // p[idxP]后面跟的字符不是 *
                // part B
                if (idxS < lenS) {
                    if (p.charAt(idxP) == '.' || p.charAt(idxP) == s.charAt(idxS)) {
                        return dfs(s, idxS + 1, p, idxP + 1);
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else { // 后面跟的字符是 *
                // part C
                int i = idxS - 1; // the first match try is [empty, *] instead of [s[idx], *]
                // here we match the [], a, aa, aaa, aaa... in the String s
                while (i < lenS && (i < idxS || p.charAt(idxP) == '.' || s.charAt(i) == p
                        .charAt(idxP))) {
                    if (dfs(s, i + 1, p, idxP + 2)) {
                        return true;
                    }
                    i++;
                }
                return false;
            }
        }

    
    /*if (idxP == lenP - 1 || p.charAt(idxP + 1) != '*') { // case 1: not "*" // ?
        if(idxS < lenS && (p.charAt(idxP) == '.' || p.charAt(idxP) == s.charAt(idxS))) {
            return dfs(s, idxS + 1, p, idxP + 1);
        } else {
            return false;
        }
    } else { // case 2: "*"
        int i = idxS - 1; // the first match try is [empty, *] instead of [s[idx], *]
        // here we match the [], a, aa, aaa, aaa... in the String s
        while (i < lenS && (i < idxS || p.charAt(idxP) == '.' || s.charAt(i) == p.charAt
        (idxP))) {
            if (dfs(s, i + 1, p, idxP + 2)) {
                return true;
            }
            i++;
        }
        return false;
    }*/
    }
    // time complexity: O((m + n) * 2^n)
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1_1: DFS without pruning
class Solution1_1 {
    
    public boolean isMatch(String s, String p) {
        // corner case
        if (s == null || p == null) {
            return false;
        }
        return dfs(s, 0, p, 0);
        
    }
    
    /**
     * dfs used to check whether s[idxS, lenS) is matched with the p[idxP, lenP)
     */
    // 因为 * 可以匹配0到任意一个 * 前面的字符，所以检测的是p[idxP + 1] 是不是 *
    private boolean dfs(String s, int idxS, String p, int idxP) {
        /*
         * base case
         * 这里的corner case是从idxP == lenP角度去出发的
         * 如果这个时候s也走到了最后，就return true, 否则return false
         *
         * 否则如果从idxS == lenS角度去出发会很麻烦，如果idxS == lenS
         * 如果idxP == lenP，return true;
         * 但是如果idxP != lenP, 结果也有可能是true，比如说P的结尾是 * 的情况下
         *
         */
        int lenS = s.length();
        int lenP = p.length();
        // base case
        if (idxP == lenP) {
            return idxS == lenS;
        }
        
        if (idxP == lenP - 1) { // p[idxP]是最后一个字符，后面没有字符了
            // part A
            if (idxS < lenS) {
                if (p.charAt(idxP) == '.' || p.charAt(idxP) == s.charAt(idxS)) {
                    return dfs(s, idxS + 1, p, idxP + 1);
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else { // p[idxP]不是最后一个字符
            if (p.charAt(idxP + 1) != '*') { // p[idxP]后面跟的字符不是 *
                // part B
                if (idxS < lenS) {
                    if (p.charAt(idxP) == '.' || p.charAt(idxP) == s.charAt(idxS)) {
                        return dfs(s, idxS + 1, p, idxP + 1);
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else { // 后面跟的字符是 *
                // part C
                int i = idxS - 1; // the first match try is [empty, *] instead of [s[idx], *]
                // here we match the [], a, aa, aaa, aaa... in the String s
                while (i < lenS && (i < idxS || p.charAt(idxP) == '.' || s.charAt(i) == p
                        .charAt(idxP))) {
                    if (dfs(s, i + 1, p, idxP + 2)) {
                        return true;
                    }
                    i++;
                }
                return false;
            }
        }
    }
    // time complexity: O((m + n) * 2^n)
}

// Solution 1_2: DFS without pruning
class Solution1_2 {
    
    public boolean isMatch(String s, String p) {
        // corner case
        if (s == null || p == null) {
            return false;
        }
        return dfs(s, 0, p, 0);
        
    }
    
    /**
     * dfs used to check whether s[idxS, lenS) is matched with the p[idxP, lenP)
     */
    // 因为 * 可以匹配0到任意一个 * 前面的字符，所以检测的是p[idxP + 1] 是不是 *
    private boolean dfs(String s, int idxS, String p, int idxP) {
        /*
         * base case
         * 这里的corner case是从idxP == lenP角度去出发的
         * 如果这个时候s也走到了最后，就return true, 否则return false
         *
         * 否则如果从idxS == lenS角度去出发会很麻烦，如果idxS == lenS
         * 如果idxP == lenP，return true;
         * 但是如果idxP != lenP, 结果也有可能是true，比如说P的结尾是 * 的情况下
         *
         */
        int lenS = s.length();
        int lenP = p.length();
        // base case
        if (idxP == lenP) {
            return idxS == lenS;
        }
        /*
        正常的逻辑是
        if (idxP == lenP - 1) { // p[idxP]是最后一个字符，后面没有字符了
            // part A
            
        } else {
            if (p.charAt(idxP + 1) != '*') { // p[idxP]不是最后一个字符，而且后面跟的字符不是 *
                // part B
            } else { // p[idxP]不是最后一个字符，而且后面跟的字符是 *
                // part C
            }
        }
        分析过程的本身代码应该是这样子的
        然后发现part A 和 B是一样的，可以合并
         */
        if (idxP == lenP - 1 || p.charAt(idxP + 1) != '*') { // case 1: not "*" // ?
            if(idxS < lenS && (p.charAt(idxP) == '.' || p.charAt(idxP) == s.charAt(idxS))) {
                return dfs(s, idxS + 1, p, idxP + 1);
            } else {
                return false;
            }
        } else { // case 2: "*"
            int i = idxS - 1; // the first match try is [empty, *] instead of [s[idx], *]
            // here we match the [], a, aa, aaa, aaa... in the String s
            while (i < lenS && (i < idxS || p.charAt(idxP) == '.' || s.charAt(i) == p.charAt(idxP))) {
                if (dfs(s, i + 1, p, idxP + 2)) {
                    return true;
                }
                i++;
            }
            return false;
        }
    }
    // time complexity: O((m + n) * 2^n)
}

// Solution 1_3: DFS with pruning
class Solution1_3 {
    
    public boolean isMatch(String s, String p) {
        // corner case
        if (s == null || p == null) {
            return false;
        }
        return dfs(s, 0, p, 0, new Boolean[s.length() + 1][p.length() + 1]);
        
    }
    
    /**
     * description: dfs used to check whether s[idxS, lenS) is matched with the p[idxP, lenP)
     * @param memo: the 2D array which stores whether s[idxS, lenS) matches p[idxP, lenP)
     */
    // 因为 * 可以匹配0到任意一个 * 前面的字符，所以检测的是p[idxP + 1] 是不是 *
    private boolean dfs(String s, int idxS, String p, int idxP, Boolean[][] memo) {
        
        int lenS = s.length();
        int lenP = p.length();
        // base case
        if (memo[idxS][idxP] != null) { // lookup the form
            return memo[idxS][idxP];
        }
        if (idxP == lenP) {
            memo[idxS][idxP] = (idxS == lenS);
            return memo[idxS][idxP];
        }
        
        if (idxP == lenP - 1 || p.charAt(idxP + 1) != '*') { // case 1: not "*" // ?
            if (idxS < lenS && (p.charAt(idxP) == '.' || p.charAt(idxP) == s.charAt(idxS))) {
                memo[idxS][idxP] = dfs(s, idxS + 1, p, idxP + 1, memo);
                return memo[idxS][idxP];
            } else {
                memo[idxS][idxP] = false; // fill in the form
                return false;
            }
        } else { // case 2: "*"
            int i = idxS - 1; // the first match try is [empty, *] instead of [s[idx], *]
            // here we match the [], a, aa, aaa, aaa... in the String s
            while (i < lenS && (i < idxS || p.charAt(idxP) == '.' || s.charAt(i) == p
                    .charAt(idxP))) {
                if (dfs(s, i + 1, p, idxP + 2, memo)) {
                    memo[idxS][idxP] = true; // fill in the form
                    return true;
                }
                i++;
            }
            memo[idxS][idxP] = false; // fill in the form
            return false;
        }
    }
    // time complexity: O(m^2 * n)
}

// Solution 2: Dynamic programming
class Solution2 {
    
    /*
    boolean dp[i][j] means whether s[i, lenS) matches p[j, lenP)
    dp[i][j] =
     */
    public boolean isMatch(String s, String p) {
        // corner case
        if (s == null || p == null) {
            return false;
        }
        int lenS = s.length();
        int lenP = p.length();
        boolean[][] dp = new boolean[lenS + 1][lenP + 1];
        dp[lenS][lenP] = true;
        
        int k = lenP - 2;
        while (k >= 0) {
            if (p.charAt(k + 1) == '*') {
                dp[lenS][k] = true;
            } else {
                break;
            }
            k -= 2;
        }
        
        for (int i = lenS - 1; i >= 0; i--) {
            for (int j = lenP - 1; j >= 0; j--) {
                if (p.charAt(j) == '*') {
                    continue;
                }
                
                if (j + 1 >= lenP || p.charAt(j + 1) != '*') { // 非*情况
                    if (p.charAt(j) == '.' || p.charAt(j) == s.charAt(i)) {
                        dp[i][j] = dp[i + 1][j + 1];
                    } else {
                        dp[i][j] = false;
                    }
                } else { // *情况
                    int idx = i - 1;
                    dp[i][j] = false;
                    while (idx < lenS && (idx < i || p.charAt(j) == '.' || p.charAt(j) == s
                            .charAt(idx))) {
                        if (dp[idx + 1][j + 2]) {
                            dp[i][j] = true;
                            break;
                        }
                        idx++;
                    }
                }
            }
        }
        
        return dp[0][0];
        
    }
}
}