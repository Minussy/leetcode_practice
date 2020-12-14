//Given a collection of integers that might contain duplicates, nums, return all
// possible subsets (the power set). 
//
// Note: The solution set must not contain duplicate subsets. 
//
// Example: 
//
// 
//Input: [1,2,2]
//Output:
//[
//  [2],
//  [1],
//  [1,2,2],
//  [2,2],
//  [1,2],
//  []
//]
// 
// Related Topics Array Backtracking 
// 👍 1778 👎 79

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
// 2020-08-27 23:12:10
// Zeshi Yang
public class Leetcode0090SubsetsIi{
    // Java: subsets-ii
    public static void main(String[] args) {
        Solution sol = new Leetcode0090SubsetsIi().new Solution();
        // TO TEST
//        ArrayList<Integer> prev = new ArrayList<>();
//        prev.add(1);
//        ArrayList<Integer> cur = null;
//        System.out.println(prev.equals(cur));
        int[] nums = {1,2,2};
        List<List<Integer>> res = sol.subsetsWithDup(nums);
        System.out.println(res);
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
//Solution 2: BFS 第2类搜索树
class Solution {

    //Zeshi Yang's code
    //                                      {}
    // level 0				    {a}                         {}
    // level 1          {a, b}          {a}            {b}	    {}
    // level 2  {a, b, c}   {a, b}  {a,c}   {a}  {b, c}  {b}  {c} {}
    // null
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        //corner case
        if (nums == null) {
            return new ArrayList<>();
        }
        Arrays.sort(nums); // important
        int len = nums.length;
        Queue<ArrayList<Integer>> queue = new LinkedList<>();
        ArrayList<Integer> cur = new ArrayList<>();

        int level = 0;

        queue.offer(new ArrayList(cur));
        // 或者用 (ArrayList) temp.clone()， temp.clone()是shallow copy,返回成Object，再强制转换成ArrayList
        while (level < len) {
            int size = queue.size();
            ArrayList<Integer> prev = null;
            while (size-- > 0) {
                cur = queue.poll();
                cur.add(nums[level]);
                if (!cur.equals(prev)) {
                    queue.offer(new ArrayList(cur));
                }
                cur.remove(cur.size() - 1);
                queue.offer(new ArrayList(cur));
                prev = cur;
            }
            level++;
        }
        return (List) queue;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

//Solution 1: BFS 第1类搜索树
class Solution1 {
    //Zeshi Yang's code
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        //corner case
        if (nums == null) {
            return res;
        }
        Arrays.sort(nums); // important
        int len = nums.length;
        Queue<ArrayList<Integer>> numberQueue = new LinkedList<>();
        Queue<ArrayList<Integer>> indexQueue = new LinkedList<>();
        ArrayList<Integer> cur = new ArrayList<>();
        int size;
        numberQueue.offer(new ArrayList<>(cur));
        indexQueue.offer(new ArrayList<Integer>() {{
                             add(-1);
                         }}
        );
        while (!numberQueue.isEmpty()) {
            size = numberQueue.size();
            // cache目前这一层里面有多少个组合
            while (size-- > 0) {
                ArrayList<Integer> indexList = indexQueue.poll();
                int index = indexList.get(indexList.size() - 1);
                cur = numberQueue.poll();
                res.add(new ArrayList(cur));
                // 找到poll出来的结果里面最后一个数字的index，下面的从index + 1的数字开始加
                int prev = 0;// = index < len - 1 ? nums[index + 1] - 1 : 0;
                for (int i = index + 1; i < len; i++) {
                    if (i == index + 1) {
                        prev = nums[index + 1];
                    } else {
                        if (nums[i] == prev) {
                            continue;
                        } else {
                            prev = nums[i];
                        }
                    }
                    indexList.add(i);
                    indexQueue.offer(new ArrayList(indexList));
                    indexQueue.remove(indexQueue.size() - 1);

                    cur.add(nums[i]);
                    numberQueue.offer(new ArrayList(cur));
                    cur.remove(cur.size() - 1);

                }
            }
        }
        return res;
    }
}

//Solution 2: BFS 第2类搜索树
class Solution2 {
    //Zeshi Yang's code
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        //corner case
        if (nums == null) {
            return new ArrayList<>();
        }
        Arrays.sort(nums); // important
        int len = nums.length;
        Queue<ArrayList<Integer>> queue = new LinkedList<>();
        ArrayList<Integer> cur = new ArrayList<>();

        int level = 0;

        queue.offer(new ArrayList(cur));
        // 或者用 (ArrayList) temp.clone()， temp.clone()是shallow copy,返回成Object，再强制转换成ArrayList
        while (level < len) {
            int size = queue.size();
            ArrayList<Integer> prev = null;
            while (size-- > 0) {
                cur = queue.poll();
                cur.add(nums[level]);
                if (!cur.equals(prev)) {
                    queue.offer(new ArrayList(cur));
                }
                cur.remove(cur.size() - 1);
                queue.offer(new ArrayList(cur));
                prev = cur;
            }
            level++;
        }
        return (List) queue;
    }
}

//Solution 3: DFS 第1类搜索树
class Solution3 {
    //Zeshi Yang's code
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        //corner case
        if (nums == null) {
            return result;
        }
        Arrays.sort(nums); // important
        result.add(new ArrayList<>());
        dfs(0, new ArrayList<>(), nums, result);
        return result;
    }

    private void dfs(int idx, List<Integer> list, int[] nums,
            List<List<Integer>> result) {
        if (idx == nums.length) {
            return;
        }
        Integer prev = idx;
        for (int i = idx; i < nums.length; i++) {
            if (i != idx && nums[i] == nums[prev]) {
                continue;
            }
            prev = i;
            list.add(nums[i]);
            result.add(new ArrayList<>(list));
            dfs(i + 1, list, nums, result);
            // wall
            list.remove(list.size() - 1);
        }
        return;
    }
}

//Solution 4: DFS 第2类搜索树
class Solution4 {
    //Zeshi Yang's code
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // corner case
        if (nums == null) {
            return result;
        }
        Arrays.sort(nums);
        dfs(0, nums, new ArrayList<>(), result);

        return result;
    }

    private void dfs(int idx, int[] nums, List<Integer> list,
            List<List<Integer>> result) {
        int len = nums.length;
        if (idx == len) {
            result.add(new ArrayList<>(list));
            return;
        }

        list.add(nums[idx]);
        dfs(idx + 1, nums, list, result);
        list.remove(list.size() - 1);

        // find next i that nums[i] != nums[idx];
        int i = idx + 1;
        while (i < len && nums[i] == nums[idx]) {
            i++;
        }
        dfs(i, nums, list, result);
    }
}
}