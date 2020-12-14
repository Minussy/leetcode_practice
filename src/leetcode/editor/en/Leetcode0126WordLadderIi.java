//Given two words (beginWord and endWord), and a dictionary's word list, find al
//l shortest transformation sequence(s) from beginWord to endWord, such that:
//
//
// Only one letter can be changed at a time
// Each transformed word must exist in the word list. Note that beginWord is not
// a transformed word.
//
//
// Note:
//
//
// Return an empty list if there is no such transformation sequence.
// All words have the same length.
// All words contain only lowercase alphabetic characters.
// You may assume no duplicates in the word list.
// You may assume beginWord and endWord are non-empty and are not the same.
//
//
// Example 1:
//
//
//Input:
//beginWord = "hit",
//endWord = "cog",
//wordList = ["hot","dot","dog","lot","log","cog"]
//
//Output:
//[
//  ["hit","hot","dot","dog","cog"],
//  ["hit","hot","lot","log","cog"]
//]
//
//
// Example 2:
//
//
//Input:
//beginWord = "hit"
//endWord = "cog"
//wordList = ["hot","dot","dog","lot","log"]
//
//Output: []
//
//Explanation: The endWord "cog" is not in wordList, therefore no possible trans
//formation.
//
//
//
//
// Related Topics Array String Backtracking Breadth-first Search

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Leetcode0126WordLadderIi {
	// Java: word-ladder-ii
	
	public static void main(String[] args) {
		
		Solution sol = new Leetcode0126WordLadderIi().new Solution();
		// TO TEST
		
		String beginWord = "maria";
		String endWord = "pearl";
		String[] words = {"flail","halon","lexus","joint","pears","slabs","lorie","lapse","wroth","yalow","swear","cavil","piety","yogis","dhaka","laxer","tatum","provo","truss","tends","deana","dried","hutch","basho","flyby","miler","fries","floes","lingo","wider","scary","marks","perry","igloo","melts","lanny","satan","foamy","perks","denim","plugs","cloak","cyril","women","issue","rocky","marry","trash","merry","topic","hicks","dicky","prado","casio","lapel","diane","serer","paige","parry","elope","balds","dated","copra","earth","marty","slake","balms","daryl","loves","civet","sweat","daley","touch","maria","dacca","muggy","chore","felix","ogled","acids","terse","cults","darla","snubs","boats","recta","cohan","purse","joist","grosz","sheri","steam","manic","luisa","gluts","spits","boxer","abner","cooke","scowl","kenya","hasps","roger","edwin","black","terns","folks","demur","dingo","party","brian","numbs","forgo","gunny","waled","bucks","titan","ruffs","pizza","ravel","poole","suits","stoic","segre","white","lemur","belts","scums","parks","gusts","ozark","umped","heard","lorna","emile","orbit","onset","cruet","amiss","fumed","gelds","italy","rakes","loxed","kilts","mania","tombs","gaped","merge","molar","smith","tangs","misty","wefts","yawns","smile","scuff","width","paris","coded","sodom","shits","benny","pudgy","mayer","peary","curve","tulsa","ramos","thick","dogie","gourd","strop","ahmad","clove","tract","calyx","maris","wants","lipid","pearl","maybe","banjo","south","blend","diana","lanai","waged","shari","magic","duchy","decca","wried","maine","nutty","turns","satyr","holds","finks","twits","peaks","teems","peace","melon","czars","robby","tabby","shove","minty","marta","dregs","lacks","casts","aruba","stall","nurse","jewry","knuth"};
		List<String> wordList = new ArrayList<>(Arrays.asList(words));
		List<List<String>> result = sol.findLadders(beginWord, endWord, wordList);
		Set<String> set = new HashSet<>();
		for (List list: result) {
			set.add(list.toString());
		}
		System.out.println(Arrays.deepToString(set.toArray()));
	}
	

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
	
	private String beginWord;
	private String endWord;
	private Set<String> wordSet;
	private Set<String> beginSet;
	private Set<String> endSet;
	private Set<String> set1;
	private Set<String> set2;
	private Map<String, List<String>> beginGraph;
	private Map<String, List<String>> endGraph;
	private Map<String, List<String>> graph1;
	private Map<String, List<String>> graph2;
	
	public List<List<String>> findLadders(String beginWord, String endWord,
			List<String> wordList) {
		
		List<List<String>> paths = new ArrayList<>();
		wordSet = new HashSet<>(wordList);
		
		// corner case
		if (wordList == null || !wordSet.contains(endWord)) {
			return paths;
		}
		
		initialize(beginWord, endWord);
		paths = bfs(beginWord, endWord);
		return paths;
	}
	
	private void initialize(String beginWord, String endWord) {
		this.beginWord = beginWord;
		this.endWord = endWord;
		
		beginSet = new HashSet<>();
		endSet = new HashSet<>();
		endSet.add(endWord);
		beginSet.add(beginWord);
		beginGraph = new HashMap<>();
		endGraph = new HashMap<>();
		
		set1 = beginSet;
		set2 = endSet;
		graph1 = beginGraph;
		graph2 = endGraph;
	}
	
	// 永远是从set1往set2走，make sure set1 <= set2
	private List<List<String>> bfs(String beginWord, String endWord) {
		List<List<String>> paths = new ArrayList<>();
		Set<String> meetingLevel = new HashSet<>();
		boolean meeted = false;
		while (!set1.isEmpty() && !set2.isEmpty()) {
			// make set1 <= set2, may exchange set1, set2; graph1, graph2
			makeSet1Smaller();
			Set<String> thisLevel = new HashSet<>();
			for (String cur : set1) {
				List<String> neighbors = convert(cur, wordSet);
				for (String str: neighbors) {
					if (set2.contains(str)) {
						meeted = true;
						meetingLevel.add(str);
					}
					thisLevel.add(str);
					graph1.computeIfAbsent(str, k -> new ArrayList<>()).add(cur);
				}
				
			}
			wordSet.removeAll(set1);
			set1 = thisLevel;
			if (meeted) {
				return buildPaths(meetingLevel, beginWord, endWord);
			}
		}
		return paths;
	}
	
	/**
	 * return the list of neighbors of the cur which are in the dict and 1 distance
	 */
	private List<String> convert(String cur, Set<String> dict) {
		List<String> res = new ArrayList<>();
		char[] chars = cur.toCharArray();
		for (int i = 0; i < cur.length(); i++) {
			char temp = chars[i];
			for (char c = 'a'; c <= 'z'; c++) {
				chars[i] = c;
				String str = String.valueOf(chars);
				if (c != temp && dict.contains(str) && !set1.contains(str)) {
					res.add(str);
				}
			}
			chars[i] = temp;
		}
		return res;
	}
	
	private List<List<String>> buildPaths(Set<String> meetingLevel, String begin, String end) {
		List<List<String>> result = new ArrayList<>();
		
		for (String cur : meetingLevel) {
			List<List<String>> pathsToCur = dfsBuildPath(cur, beginWord, true,
					new LinkedList<>(), new ArrayList<>());
			List<List<String>> pathsFromCur = dfsBuildPath(cur, endWord, false,
					new LinkedList<>(), new ArrayList<>());
			
			for (List<String> path1 : pathsToCur) {
				for (List<String> path2 : pathsFromCur) {
					List<String> temp = new ArrayList<>(path1);
					temp.add(cur);
					temp.addAll(path2);
					result.add(temp);
				}
			}
		}
		return result;
	}
	
	/**
	 * make set1 <= set2, may exchange set1, set2; graph1, graph2
	 */
	private void makeSet1Smaller() {
		if (set1.size() > set2.size()) {
			Set<String> tempSet = set1;
			set1 = set2;
			set2 = tempSet;
			
			Map<String, List<String>> tempGraph = graph1;
			graph1 = graph2;
			graph2 = tempGraph;
		}
	}
	
	private List<List<String>> dfsBuildPath(String cur, String end, boolean beginToEnd,
			LinkedList<String> list, List<List<String>> result) {
		// base case
		if (cur.equals(end)) {
			List<String> copy = new LinkedList<>(list);
			result.add(copy);
			return result;
		}
		
		// general case
		if (beginToEnd) {
			List<String> nextLevel = beginGraph.get(cur);
			for (String str : nextLevel) {
				list.addFirst(str);
				dfsBuildPath(str, beginWord, true, list, result);
				list.removeFirst();
			}
		} else {
			List<String> nextLevel = endGraph.get(cur);
			for (String str : nextLevel) {
				list.addLast(str);
				dfsBuildPath(str, endWord, false, list, result);
				list.removeLast();
			}
		}
		return result;
	}
}

