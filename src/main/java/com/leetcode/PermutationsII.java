package com.leetcode;

import java.util.LinkedList;
import java.util.List;

public class PermutationsII {

    public List<List<Integer>> permuteUnique(int[] nums) {
        return permuteUnique(nums, 0);
    }

    public List<List<Integer>> permuteUnique(int[] nums, int index) {
        List<List<Integer>> newList = new LinkedList<>();
        if (index == nums.length - 1) {
            List<Integer> newPermute = new LinkedList<>();
            newPermute.add(nums[index]);
            newList.add(newPermute);
        } else {
            List<List<Integer>> oldList = permuteUnique(nums, index + 1);
            for (List<Integer> permute : oldList) {
                int permuteSize = permute.size();
                int i = 0;
                for (; i < permuteSize; i++) {
                    List<Integer> newPermute = new LinkedList<>();
                    newPermute.addAll(permute.subList(0, i));
                    newPermute.add(nums[index]);
                    newPermute.addAll(permute.subList(i, permuteSize));
                    newList.add(newPermute);
                    if (permute.get(i) == nums[index]) {
                        break;
                    }
                }
                if (i == permuteSize) {
                    List<Integer> newPermute = new LinkedList<>();
                    newPermute.addAll(permute.subList(0, permuteSize));
                    newPermute.add(nums[index]);
                    newList.add(newPermute);
                }
            }
        }
        return newList;
    }
}
