package com.leetcode;

import java.util.HashSet;
import java.util.Set;

/*
Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
For example,
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
Your algorithm should run in O(n) complexity.
 */
public class LongestConsecutiveSequence {
    public static int longestConsecutive(int[] nums) {
        Set<Integer> numSet = new HashSet<>();

        for (int num : nums) {
            numSet.add(num);
        }
        int maxCount = 0;
        for (int num : nums) {
            if (numSet.contains(num)) {
                numSet.remove(num);
                int count = 1;

                int goLeft = num - 1;
                while (numSet.contains(goLeft)) {
                    count++;
                    numSet.remove(goLeft);
                    goLeft--;
                }

                int goRight = num + 1;
                while (numSet.contains(goRight)) {
                    count++;
                    numSet.remove(goRight);
                    goRight++;
                }
                maxCount = Math.max(count, maxCount);
            }
        }
        return maxCount;
    }

    public static void main(String[] args) {
        System.out.println(longestConsecutive(new int[] {100, 4, 200, 1, 3, 2}));
    }
}