//leetcode submit region end(Prohibit modification and deletion)

// solution 1: one directional BFS
// 如果HashSet的contains和add时间复杂度是O(1),
// 则T(n)= O(V + E) = O(min(2^k, k *n), S(n) = O(min(2^k, k * n)
// 如果HashSet的contains和add时间复杂度是O(k),
// 则T(n)= O(V + E) = O(min(k * 2^k, k^2 *n), S(n) = O(min(k * 2^k, k^2 * n)
// n: 字典里单词个数，k:每个单词的长度
class Solution1 {
	
	public List<List<String>> findLadders(String beginWord, String endWord,
			List<String> wordList) {
		List<List<String>> paths = new ArrayList<>();
		HashSet<String> wordSet = new HashSet<>(wordList);
		// corner case
		if (wordList == null || !wordSet.contains(endWord)) {
			return paths;
		}
		// elements in this level will be deleted from wordSet after traversing this level
		Queue<String> queue = new LinkedList<>();
		HashMap<String, List<String>> graph = new HashMap<>();
		queue.offer(beginWord);
		boolean reachEnd = false;
		// BFS
		while (!queue.isEmpty()) {
			Set<String> thisLevel = new HashSet<>();
			int size = queue.size();
			while (size-- > 0) {
				String cur = queue.poll();
				List<String> neighbors = convert(cur, wordSet);
				for (String str: neighbors) {
					if (str.equals(endWord)) {
						reachEnd = true;
					}
					graph.computeIfAbsent(str, k -> new ArrayList<>()).add(cur);
					if (!thisLevel.contains(str)) {
						queue.offer(str);
						thisLevel.add(str);
					}
				}
			}
			wordSet.removeAll(thisLevel);
			if (reachEnd) {
				LinkedList<String> list = new LinkedList<>();
				list.add(endWord);
				dfsBuildPaths(list, paths, endWord, beginWord, graph);
				break;
			}
		}
		return paths;
	}
	
