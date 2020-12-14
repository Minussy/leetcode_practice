//Given a list of words (without duplicates), please write a program that return
//s all concatenated words in the given list of words.
// A concatenated word is defined as a string that is comprised entirely of at l
//east two shorter words in the given array. 
//
// Example: 
// 
//Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","
//ratcatdogcat"]
//
//Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
//
//Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats";  "
//dogcatsdog" can be concatenated by "dog", "cats" and "dog"; "ratcatdogcat" can b
//e concatenated by "rat", "cat", "dog" and "cat".
// 
// 
//
// Note: 
// 
// The number of elements of the given array will not exceed 10,000 
// The length sum of elements in the given array will not exceed 600,000. 
// All the input string will only include lower case letters. 
// The returned elements order does not matter. 
// 
// Related Topics Dynamic Programming Depth-first Search Trie 
// 👍 824 👎 116

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
// 2020-09-06 17:33:52
// Zeshi Yang
public class Leetcode0472ConcatenatedWords{
    // Java: concatenated-words
    public static void main(String[] args) {
        Solution sol = new Leetcode0472ConcatenatedWords().new Solution();
        // TO TEST
        String[] words = {"cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"};
        List<String> res = sol.findAllConcatenatedWordsInADict(words);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
// Solution 1: DFS
class Solution {
    
    int min; // the min length of the word
    
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        
        Set<String> set = new HashSet<>(10000);
        List<String> res = new ArrayList<>();
        min = Integer.MAX_VALUE;
        for (String word : words) {
            if (word.length() == 0) {
                continue;
            }
            set.add(word);
            min = Math.min(min, word.length());
        }
        for (String word : words) {
            if (check(set, word, 0)) {
                res.add(word);
            }
        }
        return res;
    }
    
    private boolean check(Set<String> set, String word, int start) {
        for (int i = start + min; i <= word.length() - min; i++) {
            if (set.contains(word.substring(start, i)) &&
                    (set.contains(word.substring(i)) || check(set, word, i))) {
                return true;
            }
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


// Solution 1: DP, 477ms, 算法复杂度为O(nm^2)。n为数组长度,m为数组中字符串平均长度
class Solution1 {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> res = new ArrayList<>();
        // corner case
        if (words == null || words.length <= 2) {
            return res;
        }
        Set<String> set = new HashSet(Arrays.asList(words));
        for (String word: words) {
            set.remove(word);
            if (wordBreak(word, set)) {
                res.add(word);
            }
            set.add(word);
        }
        return res;
    }

    private boolean wordBreak(String word, Set<String> set) {
        // corner case
        if (word == null || word.length() == 0) {
            return false;
        }

        int len = word.length();
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && set.contains(word.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[len];
    }
}

// Solution 2_1: DFS 24ms, 算法复杂度为O(nm)。n为数组长度,m为数组中字符串平均长度
/*
先算出单词的最小长度min，dfs的时候，带着这个参数，会快很多，每次至少检测长度为min的单词
 */
class Solution2_1 {
    
    int min; // the min length of the word
    
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        
        Set<String> set = new HashSet<>(10000);
        List<String> res = new ArrayList<>();
        min = Integer.MAX_VALUE;
        for (String word : words) {
            if (word.length() == 0) {
                continue;
            }
            set.add(word);
            min = Math.min(min, word.length());
        }
        for (String word : words) {
            if (check(set, word, 0)) {
                res.add(word);
            }
        }
        return res;
    }
    
    private boolean check(Set<String> set, String word, int start) {
        for (int i = start + min; i <= word.length() - min; i++) {
            if (set.contains(word.substring(start, i)) &&
                    (set.contains(word.substring(i)) || check(set, word, i))) {
                return true;
            }
        }
        return false;
    }
}

// Solution 2_2: DFS in my template, 31ms, 算法复杂度为O(nm)。n为数组长度,m为数组中字符串平均长度
class Solution2_2 {
    
    int min; // the min length of the word
    
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> res = new ArrayList<>();
        if (words == null || words.length == 1) {
            return res;
        }
        Set<String> set = new HashSet<>(10000);
        min = Integer.MAX_VALUE;
        for (String word : words) {
            if (word.length() == 0) {
                continue;
            }
            set.add(word);
            min = Math.min(min, word.length());
        }
        // min = 1;
        for (String word : words) {
            if (word.length() == 0) {
                continue;
            }
            set.remove(word);
            if (dfs(set, word, 0)) {
                res.add(word);
            }
            set.add(word);
        }
        return res;
    }
    
    private boolean dfs(Set<String> set, String word, int idx) {
        // base case, success case
        if (idx == word.length() || set.contains(word.substring(idx))) {
            return true;
        }
        
        for (int i = idx + min; i <= word.length() - min; i++) {
            String str = word.substring(idx, i); // [idx, i)
            if (set.contains(str)) {
                if (dfs(set, word, i)) {
                    return true;
                }
            }
        }
        return false;
    }
}

// Solution 2_3: DFS in my template with pruning,65ms, 算法复杂度为O(nm)。n为数组长度,m为数组中字符串平均长度
class Solution1_4 {
    
    int min; // the min length of the word
    
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> res = new ArrayList<>();
        if (words == null || words.length == 1) {
            return res;
        }
        Set<String> set = new HashSet<>(10000);
        min = Integer.MAX_VALUE;
        for (String word : words) {
            if (word.length() == 0) {
                continue;
            }
            set.add(word);
            min = Math.min(min, word.length());
        }
        for (String word : words) {
            if (word.length() == 0) {
                continue;
            }
            set.remove(word);
            int len = word.length();
            Boolean[] memo = new Boolean[len + 1];
            if (dfs(set, word, 0, memo)) {
                res.add(word);
            }
            set.add(word);
        }
        return res;
    }
    
    private boolean dfs(Set<String> set, String word, int idx, Boolean[] memo) {
        if (memo[idx] != null) {
            return memo[idx];
        }
        // base case, success case
        if (set.contains(word.substring(idx))) {
            memo[idx] = true;
            return true;
        }
        
        for (int i = idx + min; i <= word.length() - min; i++) {
            String str = word.substring(idx, i); // [idx, i)
            if (set.contains(str)) {
                if (dfs(set, word, i, memo)) {
                    memo[i] = true;
                    return true;
                }
            }
        }
        memo[idx] = false;
        return false;
    }
}

// Solution 3: Trie + DFS, 142ms, 算法复杂度为O(nm)。n为数组长度,m为数组中字符串平均长度
class Solution3 {
    
