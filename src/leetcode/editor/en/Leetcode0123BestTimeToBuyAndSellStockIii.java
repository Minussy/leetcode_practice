//Say you have an array for which the ith element is the price of a given stock 
//on day i. 
//
// Design an algorithm to find the maximum profit. You may complete at most two 
//transactions. 
//
// Note: You may not engage in multiple transactions at the same time (i.e., you
// must sell the stock before you buy again). 
//
// Example 1: 
//
// 
//Input: [3,3,5,0,0,3,1,4]
//Output: 6
//Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 
//3-0 = 3.
//             Then buy on day 7 (price = 1) and sell on day 8 (price = 4), prof
//it = 4-1 = 3. 
//
// Example 2: 
//
// 
//Input: [1,2,3,4,5]
//Output: 4
//Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 
//5-1 = 4.
//             Note that you cannot buy on day 1, buy on day 2 and sell them lat
//er, as you are
//             engaging multiple transactions at the same time. You must sell be
//fore buying again.
// 
//
// Example 3: 
//
// 
//Input: [7,6,4,3,1]
//Output: 0
//Explanation: In this case, no transaction is done, i.e. max profit = 0. 
// Related Topics Array Dynamic Programming 
// 👍 2051 👎 73

package leetcode.editor.en;

// 2020-07-03 16:05:45
public class Leetcode0123BestTimeToBuyAndSellStockIii{
    // Java: best-time-to-buy-and-sell-stock-iii
    public static void main(String[] args) {
        Solution sol = new Leetcode0123BestTimeToBuyAndSellStockIii().new Solution();
        // TO TEST
        int[] prices = {3,3,5,0,0,3,1,4};
        int res = sol.maxProfit(prices);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
// Solution 2: dynamic programming, rolling(space complexity optimized)
class Solution {
    public int maxProfit(int[] prices) {
        // corner case
        if (prices == null || prices.length == 0) {
            return 0;
        }

        // general case
        int buy1 = -prices[0]; // 第一次买的最好收益（负值）
        int buy2 = -prices[0]; // 当前第一次卖 + 第二次买 的最好收益
        int sell1 = 0; // 当前第一次卖的最好收益
        int sell2 = 0; // 当前第一次和第二次卖的最好收益
        // 在第二次买之前，必须先完成第一次卖，而且不能是同一天，所以for循环里面，是先执行2的部分，在执行1的部分
        for(int price: prices) {
            buy2 = Math.max(buy2, sell1 - price);
            sell2 = Math.max(sell2, price + buy2);

            buy1 = Math.max(buy1, -price);
            sell1 = Math.max(sell1, price + buy1);
        }
        // 大部分情况是return sell2,但是corner case
        return sell2;
        // time complexity: O(n)
    }
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: dynamic programming
class Solution1_1 {
    public int maxProfit(int[] prices) {
        // corner case
        if (prices == null || prices.length == 0) {
            return 0;
        }
        
        // general case
        int len = prices.length;
        int[] buy1 = new int[len + 1]; // 第一次买的最好收益（负值）
        int[] buy2 = new int[len + 1]; // 当前第一次卖 + 第二次买 的最好收益
        buy1[0] = -prices[0];
        buy2[0] = -prices[0];
        int[] sell1 =  new int[len + 1]; // 当前第一次卖完的最好收益
        int[] sell2 =  new int[len + 1]; // 当前第一次和第二次卖的最好收益
        // 在第二次买之前，必须先完成第一次卖，而且不能是同一天，所以for循环里面，是先执行2的部分，在执行1的部分
        for(int i = 0; i < len; i++) {
            buy2[i + 1] = Math.max(buy2[i], sell1[i] - prices[i]);
            sell2[i + 1] = Math.max(sell2[i], prices[i] + buy2[i]);
            
            buy1[i + 1] = Math.max(buy1[i], -prices[i]);
            sell1[i + 1] = Math.max(sell1[i], prices[i] + buy1[i]);
        }
        // 大部分情况是return sell2,但是corner case
        /*System.out.println(Arrays.toString(buy1));
        System.out.println(Arrays.toString(sell1));
        System.out.println(Arrays.toString(buy2));
        System.out.println(Arrays.toString(sell2));*/
        return sell2[len];
        // time complexity: O(n)
    }
}
// Solution 2: dynamic programming, rolling(space complexity optimized)
class Solution1_2 {
    public int maxProfit(int[] prices) {
        // corner case
        if (prices == null || prices.length == 0) {
            return 0;
        }
        
        // general case
        int buy1 = -prices[0]; // 第一次买的最好收益（负值）
        int buy2 = -prices[0]; // 当前第一次卖 + 第二次买 的最好收益
        int sell1 = 0; // 当前第一次卖的最好收益
        int sell2 = 0; // 当前第一次和第二次卖的最好收益
        // 在第二次买之前，必须先完成第一次卖，而且不能是同一天，所以for循环里面，是先执行2的部分，在执行1的部分
        for(int price: prices) {
            buy2 = Math.max(buy2, sell1 - price);
            sell2 = Math.max(sell2, price + buy2);
            
            buy1 = Math.max(buy1, -price);
            sell1 = Math.max(sell1, price + buy1);
        }
        // 大部分情况是return sell2,但是corner case
        return sell2;
        // time complexity: O(n)
    }
}
}