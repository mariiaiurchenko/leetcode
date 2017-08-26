package com.leetcode;

/*
Given a list of non-negative numbers and a target integer k, write a function to check if the array has a continuous subarray of size at least 2 that sums up to the multiple of k, that is, sums up to n*k where n is also an integer.

Example 1:
Input: [23, 2, 4, 6, 7],  k=6
Output: True
Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
Example 2:
Input: [23, 2, 6, 4, 7],  k=6
Output: True
Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
Note:
The length of the array won't exceed 10,000.
You may assume the sum of all the numbers is in the range of a signed 32-bit integer.

* */
public class ContinuousSubarraySum {

    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums == null || nums.length <= 1) {
            return false;
        }
        if (k == 0) {
            return checkForZero(nums);
        }

        int[] prefix = calcPrefixArray(nums, k);
        for (int cur = 1; cur < nums.length; cur++) {
            if (prefix[cur] % k == 0 ) {
                return true;
            }
            for (int i = 0; i < cur - 1; i++) {
                if ((prefix[cur] - prefix[i]) % k == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private int[] calcPrefixArray(int[] nums, int k) {
        int[] prefix = new int[nums.length];
        prefix[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            prefix[i] = (prefix[i - 1] + nums[i]) % k;
        }
        return prefix;
    }

    private boolean checkForZero(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] == 0 && nums[i] == 0) {
                return true;
            }
        }
        return false;
    }

    public static void main(String args[]) {
        ContinuousSubarraySum solution = new ContinuousSubarraySum();
        System.out.println(solution.checkSubarraySum(new int[] {0, 0}, -1) + " -> true");
        System.out.println(solution.checkSubarraySum(new int[] {23, 2, 4, 6, 7}, 6) + " -> true" );
        System.out.println(solution.checkSubarraySum(new int[] {23, 2, 6, 4, 7}, 6) + " -> true");
        System.out.println(solution.checkSubarraySum(new int[] {1, 1, 1, 1, 1}, 6) + " -> false");
    }

}
