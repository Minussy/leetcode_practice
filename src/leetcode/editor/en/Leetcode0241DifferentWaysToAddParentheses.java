//Given a string of numbers and operators, return all possible results from comp
//uting all the different possible ways to group numbers and operators. The valid 
//operators are +, - and *. 
//
// Example 1: 
//
// 
//Input: "2-1-1"
//Output: [0, 2]
//Explanation: 
//((2-1)-1) = 0 
//(2-(1-1)) = 2 
//
// Example 2: 
//
// 
//Input: "2*3-4*5"
//Output: [-34, -14, -10, -10, 10]
//Explanation: 
//(2*(3-(4*5))) = -34 
//((2*3)-(4*5)) = -14 
//((2*(3-4))*5) = -10 
//(2*((3-4)*5)) = -10 
//(((2*3)-4)*5) = 10
// Related Topics Divide and Conquer 
// 👍 1587 👎 81

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.List;

// 2020-07-10 22:03:02
public class Leetcode0241DifferentWaysToAddParentheses{
    // Java: different-ways-to-add-parentheses
    public static void main(String[] args) {
        Solution sol = new Leetcode0241DifferentWaysToAddParentheses().new Solution();
        // TO TEST
        String str = "2-1-1+2*3-5+3*2";
        List<Integer> res = sol.diffWaysToCompute(str);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // DFS with pruning
    public List<Integer> diffWaysToCompute(String input) {
        // corner case
        if (input == null || input.length() == 0 ) {
            return null;
        }
        char[] chars = input.toCharArray();
        int len = chars.length;
        List[][] mem = new ArrayList[len][len];
        List<Integer> res = dfs(chars, 0, chars.length - 1, mem);
        return res;
    }

    private List<Integer> dfs(char[] chars, int i, int j, List[][] mem) {
        // lookup the form
        if (mem[i][j] != null) {
            return mem[i][j];
        }

        List<Integer> res = new ArrayList<>();
        List<Integer> IdxOperators = new ArrayList<>();
        int num = 0;
        for (int k = i; k <= j; k++) {
            char ch = chars[k];
            int temp = ch - '0';
            num = num * 10 + temp;
            if (ch == '+' || ch == '-' || ch == '*') {
                IdxOperators.add(k);
                num = 0;
            }
        }
        // base case, no operators
        if (IdxOperators.size() == 0) {
        /*String str = String.valueOf(Arrays.copyOfRange(chars, i, j + 1));
        Integer num = Integer.valueOf(str);*/
            res.add(num);
            mem[i][j] =res; // fill in the form
            return res;
        }
        // general case, has operators
        for(int k: IdxOperators) {
            char ch = chars[k];
            List<Integer> left = dfs(chars, i, k - 1, mem);
            List<Integer> right = dfs(chars, k + 1, j, mem);
            List<Integer> one = combine(left, right, ch);
            res.addAll(one);
        }
        mem[i][j] = res; // fill in the form
        return res;
    }

    private List<Integer> combine(List<Integer> left, List<Integer> right, char optr) {
        List<Integer> res = new ArrayList<>();
        for (int l : left) {
            for (int r : right) {
                int result = 0;
                if (optr == '+')  result = l + r;
                if (optr == '-')  result = l - r;
                if (optr == '*')  result = l * r;
                res.add(result);
            }
        }
        return res;
    }
}


//leetcode submit region end(Prohibit modification and deletion)
class Solution1 {
    // DFS
    public List<Integer> diffWaysToCompute(String input) {
        // corner case
        if (input == null || input.length() == 0 ) {
            return null;
        }
        char[] chars = input.toCharArray();
        List<Integer> res = dfs(chars, 0, chars.length - 1);
        return res;
    }

    private List<Integer> dfs(char[] chars, int i, int j) {
        List<Integer> res = new ArrayList<>();
        List<Integer> IdxOperators = new ArrayList<>();
        int num = 0;
        for (int k = i; k <= j; k++) {
            char ch = chars[k];
            int temp = ch - '0';
            num = num * 10 + temp;
            if (ch == '+' || ch == '-' || ch == '*') {
                IdxOperators.add(k);
                num = 0;
            }
        }
        // base case, no operators
        if (IdxOperators.size() == 0) {
            /*String str = String.valueOf(Arrays.copyOfRange(chars, i, j + 1));
            Integer num = Integer.valueOf(str);*/
            res.add(num);
            return res;
        }
        // general case, has operators
        for(int k: IdxOperators) {
            char ch = chars[k];
            List<Integer> left = dfs(chars, i, k - 1);
            List<Integer> right = dfs(chars, k + 1, j);
            List<Integer> one = combine(left, right, ch);
            res.addAll(one);
        }
        return res;
    }

