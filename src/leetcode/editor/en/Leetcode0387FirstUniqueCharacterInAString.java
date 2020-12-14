//Given a string, find the first non-repeating character in it and return its in
//dex. If it doesn't exist, return -1. 
//
// Examples: 
//
// 
//s = "leetcode"
//return 0.
//
//s = "loveleetcode"
//return 2.
// 
//
// 
//
// Note: You may assume the string contains only lowercase English letters. 
// Related Topics Hash Table String 
// 👍 2170 👎 120

package leetcode.editor.en;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

// 2020-09-15 16:54:43
// Zeshi Yang
public class Leetcode0387FirstUniqueCharacterInAString{
    // Java: first-unique-character-in-a-string
    public static void main(String[] args) {
        Solution sol = new Leetcode0387FirstUniqueCharacterInAString().new Solution();
        // TO TEST
        String s = "loveleetcode";
        int res = sol.firstUniqChar(s);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public int firstUniqChar(String s) {
        Queue<Integer> queue = new LinkedList<>();
        // null: have not appeared, false: appeared once; true -- appeared more than once
        Boolean[] visited = new Boolean[26];
        int len = s.length();
        for (int i = 0; i < len; i++) {
            int idx = s.charAt(i) - 'a';
            if (visited[idx] == null) {
                queue.offer(i);
                visited[idx]= false;
            } else {
                visited[idx] = true;
                while(!queue.isEmpty() && visited[s.charAt(queue.peek()) - 'a']) {
                    queue.poll();
                }
            }
        }
        return queue.isEmpty() ? -1 : queue.peek();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

// Solution 1: two passes, HashMap
class Solution1 {
    public int firstUniqChar(String s) {
        Map<Character, Integer> charToIndex = new HashMap<>();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            if (!charToIndex.containsKey(ch)) {
                charToIndex.put(ch, i);
            } else if (charToIndex.get(ch) != null) {
                charToIndex.put(ch, null);
            }// else charToIndex.get(ch) == null，说明这个ch是重复的，什么都不做
        }
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            if (charToIndex.containsKey(ch) && charToIndex.get(ch) != null) {
                return i;
            }
        }
        return -1;
    }
}

// Solution 2: one pass, HashMap + DoubleLinkedList, can be used to deal with streaming flow
class Solution2 {
    
    Map<Character, Node> charToIndex;
    DoubleList list;
    
    public int firstUniqChar(String s) {
        if (s.length() == 0) {
            return -1;
        }
        
        charToIndex = new HashMap<>();
        list = new DoubleList();
        
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (!charToIndex.containsKey(ch)) {
                Node cur = new Node(i);
                // charToIndex.put(ch, new Node(i));
                // list.addTail(new Node(i));
            /*
            上面注释的写法是有问题的，HashMap存的Object是地址，你这两个Node虽然值一样，但是地址不一样，
            后面的删除肯定会出问题的，因为在hashMap.get得到的这个Node没有prev和next
             */
                charToIndex.put(ch, cur);
                list.addTail(cur);
                
            } else if (charToIndex.get(ch).val != null) {
                list.remove(charToIndex.get(ch));
                charToIndex.put(ch, new Node(null)); // put value to null表示这个值是重复的
            }
        }
        return list.head.next.val;
    }
    
    private class Node {
        
        Integer val;
        Node prev;
        Node next;
        
        public Node() {
        }
        
        
        public Node(Integer val) {
            this.val = val;
        }
        
        public Node(Integer val, Node next, Node prev) {
            this.val = val;
            this.next = next;
            this.prev = prev;
        }
        
    }
    
    class DoubleList {
        
        Node head;
        Node tail;
        int size;
        
        public DoubleList() {
            head = new Node(-1);
            tail = new Node(-1);
            head.next = tail;
            tail.prev = head;
        }
        
        private void addTail(Node node) {
            if (tail == null) { // 你上面就没有用到add(null)的地方
                return;
            }
            node.next = tail;
            node.prev = tail.prev;
            node.prev.next = node;
            tail.prev = node;
            size++;
        }
        
        private void remove(Node node) {
            if (node == null) {
                return;
            }
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.next = null;
            node.prev = null;
            size--;
        }
        
        public int size() {
            return this.size;
        }
    }
    
}

// Solution 3: one pass, HashMap + Queue
class Solution3 {
    public int firstUniqChar(String s) {
        Queue<Integer> queue = new LinkedList<>();
        // null: have not appeared, false: appeared once; true -- appeared more than once
        Boolean[] visited = new Boolean[26];
        int len = s.length();
        for (int i = 0; i < len; i++) {
            int idx = s.charAt(i) - 'a';
            if (visited[idx] == null) {
                queue.offer(i);
                visited[idx]= false;
            } else {
                visited[idx] = true;
                while(!queue.isEmpty() && visited[s.charAt(queue.peek()) - 'a']) {
                    queue.poll();
                }
            }
        }
        return queue.isEmpty() ? -1 : queue.peek();
    }
}

}