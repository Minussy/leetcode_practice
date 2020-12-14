//The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibon
//acci sequence, such that each number is the sum of the two preceding ones, start
//ing from 0 and 1. That is, 
//
// 
//F(0) = 0,   F(1) = 1
//F(N) = F(N - 1) + F(N - 2), for N > 1.
// 
//
// Given N, calculate F(N). 
//
// 
//
// Example 1: 
//
// 
//Input: 2
//Output: 1
//Explanation: F(2) = F(1) + F(0) = 1 + 0 = 1.
// 
//
// Example 2: 
//
// 
//Input: 3
//Output: 2
//Explanation: F(3) = F(2) + F(1) = 1 + 1 = 2.
// 
//
// Example 3: 
//
// 
//Input: 4
//Output: 3
//Explanation: F(4) = F(3) + F(2) = 2 + 1 = 3.
// 
//
// 
//
// Note: 
//
// 0 ≤ N ≤ 30. 
// Related Topics Array 
// 👍 658 👎 193

package leetcode.editor.en;

// 2020-07-26 12:37:26
// Zeshi Yang
public class Leetcode0509FibonacciNumber{
    // Java: fibonacci-number
    public static void main(String[] args) {
        Solution sol = new Leetcode0509FibonacciNumber().new Solution();
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int fib(int N) {
        if (N <= 1) {
            return N;
        }
        if (N == 2) {
            return 1;
        }

        int current = 0;
        int prev1 = 1;
        int prev2 = 1;

        for (int i = 3; i <= N; i++) {
            current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }
        return current;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: Recursion
class Solution1 {
    // Time complexity : O(2^N)
    //Space complexity : O(N)
    public int fib(int N) {
        if (N <= 1) {
            return N;
        }
        return fib(N-1) + fib(N-2);
    }
}

// Solution 2: Bottom-Up Solution using Memoization
class Solution2 {
    public int fib(int N) {
        if (N <= 1) {
            return N;
        }
        return memoize(N);
    }

    public int memoize(int N) {
        int[] cache = new int[N + 1];
        cache[1] = 1;

        for (int i = 2; i <= N; i++) {
            cache[i] = cache[i-1] + cache[i-2];
        }
        return cache[N];
    }
}

// Solution 3: Top-Down Solution using Memoization
class Solution3 {
    private Integer[] cache = new Integer[31];

    public int fib(int N) {
        if (N <= 1) {
            return N;
        }
        cache[0] = 0;
        cache[1] = 1;
        return memoize(N);
    }

    public int memoize(int N) {
        if (cache[N] != null) {
            return cache[N];
        }
        cache[N] = memoize(N-1) + memoize(N-2);
        return memoize(N);
    }
}

// Solution 4: Iterative Top-Down Solution
class Solution4 {
    public int fib(int N) {
        if (N <= 1) {
            return N;
        }
        if (N == 2) {
            return 1;
        }

        int current = 0;
        int prev1 = 1;
        int prev2 = 1;

        for (int i = 3; i <= N; i++) {
            current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }
        return current;
    }
}

// Solution 5: Math
class Solution5 {
    public int fib(int N) {
        double goldenRatio = (1 + Math.sqrt(5)) / 2;
        return (int)Math.round(Math.pow(goldenRatio, N)/ Math.sqrt(5));
    }
}
}