	private List<String> convert(String cur, Set<String> dict) {
		List<String> res = new ArrayList<>();
		char[] chars = cur.toCharArray();
		for (int i = 0; i < cur.length(); i++) {
			char temp = chars[i];
			for (char c = 'a'; c <= 'z'; c++) {
				chars[i] = c;
				String str = String.valueOf(chars);
				if (c != temp && dict.contains(str)) {
					res.add(str);
				}
			}
			chars[i] = temp;
		}
		return res;
	}
	
	private void dfsBuildPaths(LinkedList<String> list, List<List<String>> paths, String cur,
			String target, HashMap<String, List<String>> graph) {
		// base case
		if (cur.equals(target)) {
			List<String> copy = new LinkedList<>(list);
			paths.add(copy);
			return;
		}
		
		List<String> nextLevel = graph.get(cur);
		
		for (String str : nextLevel) {
			list.addFirst(str);
			dfsBuildPaths(list, paths, str, target, graph);
			list.removeFirst();
		}
	}
}

// solution 2: bidirectional BFS
// 如果HashSet的contains和add时间复杂度是O(1),
// 则T(n)= O(V + E) = O(min(2^k, k *n), S(n) = O(min(2^k, k * n)
// 如果HashSet的contains和add时间复杂度是O(k),
// 则T(n)= O(V + E) = O(min(k * 2^k, k^2 *n), S(n) = O(min(k * 2^k, k^2 * n)
// n: 字典里单词个数，k:每个单词的长度
class Solution2 {
	
	private String beginWord;
	private String endWord;
	private Set<String> wordSet;
	private Set<String> beginSet;
	private Set<String> endSet;
	private Set<String> set1;
	private Set<String> set2;
	private Map<String, List<String>> beginGraph;
	private Map<String, List<String>> endGraph;
	private Map<String, List<String>> graph1;
	private Map<String, List<String>> graph2;
	
	public List<List<String>> findLadders(String beginWord, String endWord,
			List<String> wordList) {
		
		List<List<String>> paths = new ArrayList<>();
		wordSet = new HashSet<>(wordList);
		
		// corner case
		if (wordList == null || !wordSet.contains(endWord)) {
			return paths;
		}
		
		initialize(beginWord, endWord);
		paths = bfs(beginWord, endWord);
		return paths;
	}
	