    class TrieNode {
        
        boolean isWord = false;
        TrieNode[] children;
        
        TrieNode() {
            children = new TrieNode[26];
        }
    }
    
    class Trie {
        
        TrieNode root;
        
        Trie() {
            root = new TrieNode();
        }
        
        public void insert(String word) {
            TrieNode node = root;
            for (char ch : word.toCharArray()) {
                if (node.children[ch - 'a'] == null) {
                    node.children[ch - 'a'] = new TrieNode();
                }
                node = node.children[ch - 'a'];
            }
            node.isWord = true;
        }
        
        public boolean search(String word) {
            TrieNode node = root;
            for (char ch : word.toCharArray()) {
                if (node.children[ch - 'a'] == null) {
                    return false;
                }
                node = node.children[ch - 'a'];
            }
            return node.isWord;
            
        }
        
        public boolean startsWith(String prefix) {
            TrieNode node = root;
            for (char ch : prefix.toCharArray()) {
                if (node.children[ch - 'a'] == null) {
                    return false;
                }
                node = node.children[ch - 'a'];
            }
            return true;
        }
    }
    
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }
        List<String> res = new ArrayList<>();
        
        for (String word : words) {
            if (dfs(trie, word, false)) {
                res.add(word);
            }
        }
        return res;
    }
    
    private boolean dfs(Trie trie, String word, boolean isCut) {
        // base case
        if (word.equals("")) {
            return isCut;
        }
        for (int i = 0; i < word.length(); i++) {
            String pre = word.substring(0, i + 1);
            if (i == word.length() - 1 && !isCut) {
                return false;
            }
            if (trie.search(pre)) {
                // cut = true;
                if (dfs(trie, word.substring(i + 1), true)) {
                    return true;
                }
            }
        }
        return false;
    }
}
}