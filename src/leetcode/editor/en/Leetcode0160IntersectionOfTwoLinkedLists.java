//Write a program to find the node at which the intersection of two singly linke
//d lists begins. 
//
// For example, the following two linked lists: 
//
//
// begin to intersect at node c1. 
//
// 
//
// Example 1: 
//
//
// 
//Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2
//, skipB = 3
//Output: Reference of the node with value = 8
//Input Explanation: The intersected node's value is 8 (note that this must not 
//be 0 if the two lists intersect). From the head of A, it reads as [4,1,8,4,5]. F
//rom the head of B, it reads as [5,6,1,8,4,5]. There are 2 nodes before the inter
//sected node in A; There are 3 nodes before the intersected node in B. 
//
// 
//
// Example 2: 
//
//
// 
//Input: intersectVal = 2, listA = [1,9,1,2,4], listB = [3,2,4], skipA = 3, skip
//B = 1
//Output: Reference of the node with value = 2
//Input Explanation: The intersected node's value is 2 (note that this must not 
//be 0 if the two lists intersect). From the head of A, it reads as [1,9,1,2,4]. F
//rom the head of B, it reads as [3,2,4]. There are 3 nodes before the intersected
// node in A; There are 1 node before the intersected node in B.
// 
//
// 
//
// Example 3: 
//
//
// 
//Input: intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
//Output: null
//Input Explanation: From the head of A, it reads as [2,6,4]. From the head of B
//, it reads as [1,5]. Since the two lists do not intersect, intersectVal must be 
//0, while skipA and skipB can be arbitrary values.
//Explanation: The two lists do not intersect, so return null.
// 
//
// 
//
// Notes: 
//
// 
// If the two linked lists have no intersection at all, return null. 
// The linked lists must retain their original structure after the function retu
//rns. 
// You may assume there are no cycles anywhere in the entire linked structure. 
// Each value on each linked list is in the range [1, 10^9]. 
// Your code should preferably run in O(n) time and use only O(1) memory. 
// 
// Related Topics Linked List 
// 👍 3767 👎 432

package leetcode.editor.en;

import java.util.HashSet;
import java.util.Set;
import leetcode.editor.ListGenerator;
import leetcode.editor.ListNode;

// 2020-08-04 11:40:18
// Zeshi Yang
public class Leetcode0160IntersectionOfTwoLinkedLists{
    // Java: intersection-of-two-linked-lists
    public static void main(String[] args) {
        Solution sol = new Leetcode0160IntersectionOfTwoLinkedLists().new Solution();
        // TO TEST
        String str1 = "2,6,4";
        String str2 = "1, 5";
        ListNode head1 = ListGenerator.generateSingleListOfListNode(str1);
        System.out.println(null != null);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        Set<ListNode> visited = new HashSet<>();
        ListNode p = headA;
        ListNode q = headB;
        while (p != null || q != null) {
            if (!visited.contains(p)) {
                if (p != null) {
                    visited.add(p);
                    p = p.next;
                }
            } else {
                return p;
            }
    
            if (!visited.contains(q)) {
                if (q != null) {
                    visited.add(q);
                    q = q.next;
                }
            } else {
                return q;
            }
        }
        return null;
        // // visited p and its ancestors
        // ListNode p = headA;
        // while (p != null) {
        //     visited.add(p);
        //     p = p.next;
        // }
        //
        // // visited q and its ancestors
        // ListNode q = headB;
        // while (q != null) {
        //     if (visited.contains(q)) {
        //         return q;
        //     }
        //     visited.add(q);
        //     q = q.next;
        // }
        // return q;
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)
//Soluion 1: T(n) = O(n), S(n) = O(1)
/*
 * 先计算A和B的长度，让长的那个减到和短的那个一样的长度，
 * 然后同时往前走，重合的时候就是intersection
 * 如果没有重合点，就return null（其实也是两者都走到null的地方，也是重合的）
 */
public class Solution1 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        
        int count1 = getListCount(headA);
        int count2 = getListCount(headB);
        
        return getIntersectionNode(headA, count1, headB, count2);
    }
    
    private int getListCount(ListNode head) {
        ListNode p = head;
        int count = 0;
        while (p != null) {
            p = p.next;
            count++;
        }
        return count;
    }
    
    private ListNode getIntersectionNode(ListNode p1, int count1, ListNode p2, int count2) {
        if (count2 > count1) {
            return getIntersectionNode(p2, count2, p1, count1);
        }
        while (count1 > count2) {
            p1 = p1.next;
            count1--;
        }
        // null == null is always true, null != null is alwasy false
        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }
}

//Solution 2: T(n) = O(n), S(n) = O(1)
/*走完A，C，B和走完B，C，A的长度是一样的*/
public class Solution2 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //Solution 1: 走完A，C，B和走完B，C，A的长度是一样的
        if (headA == null || headB == null) {
            return null;
        }
        ListNode p1 = headA;
        ListNode p2 = headB;
        while (p1 != p2) {
            p1 = (p1 != null ? p1.next: headB);
            p2 = (p2 != null ? p2.next: headA);
        }
        return p1;
    }
}

// Solution 3: T(n) = O(n), S(n) = O(n)
/**
 * 用HashSet去重
 */
public class Solution3 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        Set<ListNode> visited = new HashSet<>();
        
        // visited p and its ancestors
        ListNode p = headA;
        while (p != null) {
            visited.add(p);
            p = p.next;
        }
        
        // visited q and its ancestors
        ListNode q = headB;
        while (q != null) {
            if (visited.contains(q)) {
                return q;
            }
            visited.add(q);
            q = q.next;
        }
        return q;
    }
    
}

}