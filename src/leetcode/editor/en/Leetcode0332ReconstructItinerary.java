//Given a list of airline tickets represented by pairs of departure and arrival 
//airports [from, to], reconstruct the itinerary in order. All of the tickets belo
//ng to a man who departs from JFK. Thus, the itinerary must begin with JFK. 
//
// Note: 
//
// 
// If there are multiple valid itineraries, you should return the itinerary that
// has the smallest lexical order when read as a single string. For example, the i
//tinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"]. 
// All airports are represented by three capital letters (IATA code). 
// You may assume all tickets form at least one valid itinerary. 
// One must use all the tickets once and only once. 
// 
//
// Example 1: 
//
// 
//Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
//Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
// 
//
// Example 2: 
//
// 
//Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
//
//Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
//Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL"
//,"SFO"].
//             But it is larger in lexical order.
// 
// Related Topics Depth-first Search Graph 
// 👍 1894 👎 985

package leetcode.editor.en;

import java.util.*;
// 2020-07-19 23:01:09
// Zeshi Yang
public class Leetcode0332ReconstructItinerary{
    // Java: reconstruct-itinerary
    public static void main(String[] args) {
        Solution sol = new Leetcode0332ReconstructItinerary().new Solution();
        // TO TEST
        
        System.out.println();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<String> findItinerary(List<List<String>> tickets) {
        LinkedList<String> res= new LinkedList<>();
        if (tickets == null || tickets.size() == 0 || tickets.get(0) == null) {
            return res;
        }

        HashMap<String, PriorityQueue<String>> graph = new HashMap<>();
        for (List<String> pair: tickets) {
            String from = pair.get(0);
            String to = pair.get(1);
            if (!graph.containsKey(from)) {
                graph.put(from, new PriorityQueue<String>());
            }
            graph.get(from).offer(to);
        }
        search(res, "JFK", graph);
        return res;
    }

    private void search(LinkedList<String> res, String cur, HashMap<String, PriorityQueue<String>> graph) {
        PriorityQueue<String> nexts = graph.get(cur);
        while(nexts != null && !nexts.isEmpty()) {
            String to = nexts.poll();
            search(res, to, graph);
        }
        res.addFirst(cur);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}