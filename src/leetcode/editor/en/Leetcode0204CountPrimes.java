//Count the number of prime numbers less than a non-negative number, n. 
//
// Example: 
//
// 
//Input: 10
//Output: 4
//Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
// 
// Related Topics Hash Table Math 
// 👍 2245 👎 644

package leetcode.editor.en;

import java.util.*;
// 2020-09-10 19:24:26
// Zeshi Yang
public class Leetcode0204CountPrimes{
    // Java: count-primes
    public static void main(String[] args) {
        Solution1 sol1 = new Leetcode0204CountPrimes().new Solution1();
        Solution2 sol2 = new Leetcode0204CountPrimes().new Solution2();

        // TO TEST
        int n = 10000000;

        System.out.println("test the sieve of Euler" + ":");
        long startTime1 = System.currentTimeMillis();   //获取开始时间
        int res1 = sol1.countPrimes(n);  //测试的代码段
        long endTime1 = System.currentTimeMillis(); //获取结束时间
        System.out.println("time:" + (endTime1 - startTime1) + "ms");

        System.out.println("test the sieve of Eratosthenes" + ":");
        long startTime2 = System.currentTimeMillis();   //获取开始时间
        int res2 = sol2.countPrimes(n);  //测试的代码段
        long endTime2 = System.currentTimeMillis(); //获取结束时间
        System.out.println("time:" + (endTime2 - startTime2) + "ms");

        System.out.println(res1);
        System.out.println(res2);
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int countPrimes(int n) {
        if (n <= 2) {
            return 0;
        }
        List<Integer> primeList = new ArrayList<>();
        primeList.add(2);
        for (int i = 3; i < n; i++) {
            boolean isPrime = true; // whether i is prime
            for (Integer prime: primeList) {
                if (prime * prime > i) {
                    break;
                }
                if (i % prime == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                primeList.add(i);
            }
        }
        return primeList.size();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: The sieve of Euler
class Solution1 {
    public int countPrimes(int n) {
        if (n <= 2) {
            return 0;
        }
        List<Integer> primeList = new ArrayList<>();
        primeList.add(2);
        int count = 1;
        for (int i = 3; i < n; i++) {
            boolean isPrime = true; // whether i is prime
            for (Integer prime : primeList) {
                if (prime * prime > i) {
                    break;
                }
                if (i % prime == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                count++;
                primeList.add(i);
            }
        }
        return count;
    }
}

// Solution 2: The sieve of Eratosthenes
class Solution2 {
    public int countPrimes(int n) {
        if (n <= 1) {
            return 0;
        }
        // 默认所有的元素值都会设置为false，boolean初始值为false
        boolean[] notPrime = new boolean[n];
        notPrime[0] = true;
        notPrime[1] = true;
        for (int i = 2; i * i < n; i++) {
            if (!notPrime[i]) {
                // 如果i是一个质数， 将i的倍数设置为非质数,
                //j += i相当于i的3倍，4倍……
                for (int j = 2 * i; j < n; j += i) {
                    notPrime[j] = true;
                }
            }
        }
        // 统计质数的个数
        int result = 0;
        for (boolean notPri : notPrime) {
            if (!notPri) {
                result++;
            }
        }
        return result;
    }
}

}