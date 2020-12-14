//Given a 2D board and a list of words from the dictionary, find all words in th
//e board. 
//
// Each word must be constructed from letters of sequentially adjacent cell, whe
//re "adjacent" cells are those horizontally or vertically neighboring. The same l
//etter cell may not be used more than once in a word. 
//
// 
//
// Example: 
//
// 
//Input: 
//board = [
//  ['o','a','a','n'],
//  ['e','t','a','e'],
//  ['i','h','k','r'],
//  ['i','f','l','v']
//]
//words = ["oath","pea","eat","rain"]
//
//Output: ["eat","oath"]
// 
//
// 
//
// Note: 
//
// 
// All inputs are consist of lowercase letters a-z. 
// The values of words are distinct. 
// 
// Related Topics Backtracking Trie 
// 👍 2814 👎 121

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
// 2020-08-27 23:14:47
// Zeshi Yang
public class Leetcode0212WordSearchIi{
    // Java: word-search-ii
    public static void main(String[] args) {
        Solution sol = new Leetcode0212WordSearchIi().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    class TrieNode {

        HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
        String word = null;

        public TrieNode() {
        }
    }

    char[][] _board = null;
    ArrayList<String> _result = new ArrayList<String>();

    public List<String> findWords(char[][] board, String[] words) {

        // Step 1). Construct the Trie
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode node = root;

            for (Character letter : word.toCharArray()) {
                if (node.children.containsKey(letter)) {
                    node = node.children.get(letter);
                } else {
                    TrieNode newNode = new TrieNode();
                    node.children.put(letter, newNode);
                    node = newNode;
                }
            }
            node.word = word;  // store words in Trie
        }

        this._board = board;
        // Step 2). Backtracking starting for each cell in the board
        for (int row = 0; row < board.length; ++row) {
            for (int col = 0; col < board[row].length; ++col) {
                if (root.children.containsKey(board[row][col])) {
                    backtracking(row, col, root);
                }
            }
        }

        return this._result;
    }

    private void backtracking(int row, int col, TrieNode parent) {
        Character letter = this._board[row][col];
        TrieNode currNode = parent.children.get(letter);

        // check if there is any match
        if (currNode.word != null) {
            this._result.add(currNode.word);
            currNode.word = null;
        }

        // mark the current letter before the EXPLORATION
        this._board[row][col] = '#';

        // explore neighbor cells in around-clock directions: up, right, down, left
        int[] rowOffset = {-1, 0, 1, 0};
        int[] colOffset = {0, 1, 0, -1};
        for (int i = 0; i < 4; ++i) {
            int newRow = row + rowOffset[i];
            int newCol = col + colOffset[i];
            if (newRow < 0 || newRow >= this._board.length || newCol < 0
                    || newCol >= this._board[0].length) {
                continue;
            }
            if (currNode.children.containsKey(this._board[newRow][newCol])) {
                backtracking(newRow, newCol, currNode);
            }
        }

        // End of EXPLORATION, restore the original letter in the board.
        this._board[row][col] = letter;

        // Optimization: incrementally remove the leaf nodes
        if (currNode.children.isEmpty()) {
            parent.children.remove(letter);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: DFS
class Solution1 {

    private final int[][] DIRECTIONS = new int[][]{{1, 0}, {0, -1}, {-1, 0}, {0, 1}};

    public List<String> findWords(char[][] board, String[] words) {
        Set<String> res = new HashSet<>();
        // corner case
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
            return new ArrayList<>(res);
        }
        int rows = board.length;
        int cols = board[0].length;
        boolean[][] visited = new boolean[rows][cols];
        for (String word: words) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (dfs(board, i, j, word, 0, visited)) {
                        res.add(word);
                    }
                }
            }
        }
        return new ArrayList<>(res);
    }

    private boolean dfs(char[][] matrix, int i, int j, String word, int idx,
            boolean[][] visited) {

        int rows = matrix.length;
        int cols = matrix[0].length;
        int len = word.length();
        // base case
        // success case
        if (idx == len) {
            return true;
        }
        // failture case
        if (i < 0 || i >= rows || j < 0 || j >= cols || word.charAt(idx) != matrix[i][j]
                || visited[i][j]) {
            return false;
        }
        visited[i][j] = true;
        // Solution 1:
        boolean res = false;
        for (int[] dir : DIRECTIONS) {
            int row = i + dir[0];
            int col = j + dir[1];
            res = dfs(matrix, row, col, word, idx + 1, visited);
            if (res) {
                break;
            }
        }
        // Solution 2: 或者改成
//		boolean res = dfs(matrix, i - 1, j, word, idx + 1, visited)
//				|| dfs(matrix, i + 1, j, word, idx + 1, visited)
//				|| dfs(matrix, i, j - 1, word, idx + 1, visited)
//				|| dfs(matrix, i, j + 1, word, idx + 1, visited);
        visited[i][j] = false;
        return res;
    }
}

// Solution 2: Trie
class Solution2 {

    class TrieNode {

        HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
        String word = null;

        public TrieNode() {
        }
    }

    char[][] _board = null;
    ArrayList<String> _result = new ArrayList<String>();

    public List<String> findWords(char[][] board, String[] words) {

        // Step 1). Construct the Trie
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode node = root;

            for (Character letter : word.toCharArray()) {
                if (node.children.containsKey(letter)) {
                    node = node.children.get(letter);
                } else {
                    TrieNode newNode = new TrieNode();
                    node.children.put(letter, newNode);
                    node = newNode;
                }
            }
            node.word = word;  // store words in Trie
        }

        this._board = board;
        // Step 2). Backtracking starting for each cell in the board
        for (int row = 0; row < board.length; ++row) {
            for (int col = 0; col < board[row].length; ++col) {
                if (root.children.containsKey(board[row][col])) {
                    backtracking(row, col, root);
                }
            }
        }

        return this._result;
    }

    private void backtracking(int row, int col, TrieNode parent) {
        Character letter = this._board[row][col];
        TrieNode currNode = parent.children.get(letter);

        // check if there is any match
        if (currNode.word != null) {
            this._result.add(currNode.word);
            currNode.word = null;
        }

        // mark the current letter before the EXPLORATION
        this._board[row][col] = '#';

        // explore neighbor cells in around-clock directions: up, right, down, left
        int[] rowOffset = {-1, 0, 1, 0};
        int[] colOffset = {0, 1, 0, -1};
        for (int i = 0; i < 4; ++i) {
            int newRow = row + rowOffset[i];
            int newCol = col + colOffset[i];
            if (newRow < 0 || newRow >= this._board.length || newCol < 0
                    || newCol >= this._board[0].length) {
                continue;
            }
            if (currNode.children.containsKey(this._board[newRow][newCol])) {
                backtracking(newRow, newCol, currNode);
            }
        }

        // End of EXPLORATION, restore the original letter in the board.
        this._board[row][col] = letter;

        // Optimization: incrementally remove the leaf nodes
        if (currNode.children.isEmpty()) {
            parent.children.remove(letter);
        }
    }
}
}