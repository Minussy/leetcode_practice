//Given an array of meeting time intervals consisting of start and end times [[s
//1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms requir
//ed. 
//
// Example 1: 
//
// 
//Input: [[0, 30],[5, 10],[15, 20]]
//Output: 2 
//
// Example 2: 
//
// 
//Input: [[7,10],[2,4]]
//Output: 1 
//
// NOTE: input types have been changed on April 15, 2019. Please reset to defaul
//t code definition to get new method signature. 
// Related Topics Heap Greedy Sort 
// 👍 2631 👎 41

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

// 2020-07-27 17:43:01
// Zeshi Yang
public class Leetcode0253MeetingRoomsIi {

	// Java: meeting-rooms-ii
	public static void main(String[] args) {
		Solution sol = new Leetcode0253MeetingRoomsIi().new Solution();
		// TO TEST
        int[][] intervals = {{13, 15}, {1, 13}};
        int res = sol.minMeetingRooms(intervals);
		System.out.println(res);
	}

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public int minMeetingRooms(int[][] intervals) {
        // corner case
        if (intervals == null || intervals.length == 0 ||
                intervals[0] == null || intervals[0].length == 0) {
            return 0;
        }
        // general case
        List<Point> list = new ArrayList<>();
        for (int[] interval : intervals) {
            list.add(new Point(interval[0], true));
            list.add(new Point(interval[1], false));
        }
        Collections.sort(list);
        int count = 0;
        int max = 0;
        for (Point point : list) {
            if (point.isStart) {
                count++;
            } else {
                count--;
            }
            max = Math.max(max, count);
        }
        return max;
    }
    
    class Point implements Comparable<Point> {
        
        int val;
        boolean isStart;
        
        public Point(int val, boolean isStart) {
            this.val = val;
            this.isStart = isStart;
        }
        
        @Override
        public int compareTo(Point o) {
            if (this.val != o.val) {
                return this.val - o.val;
            } else {
                return this.isStart ? 1 : -1;
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: interval打散成point进行排序 T(n) = O(nlog(n)), S(n) = O(n)
// 7 ms,击败了38.84% 的Java用户, 40.3 MB,击败了8.55% 的Java用户
/**
 * 把interval打散成point，按照point的时间升序排序，如果时间相同的话，right优先
 * 然后从前往后遍历，遇到start需要多加一个房间，遇到end，减小一个房间。 global max就是最小房间数
 */
class Solution1 {
    
    public int minMeetingRooms(int[][] intervals) {
        // corner case
        if (intervals == null || intervals.length == 0 ||
                intervals[0] == null || intervals[0].length == 0) {
            return 0;
        }
        // general case
        List<Point> list = new ArrayList<>();
        for (int[] interval : intervals) {
            list.add(new Point(interval[0], true));
            list.add(new Point(interval[1], false));
        }
        Collections.sort(list);
        int count = 0;
        int max = 0;
        for (Point point : list) {
            if (point.isStart) {
                count++;
            } else {
                count--;
            }
            max = Math.max(max, count);
        }
        return max;
    }
    
    class Point implements Comparable<Point> {
        
        int val;
        boolean isStart;
        
        public Point(int val, boolean isStart) {
            this.val = val;
            this.isStart = isStart;
        }
        
        @Override
        public int compareTo(Point o) {
            if (this.val != o.val) {
                return this.val - o.val;
            } else {
                return this.isStart ? 1 : -1;
            }
        }
    }
}

// Solution 2: 把interval按照start time的升序排序, T(n) = O(nlog(n)), S(n) = O(n)
// Solution 2_1 消耗资源： 6 ms,击败了74.49% 的Java用户，39 MB,击败了41.59% 的Java用户
/**
 * 把interval按照start time的升序排序遍历，
 * 每次遇到新的interval开始的时候，检测当前interval之前最早结束interval分配的房间有没有被空出来
 *      如果空出来一个的话，就放进去
 *      没有空出来的话，就加个房间。
 *  房间数只会增加，不会减小，所以只要return最后的房间数就行了。
 */

class Solution2_1 {
    
    public int minMeetingRooms(int[][] intervals) {
        
        // Check for the base case. If there are no intervals, return 0
        if (intervals.length == 0) {
            return 0;
        }
        
        // Min heap
        PriorityQueue<Integer> allocator = new PriorityQueue<>(intervals.length); // min heap
        
        // Sort the intervals by start time
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0])); // (a, b) -> a[0] - b[0]
        // Iterate over remaining intervals
        for (int[] interval : intervals) {
            // If the room due to free up the earliest is free, assign that room to this meeting.
            if (!allocator.isEmpty() && interval[0] >= allocator.peek()) {
                allocator.poll();
            }
            // If a new room is to be assigned, then also we add to the heap,
            // If an old room is allocated, then also we have to add to the heap with updated end
            // time.
            allocator.add(interval[1]);
        }
        
        // The size of the heap tells us the minimum rooms required for all the meetings.
        return allocator.size();
    }
}


// Solution 2_2 消耗资源： 7 ms,击败了38.84% 的Java用户, 38.9 MB,击败了50.87% 的Java用户
/**
 * 把interval按照start time的升序排序遍历，
 * 每次遇到新的interval开始的时候，检测当前interval之前最早结束interval分配的房间的会议是不是已经结束了
 *      如果会议已经结束的话，空出这个房间，再继续检测下一个房间的会议是不是结束了,一直往后腾空房间
 *      没有空出来的话，就加个房间。
 *  房间数只会增加，不会减小，所以只要return最后的房间数就行了。
 */
class Solution2_2 {
    
