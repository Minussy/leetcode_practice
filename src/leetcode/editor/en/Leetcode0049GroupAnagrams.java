//Given an array of strings, group anagrams together. 
//
// Example: 
//
// 
//Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
//Output:
//[
//  ["ate","eat","tea"],
//  ["nat","tan"],
//  ["bat"]
//] 
//
// Note: 
//
// 
// All inputs will be in lowercase. 
// The order of your output does not matter. 
// 
// Related Topics Hash Table String 
// 👍 3715 👎 190

package leetcode.editor.en;

import java.util.*;
// 2020-08-04 11:22:56
// Zeshi Yang
public class Leetcode0049GroupAnagrams{
    // Java: group-anagrams
    public static void main(String[] args) {
        Solution sol = new Leetcode0049GroupAnagrams().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0)
            return new ArrayList<>();
        // 此排序是为了让每个List<String>的结果按照字典序输出
        // 但是并不需要，此题没要求
        Arrays.sort(strs);
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] cs = str.toCharArray();
            // 将char数组按照字典顺序排序
            Arrays.sort(cs);
            String sortStr = String.valueOf(cs);
            // 排序后的String作为key，
            if (!map.containsKey(sortStr))
                map.put(sortStr, new ArrayList<>());
            map.get(sortStr).add(str);
        }
        // 注意ArrayList的构造方法的应用
        return new ArrayList<>(map.values());
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}