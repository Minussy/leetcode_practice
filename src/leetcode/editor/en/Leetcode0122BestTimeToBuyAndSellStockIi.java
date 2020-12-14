//Say you have an array prices for which the ith element is the price of a given
// stock on day i. 
//
// Design an algorithm to find the maximum profit. You may complete as many tran
//sactions as you like (i.e., buy one and sell one share of the stock multiple tim
//es). 
//
// Note: You may not engage in multiple transactions at the same time (i.e., you
// must sell the stock before you buy again). 
//
// Example 1: 
//
// 
//Input: [7,1,5,3,6,4]
//Output: 7
//Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 
//5-1 = 4.
//             Then buy on day 4 (price = 3) and sell on day 5 (price = 6), prof
//it = 6-3 = 3.
// 
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
//
// 
// Constraints: 
//
// 
// 1 <= prices.length <= 3 * 10 ^ 4 
// 0 <= prices[i] <= 10 ^ 4 
// 
// Related Topics Array Greedy 
// 👍 2403 👎 1751

package leetcode.editor.en;

// 2020-07-03 16:05:09
public class Leetcode0122BestTimeToBuyAndSellStockIi{
    // Java: best-time-to-buy-and-sell-stock-ii
    public static void main(String[] args) {
        Solution sol = new Leetcode0122BestTimeToBuyAndSellStockIi().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
// Solution 2: greedy
class Solution {
    public int maxProfit(int[] prices) {
        // corner case
        if (prices == null || prices.length == 0) {
            return 0;
        }

        // general case
        int maxprofit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1])
                maxprofit += prices[i] - prices[i - 1];
        }
        return maxprofit;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: dynamic programming
class Solution1 {
    public int maxProfit(int[] prices) {
        // corner case
        if (prices == null || prices.length == 0) {
            return 0;
        }
        
        // general case
        int buy = - prices[0];
        int sell = 0;
        int maxprofit = 0;
        for (int price: prices) {
            int nextSell = Math.max(sell, buy + price);
            int nextBuy = Math.max(buy, sell - price);
            sell = nextSell;
            buy = nextBuy;
        }
        return sell;
    }
}
// Solution 2: greedy
class Solution2 {
    public int maxProfit(int[] prices) {
        // corner case
        if (prices == null || prices.length == 0) {
            return 0;
        }
    
        // general case
        int maxprofit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1])
                maxprofit += prices[i] - prices[i - 1];
        }
        return maxprofit;
    }
}
}