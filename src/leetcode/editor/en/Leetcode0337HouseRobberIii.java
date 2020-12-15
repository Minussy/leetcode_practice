//The thief has found himself a new place for his thievery again. There is only 
//one entrance to this area, called the "root." Besides the root, each house has o
//ne and only one parent house. After a tour, the smart thief realized that "all h
//ouses in this place forms a binary tree". It will automatically contact the poli
//ce if two directly-linked houses were broken into on the same night. 
//
// Determine the maximum amount of money the thief can rob tonight without alert
//ing the police. 
//
// Example 1: 
//
// 
//Input: [3,2,3,null,3,null,1]
//
//     3
//    / \
//   2   3
//    \   \ 
//     3   1
//
//Output: 7 
//Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7. 
//
// Example 2: 
//
// 
//Input: [3,4,5,1,3,null,1]
//
//     3
//    / \
//   4   5
//  / \   \ 
// 1   3   1
//
//Output: 9
//Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
// Related Topics Dynamic Programming Tree Depth-first Search 
// 👍 3530 👎 63

package leetcode.editor.en;

import leetcode.editor.TreeNode;

// 2020-12-14 23:49:55
// Zeshi Yang
public class Leetcode0337HouseRobberIii{
    // Java: house-robber-iii
    public static void main(String[] args) {
        Solution sol = new Leetcode0337HouseRobberIii().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int rob(TreeNode root) {
        
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}