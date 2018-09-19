package com.leetcode;

import java.util.Arrays;
import java.util.Random;

/*
Shuffle a set of numbers without duplicates.

Example:

// Init an array with set 1, 2, and 3.
int[] nums = {1,2,3};
Solution solution = new Solution(nums);

// Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
solution.shuffle();

// Resets the array back to its original configuration [1,2,3].
solution.reset();

// Returns the random shuffling of array [1,2,3].
solution.shuffle();
 */
public class ShuffleAnArray {

    int[] nums;

    public ShuffleAnArray(int[] nums) {
        this.nums = nums;
    }

    /**
     * Resets the array to its original configuration and return it.
     */
    public int[] reset() {
        return this.nums;
    }

    /**
     * Returns a random shuffling of the array.
     */
    public int[] shuffle() {
        int[] shuffle = Arrays.copyOf(nums, nums.length);
        Random r = new Random();
        for (int i = shuffle.length - 1; i > 0; i--) {
            int index = r.nextInt(i);
            int tmp = shuffle[i];
            shuffle[i] = shuffle[index];
            shuffle[index] = tmp;
        }
        return shuffle;
    }


}
