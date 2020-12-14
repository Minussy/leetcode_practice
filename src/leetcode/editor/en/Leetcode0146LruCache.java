//Design and implement a data structure for Least Recently Used (LRU) cache. It 
//should support the following operations: get and put. 
//
// get(key) - Get the value (will always be positive) of the key if the key exis
//ts in the cache, otherwise return -1. 
//put(key, value) - Set or insert the value if the key is not already present. W
//hen the cache reached its capacity, it should invalidate the least recently used
// item before inserting a new item. 
//
// The cache is initialized with a positive capacity. 
//
// Follow up: 
//Could you do both operations in O(1) time complexity? 
//
// Example: 
//
// 
//LRUCache cache = new LRUCache( 2 /* capacity */ );
//
//cache.put(1, 1);
//cache.put(2, 2);
//cache.get(1);       // returns 1
//cache.put(3, 3);    // evicts key 2
//cache.get(2);       // returns -1 (not found)
//cache.put(4, 4);    // evicts key 1
//cache.get(1);       // returns -1 (not found)
//cache.get(3);       // returns 3
//cache.get(4);       // returns 4
// 
//
// 
// Related Topics Design 
// 👍 6498 👎 275

package leetcode.editor.en;

import java.util.HashMap;
import java.util.Map;
// 2020-09-09 17:16:07
// Zeshi Yang
public class Leetcode0146LruCache {
    // Java: lru-cache
    public static void main(String[] args) {
        int capacity = 10;
        LRUCache cache = new Leetcode0146LruCache().new LRUCache(capacity);
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class LRUCache {

    private int capacity;
    private int curSize;
    private Map<Integer, Node> map;
    private Node head;
    private Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>(capacity);
        curSize = 0;
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Node node = map.get(key);
        moveToHead(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        Node node;
        if (!map.containsKey(key)) {
            node = new Node(key, value);
            map.put(key, node);
            if (curSize < capacity) {
                curSize++;
            } else { // remove last one to get the place for new node
                Node lastNode = tail.prev;
                lastNode.prev.next = tail;
                tail.prev = lastNode.prev;
                lastNode.next = null;
                lastNode.prev = null;
                map.remove(lastNode.key);
            }
        } else {
            node = map.get(key);
            node.val = value;
        }
        moveToHead(node);
    }

    private void moveToHead(Node node) {
        // remove node
        Node prev = node.prev;
        Node next = node.next;
        if (prev != null) {
            prev.next = next;
        }
        if (prev != null) {
            next.prev = prev;
        }
        // move to first place
        node.next = head.next;
        head.next = node;
        node.prev = head;
        node.next.prev = node;
    }

}

private static class Node {

    public int key;
    public int val;
    public Node prev;
    public Node next;

    public Node(int key, int val) {
        this.key = key;
        this.val = val;
    }
}
// Your LRUCache object will be instantiated and called as such:
// LRUCache obj = new LRUCache(capacity);
// int param_1 = obj.get(key);
// obj.put(key,value);
//leetcode submit region end(Prohibit modification and deletion)

}