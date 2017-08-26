package com.leetcode;

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
