package com.leetcode;

/*
Follow up for "Find Minimum in Rotated Sorted Array":
What if duplicates are allowed?
Would this affect the run-time complexity? How and why?
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
Find the minimum element.
The array may contain duplicates.
 */
public class FindMinimumInRotatedSortedArrayII {
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
        } else if (nums[mid] < nums[left]) {
            return findMin(nums, left, mid);
        } else {
            return Math.min(findMin(nums, mid, right), findMin(nums, left, mid));
        }
    }
}
