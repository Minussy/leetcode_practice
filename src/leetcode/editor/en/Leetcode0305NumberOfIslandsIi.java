//A 2d grid map of m rows and n columns is initially filled with water. We may
//perform an addLand operation which turns the water at position (row, col) into a
//land. Given a list of positions to operate, count the number of islands after ea
//ch addLand operation. An island is surrounded by water and is formed by connecting
// adjacent lands horizontally or vertically. You may assume all four edges of t
//he grid are all surrounded by water. 
//
// Example: 
//
// 
//Input: m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
//Output: [1,1,2,3]
// 
//
// Explanation: 
//
// Initially, the 2d grid grid is filled with water. (Assume 0 represents water 
//and 1 represents land). 
//
// 
//0 0 0
//0 0 0
//0 0 0
// 
//
// Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land. 
//
// 
//1 0 0
//0 0 0   Number of islands = 1
//0 0 0
// 
//
// Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land. 
//
// 
//1 1 0
//0 0 0   Number of islands = 1
//0 0 0
// 
//
// Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land. 
//
// 
//1 1 0
//0 0 1   Number of islands = 2
//0 0 0
// 
//
// Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land. 
//
// 
//1 1 0
//0 0 1   Number of islands = 3
//0 1 0
// 
//
// Follow up: 
//
// Can you do it in time complexity O(k log mn), where k is the length of the
//positions?
// Related Topics Union Find 
// 👍 839 👎 21

package leetcode.editor.en;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

// 2020-07-20 23:31:38
// Zeshi Yang
public class Leetcode0305NumberOfIslandsIi {

    // Java: number-of-islands-ii
    public static void main(String[] args) {
        Solution sol = new Leetcode0305NumberOfIslandsIi().new Solution();
        // TO TEST
        int m = 3;
        int n = 3;
        int[][] positions = new int[][]{{0, 0}, {0, 1}, {1, 2}, {1, 2}};
        List<Integer> res = sol.numIslands2(m, n, positions);
        System.out.println(res);
    }

//leetcode submit region begin(Prohibit modification and deletion)
class UnionFind {

	private int[] parents;
	private int[] sizes;
	private int sizeIslands;

	public UnionFind(int n) {
		this.parents = new int[n];
		Arrays.fill(parents, -1); // 有可能会在某个点重复设置为陆地， -1表示还没设置过为陆地
		this.sizes = new int[n];
		sizeIslands = 0;
	}

	private int findRoot(int p) {
		int cur = p;
		while (cur != parents[cur]) {
			parents[cur] = parents[parents[cur]];
			cur = parents[cur];
		}
		parents[p] = cur;
		return cur;
	}

	public boolean find(int p, int q) {
		return findRoot(p) == findRoot(q);
	}

	public void union(int p, int q) {
		int rootP = findRoot(p);
		int rootQ = findRoot(q);
		if (sizes[rootP] < sizes[rootQ]) { // p -> q
			parents[rootP] = rootQ;
			sizes[rootQ] += sizes[rootP];
		} else { // q -> p
			parents[rootQ] = rootP;
			sizes[rootP] += sizes[rootQ];
		}
		this.sizeIslands--;
	}

	public void addIsLand(int p) { // 有可能会在某个点重复设置为陆地， -1表示还没设置过为陆地
		if (parents[p] == -1) {
			parents[p] = p;
			sizes[p] = 1;
			this.sizeIslands++;
		}
	}

	private boolean isIsland(int p) {
		return sizes[p] > 0;
	}

	public int getSizeOfIsLands() {
		return this.sizeIslands;
	}
}

class Solution {

	private final int[][] DIRECTIONS = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

	public List<Integer> numIslands2(int m, int n, int[][] positions) {
		// corner case
		List<Integer> res = new LinkedList<>();
		UnionFind uf = new UnionFind(m * n);
		for (int[] position : positions) {
			int row = position[0];
			int col = position[1];
			int idx = row * n + col;
			uf.addIsLand(idx);
			for (int[] dir : DIRECTIONS) {
				int r = row + dir[0];
				int c = col + dir[1];
				int neighbor = r * n + c;
				if (r >= 0 && r < m && c >= 0 && c < n && uf.isIsland(neighbor)) {
					if (!uf.find(idx, neighbor)) {
						uf.union(idx, neighbor);
					}
				}
			}
			res.add(uf.getSizeOfIsLands());
		}
		return res;
	}

}
//leetcode submit region end(Prohibit modification and deletion)

}