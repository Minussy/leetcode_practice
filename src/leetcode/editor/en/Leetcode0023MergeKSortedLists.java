//Merge k sorted linked lists and return it as one sorted list. Analyze and desc
//ribe its complexity. 
//
// Example: 
//
// 
//Input:
//[
//  1->4->5,
//  1->3->4,
//  2->6
//]
//Output: 1->1->2->3->4->4->5->6
// 
// Related Topics Linked List Divide and Conquer Heap 
// 👍 4957 👎 296

package leetcode.editor.en;

import leetcode.editor.ListNode;

import java.util.PriorityQueue;

// 2020-08-04 11:12:27
// Zeshi Yang
public class Leetcode0023MergeKSortedLists{
    // Java: merge-k-sorted-lists
    public static void main(String[] args) {
        Solution sol = new Leetcode0023MergeKSortedLists().new Solution();
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
    public ListNode mergeKLists(ListNode[] lists) {
        // corner case
        if (lists == null || lists.length == 0) {
            return null;
        }

        // general case
        ListNode head = null;
        ListNode cur = null;
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(lists.length, (o1, o2) -> o1.val - o2.val);
        for (int i = 0; i < lists.length; i++) {
            if(lists[i] != null) {
                minHeap.offer(lists[i]);
            }
        }
        while (!minHeap.isEmpty()) {
            ListNode temp = minHeap.poll();
            if(head == null) {
                head = temp;
                cur = temp;
            }
            else {
                cur.next = temp;
                cur = cur.next;
            }

            if (temp.next != null) {
                minHeap.offer(temp.next);
            }
        }
        return head;
    }
}

//leetcode submit region end(Prohibit modification and deletion)


}