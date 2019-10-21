package com.leetcode;

/*
We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i], obtaining a profit of profit[i].
You're given the startTime , endTime and profit arrays, you need to output the maximum profit you can take such that there are no 2 jobs in the subset with overlapping time range.
If you choose a job that ends at time X you will be able to start another job that starts at time X.

Example 1:
Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
Output: 120
Explanation: The subset chosen is the first and fourth job.
Time range [1-3]+[3-6] , we get profit of 120 = 50 + 70.

Example 2:
Input: startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
Output: 150
Explanation: The subset chosen is the first, fourth and fifth job.
Profit obtained 150 = 20 + 70 + 60.

Example 3:
Input: startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
Output: 6

Constraints:

1 <= startTime.length == endTime.length == profit.length <= 5 * 10^4
1 <= startTime[i] < endTime[i] <= 10^9
1 <= profit[i] <= 10^4
* */

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class MaximumProfitInJobScheduling {

    public static void main(String[] args) {
        int[][] nums = {{3, 4, 5}, {3, 2, 6}, {2, 2, 1}};
        MaximumProfitInJobScheduling jobScheduling = new MaximumProfitInJobScheduling();
        System.out.println(jobScheduling.jobScheduling(new int[]{1, 2, 3, 4, 6}, new int[]{3, 5, 10, 6, 9}, new int[]{20, 20, 100, 70, 60}));

    }

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        if (startTime == null || startTime.length == 0 ) return 0;
        sortArrays(startTime, endTime, profit);
        TreeMap<Integer, Integer> profitMap = new TreeMap<>();
        int maxProfit = 0;
        for (int i = startTime.length - 1; i >= 0; i--) {
            Map.Entry<Integer, Integer> ceilingEntry = profitMap.ceilingEntry(endTime[i]);
            int prevJobs = ceilingEntry == null? 0 : ceilingEntry.getValue();
            maxProfit = Math.max(maxProfit, profit[i] + prevJobs);
            profitMap.put(startTime[i], maxProfit);
        }
        return profitMap.get(startTime[0]);
    }


    private static class Triple implements Comparable<Triple> {
        Integer a,b,c;

        public int compareTo(Triple p) {
            return a.compareTo(p.a);
        }

    }

    private void sortArrays(int[] startTime, int[] endTime, int[] profit) {
        Triple[] triples = new Triple[startTime.length];
        for (int i = 0; i < startTime.length; i++) {
            triples[i] = new Triple();
            triples[i].a = startTime[i];
            triples[i].b = endTime[i];
            triples[i].c = profit[i];
        }

        Arrays.sort(triples);

        for (int i = 0; i < startTime.length; i++) {
            startTime[i] = triples[i].a;
            endTime[i] = triples[i].b;
            profit[i] = triples[i].c;
        }
    }

}
