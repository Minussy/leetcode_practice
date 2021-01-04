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
        // corner case
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(-1, head);
        ListNode prevTail = dummy;
        while (head != null) {
            ListNode tail = reverseListByK(prevTail, head, k);
            if (tail == null) {
                reverseListByK(prevTail, prevTail.next, k);
                break;
            }
            prevTail = tail;
            head = prevTail.next;
        }
        return dummy.next;
    }
    
    /**
     * let prevTail connect to this reversed k group LinkedList,
     * and return the tail of k group, if the length of left listNode < k, return null
     * @param prevTail previous tail before this k group
     * @param head head of this k group
     * @param k the length of LinkedList we need to reverse
     * @return the tail of the reversed LinkedList or null if the length of left listNode < k
     */
    private ListNode reverseListByK(ListNode prevTail, ListNode head, int k) {
        // corner case
        if (head == null || head.next == null) {
            return null;
        }
        ListNode pre = null;
        ListNode cur = head;
        ListNode next;
        int count = 0;
        while (cur != null && count++ < k) {
            next = cur.next;
            cur.next = pre;
            
            pre = cur; // update pre
            cur = next; // update cur
        }
        prevTail.next = pre;
        head.next = cur;
        if (count < k) {
            return null;
        }
        return head;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: recursion. T(n) = O(n), S(n) = O(max(k, n/k)), 2 pass
// 0 ms,击败了100.00% 的Java用户, 39.6 MB,击败了20.14% 的Java用户
class Solution1 {
    
    public ListNode reverseKGroup(ListNode head, int k) {
        // corner case
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = head;
        for (int i = 0; i < k - 1; i++) {
            cur = cur.next;
            if (cur == null) {
                return head;
            }
        }
        ListNode next = cur.next; // next is first node of next k node or NULL
        cur.next = null;  // 与前k个断开，分别reverse
        ListNode newHead = reverse(head); // reverse前k个
        head.next = reverseKGroup(next, k); // recursion后面n个k group，并与前k个接上
        return newHead;
    }
    
    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode reversedHead = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return reversedHead;
    }
    
}

// Solution 2: iteration, T(n) = O(n), S(n) = O(1), one pass
// 0 ms,击败了100.00% 的Java用户, 39.1 MB,击败了68.16% 的Java用户
class Solution2 {
    
    public ListNode reverseKGroup(ListNode head, int k) {
        // corner case
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(-1, head);
        ListNode prevTail = dummy;
        while (head != null) {
            ListNode tail = reverseListByK(prevTail, head, k);
            if (tail == null) {
                reverseListByK(prevTail, prevTail.next, k);
                break;
            }
            prevTail = tail;
            head = prevTail.next;
        }
        return dummy.next;
    }
    
    /**
     * let prevTail connect to this reversed k group LinkedList,
     * and return the tail of k group, if the length of left listNode < k, return null
     * @param prevTail previous tail before this k group
     * @param head head of this k group
     * @param k the length of LinkedList we need to reverse
     * @return the tail of the reversed LinkedList or null if the length of left listNode < k
     */
    private ListNode reverseListByK(ListNode prevTail, ListNode head, int k) {
        // corner case
        if (head == null || head.next == null) {
            return null;
        }
        ListNode pre = null;
        ListNode cur = head;
        ListNode next;
        int count = 0;
        while (cur != null && count++ < k) {
            next = cur.next;
            cur.next = pre;
            
            pre = cur; // update pre
            cur = next; // update cur
        }
        prevTail.next = pre;
        head.next = cur;
        if (count < k) {
            return null;
        }
        return head;
    }
    
}
}