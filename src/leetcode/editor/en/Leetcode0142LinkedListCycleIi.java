//Given a linked list, return the node where the cycle begins. If there is no cy
//cle, return null. 
//
// To represent a cycle in the given linked list, we use an integer pos which re
//presents the position (0-indexed) in the linked list where tail connects to. If 
//pos is -1, then there is no cycle in the linked list. 
//
// Note: Do not modify the linked list. 
//
// 
//
// Example 1: 
//
// 
//Input: head = [3,2,0,-4], pos = 1
//Output: tail connects to node index 1
//Explanation: There is a cycle in the linked list, where tail connects to the s
//econd node.
// 
//
// 
//
// Example 2: 
//
// 
//Input: head = [1,2], pos = 0
//Output: tail connects to node index 0
//Explanation: There is a cycle in the linked list, where tail connects to the f
//irst node.
// 
//
// 
//
// Example 3: 
//
// 
//Input: head = [1], pos = -1
//Output: no cycle
//Explanation: There is no cycle in the linked list.
// 
//
// 
//
// 
//
// Follow-up: 
//Can you solve it without using extra space? 
// Related Topics Linked List Two Pointers 
// 👍 2838 👎 224

package leetcode.editor.en;

import leetcode.editor.ListNode;

// 2020-08-04 11:35:47
// Zeshi Yang
public class Leetcode0142LinkedListCycleIi{
    // Java: linked-list-cycle-ii
    public static void main(String[] args) {
        Solution sol = new Leetcode0142LinkedListCycleIi().new Solution();
        // TO TEST
        
        System.out.println();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        slow = slow.next;
        fast = fast.next.next;
        while (fast != null && fast.next != null && fast != slow) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast == null || fast.next == null) {
            return null;
        }
        ListNode temp = head;
        while (temp != slow) {
            temp = temp.next;
            slow = slow.next;
        }
        return temp;

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}