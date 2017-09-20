package com.leetcode;


/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 Each element in the array represents your maximum jump length at that position.
 Your goal is to reach the last index in the minimum number of jumps.
 For example:
 Given array A = [2,3,1,1,4]
 The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
 Note:
 You can assume that you can always reach the last index.
 */
public class JumpGameII {
    public int jump(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        int count = 1;
        int i = 0;
        while (i + nums[i] < nums.length - 1) {
            int nextIndex = i + 1;
            int nextJump = 0;
            int jumpSize = nums[i];
            for (int j = 1; j <= jumpSize && i + j < nums.length; j++) {
                int tmpJump = nums[i + j] - jumpSize + j;
                if (tmpJump > nextJump) {
                    nextJump = tmpJump;
                    nextIndex = i + j;
                }
            }
            i = nextIndex;
            count++;
        }
        return count;
    }
}
