//Find the kth largest element in an unsorted array. Note that it is the kth lar
//gest element in the sorted order, not the kth distinct element. 
//
// Example 1: 
//
// 
//Input: [3,2,1,5,6,4] and k = 2
//Output: 5
// 
//
// Example 2: 
//
// 
//Input: [3,2,3,1,2,4,5,5,6] and k = 4
//Output: 4 
//
// Note: 
//You may assume k is always valid, 1 ≤ k ≤ array's length. 
// Related Topics Divide and Conquer Heap 
// 👍 3854 👎 256

package leetcode.editor.en;

import java.util.Arrays;
import java.util.PriorityQueue;

// 2020-07-26 11:51:04
// Zeshi Yang
public class Leetcode0215KthLargestElementInAnArray {

	// Java: kth-largest-element-in-an-array
	public static void main(String[] args) {
		Solution sol = new Leetcode0215KthLargestElementInAnArray().new Solution();
		// TO TEST
        int[] nums = {3,2,1,5,6,4};
        int k = 2;
        int res = sol.findKthLargest(nums, k);
		System.out.println(res);
	}

//leetcode submit region begin(Prohibit modification and deletion)
//Solution 4: Quick Selection / QuickSort Partition + Binary Search, average T(n) = O(n)
class Solution {
    
    public int findKthLargest(int[] nums, int k) {
        int length = nums.length;
        int left = 0;
        int right = length - 1;
        return findPosPartition(k, left, right, nums);
    }
    
    private int findPosPartition(int k, int left, int right, int[] nums) {
        int len = nums.length;
        /**
         《代码存档》文件里面下面这个语句是错误的，它里面写的是
         int pivotRandIndex = left + (int) Math.random() * (right - left + 1);
         这样写 Math.random() 返回[0,1)之间的随机数，强制转换之后变成0
         所以应该改成下面这句代码，先算完在强制取整
         */
        int pivotRandIndex = left + (int) (Math.random() * (right - left + 1));
        int pivotValue = nums[pivotRandIndex];
        swap(nums, pivotRandIndex, right);
        
        //**************************different part 1 start ***********************//
        /*
        int leftI = left;
        int rightI = right - 1;
    
        while (leftI <= rightI) {
            if (array[leftI] < pivotValue) {
                leftI++;
            } else if (array[rightI] >= pivotValue) {
                //maybe duplicate
                rightI--;
            } else {
                // array[leftI] > pivotValue
                swap(array, leftI++, rightI--);
            }
        }
        swap(array, leftI, right);
        return leftI;
        */
        // ***********************different part 1 end *************************//
    
        // S2: use slow and fast pointers, two pointers forward, stable
        /*
         * [0, slow) < pivot
         * [slow, fast) >= pivot
         * [fast, length - 2) to check
         */
        int slow = left;
        int fast;
        for (fast = left; fast < right; fast++) {
            if (nums[fast] < pivotValue) {
                swap(nums, slow, fast);
                slow++;
            }
        }
        swap(nums, slow, right); // move the pivot from right to the real place
        
        // After operation, the target(pivotRandIndex) 's index is slow;
        if (slow == len - k) {
            return nums[slow];
        } else if (slow < len - k) {
            return findPosPartition(k, slow + 1, right, nums);
        } else {
            return findPosPartition(k, left, slow - 1, nums);
        }
    }
    
    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    
    
}

//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: Sort and return Kth element, Time O(nlogn) O(1) → count/bucket sort
class Solution1 {

    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
}

//Solution 2: MinHeap → size n, T(n) = O(n*log(k))
class Solution2 {

    public int findKthLargest(int[] nums, int k) {
        // use minHeap
        // Anonymous class
        /*PriorityQueue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>() {
            public int compare(Integer object1, Integer object2) {
                return object1 - object2;
            }
        });*/
        // 等价于下面的lambda表达式，因为java中heap是默认minHeap
        // PriorityQueue<Integer> heap = new PriorityQueue<>((n1, n2) -> n1 - n2);
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        // keep k largest elements in the heap
        for (int n : nums) {
            heap.add(n);
            if (heap.size() > k) {
                heap.poll();
            }
        }
        // output
        return heap.poll();
    }
}

// Solution 3: Max Heap
class Solution3 {

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>((n1, n2) -> n2 - n1); // max heap

        // keep k largest elements in the heap
        int temp;
        for (int n : nums) {
            heap.add(n);
        }
        // output
        for (int i = 1; i < k; i++) {
            heap.poll();
        }
        return heap.poll();
    }
}

//Solution 4: Quick Selection / QuickSort Partition + Binary Search, average T(n) = O(n)
class Solution4 {
    
    public int findKthLargest(int[] nums, int k) {
        int length = nums.length;
        int left = 0;
        int right = length - 1;
        return findPosPartition(k, left, right, nums);
    }
    
    private int findPosPartition(int k, int left, int right, int[] nums) {
        int len = nums.length;
        /**
         《代码存档》文件里面下面这个语句是错误的，它里面写的是
         int pivotRandIndex = left + (int) Math.random() * (right - left + 1);
         这样写 Math.random() 返回[0,1)之间的随机数，强制转换之后变成0
         所以应该改成下面这句代码，先算完在强制取整
         */
        int pivotRandIndex = left + (int) (Math.random() * (right - left + 1));
        int pivotValue = nums[pivotRandIndex];
        swap(nums, pivotRandIndex, right);
        
        //**************************different part 1 start ***********************//
        /*
        int leftI = left;
        int rightI = right - 1;
    
        while (leftI <= rightI) {
            if (array[leftI] < pivotValue) {
                leftI++;
            } else if (array[rightI] >= pivotValue) {
                //maybe duplicate
                rightI--;
            } else {
                // array[leftI] > pivotValue
                swap(array, leftI++, rightI--);
            }
        }
        swap(array, leftI, right);
        return leftI;
        */
        // ***********************different part 1 end *************************//
        
        // S2: use slow and fast pointers, two pointers forward, stable
        /*
         * [0, slow) < pivot
         * [slow, fast) >= pivot
         * [fast, length - 2) to check
         */
        int slow = left;
        int fast;
        for (fast = left; fast < right; fast++) {
            if (nums[fast] < pivotValue) {
                swap(nums, slow, fast);
                slow++;
            }
        }
        swap(nums, slow, right); // move the pivot from right to the real place
        
        // After operation, the target(pivotRandIndex) 's index is slow;
        if (slow == len - k) {
            return nums[slow];
        } else if (slow < len - k) {
            return findPosPartition(k, slow + 1, right, nums);
        } else {
            return findPosPartition(k, left, slow - 1, nums);
        }
    }
    
    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    
    
}


}