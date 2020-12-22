package leetcode.editor.en;

/*
 Program: leetcode_practice
 ClassName: temp
 Description:
 Author: Zeshi(Jesse) Yang
 Date: 2020-08-06 15:25
 */

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class Foo {
	
	public static void main(String[] args) {
		LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
		map.put(1, 1);
		map.put(2, 2);
		map.put(3, 3);
		map.put(2, 1);
		map.forEach((k, v) -> System.out.println("key: " + k + " value:" + v));
		for(Map.Entry<Integer, Integer> entry: map.entrySet()) {
			System.out.println(entry);
		}
		LinkedHashSet<Integer> set = new LinkedHashSet<>();
		set.add(1);
		set.add(2);
		set.add(3);
		set.remove(2);
		set.add(2);
		set.forEach(k-> System.out.println("key: " + k));
	}
}