//Given a collection of distinct integers, return all possible permutations. 
//
// Example: 
//
// 
//Input: [1,2,3]
//Output:
//[
//  [1,2,3],
//  [1,3,2],
//  [2,1,3],
//  [2,3,1],
//  [3,1,2],
//  [3,2,1]
//]
// 
// Related Topics Backtracking 
// 👍 3984 👎 105

package leetcode.editor.en;

import java.util.*;
// 2020-07-26 12:19:13
// Zeshi Yang
public class Leetcode0046Permutations{
    // Java: permutations
    public static void main(String[] args) {
        Solution sol = new Leetcode0046Permutations().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
// Solution 2: using HashSet to avoid duplicate
class Solution {
    public List<List<Integer>> permute(int[] nums) {

        // S2: use HashSet to using HashSet to avoid duplicate
        List<List<Integer>> result = new ArrayList<>();

        //corner case
        if (nums == null || nums.length == 0) {
            return result;
        }

        List<Integer> list = new ArrayList<>();
        HashSet<Integer> set = new HashSet<>();
        int index = 0;

        getResult(index, nums, set, list, result);
        return result;

    }

    private void getResult(int index, int[] nums, HashSet<Integer> set,
                           List<Integer> list, List<List<Integer>> result) {
        // base case
        if (list.size() == nums.length) { // 或者用index == nums.length
            result.add(new ArrayList(list));
            return;
        }

        for (int i = index; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                continue;
            }
            list.add(nums[i]);
            set.add(nums[i]);
            getResult(index, nums, set, list, result);
            list.remove(list.size() - 1);
            set.remove(nums[i]);
        }
        return;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1.1: using swap to avoid duplicate 第1类搜索树
class Solution1_1 {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // Zeshi Yang's code 按照老师上课方法写的
        // S1.1: using swap to avoid
        // using Arrays.stream(nums).boxed().collect(Collectors.toList()) to
        // change array into ArrayList,这个步骤很麻烦，不推荐

        //corner case
        if (nums == null || nums.length == 0) {
            return result;
        }

        List<Integer> list = new ArrayList<>();
        int index = 0;

        getResult(index, nums, result);
        return result;

    }

    private void getResult(int index, int[] nums, List<List<Integer>> result) {
        // base case
        if (index == nums.length - 1) {
            List<Integer> temp = new ArrayList<>();
            for (int j = 0; j < nums.length; j++){
                temp.add(nums[j]);
            }
            result.add(temp);
            return;
        }

        for (int i = index; i < nums.length; i++) {
            swap(nums, index, i);
            getResult(index + 1, nums, result);
            swap(nums, index, i);
        }
        return;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
// Solution 1.2: using swap to avoid duplicate 第2类搜索树
class Solution1_2 {
    public List<List<Integer>> permute(int[] nums) {
        // Zeshi Yang's code 按照老师上课方法写的
        // S1.2: using swap to avoid duplicate
        // using List temp to add element
        List<List<Integer>> result = new ArrayList<>();

        //corner case
        if (nums == null || nums.length == 0) {
            return result;
        }

        List<Integer> list = new ArrayList<>();
        int index = 0;

        getResult(index, nums, list, result);
        return result;
    }

    private void getResult(int index, int[] nums, List<Integer> list,
            List<List<Integer>> result) {
        // base case
        if (list.size() == nums.length) { // 或者用index == nums.length
            result.add(new ArrayList(list));
            return;
        }

        for (int i = index; i < nums.length; i++) {
            swap(nums, i, index);
            // 注意，索引是index，不是i。第index层，加第index个元素
            list.add(nums[index]);
            getResult(index + 1, nums, list, result);
            list.remove(list.size() - 1);
            swap(nums, i, index);
        }
        return;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
// Solution 2: using HashSet to avoid duplicate
class Solution2 {
    public List<List<Integer>> permute(int[] nums) {

        // S2: use HashSet to using HashSet to avoid duplicate
        List<List<Integer>> result = new ArrayList<>();

        //corner case
        if (nums == null || nums.length == 0) {
            return result;
        }

        List<Integer> list = new ArrayList<>();
        HashSet<Integer> set = new HashSet<>();
        int index = 0;

        getResult(index, nums, set, list, result);
        return result;

    }

    private void getResult(int index, int[] nums, HashSet<Integer> set,
            List<Integer> list, List<List<Integer>> result) {
        // base case
        if (list.size() == nums.length) { // 或者用index == nums.length
            result.add(new ArrayList(list));
            return;
        }

        for (int i = index; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                continue;
            }
            list.add(nums[i]);
            set.add(nums[i]);
            getResult(index, nums, set, list, result);
            list.remove(list.size() - 1);
            set.remove(nums[i]);
        }
        return;
    }
}
}