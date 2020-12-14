//We are given some website visits: the user with name username[i] visited the w
//ebsite website[i] at time timestamp[i]. 
//
// A 3-sequence is a list of websites of length 3 sorted in ascending order by t
//he time of their visits. (The websites in a 3-sequence are not necessarily disti
//nct.) 
//
// Find the 3-sequence visited by the largest number of users. If there is more 
//than one solution, return the lexicographically smallest such 3-sequence. 
//
// 
//
// Example 1: 
//
// 
//Input: username = ["joe","joe","joe","james","james","james","james","mary","m
//ary","mary"], timestamp = [1,2,3,4,5,6,7,8,9,10], website = ["home","about","car
//eer","home","cart","maps","home","home","about","career"]
//Output: ["home","about","career"]
//Explanation: 
//The tuples in this example are:
//["joe", 1, "home"]
//["joe", 2, "about"]
//["joe", 3, "career"]
//["james", 4, "home"]
//["james", 5, "cart"]
//["james", 6, "maps"]
//["james", 7, "home"]
//["mary", 8, "home"]
//["mary", 9, "about"]
//["mary", 10, "career"]
//The 3-sequence ("home", "about", "career") was visited at least once by 2 user
//s.
//The 3-sequence ("home", "cart", "maps") was visited at least once by 1 user.
//The 3-sequence ("home", "cart", "home") was visited at least once by 1 user.
//The 3-sequence ("home", "maps", "home") was visited at least once by 1 user.
//The 3-sequence ("cart", "maps", "home") was visited at least once by 1 user.
// 
//
// 
//
// Note: 
//
// 
// 3 <= N = username.length = timestamp.length = website.length <= 50 
// 1 <= username[i].length <= 10 
// 0 <= timestamp[i] <= 10^9 
// 1 <= website[i].length <= 10 
// Both username[i] and website[i] contain only lowercase characters. 
// It is guaranteed that there is at least one user who visited at least 3 websi
//tes. 
// No user visits two websites at the same time. 
// 
// Related Topics Array Hash Table Sort 
// 👍 115 👎 1016

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

// 2020-11-17 16:38:42
// Zeshi Yang
public class Leetcode1152AnalyzeUserWebsiteVisitPattern{
    // Java: analyze-user-website-visit-pattern
    public static void main(String[] args) {
        Solution sol = new Leetcode1152AnalyzeUserWebsiteVisitPattern().new Solution();
        // TO TEST
        String[] username = {"joe","joe","joe","james","james","james","james","mary","mary",
                "mary"};
        int[] timestamp = {1,2,3,4,5,6,7,8,9,10};
        String[] website = {"home","about","career","home","cart","maps","home","home","about",
                "career"};
        List<String> res = sol.mostVisitedPattern(username, timestamp, website);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    // time = O(m * n * k), space = O(m * n * k)
    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        List<String> res = new ArrayList<>();
        // corner case
        if (username == null || username.length == 0 || timestamp == null || timestamp.length == 0
                || website == null || website.length == 0) {
            return res;
        }
        
        int len = username.length;
        List<Log> logList = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            logList.add(new Log(username[i], timestamp[i], website[i]));
        }
        // sort list by timestamp
        logList.sort(Comparator.comparingInt(o -> o.timestamp));
        
        // build graph
        HashMap<String, List<String>> map = new HashMap<>();
        for (Log log : logList) {
            if (!map.containsKey(log.username)) {
                map.put(log.username, new ArrayList<>());
            }
            map.get(log.username).add(log.website);
        }
        
        // find all possible 3-sequence
        // every Set stores every one's all 3-sequence
        List<HashSet<String>> setList = new ArrayList<>();
        for (String key : map.keySet()) {
            List<String> webList = map.get(key);
            findSequence(webList, setList);
        }
        
        // find top 3 website sequence
        int max = 0;
        PriorityQueue<String> minHeap = new PriorityQueue<>(String::compareTo);
        HashMap<String, Integer> topMap = new HashMap<>();
        for (HashSet<String> set : setList) {
            for (String s : set) {
                topMap.put(s, topMap.getOrDefault(s, 0) + 1);
                if (topMap.get(s) >= max) {
                    if (topMap.get(s) > max) {
                        max = topMap.get(s);
                        minHeap.clear();
                    }
                    minHeap.offer(s);
                }
            }
        }
        
        String smallest = minHeap.poll();
        assert smallest != null;
        String[] strs = smallest.split(",");
        Collections.addAll(res, strs);
        return res;
    }
    
    /**
     * find all the possible 3 website sequence
     * @param webList website of some one
     * @param setList List of HashSet which contains all the possible 3 websites sequences
     */
    private void findSequence(List<String> webList, List<HashSet<String>> setList) {
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < webList.size() - 2; i++) {
            for (int j = i + 1; j < webList.size() - 1; j++) {
                for (int k = j + 1; k < webList.size(); k++) {
                    String s = "";
                    s += webList.get(i) + "," + webList.get(j) + "," + webList.get(k);
                    set.add(s);
                }
            }
        }
        setList.add(set);
    }
    
    private class Log {
        
        private final String username;
        private final int timestamp;
        private final String website;
        
        public Log(String username, int timestamp, String website) {
            this.username = username;
            this.timestamp = timestamp;
            this.website = website;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}