//Given a linked list, swap every two adjacent nodes and return its head. 
//
// You may not modify the values in the list's nodes, only nodes itself may be c
//hanged. 
//
// 
//
// Example: 
//
// 
//Given 1->2->3->4, you should return the list as 2->1->4->3.
// 
// Related Topics Linked List 
// 👍 2389 👎 172

package leetcode.editor.en;

import leetcode.editor.ListNode;

// 2020-08-04 11:12:29
// Zeshi Yang
public class Leetcode0024SwapNodesInPairs{
    // Java: swap-nodes-in-pairs
    public static void main(String[] args) {
        Solution sol = new Leetcode0024SwapNodesInPairs().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode swapPairs(ListNode head) {
        // S2: iteration
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode preNode = dummy;

        while (head != null && head.next != null) {
            // nodes to be swapped
            ListNode firstNode = head;
            ListNode secondNode = head.next;

            //swapping
            preNode.next = secondNode;
            firstNode.next = secondNode.next;
            secondNode.next = firstNode;

            //Reinitializing the head and prevNode for next swap
            preNode = preNode.next.next;
            head = firstNode.next;
        }
        return dummy.next;

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}