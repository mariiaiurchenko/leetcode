package com.leetcode;

import java.util.Arrays;

/*
Given an array, rotate the array to the right by k steps, where k is non-negative.

Example 1:

Input: [1,2,3,4,5,6,7] and k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]
Example 2:

Input: [-1,-100,3,99] and k = 2
Output: [3,99,-1,-100]
Explanation:
rotate 1 steps to the right: [99,-1,-100,3]
rotate 2 steps to the right: [3,99,-1,-100]
Note:

Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
Could you do it in-place with O(1) extra space?
 */
public class RotateArray {

    public static void main(String[] args) {
        RotateArray rotateArray = new RotateArray();
        //rotateArray.rotate(new int[1], 1);
        int[] arr = new int[] {1, 2, 3, 4, 5, 6};
        rotateArray.rotate(arr, 4);
        System.out.println(Arrays.toString(arr));
      }

    public void rotate(int[] nums, int k) {
        int shift = k % nums.length;
        if (shift == 0) {
            return;
        }
        int gcd = greatestCommonDivisor(nums.length, shift);
        int iterationLength = nums.length / gcd;
        for (int count = 0; count < gcd; count++) {
            int pos = count;
            int el = nums[count];
            for (int i = 0; i < iterationLength; i++) {
                pos = (pos + shift) % nums.length;
                int tmp = nums[pos];
                nums[pos] = el;
                el = tmp;
            }
        }
    }
    private static int greatestCommonDivisor(int a, int b) {
        int big = a;
        int small = b;
        while (small != 0) {
            int tmp = big % small;
            big = small;
            small = tmp;
        }
        return big;
    }

}
