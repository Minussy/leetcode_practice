//Equations are given in the format A / B = k, where A and B are variables repre
//sented as strings, and k is a real number (floating point number). Given some qu
//eries, return the answers. If the answer does not exist, return -1.0. 
//
// Example: 
//Given a / b = 2.0, b / c = 3.0. 
//queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? . 
//return [6.0, 0.5, -1.0, 1.0, -1.0 ]. 
//
// The input is: vector<pair<string, string>> equations, vector<double>& values,
// vector<pair<string, string>> queries , where equations.size() == values.size(),
// and the values are positive. This represents the equations. Return vector<doubl
//e>. 
//
// According to the example above: 
//
// 
//equations = [ ["a", "b"], ["b", "c"] ],
//values = [2.0, 3.0],
//queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ]. 
//
// 
//
// The input is always valid. You may assume that evaluating the queries will re
//sult in no division by zero and there is no contradiction. 
// Related Topics Union Find Graph 
// 👍 2270 👎 175

package leetcode.editor.en;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

// 2020-07-21 14:15:19
// Zeshi Yang
public class Leetcode0399EvaluateDivision {

	// Java: evaluate-division
	public static void main(String[] args) {
		Solution sol = new Leetcode0399EvaluateDivision().new Solution();
		// TO TEST

		System.out.println();
	}

//leetcode submit region begin(Prohibit modification and deletion)
class Vertex {

	String name;
	Vertex parent;
	double val; // this.value / parent.value
	int size;

	public Vertex(String name) {
		this.name = name;
		parent = this;
		val = 1.0;
		size = 1;
	}
}

class Solution {

	public double[] calcEquation(List<List<String>> equations, double[] values,
			List<List<String>> queries) {
		int len = queries.size();
		double[] res = new double[len];
		HashMap<String, Vertex> map = new HashMap<>(); // 存放某个点——它的Vertex

		traverseEquations(equations, values, map);

		Iterator<List<String>> query = queries.iterator();
		for (int i = 0; i < len; i++) {
			List<String> list = query.next();
			String dividend = list.get(0);
			String divider = list.get(1);
			if (!map.containsKey(dividend) || !map.containsKey(divider)) {
				res[i] = -1;
			} else {
				Vertex v1 = map.get(dividend);
				Vertex v2 = map.get(divider);
				if (find(v1, v2)) {
					res[i] = division(v1, v2);
				} else {
					res[i] = -1;
				}
			}
		}
		return res;
	}

	private void traverseEquations (List<List<String>> equations,
			double[] values, HashMap<String, Vertex> map) {
		int len = equations.size();
		Iterator<List<String>> equation = equations.iterator();
		for (int i = 0; i < equations.size(); i++) {
			List<String> list = equation.next();

			String dividend = list.get(0);
			String divider = list.get(1);
			double value = values[i];
			if (!map.containsKey(dividend)) {
				map.put(dividend, new Vertex(dividend));
			}
			if (!map.containsKey(divider)) {
				map.put(divider, new Vertex(divider));
			}

			Vertex v1 = map.get(dividend);
			Vertex v2 = map.get(divider);
			if (!find(v1, v2)) {
				union(v1, v2, value);
			}
		}
	}

	private void union(Vertex v1, Vertex v2, double quotient) {
		Vertex root1 = findRoot(v1);
		Vertex root2 = findRoot(v2);
		if (root1.size < root2.size) { // root1 -> root2

			root1.parent = root2.parent;
			root2.size += root1.size;
			root1.val = 1 / v1.val * (quotient * v2.val);
		} else { // root2 -> root1

			root2.parent = root1.parent;
			root1.size += root2.size;
			root2.val = 1 / v2.val / quotient * v1.val;
		}
	}

	private boolean find(Vertex v1, Vertex v2) {
		return findRoot(v1) == findRoot(v2);
	}

	private Vertex findRoot(Vertex v) {
		Vertex cur = v;
		double value = 1;
		while (cur != cur.parent) {
			cur.val *= cur.parent.val;
			value *= cur.val;
			cur.parent = cur.parent.parent;
			cur = cur.parent;
		}
		v.parent = cur;
		v.val = value;
		return cur;
	}

	private double division(Vertex dividend, Vertex divider) {
		return dividend.val / divider.val;
	}
}
//leetcode submit region end(Prohibit modification and deletion)

}