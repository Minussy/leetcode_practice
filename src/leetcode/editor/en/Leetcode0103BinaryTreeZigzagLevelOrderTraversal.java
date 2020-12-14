//Given a binary tree, return the zigzag level order traversal of its nodes' val
//ues. (ie, from left to right, then right to left for the next level and alternat
//e between). 
//
// 
//For example: 
//Given binary tree [3,9,20,null,null,15,7], 
// 
//    3
//   / \
//  9  20
//    /  \
//   15   7
// 
// 
// 
//return its zigzag level order traversal as: 
// 
//[
//  [3],
//  [20,9],
//  [15,7]
//]
// 
// Related Topics Stack Tree Breadth-first Search 
// 👍 2245 👎 102

package leetcode.editor.en;

import leetcode.editor.TreeNode;

import java.util.*;

// 2020-07-26 12:01:45
// Zeshi Yang
public class Leetcode0103BinaryTreeZigzagLevelOrderTraversal{
    // Java: binary-tree-zigzag-level-order-traversal
    public static void main(String[] args) {
        Solution sol = new Leetcode0103BinaryTreeZigzagLevelOrderTraversal().new Solution();
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
// Solution 2: 设置flag isEven，然tempList.add还是tempList.addFirst
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean isEven = false;

        while(!queue.isEmpty()) {
            LinkedList<Integer> tempList = new LinkedList<>();
            int size = queue.size();

            while (size --> 0) {
                TreeNode cur = queue.poll();

                if(!isEven){
                    tempList.add(cur.val);
                }else {
                    tempList.addFirst(cur.val);
                }
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }

            isEven = !isEven;
            res.add(tempList);
        }


        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: 2个stack
class Solution1 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Stack<TreeNode> stackOdd = new Stack<>(); // from left to right
        Stack<TreeNode> stackEven = new Stack<>(); // from right to left

        // boolean flagEven = true;
        stackOdd.push(root); // odd level
        List<Integer> list = new ArrayList<>();
        // list.add(root.val);
        // result.add(list);
        TreeNode temp;


        while (stackOdd.isEmpty() == false || stackEven.isEmpty() == false) {
            list = new ArrayList<Integer>();
            while (stackOdd.isEmpty() == false){
                temp = stackOdd.pop();
                list.add(temp.val);

                if (temp.left != null) {
                    stackEven.push(temp.left);
                }
                if (temp.right != null) {
                    stackEven.push(temp.right);
                }
            }
            if (list.isEmpty()== false) {
                result.add(list);
            }


            list = new ArrayList<Integer>();
            while (stackEven.isEmpty() == false){
                temp = stackEven.pop();
                list.add(temp.val);

                if (temp.right != null) {
                    stackOdd.push(temp.right);
                }
                if (temp.left != null) {
                    stackOdd.push(temp.left);
                }
            }
            if (list.isEmpty()== false) {
                result.add(list);
            }
        }
        return result;
    }
}

// Solution 2: 设置flag isEven，然tempList.add还是tempList.addFirst
class Solution2 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean isEven = false;

        while(!queue.isEmpty()) {
            LinkedList<Integer> tempList = new LinkedList<>();
            int size = queue.size();

            while (size --> 0) {
                TreeNode cur = queue.poll();

                if(!isEven){
                    tempList.add(cur.val);
                }else {
                    tempList.addFirst(cur.val);
                }
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }

            isEven = !isEven;
            res.add(tempList);
        }


        return res;
    }
}
}