    public int minMeetingRooms(int[][] intervals) {
        
        // Check for the base case. If there are no intervals, return 0
        if (intervals.length == 0) {
            return 0;
        }
        
        // Min heap
        PriorityQueue<Integer> allocator = new PriorityQueue<>(intervals.length); // min heap
        
        // Sort the intervals by start time
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0])); // (a, b) -> a[0] - b[0]
        // Iterate over remaining intervals
        int max = 0;
        for (int[] interval : intervals) {
            
            // If the room due to free up the earliest is free, assign that room to this meeting.
            while (!allocator.isEmpty() && interval[0] >= allocator.peek()) {
                allocator.poll();
            }
            // If a new room is to be assigned, then also we add to the heap,
            // If an old room is allocated, then also we have to add to the heap with updated end
            // time.
            allocator.add(interval[1]);
            max = Math.max(max, allocator.size());
        }
        
        // The size of the heap tells us the minimum rooms required for all the meetings.
        return max;
    }
}

/* follow up这次不是要找出能hold住所有meeting的最小房间个数
  而是要给出一种解决方案，每个房间举行哪些interval，而且使得房间总数最小。（给出一种解决方案就行了）
*/

// Solution 1: 打散点之后排序的方法，T(n) = O(nlog(n)), S(n) = O(n)
// 14 ms,击败了6.25% 的Java用户, 41.1 MB,击败了7.63% 的Java用户
/**
 * 设置一个List<Room>
 * 打散点之后排序的方法，
 * 每次遇到一个新的interval，就检测List里面有没有可用的room
 *      如果有可用的room，就那一个出来，放当前的interval
 *      如果没有可用的room，就创建一个room出来，放当前的interval
 * 每次结束一个interval，就把这个interval对应的room放到List里面，表示这个room里面的会议结束了，可以用了
 *
 */
class FollowupSolution1 {
    
    public List<String> minMeetingRoomsAndItsIntervals(int[][] intervals) {
        List<String> res = new ArrayList<>();
        if (intervals == null || intervals.length == 0 || intervals[0] == null
                || intervals[0].length == 0) {
            return res;
        }
        
        List<Point> endpoints = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            int[] interval = intervals[i]; // --> O(n)
            endpoints.add(new Point(i + 1, interval[0], true, interval));
            endpoints.add(new Point(i + 1, interval[1], false, interval));
        }
        Collections.sort(endpoints); // --> O(nlogn)
        
        int curRooms = 0;
        int maxRooms = 0;
        
