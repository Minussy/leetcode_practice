//Given a linked list, reverse the nodes of a linked list k at a time and return
// its modified list. 
//
// k is a positive integer and is less than or equal to the length of the linked
// list. If the number of nodes is not a multiple of k then left-out nodes in the 
//end should remain as it is. 
//
// 
// 
//
// Example: 
//
// Given this linked list: 1->2->3->4->5 
//
// For k = 2, you should return: 2->1->4->3->5 
//
// For k = 3, you should return: 3->2->1->4->5 
//
// Note: 
//
// 
// Only constant extra memory is allowed. 
// You may not alter the values in the list's nodes, only nodes itself may be ch
//anged. 
// 
// Related Topics Linked List 
// 👍 2352 👎 359

package leetcode.editor.en;

import leetcode.editor.ListNode;

// 2020-08-04 11:12:31
// Zeshi Yang
public class Leetcode0025ReverseNodesInKGroup{
    // Java: reverse-nodes-in-k-group
    public static void main(String[] args) {
        Solution sol = new Leetcode0025ReverseNodesInKGroup().new Solution();
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
    public ListNode reverseKGroup(ListNode head, int k) {
        // S2: recursion,先reverse再recursion，reverse里面用recursion做
        if(head == null || head.next == null) {
            return head;
        }
        ListNode cur = head;
        //这个也可以当做base case
        for(int i = 0; i < k-1; i++){
            cur = cur.next;
            if(cur == null){
                return head;
            }
        }
        ListNode next = cur.next; // next is first node of next k node or NULL
        cur.next = null;
        ListNode newHead = reverse(head);
        head.next = reverseKGroup(next, k);
        return newHead;
    }
    private ListNode reverse(ListNode head){
        if(head == null || head.next == null) return head;
        ListNode reversedHead = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return reversedHead;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}