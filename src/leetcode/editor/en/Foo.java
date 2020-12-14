package leetcode.editor.en;

/*
 Program: leetcode_practice
 ClassName: temp
 Description:
 Author: Zeshi(Jesse) Yang
 Date: 2020-08-06 15:25
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Foo {
	
	// time = O(nlogn), space = O(n)
	public int minMeetingRooms(int[][] intervals) {
		// corner case
		if (intervals == null || intervals.length == 0 || intervals[0] == null
				|| intervals[0].length == 0) {
			return 0;
		}
		
		List<EndPoint> endpoints = new ArrayList<>();
		for (int[] interval : intervals) { // --> O(n)
			endpoints.add(new EndPoint(interval[0], true));
			endpoints.add(new EndPoint(interval[1], false));
		}
		Collections.sort(endpoints); // --> O(nlogn)
		
		int curRooms = 0;
		int maxRooms = 0;
		for (EndPoint p : endpoints) { // --> O(n)
            if (p.isStart) {
                curRooms++;
            } else {
                curRooms--;
            }
			maxRooms = Math.max(maxRooms, curRooms);
		}
		return maxRooms;
	}
	
	class EndPoint implements Comparable<EndPoint> {
		
		public int val;
		public boolean isStart;
		
		public EndPoint(int val, boolean isStart) {
			this.val = val;
			this.isStart = isStart;
		}
		
		@Override
		public int compareTo(EndPoint that) {
			if (this.val != that.val) {
				return this.val - that.val;
			} else {
				return this.isStart ? 1 : -1;// 右端点在前，左端点在后
			}
		}
	}
}