	private void initialize(String beginWord, String endWord) {
		this.beginWord = beginWord;
		this.endWord = endWord;
		
		beginSet = new HashSet<>();
		endSet = new HashSet<>();
		endSet.add(endWord);
		beginSet.add(beginWord);
		beginGraph = new HashMap<>();
		endGraph = new HashMap<>();
		
		set1 = beginSet;
		set2 = endSet;
		graph1 = beginGraph;
		graph2 = endGraph;
	}
	
	// 永远是从set1往set2走，make sure set1 <= set2
	private List<List<String>> bfs(String beginWord, String endWord) {
		List<List<String>> paths = new ArrayList<>();
		Set<String> meetingLevel = new HashSet<>();
		boolean meeted = false;
		while (!set1.isEmpty() && !set2.isEmpty()) {
			// make set1 <= set2, may exchange set1, set2; graph1, graph2
			makeSet1Smaller();
			Set<String> thisLevel = new HashSet<>();
			for (String cur : set1) {
				List<String> neighbors = convert(cur, wordSet);
				for (String str: neighbors) {
					if (set2.contains(str)) {
						meeted = true;
						meetingLevel.add(str);
					}
					thisLevel.add(str);
					graph1.computeIfAbsent(str, k -> new ArrayList<>()).add(cur);
				}
				
			}
			wordSet.removeAll(set1);
			set1 = thisLevel;
			if (meeted) {
				return buildPaths(meetingLevel, beginWord, endWord);
			}
		}
		return paths;
	}
	
	/**
	 * return the list of neighbors of the cur which are in the dict and 1 distance
	 */
	private List<String> convert(String cur, Set<String> dict) {
		List<String> res = new ArrayList<>();
		char[] chars = cur.toCharArray();
		for (int i = 0; i < cur.length(); i++) {
			char temp = chars[i];
			for (char c = 'a'; c <= 'z'; c++) {
				chars[i] = c;
				String str = String.valueOf(chars);
				if (c != temp && dict.contains(str) && !set1.contains(str)) {
					res.add(str);
				}
			}
			chars[i] = temp;
		}
		return res;
	}
	
	private List<List<String>> buildPaths(Set<String> meetingLevel, String begin, String end) {
		List<List<String>> result = new ArrayList<>();
		
		for (String cur : meetingLevel) {
			List<List<String>> pathsToCur = dfsBuildPath(cur, beginWord, true,
					new LinkedList<>(), new ArrayList<>());
			List<List<String>> pathsFromCur = dfsBuildPath(cur, endWord, false,
					new LinkedList<>(), new ArrayList<>());
			
			for (List<String> path1 : pathsToCur) {
				for (List<String> path2 : pathsFromCur) {
					List<String> temp = new ArrayList<>(path1);
					temp.add(cur);
					temp.addAll(path2);
					result.add(temp);
				}
			}
		}
		return result;
	}
	
	/**
	 * make set1 <= set2, may exchange set1, set2; graph1, graph2
	 */
	private void makeSet1Smaller() {
		if (set1.size() > set2.size()) {
			Set<String> tempSet = set1;
			set1 = set2;
			set2 = tempSet;
			
			Map<String, List<String>> tempGraph = graph1;
			graph1 = graph2;
			graph2 = tempGraph;
		}
	}
	
	private List<List<String>> dfsBuildPath(String cur, String end, boolean beginToEnd,
			LinkedList<String> list, List<List<String>> result) {
		// base case
		if (cur.equals(end)) {
			List<String> copy = new LinkedList<>(list);
			result.add(copy);
			return result;
		}
		
		// general case
		if (beginToEnd) {
			List<String> nextLevel = beginGraph.get(cur);
			for (String str : nextLevel) {
				list.addFirst(str);
				dfsBuildPath(str, beginWord, true, list, result);
				list.removeFirst();
			}
		} else {
			List<String> nextLevel = endGraph.get(cur);
			for (String str : nextLevel) {
				list.addLast(str);
				dfsBuildPath(str, endWord, false, list, result);
				list.removeLast();
			}
		}
		return result;
	}
}


}