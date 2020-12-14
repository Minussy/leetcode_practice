//There are n servers numbered from 0 to n-1 connected by undirected server-to-s
//erver connections forming a network where connections[i] = [a, b] represents a c
//onnection between servers a and b. Any server can reach any other server directl
//y or indirectly through the network. 
//
// A critical connection is a connection that, if removed, will make some server
// unable to reach some other server. 
//
// Return all critical connections in the network in any order. 
//
// 
// Example 1: 
//
// 
//
// 
//Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
//Output: [[1,3]]
//Explanation: [[3,1]] is also accepted.
// 
//
// 
// Constraints: 
//
// 
// 1 <= n <= 10^5 
// n-1 <= connections.length <= 10^5 
// connections[i][0] != connections[i][1] 
// There are no repeated connections. 
// 
// Related Topics Depth-first Search 
// 👍 1173 👎 82

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// 2020-07-19 23:21:35
// Zeshi Yang
public class Leetcode1192CriticalConnectionsInANetwork{
    // Java: critical-connections-in-a-network
    public static void main(String[] args) {
        Solution sol = new Leetcode1192CriticalConnectionsInANetwork().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // Solution 1: DFS with list denoting neighbors
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        // build the graph
        for (List<Integer> neighbor: connections) {
            graph.get(neighbor.get(0)).add(neighbor.get(1));
            graph.get(neighbor.get(1)).add(neighbor.get(0));
        }
        // an array to save the timestamp that we meet a certain node
        List<List<Integer>> res = new ArrayList<>();
        int[] id = new int[n];
        dfs(1, -1, 0, id, graph, res);
        return res;
    }

    /**
     * @param vertexID: the # of the vertex
     * @param prev: index of the previous vertex
     * @param cur: index of current vertex
     * @param id: the time stamp array
     * @param graph: the graph connection between vertices
     * @param res: result
     * @return: the # of the vertex cur
     */
    private int dfs(int vertexID, int prev, int cur, int[] id,
            List<List<Integer>> graph, List<List<Integer>> res) {
        if (id[cur] > 0) { // pruning, look up the form
            return id[cur];
        }
        id[cur] = vertexID;

        for (int next: graph.get(cur)) {
            if (next == prev) { // ignore the edge where 'cur' comes from, 避免A走向B之后，B又走向A
                continue;
            }
            id[next] = dfs(vertexID + 1, cur, next, id, graph, res);
            id[cur] = Math.min(id[cur], id[next]);
        }

        // determine whether (prev, cur) is a critical edge
        if (id[cur] == vertexID && prev != -1) {
            res.add(Arrays.asList(prev, cur));
        }
        return id[cur];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
class Solution1 {
    // Solution 1: DFS with list denoting neighbors
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        // build the graph
        for (List<Integer> neighbor: connections) {
            graph.get(neighbor.get(0)).add(neighbor.get(1));
            graph.get(neighbor.get(1)).add(neighbor.get(0));
        }
        // an array to save the timestamp that we meet a certain node
        List<List<Integer>> res = new ArrayList<>();
        int[] id = new int[n];
        dfs(1, -1, 0, id, graph, res);
        return res;
    }

    /**
     * @param vertexID: the # of the vertex
     * @param prev: index of the previous vertex
     * @param cur: index of current vertex
     * @param id: the time stamp array
     * @param graph: the graph connection between vertices
     * @param res: result
     * @return: the # of the vertex cur
     */
    private int dfs(int vertexID, int prev, int cur, int[] id,
            List<List<Integer>> graph, List<List<Integer>> res) {
        if (id[cur] > 0) { // pruning, look up the form
            return id[cur];
        }
        id[cur] = vertexID;

        for (int next: graph.get(cur)) {
            if (next == prev) { // ignore the edge where 'cur' comes from, 避免A走向B之后，B又走向A
                continue;
            }
            id[next] = dfs(vertexID + 1, cur, next, id, graph, res);
            id[cur] = Math.min(id[cur], id[next]);
        }

        // determine whether (prev, cur) is a critical edge
        if (id[cur] == vertexID && prev != -1) {
            res.add(Arrays.asList(prev, cur));
        }
        return id[cur];
    }
}

class Solution2 {
    // Solution 2: DFS with HashSet denoting neighbors
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        buildGraph(connections, graph);

        // an array to save the timestamp that we meet a certain node
        List<List<Integer>> res = new ArrayList<>();
        int[] id = new int[n];
        dfs(1, -1, 0, id, graph, res);
        return res;
    }

    private void buildGraph(List<List<Integer>> con, Map<Integer, Set<Integer>> graph) {
        for (List<Integer> edge: con) {
            int n1 = edge.get(0);
            int n2 = edge.get(1);
            if (!graph.containsKey(n1)) {
                Set<Integer> set2 = new HashSet<>();
                graph.put(n1, set2);
            }
            if (!graph.containsKey(n2)) {
                Set<Integer> set1 = new HashSet<>();
                graph.put(n2, set1);
            }
            graph.get(n1).add(n2);
            graph.get(n2).add(n1);
        }
    }

    /**
     * @param vertexID: the # of the vertex
     * @param prev: index of the previous vertex
     * @param cur: index of current vertex
     * @param id: the time stamp array
     * @param graph: the graph connection between vertices
     * @param res: result
     * @return: the # of the vertex cur
     */
    private int dfs(int vertexID, int prev, int cur, int[] id,
            Map<Integer, Set<Integer>> graph, List<List<Integer>> res) {
        if (id[cur] > 0) { // pruning, look up the form
            return id[cur];
        }
        id[cur] = vertexID;

        Set<Integer> set = graph.get(cur);
        for (int next: set) {
            if (next == prev) { // ignore the edge where 'cur' comes from, 避免A走向B之后，B又走向A
                continue;
            }
            id[next] = dfs(vertexID + 1, cur, next, id, graph, res);
            id[cur] = Math.min(id[cur], id[next]);
        }
        // determine whether (prev, cur) is a critical edge
        if (id[cur] == vertexID && prev != -1) {
            res.add(Arrays.asList(prev, cur));
        }
        return id[cur];
    }
}

}