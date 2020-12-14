//Given a string, sort it in decreasing order based on the frequency of characte
//rs. 
//
// Example 1:
// 
//Input:
//"tree"
//
//Output:
//"eert"
//
//Explanation:
//'e' appears twice while 'r' and 't' both appear once.
//So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid a
//nswer.
// 
// 
//
// Example 2:
// 
//Input:
//"cccaaa"
//
//Output:
//"cccaaa"
//
//Explanation:
//Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
//Note that "cacaca" is incorrect, as the same characters must be together.
// 
// 
//
// Example 3:
// 
//Input:
//"Aabb"
//
//Output:
//"bbAa"
//
//Explanation:
//"bbaA" is also a valid answer, but "Aabb" is incorrect.
//Note that 'A' and 'a' are treated as two different characters.
// 
// Related Topics Hash Table Heap 
// 👍 1825 👎 133

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 2020-09-25 17:11:17
// Zeshi Yang
public class Leetcode0451SortCharactersByFrequency {
	
	// Java: sort-characters-by-frequency
	public static void main(String[] args) {
		Solution sol = new Leetcode0451SortCharactersByFrequency().new Solution();
		// TO TEST
		
		System.out.println();
	}
	
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public String frequencySort(String s) {
        if (s.length() <= 1) {
            return s;
        }
        char[] charS = s.toCharArray();
        Map<Character, Integer> charFreq = new HashMap<>();
        for (char ch : charS) {
            charFreq.put(ch, charFreq.getOrDefault(ch, 0) + 1);
        }
        List<Character> listS = new ArrayList<>();
        // for (Map.Entry<Character, Integer> entry : charFreq.entrySet()) {
        //     listS.add(entry.getKey());
        // }
        charFreq.forEach((k, v) ->listS.add(k)); // 上面的lambda表达式
        listS.sort(new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                if (!charFreq.get(o1).equals(charFreq.get(o2))) {
                    return charFreq.get(o2) - charFreq.get(o1);
                } else {
                    return Character.compare(o1, o2);
                }
            }
        });
        StringBuilder sb = new StringBuilder();
        for (Character ch : listS) {
            int count = 0;
            int freq = charFreq.get(ch);
            while (count++ < freq) {
                sb.append(ch);
            }
        }
        return sb.toString();
    }
    
}
//leetcode submit region end(Prohibit modification and deletion)

}