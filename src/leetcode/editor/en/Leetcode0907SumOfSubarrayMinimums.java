//Given an array of integers A, find the sum of min(B), where B ranges over ever
//y (contiguous) subarray of A. 
//
// Since the answer may be large, return the answer modulo 10^9 + 7. 
//
// 
//
// Example 1: 
//
// 
//Input: [3,1,2,4]
//Output: 17
//Explanation: Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [
//1,2,4], [3,1,2,4]. 
//Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.  Sum is 17. 
//
// 
//
// Note: 
//
// 
// 1 <= A.length <= 30000 
// 1 <= A[i] <= 30000 
// 
//
// 
// 
// 
//
// 
// Example 1: 
//
// 
//Input: arr = [3,1,2,4]
//Output: 17
//Explanation: 
//Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,
//2,4]. 
//Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
//Sum is 17.
// 
//
// Example 2: 
//
// 
//Input: arr = [11,81,94,43,3]
//Output: 444
// 
//
// 
// Constraints: 
//
// 
// 1 <= arr.length <= 3 * 104 
// 1 <= arr[i] <= 3 * 104 
// 
// Related Topics Array Stack 
// 👍 1700 👎 111

package leetcode.editor.en;

import java.util.*;
// 2021-01-13 17:47:44
// Zeshi Yang
public class Leetcode0907SumOfSubarrayMinimums{
    // Java: sum-of-subarray-minimums
    public static void main(String[] args) {
        Solution sol = new Leetcode0907SumOfSubarrayMinimums().new Solution();
        // TO TEST
        int[] arr = {3, 1, 2, 4};
        int res = sol.sumSubarrayMins(arr);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
// T(n) = O(n), S(n) = O(n)
// 23 ms,击败了77.55% 的Java用户, 46.9 MB,击败了42.77% 的Java用户
/*
when comes in a new number arr[i]
pop all the numbers >= arr[i],
after all the pop, and push the arr[i] to the stack
now, the nums in stack is min value of the sub array whose start = 0 to i, end is i;
and using another stackWeight to maintain the weight of num in stack
stackWeight[i] is the weight of the stackNum[i]
    when calculating the min value in the all subarray ends in the i

update the weightedSum to record the weighted sum of the stackNum and stackWeight instead of
calculating from scratch, which means the sum of the min number in all sub arrays ends in the i

res += weightedSum;

 */
class Solution {
    public int sumSubarrayMins(int[] arr) {
        int MOD = 1_000_000_007;
        Stack<Integer> stackNum = new Stack<>(); // keep a increasing stack from bottom
        Stack<Integer> stackWeight = new Stack<>();
        int weightedSum = 0; // weighted sum of stackNum and stackWeight
        int res = 0;
        for (int num: arr) {
            int weight = 0;
            while (!stackNum.isEmpty() &&stackNum.peek() >= num) {
                int prevNum = stackNum.pop();
                int prevWeight = stackWeight.pop();
                weightedSum -=  prevWeight * prevNum;
                weight += prevWeight;
            }
            weight += 1;
            stackNum.push(num);
            stackWeight.push(weight);
            weightedSum += weight * num;
            res += weightedSum;
            res %= MOD;
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
