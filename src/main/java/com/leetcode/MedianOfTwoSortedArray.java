package com.leetcode;

/*
There are two sorted arrays nums1 and nums2 of size m and n respectively.
Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

Example 1:
nums1 = [1, 3]
nums2 = [2]
The median is 2.0

Example 2:
nums1 = [1, 2]
nums2 = [3, 4]
The median is (2 + 3)/2 = 2.5
 */
public class MedianOfTwoSortedArray {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length == 0) {
            int mid = getMidIndex(0, nums2.length);
            return average(nums2[mid], isOdd(nums2.length) ? nums2[mid] : nums2[mid + 1]);
        }
        if (nums2.length == 0) {
            int mid = getMidIndex(0, nums1.length);
            return average(nums1[mid], isOdd(nums1.length) ? nums1[mid] : nums1[mid + 1]);
        }
        if (nums1.length == 1 && nums2.length == 1) {
            return average(nums1[0], nums2[0]);
        }
        return findMid(nums1, nums2, 0, nums1.length, 0, nums2.length);
    }

    private static double findMid(int[] nums1, int[] nums2, int b1, int e1, int b2, int e2) {
        if (b1 == e1) {
            int midIndex = getMidIndex(0, nums1.length + nums2.length) - e1;
            return nums2[midIndex] + evenAdjustment(nums1, nums2, b1, midIndex + 1, nums2[midIndex], 2);
        }
        if (b2 == e2) {
            int midIndex = getMidIndex(0, nums1.length + nums2.length) - e2;
            return nums1[midIndex] + evenAdjustment(nums1, nums2, midIndex + 1, b2, nums1[midIndex], 1);
        }
        int m1 = getMidIndex(b1, e1);
        int m2 = getMidIndex(b2, e2);
        if (nums2[m2] >= nums1[m1] && (e1 - b1 <= 1 || nums2[m2] <= nums1[m1 + 1])) {
            return nums2[m2] + evenAdjustment(nums1, nums2, m1 + 1, m2 + 1, nums2[m2], 2);
        }
        if (nums1[m1] >= nums2[m2] && (e2 - b2 <= 1 || nums1[m1] <= nums2[m2 + 1])) {
            return nums1[m1] + evenAdjustment(nums1, nums2, m1 + 1, m2 + 1, nums1[m1], 1);
        }
        if (nums1[m1] < nums2[m2]) {
            int step = Math.max(Math.min(e1 - m1 - 1, m2 - b2), 1);
            return findMid(nums1, nums2, b1 + step, e1, b2, e2 - step);
        } else {
            int step = Math.max(Math.min(e2 - m2 - 1, m1 - b1), 1);
            return findMid(nums1, nums2, b1, e1 - step, b2 + step, e2);
        }
    }

    private static double evenAdjustment(int[] nums1, int[] nums2, int b1, int b2, int curr, int arrayNum) {
        int sumLength = nums1.length + nums2.length;
        if (isOdd(sumLength)) {
            return 0;
        }
        int next = 0;
        int num = b1 + b2;
        if (num <= sumLength / 2) {
            next = getNext(nums1, nums2, b1, b2);
        } else {
            if (arrayNum == 1) {
                b1 = b1 - 2;
                b2 = b2 - 1;
            } else {
                b1 = b1 - 1;
                b2 = b2 - 2;
            }
            next = getPrev(nums1, nums2, b1, b2);
        }
        return average(curr, next) - curr;
    }

    private static int getNext(int[] nums1, int[] nums2, int b1, int b2) {
        if (b1 < nums1.length && b2 < nums2.length) {
            return Math.min(nums1[b1], nums2[b2]);
        }
        if (b1 == nums1.length) {
            return nums2[b2];
        } else {
            return nums1[b1];
        }
    }

    private static int getPrev(int[] nums1, int[] nums2, int b1, int b2) {
        if (b1 >= 0 && b2 >= 0) {
            return Math.max(nums1[b1], nums2[b2]);
        }
        if (b1 < 0) {
            return nums2[b2];
        } else {
            return nums1[b1];
        }
    }

    private static double average(int a, int b) {
        return (a + b) / (double) 2;
    }

    private static int getMidIndex(int b, int e) {
        return b + (e - b) / 2 - 1 + (isOdd(e - b) ? 1 : 0);
    }

    private static boolean isOdd(int n) {
        return (n & 1) == 1 ? true : false;
    }
}
