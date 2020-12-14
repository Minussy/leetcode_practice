//Serialization is the process of converting a data structure or object into a s
//equence of bits so that it can be stored in a file or memory buffer, or transmit
//ted across a network connection link to be reconstructed later in the same or an
//other computer environment. 
//
// Design an algorithm to serialize and deserialize a binary search tree. There 
//is no restriction on how your serialization/deserialization algorithm should wor
//k. You just need to ensure that a binary search tree can be serialized to a stri
//ng and this string can be deserialized to the original tree structure. 
//
// The encoded string should be as compact as possible. 
//
// Note: Do not use class member/global/static variables to store states. Your s
//erialize and deserialize algorithms should be stateless. 
// Related Topics Tree 
// 👍 1397 👎 70

package leetcode.editor.en;

import java.util.LinkedList;
import java.util.Queue;
import leetcode.editor.TreeNode;

// 2020-09-07 16:49:05
// Zeshi Yang
public class Leetcode0449SerializeAndDeserializeBst{
    // Java: serialize-and-deserialize-bst
    public static void main(String[] args) {
        // TO TEST
        String data = "2,1,3";
        TreeNode root = new Leetcode0449SerializeAndDeserializeBst().new Codec().deserialize(data);
        System.out.println(new Leetcode0449SerializeAndDeserializeBst().new Codec().serialize(root));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur == null) {
                sb.append("null, ");
            } else {
                sb.append(cur.val).append(", ");
                queue.offer(cur.left);
                queue.offer(cur.right);
            }
        }
        sb.setLength(sb.length() - 2);
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String str = data.replaceAll("\\s*", "");
        String[] strArr = str.split(",");
        if (strArr.length == 0) {
            throw new IllegalArgumentException("Not valid Tree");
        }
        if (strArr.length == 1 && (strArr[0].equals("null") || strArr[0].equals(" null"))) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(strArr[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 1;
        while (i < strArr.length) {
            TreeNode cur = queue.poll();
            TreeNode left =
                    strArr[i].equals("null") || strArr[i].equals(" null") ?
                            null : new TreeNode(Integer.parseInt(strArr[i]));
            TreeNode right =
                    (++i >= strArr.length || strArr[i].equals("null")|| strArr[i].equals(" null")) ?
                    null : new TreeNode(Integer.parseInt(strArr[i]));
            cur.left = left;
            cur.right = right;

            if (left != null) {
                queue.offer(left);
            }
            if (right != null) {
                queue.offer(right);
            }
            i++;
        }
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
//leetcode submit region end(Prohibit modification and deletion)

}