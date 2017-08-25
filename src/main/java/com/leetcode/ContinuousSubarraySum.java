package com.leetcode;

public class ContinuousSubarraySum {

    public static boolean checkSubarraySum(int[] nums, int k) {
        int[] prefix = calcPrefixArray(nums, k);
        for (int cur = 0; cur < nums.length; cur++) {
            for (int i = 0; i < cur - 1; i++) {
                if ((prefix[cur] - prefix[i]) % k == 0 ) {
                    return true;
                }
            }
        }
        return false;
    }

    private static int[] calcPrefixArray(int[] nums, int k) {
        int[] prefix = new int[nums.length];
        prefix[0] = nums[0];
        for (int i = 1; i < nums.length;i++) {
            prefix[i] = (prefix[i - 1] + nums[i]) %k;
        }
        return prefix;
    }

    public static void main(String args[]) {
        System.out.println(checkSubarraySum(new int[] {23, 2, 4, 6, 7}, 6));
        System.out.println(checkSubarraySum(new int[] {23, 2, 6, 4, 7}, 6));
        System.out.println(checkSubarraySum(new int[] {1, 1, 1, 1, 1}, 6));
    }

}
