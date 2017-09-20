package com.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
Note: The solution set must not contain duplicate triplets.
For example, given array S = [-1, 0, 1, 2, -1, -4],
A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]
 */
public class _3Sum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                for (int j = i + 1; j < nums.length - 1; j++) {
                    if (j == i + 1 || nums[j] != nums[j - 1]) {
                        int lastToZero = 0 - nums[i] - nums[j];
                        int k = Arrays.binarySearch(nums, lastToZero);
                        if (lastToZero == nums[j + 1] || k > 0 && k > j) {
                            List<Integer> triplet = new LinkedList<Integer>();
                            triplet.add(nums[i]);
                            triplet.add(nums[j]);
                            triplet.add(lastToZero);
                            res.add(triplet);
                        }
                    }
                }
            }
        }
        return res;
    }
}
