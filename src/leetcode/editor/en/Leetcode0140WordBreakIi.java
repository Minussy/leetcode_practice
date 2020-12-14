//Given a non-empty string s and a dictionary wordDict containing a list of non-
//empty words, add spaces in s to construct a sentence where each word is a valid 
//dictionary word. Return all such possible sentences. 
//
// Note: 
//
// 
// The same word in the dictionary may be reused multiple times in the segmentat
//ion. 
// You may assume the dictionary does not contain duplicate words. 
// 
//
// Example 1: 
//
// 
//Input:
//s = "catsanddog"
//wordDict = ["cat", "cats", "and", "sand", "dog"]
//Output:
//[
//  "cats and dog",
//  "cat sand dog"
//]
// 
//
// Example 2: 
//
// 
//Input:
//s = "pineapplepenapple"
//wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
//Output:
//[
//  "pine apple pen apple",
//  "pineapple pen apple",
//  "pine applepen apple"
//]
//Explanation: Note that you are allowed to reuse a dictionary word.
// 
//
// Example 3: 
//
// 
//Input:
//s = "catsandog"
//wordDict = ["cats", "dog", "sand", "and", "cat"]
//Output:
//[] 
// Related Topics Dynamic Programming Backtracking

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// 2020-07-01 00:02:13
// Zeshi Yang
public class Leetcode0140WordBreakIi {
    // Java: word-break-ii
    public static void main(String[] args) {
        Solution sol = new Leetcode0140WordBreakIi().new Solution();
        // TO TEST
        String s = "catsanddog";
        List<String> wordDict = Arrays.asList("cats","dog","sand","and","cat");
        List<String> result = sol.wordBreak(s, wordDict);
        System.out.println(result);
    }
//leetcode submit region begin(Prohibit modification and deletion)
// Solution 2: dynamic Programming
class Solution {
    // Zeshi Yang's code
    // dynamic programming
    /*
    建立一个HashMap, 从后往前搜索分叉，当index i 之后可以分叉到index j的时候（j < i)，
    我们在HashMap里面存<j, i>，第一个DFS函数用来建立图，第二个private函数用来建立路径
     */
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> result = new ArrayList<>();
        
        //corner case
        if (s == null || s.length() == 0) {
            return result;
        }
        int length = s.length();
        boolean[] mem = new boolean[length + 1];
        mem[length] = true;
        Set<String> set = new HashSet<>(wordDict);
        Map<Integer, List<Integer>> graph = new HashMap<Integer, List<Integer>>();
        
        // dynamic programming
        for (int i = length - 1; i >= 0; i--) {
            for (int j = i + 1; j <= length; j++) {
                String str = s.substring(i, j);
                if (set.contains(str) && mem[j]) {
                    if (!graph.containsKey(i)) {
                        mem[i] = true;
                        graph.put(i, new ArrayList<Integer>());
                    }
                    graph.get(i).add(j);
                }
            }
        }
        
        dfsBuildPaths(0, s, new StringBuilder(), graph, result);
        
        return result;
    }
    