    private List<Integer> combine(List<Integer> left, List<Integer> right, char optr) {
        List<Integer> res = new ArrayList<>();
        for (int l : left) {
            for (int r : right) {
                int result = 0;
                if (optr == '+')  result = l + r;
                if (optr == '-')  result = l - r;
                if (optr == '*')  result = l * r;
                res.add(result);
            }
        }
        return res;
    }
}
class Solution2 {
    // DFS with pruning
    public List<Integer> diffWaysToCompute(String input) {
        // corner case
        if (input == null || input.length() == 0 ) {
            return null;
        }
        char[] chars = input.toCharArray();
        int len = chars.length;
        List[][] mem = new ArrayList[len][len];
        List<Integer> res = dfs(chars, 0, chars.length - 1, mem);
        return res;
    }

    private List<Integer> dfs(char[] chars, int i, int j, List[][] mem) {
        // lookup the form
        if (mem[i][j] != null) {
            return mem[i][j];
        }

        List<Integer> res = new ArrayList<>();
        List<Integer> IdxOperators = new ArrayList<>();
        int num = 0;
        for (int k = i; k <= j; k++) {
            char ch = chars[k];
            int temp = ch - '0';
            num = num * 10 + temp;
            if (ch == '+' || ch == '-' || ch == '*') {
                IdxOperators.add(k);
                num = 0;
            }
        }
        // base case, no operators
        if (IdxOperators.size() == 0) {
        /*String str = String.valueOf(Arrays.copyOfRange(chars, i, j + 1));
        Integer num = Integer.valueOf(str);*/
            res.add(num);
            mem[i][j] =res; // fill in the form
            return res;
        }
        // general case, has operators
        for(int k: IdxOperators) {
            char ch = chars[k];
            List<Integer> left = dfs(chars, i, k - 1, mem);
            List<Integer> right = dfs(chars, k + 1, j, mem);
            List<Integer> one = combine(left, right, ch);
            res.addAll(one);
        }
        mem[i][j] = res; // fill in the form
        return res;
    }

    private List<Integer> combine(List<Integer> left, List<Integer> right, char optr) {
        List<Integer> res = new ArrayList<>();
        for (int l : left) {
            for (int r : right) {
                int result = 0;
                if (optr == '+')  result = l + r;
                if (optr == '-')  result = l - r;
                if (optr == '*')  result = l * r;
                res.add(result);
            }
        }
        return res;
    }
}
class Solution3 {
    // dynamic programming
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> numList = new ArrayList<>();
        List<Character> opList = new ArrayList<>();
        char[] array = input.toCharArray();
        int num = 0;
        for (int i = 0; i < array.length; i++) {
            if (isOperation(array[i])) {
                numList.add(num);
                num = 0;
                opList.add(array[i]);
                continue;
            }
            num = num * 10 + array[i] - '0';
        }
        numList.add(num);
        int N = numList.size(); // 数字的个数

        // 一个数字
        ArrayList<Integer>[][] dp = (ArrayList<Integer>[][]) new ArrayList[N][N];
        for (int i = 0; i < N; i++) {
            ArrayList<Integer> result = new ArrayList<>();
            result.add(numList.get(i));
            dp[i][i] = result;
        }
        // 2 个数字到 N 个数字
        for (int n = 2; n <= N; n++) {
            // 开始下标
            for (int i = 0; i < N; i++) {
                // 结束下标
                int j = i + n - 1;
                if (j >= N) {
                    break;
                }
                ArrayList<Integer> result = new ArrayList<>();
                // 分成 i ~ s 和 s+1 ~ j 两部分
                for (int s = i; s < j; s++) {
                    ArrayList<Integer> result1 = dp[i][s];
                    ArrayList<Integer> result2 = dp[s + 1][j];
                    for (int x = 0; x < result1.size(); x++) {
                        for (int y = 0; y < result2.size(); y++) {
                            // 第 s 个数字下标对应是第 s 个运算符
                            char op = opList.get(s);
                            result.add(caculate(result1.get(x), op, result2.get(y)));
                        }
                    }
                }
                dp[i][j] = result;

            }
        }
        return dp[0][N-1];
    }

    private int caculate(int num1, char c, int num2) {
        switch (c) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
        }
        return -1;
    }

    private boolean isOperation(char c) {
        return c == '+' || c == '-' || c == '*';
    }

}
}