        int roomId = 1;
        LinkedList<Room> availableRooms = new LinkedList<>();
        Map<Integer, Room> intervalToRoom = new HashMap<>();
        for (Point p : endpoints) { // --> O(n)
            if (p.isStart) { // 要开始一个interval
                curRooms++;
                Room chosenRoom;
                if (availableRooms.size() != 0) { // 消耗一个空的Room
                    chosenRoom = availableRooms.poll();
                } else { // 增加一个room
                    chosenRoom = new Room(roomId++);
                }
                int[] interval = p.interval;// 以这个点为起点的第一个interval
                chosenRoom.addInterval(interval);
                intervalToRoom.put(p.id, chosenRoom);
            } else {
                curRooms--;
                Room chosenRoom = intervalToRoom.get(p.id);
                availableRooms.offer(chosenRoom);
            }
            maxRooms = Math.max(maxRooms, curRooms);
        }
        Collections.sort(availableRooms);
        for (Room room : availableRooms) {
            String roomAndIntervals = room.toString();
            res.add(roomAndIntervals);
        }
        return res;
    }
    
    class Point implements Comparable<Point> {
        
        final int id;
        final int val;
        final boolean isStart;
        final int[] interval;
        
        public Point(int id, int val, boolean isStart, int[] interval) {
            this.id = id;
            this.val = val;
            this.isStart = isStart;
            this.interval = interval;
        }
        
        @Override
        public int compareTo(Point that) {
            if (this.val != that.val) {
                return this.val - that.val;
            } else {
                return this.isStart ? 1 : -1;// 右端点在前，左端点在后
            }
        }
    }
    
    class Room implements Comparable<Room> {
        
        final int id;
        
        private final List<int[]> holdIntervals;
        
        public Room(int id) {
            this.id = id;
            holdIntervals = new ArrayList<>();
        }
        
        public void addInterval(int[] interval) {
            holdIntervals.add(interval);
        }
        
        @Override
        /**
         * return id of room and its all hold intervals
         */
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(id).append(": ");
            for (int[] interval : holdIntervals) {
                sb.append(Arrays.toString(interval)).append(", ");
            }
            sb.setLength(sb.length() - 2);
            return sb.toString();
        }
        
        @Override
        public int compareTo(Room that) {
            return Integer.compare(this.id, that.id);
        }
    }
}

// Solution 2 :把interval按照start time的升序排序, T(n) = O(nlog(n)), S(n) = O(n)
// 11 ms,击败了10.86% 的Java用户, 39.9 MB,击败了13.87% 的Java用户
/**
 * 把interval按照start time的升序排序遍历，
 * 每次遇到新的interval开始的时候，检测当前interval之前最早结束interval分配的房间的会议是不是已经结束了
 *      如果会议已经结束的话，空出这个房间，再继续检测下一个房间的会议是不是结束了
 *      没有空出来的话，就加个房间。
 *  房间数只会增加，不会减小，所以只要return最后的房间数就行了。
 */
class FollowupSolution2 {
    
    public List<String> minMeetingRoomsAndItsIntervals(int[][] intervals) {
        List<String> res = new ArrayList<>();
        if (intervals == null || intervals.length == 0 || intervals[0] == null
                || intervals[0].length == 0) {
            return res;
        }
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0])); // (a, b) -> a[0] - b[0]
        Comparator<Room> comparator = new Comparator<Room>() {
            @Override
            public int compare(Room o1, Room o2) {
                return Integer.compare(o1.getLastIntervalEndTime(), o2.getLastIntervalEndTime());
            }
        };
        PriorityQueue<Room> minHeap = new PriorityQueue<>(comparator);
        
        int roomId = 1;
        for (int[] interval : intervals) {
            Room room;
            if (!minHeap.isEmpty() && interval[0] >= minHeap.peek().getLastIntervalEndTime()) {
                room = minHeap.poll();
            } else {
                room = new Room(roomId++);
            }
            room.addInterval(interval);
            minHeap.offer(room);
        }
        List<Room> rooms = new ArrayList<>(minHeap);
        Collections.sort(rooms);
        for (Room room : rooms) {
            String roomAndIntervals = room.toString();
            res.add(roomAndIntervals);
        }
        return res;
    }
    
    class Room implements Comparable<Room> {
        
        final int id;
        
        private final List<int[]> holdIntervals;
        
        public Room(int id) {
            this.id = id;
            holdIntervals = new ArrayList<>();
        }
        
        public void addInterval(int[] interval) {
            holdIntervals.add(interval);
        }
        
        @Override
        /*
          return id of room and its all hold intervals
         */
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(id).append(": ");
            for (int[] interval : holdIntervals) {
                sb.append(Arrays.toString(interval)).append(", ");
            }
            sb.setLength(sb.length() - 2);
            return sb.toString();
        }
        
        @Override
        public int compareTo(Room that) {
            return Integer.compare(this.id, that.id);
        }
        
        public int getLastIntervalEndTime() {
            return holdIntervals.get(holdIntervals.size() - 1)[1];
        }
    }
}
}