    private void dfsBuildPaths(int index, String s, StringBuilder sb,
            Map<Integer, List<Integer>> graph, List<String> result) {
        int len = sb.length();
        // base case
        if (index == s.length()) {
            sb.setLength(len - 1);
            result.add(sb.toString());
            return;
        }
        
        if (graph.containsKey(index)) {
            for (int i: graph.get(index)) {
                sb.append(s.substring(index, i)).append(" ");
                dfsBuildPaths(i, s, sb, graph, result);
                sb.setLength(len);
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: DFS
// Solution 1_1: DFS without pruning, time limit exceed
class Solution1_1 {
    // DFS without pruning. time limit exceeding
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> result = new ArrayList<>();
        
        //corner case
        if (s == null || s.length() == 0) {
            return result;
        }
        int index = 0;
        int length = s.length();
        
        Set<String> set = new HashSet<>(wordDict);
        Boolean[] memo = new Boolean[length + 1];
        StringBuilder sb = new StringBuilder();
        
        wordBreak(index, s, set, sb, result);
        
        return result;
    }
    
    private void wordBreak(int index, String s, Set set, StringBuilder sb, List<String> result) {
        // base case
        if (index == s.length()) {
            sb.setLength(sb.length() - 1); // delete the " " at the end of the String
            result.add(sb.toString());
        }
        
        int size = result.size();
        
        for (int i = index + 1; i <= s.length(); i++) {
            String word = s.substring(index, i);
            if (set.contains(word)) {
                int len = sb.length();
                sb.append(word).append(" ");
                wordBreak(i, s, set, sb, result);
                sb.setLength(len);
            }
        }
    }
}

// Solution 1_2: DFS with pruning by memo[], 4ms
// DFS with pruning
/** 设置一个Boolean memo[]数组，初始值都是Null，
 memo[i] 表示[i, lengtih)的String是不是可以被wordBreak
 
 用DFS思想，遇到memo[i]是false的时候，就return false; 否则继续做下去
 某个位置开始的word不能被break的时候，memo[i]就标为false
 */
class Solution1_2 {
    
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> result = new ArrayList<>();
        
        //corner case
        if (s == null || s.length() == 0) {
            return result;
        }
        int length = s.length();
        
        Set<String> set = new HashSet<>(wordDict);
        Boolean[] memo = new Boolean[length + 1];
        StringBuilder sb = new StringBuilder();
        
        wordBreak(0, s, set, sb, memo, result);
        return result;
    }
    
    private boolean wordBreak(int index, String s, Set set, StringBuilder sb, Boolean[] memo,
            List<String> result) {
        // base case
        if (index == s.length()) {
            sb.setLength(sb.length() - 1); // delete the " " at the end of the String
            result.add(sb.toString());
            return true;
        }
        if (memo[index] != null && !memo[index]) { // Null和false不能比较
            return false;
        }
        
        int size = result.size();
        
        for (int i = index + 1; i <= s.length(); i++) {
            String word = s.substring(index, i);
            if (set.contains(word)) {
                int len = sb.length();
                sb.append(word);
                sb.append(" ");
                if (wordBreak(i, s, set, sb, memo, result)) {
                    memo[index] = true; // 注意这里索引是index，不是i
                }
                sb.setLength(len);
            }
        }
        if (result.size() == size) {
            memo[index] = false;
        }
        return memo[index];
    }
}

// Solution 1_3: DFS with pruning by HashMap, 3ms
/*
建立一个HashMap, 从后往前搜索分叉，当index i 之后可以分叉到index j的时候（j < i)，
我们在HashMap里面存<j, i>，第一个DFS函数用来建立图，第二个private函数用来建立路径
 */
class Solution1_3 {
    
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> result = new ArrayList<>();

        //corner case
        if (s == null || s.length() == 0) {
            return result;
        }
        int length = s.length();

        Set<String> set = new HashSet<>(wordDict);
        Boolean[] memo = new Boolean[length + 1]; // 表示能不能从起点走到s[i]
        Map<Integer, HashSet<Integer>> graph = new HashMap<>();
        wordBreak(length, s, set, graph, memo);
        dfsBuildPaths(0, s, new StringBuilder(), graph, result);
        return result;
    }
    /*

     */
    private boolean wordBreak(int index, String s, Set set, Map<Integer, HashSet<Integer>> graph,
            Boolean[] memo) {
        // base case
        if (index == 0) {
            return true;
        }
        if (memo[index] != null && !memo[index]) { // Null和false不能比较
            return false;
        }
        for (int i = index; i >= 0; i--) {
            if (memo[index] != null && !memo[index]) {
                break;
            }
            String word = s.substring(i, index);
            if (set.contains(word)) {
                if (!graph.containsKey(i)) {
                    graph.put(i, new HashSet<>());
                }
                graph.get(i).add(index);

                if (wordBreak(i, s, set, graph, memo)) {
                    memo[index] = true;
                }
            }
        }
        if (memo[index] == null) {
            memo[index] = false;
        }
        return memo[index];
    }

    private void dfsBuildPaths(int index, String s, StringBuilder sb, Map<Integer,
            HashSet<Integer>> graph, List<String> result) {
        int len = sb.length();
        // base case
        if (index == s.length()) {
            sb.setLength(len - 1);
            result.add(sb.toString());
            return;
        }

        if (graph.containsKey(index)) {
            for (int i: graph.get(index)) {
                sb.append(s.substring(index, i)).append(" ");
                dfsBuildPaths(i, s, sb, graph, result);
                sb.setLength(len);
            }
        }
    }
}

// Solution 2: dynamic Programming, 8ms
/*
建立一个HashMap, 从后往前搜索分叉，当index i 之后可以分叉到index j的时候（j < i)，
我们在HashMap里面存<j, i>，第一个DFS函数用来建立图，第二个private函数用来建立路径
 */
class Solution2 {
    
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> result = new ArrayList<>();
        
        //corner case
        if (s == null || s.length() == 0) {
            return result;
        }
        int length = s.length();
        boolean[] mem = new boolean[length + 1];
        mem[length] = true;
        Set<String> set = new HashSet<>(wordDict);
        Map<Integer, List<Integer>> graph = new HashMap<Integer, List<Integer>>();
        
        // dynamic programming
        for (int i = length - 1; i >= 0; i--) {
            for (int j = i + 1; j <= length; j++) {
                String str = s.substring(i, j);
                if (set.contains(str) && mem[j]) {
                    if (!graph.containsKey(i)) {
                        mem[i] = true;
                        graph.put(i, new ArrayList<Integer>());
                    }
                    graph.get(i).add(j);
                }
            }
        }
        
        dfsBuildPaths(0, s, new StringBuilder(), graph, result);
        
        return result;
    }
    
    private void dfsBuildPaths(int index, String s, StringBuilder sb,
            Map<Integer, List<Integer>> graph, List<String> result) {
        int len = sb.length();
        // base case
        if (index == s.length()) {
            sb.setLength(len - 1);
            result.add(sb.toString());
            return;
        }
        
        if (graph.containsKey(index)) {
            for (int i: graph.get(index)) {
                sb.append(s.substring(index, i)).append(" ");
                dfsBuildPaths(i, s, sb, graph, result);
                sb.setLength(len);
            }
        }
    }
}
}