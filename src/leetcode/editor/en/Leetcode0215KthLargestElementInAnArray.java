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
class Solution {
    
    public int findKthLargest(int[] nums, int k) {
        int length = nums.length;
        int left = 0;
        int right = length - 1;
        return findPosPartition(k, left, right, nums);
    }
    
    private int findPosPartition(int k, int left, int right, int[] nums) {
        int len = nums.length;
        /*
         《代码存档》文件里面下面这个语句是错误的，它里面写的是
         int pivotRandIndex = left + (int) Math.random() * (right - left + 1);
         这样写 Math.random() 返回[0,1)之间的随机数，强制转换之后变成0
         所以应该改成下面这句代码，先算完在强制取整
         */
        int pivotIndex = left + (int) (Math.random() * (right - left + 1));
        int pivot = nums[pivotIndex];
        swap(nums, pivotIndex, right);
        
        /*
        每个while循环开始之前
        [left, l) < pivotValue
        (r, right - 1] > = pivotValue
         */
        int l = left; // left pointer
        int r = right - 1; // right pointer
        
        while (l <= r) {
            if (nums[l] < pivot) {
                l++;
            } else if (nums[r] >= pivot) {
                //maybe duplicate
                r--;
            } else {
                // array[leftI] > pivotValue && nums[r] < pivotValue
                swap(nums, l++, r--);
            }
        }
        swap(nums, l, right);
        
        if (l == len - k) {
            return nums[l];
        } else if (l < len - k) {
            return findPosPartition(k, l + 1, right, nums);
        } else {
            return findPosPartition(k, left, l - 1, nums);
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
// 1 ms, faster than 97.59%, 39.1 MB, less than 90.08%
class Solution1 {

    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
}

//Solution 2_1: MinHeap → size n, T(n) = O(n*log(k))
// 4 ms, faster than 62.64%, 39.1 MB, less than 80.25%
class Solution2_1 {

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

// Solution 2_2: Max Heap
// 4 ms, faster than 62.64%, 39.1 MB, less than 90.08%
class Solution2_2 {

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

//Solution 3_1: Quick Selection / QuickSort Partition + Binary Search, average T(n) = O(n)
// left, right pointer, relative position not stable
// 1 ms, faster than 97.59%, 39.1 MB, less than 80.25%
class Solution3_1 {
    
    public int findKthLargest(int[] nums, int k) {
        int length = nums.length;
        int left = 0;
        int right = length - 1;
        return findPosPartition(k, left, right, nums);
    }
    
    private int findPosPartition(int k, int left, int right, int[] nums) {
        int len = nums.length;
        /*
         《代码存档》文件里面下面这个语句是错误的，它里面写的是
         int pivotRandIndex = left + (int) Math.random() * (right - left + 1);
         这样写 Math.random() 返回[0,1)之间的随机数，强制转换之后变成0
         所以应该改成下面这句代码，先算完在强制取整
         */
        int pivotIndex = left + (int) (Math.random() * (right - left + 1));
        int pivot = nums[pivotIndex];
        swap(nums, pivotIndex, right);
        
        /*
        每个while循环开始之前
        [left, l) < pivotValue
        (r, right - 1] > = pivotValue
         */
        int l = left; // left pointer
        int r = right - 1; // right pointer
        
        while (l <= r) {
            if (nums[l] < pivot) {
                l++;
            } else if (nums[r] >= pivot) {
                //maybe duplicate
                r--;
            } else {
                // array[leftI] > pivotValue && nums[r] < pivotValue
                swap(nums, l++, r--);
            }
        }
        swap(nums, l, right);
        
        if (l == len - k) {
            return nums[l];
        } else if (l < len - k) {
            return findPosPartition(k, l + 1, right, nums);
        } else {
            return findPosPartition(k, left, l - 1, nums);
        }
    }
    
    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    
}

// Solution 3_2: quick selection + binary search, average average T(n) = O(n)
// slow and fast pointer, relative position stable
// 1 ms, faster than 97.59%, 39 MB, less than 95.77%
class Solution3_2 {
    
    public int findKthLargest(int[] nums, int k) {
        int length = nums.length;
        int left = 0;
        int right = length - 1;
        return findPosPartition(k, left, right, nums);
    }
    
    private int findPosPartition(int k, int left, int right, int[] nums) {
        int len = nums.length;
        /*
         《代码存档》文件里面下面这个语句是错误的，它里面写的是
         int pivotRandIndex = left + (int) Math.random() * (right - left + 1);
         这样写 Math.random() 返回[0,1)之间的随机数，强制转换之后变成0
         所以应该改成下面这句代码，先算完在强制取整
         */
        int pivotIndex = left + (int) (Math.random() * (right - left + 1));
        int pivot = nums[pivotIndex];
        swap(nums, pivotIndex, right);
        
        // S2: use slow and fast pointers, two pointers forward, stable
        /*
         * [0, slow) < pivot
         * [slow, fast) >= pivot
         * [fast, length - 2) to check
         */
        int slow = left;
        int fast;
        for (fast = left; fast < right; fast++) {
            if (nums[fast] < pivot) {
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