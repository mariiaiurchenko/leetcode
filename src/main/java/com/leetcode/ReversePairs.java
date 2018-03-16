package com.leetcode;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

/*
* Given an array nums, we call (i, j) an important reverse pair if i < j and nums[i] > 2*nums[j].
You need to return the number of important reverse pairs in the given array.

Example1:

Input: [1,3,2,3,1]
Output: 2
Example2:

Input: [2,4,3,5,1]
Output: 3

Input: [-5,-5]
Output: 1

Note:
The length of the given array will not exceed 50,000.
All the numbers in the input array are in the range of 32-bit integer.
* */
public class ReversePairs {

    private static final Integer MAX_TO_LOOKUP = Integer.MAX_VALUE / 2;
    private static final Integer MIN_TO_LOOKUP = Integer.MIN_VALUE / 2;

    public int reversePairs(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        TreeMap<Integer, Integer> countMap = createSortedCountMap(nums);
        Map<Integer, Integer> valueMap = new HashMap<>();
        TreeSet<Integer> tree = initTree(countMap, valueMap);

        int count = 0;
        for (int i = nums.length - 1; i >=0; i--) {
            removeChecked(nums[i], tree, valueMap, countMap);
            if (nums[i] >= MAX_TO_LOOKUP) {
                continue;
            }
            count += countBiggerValueLeft(nums[i], tree, valueMap, countMap);
        }

        return count;
    }

    private int countBiggerValueLeft(int val, TreeSet<Integer> tree,
                                            Map<Integer, Integer> valueMap, TreeMap<Integer, Integer> countMap) {
        int biggerThan = val < MIN_TO_LOOKUP ? Integer.MIN_VALUE : 2 * val + 1;
        Integer inMapBiggerValue = countMap.ceilingKey(biggerThan);
        Integer biggerValueInTree = valueMap.get(inMapBiggerValue);
        biggerValueInTree = biggerValueInTree == null ? Integer.MAX_VALUE : biggerValueInTree;
        SortedSet<Integer> tailView = tree.tailSet(biggerValueInTree);
        int count = tailView == null ? 0 : tailView.size();

        return count;
    }

    private void removeChecked(int val, TreeSet<Integer> tree,
                               Map<Integer, Integer> valueMap, TreeMap<Integer, Integer> countMap) {
        countMap.put(val, countMap.get(val) - 1);
        Integer valueInTree = tree.ceiling(valueMap.get(val));
        tree.remove(valueInTree);
    }

    private TreeMap<Integer, Integer> createSortedCountMap(int[] nums) {
        TreeMap<Integer, Integer> countMap = new TreeMap<>();
        for (Integer el : nums) {
            if (!countMap.containsKey(el)) {
                countMap.put(el, 0);
            }
            countMap.put(el, countMap.get(el) + 1);
        }
        return countMap;
    }

    private int finMaxDuplicate(Map<Integer, Integer> map) {
        int max = map.values().stream().max(Comparator.naturalOrder()).get();
        return max;
    }

    private TreeSet<Integer> initTree(Map<Integer, Integer> countMap, Map<Integer, Integer> valueMap) {
        Integer interval = finMaxDuplicate(countMap);
        TreeSet<Integer> tree = new TreeSet<>();
        int number = 0;
        for (Integer key : countMap.keySet()) {
            Integer val = countMap.get(key);
            Integer base = number * interval;
            valueMap.put(key, base);
            for (int countSimilar = 0; countSimilar < val; countSimilar++) {
                tree.add(base + countSimilar);
            }
            number++;
        }

        return tree;
    }


    public static void main(String[] args) {
        ReversePairs reversePairs = new ReversePairs();
        int[] nums = null;

        nums = new int[] {2147483647, 2147483647, -2147483647, -2147483647, -2147483647, 2147483647};
        System.out.println(String.format("result = [%d]  expected is 9", reversePairs.reversePairs(nums)));
        nums = new int[]{1, 3, 2, 3, 1};
        System.out.println(String.format("result = [%d]  expected is 2", reversePairs.reversePairs(nums)));
        nums = new int[] {2, 4, 3, 5, 1};
        System.out.println(String.format("result = [%d]  expected is 3", reversePairs.reversePairs(nums)));
        nums = new int[] {-5, -5};
        System.out.println(String.format("result = [%d]  expected is 1", reversePairs.reversePairs(nums)));
        nums = new int[]{ 233, 2000000001, 234, 2000000006, 235, 2000000003, 236, 2000000007, 237,
            2000000002, 2000000005, 233, 233, 233, 233, 233, 2000000004};
        System.out.println(String.format("result = [%d]  expected is 40", reversePairs.reversePairs(nums)));
        nums = new int[]{2147483647, 2147483647, -2147483647, -2147483647, -2147483647, 2147483647};
        System.out.println(String.format("result = [%d]  expected is 9", reversePairs.reversePairs(nums)));

    }
}
