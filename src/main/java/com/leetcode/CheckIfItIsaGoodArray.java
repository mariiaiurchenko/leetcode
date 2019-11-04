package com.leetcode;

import java.util.HashSet;
import java.util.Set;

/*
1250. Check If It Is a Good Array My SubmissionsBack to Contest
User Accepted: 563
User Tried: 847
Total Accepted: 582
Total Submissions: 1840
Difficulty: Hard
Given an array nums of positive integers. Your task is to select some subset of nums, multiply each element by an integer and add all these numbers. The array is said to be good if you can obtain a sum of 1 from the array by any possible subset and multiplicand.

Return True if the array is good otherwise return False.

Example 1:

Input: nums = [12,5,7,23]
Output: true
Explanation: Pick numbers 5 and 7.
5*3 + 7*(-2) = 1
Example 2:

Input: nums = [29,6,10]
Output: true
Explanation: Pick numbers 29, 6 and 10.
29*1 + 6*(-3) + 10*(-1) = 1
Example 3:

Input: nums = [3,6]
Output: false

Constraints:

1 <= nums.length <= 10^5
1 <= nums[i] <= 10^9
 */
public class CheckIfItIsaGoodArray {

    public static void main(String[] args) {
        CheckIfItIsaGoodArray obj = new CheckIfItIsaGoodArray();
        System.out.println(obj.isGoodArray(new int[] {18, 18, 18, 15, 54, 45, 45, 45}));
        System.out.println(obj.isGoodArray(new int[] {29, 6, 10}));
        System.out.println(obj.isGoodArray(new int[] {3, 6}));
        System.out.println(obj.isGoodArray(new int[] {12, 5, 7, 23}));

    }

    public boolean isGoodArray(int[] nums) {
        int min = Integer.MAX_VALUE;
        for (int el : nums) {
            if (el == 1) {
                return true;
            }
            min = Math.min(Math.abs(el), min);
        }
        Set<Integer> primes = factorization(min);
        for (int div : primes) {
            int count = 0;
            for (int el : nums) {
                if (el % div == 0) {
                    count++;
                }
            }
            if (count == nums.length) {
                return false;
            }
        }
        return true;
    }

    private Set<Integer> factorization(int num) {
        Set<Integer> res = new HashSet<>();
        int div = 2;
        while (num > 1) {
            while (num % div == 0) {
                res.add(div);
                num /= div;
            }
            div++;
        }
        return res;
    }
}
