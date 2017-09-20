package com.leetcode;

/*
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
Find the minimum element.
You may assume no duplicate exists in the array.
 */
public class FindMinimumInRotatedSortedArray {

    public int findMin(int[] nums) {
        return findMin(nums, 0, nums.length - 1);
    }

    private int findMin(int[] nums, int left, int right) {
        if (right - left <= 1) {
            return Math.min(nums[left], nums[right]);
        }
        int mid = left + (right - left) / 2;
        if (nums[left] < nums[right]) {
            return nums[left];
        } else if (nums[mid] > nums[left]) {
            return findMin(nums, mid, right);
        } else {
            return findMin(nums, left, mid);
        }
    }
}
