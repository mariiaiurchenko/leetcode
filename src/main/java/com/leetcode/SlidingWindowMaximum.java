package com.leetcode;

import java.util.LinkedList;
import java.util.List;

/*
Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.

For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
Therefore, return the max sliding window as [3,3,5,5,6,7].

Note:
You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.

Follow up:
Could you solve it in linear time?
 */
public class SlidingWindowMaximum {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null) {
            return null;
        } else if (nums.length == 0) {
            return new int[0];
        }

        int[] maxs = new int[nums.length - k + 1];

        MaxQueue<Integer> maxQueue = new MaxQueue<>(k);
        for (int i = 0; i < k - 1; i++) {
            maxQueue.add(nums[i]);
        }
        for (int i = 0; i < maxs.length; i++) {
            maxQueue.add(nums[k + i - 1]);
            maxs[i] = maxQueue.getMax();
        }
        return maxs;
    }

    private static class MaxQueue<T extends Comparable<T>> {
        private List<T> queue;
        private List<T> maxValues;
        int size;

        MaxQueue(int size) {
            this.size = size;
            queue = new LinkedList<>();
            maxValues = new LinkedList<>();
        }

        T getMax() {
            return maxValues.get(0);
        }

        void add(T el) {
            if (queue.size() == size) {
                remove();
            }
            queue.add(el);
            while (maxValues.size() > 0 && el.compareTo(maxValues.get(maxValues.size() - 1)) > 0) {
                maxValues.remove(maxValues.size() - 1);
            }
            maxValues.add(el);
        }

        void remove() {
            T el = queue.get(0);
            if (el.compareTo(getMax()) == 0) {
                maxValues.remove(0);
            }
            queue.remove(0);
        }
    }
}
