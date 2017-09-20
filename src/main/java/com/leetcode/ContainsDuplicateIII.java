package com.leetcode;

import java.util.TreeSet;

/*
Given an array of integers, find out whether there are two distinct
indices i and j in the array such that the absolute difference
between nums[i] and nums[j] is at most t and the absolute difference
between i and j is at most k.
 */
public class ContainsDuplicateIII {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (k == 0 || t < 0) {
            return false;
        }
        TreeSet<Integer> nearest = new TreeSet<Integer>();
        for (int i = 0; i < nums.length; i++) {
            Integer ceiling = nearest.ceiling(nums[i]);
            Integer floor = nearest.floor(nums[i]);
            if (ceiling != null && ceiling.longValue() - nums[i] <= t || floor != null
                && nums[i] - floor.longValue() <= t) {
                return true;
            }
            if (nearest.size() >= k) {
                nearest.remove(nums[i - k]);
            }
            nearest.add(nums[i]);
        }
        return false;
    }
}
