package com.leetcode;

import java.util.ArrayList;
import java.util.List;

/*
Say you have an array for which the ith element is the price of a given stock on day i.
Design an algorithm to find the maximum profit. You may complete at most k transactions.
Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 */
public class BestTimeToBuyAndSellStockIV {
    private static class Transaction {
        int buy;
        int sell;

        Transaction(int buy, int sell) {
            this.buy = buy;
            this.sell = sell;
        }
    }

    public int maxProfit(int k, int[] prices) {
        if (k == 0 || prices == null || prices.length == 0) {
            return 0;
        }
        ArrayList<Transaction> transactions = new ArrayList<>();
        int buy = 0;
        for (int i = 1; i < prices.length - 1; i++) {
            if (prices[i] < prices[buy]) {
                buy = i;
            }
            if (prices[i] > prices[i + 1] && prices[i] > prices[buy]) {
                transactions.add(new Transaction(buy, i));
                buy = i + 1;
            }
        }
        if (prices[buy] < prices[prices.length - 1]) {
            transactions.add(new Transaction(buy, prices.length - 1));
        }
        return getMaxProfitForKTransactions(transactions, k, prices);
    }

    private int getMaxProfitForKTransactions(List<Transaction> transactions, int k, int[] prices) {
        while (transactions.size() > k) {
            absorbOneTransaction(transactions, prices);
        }
        int profit = 0;
        for (int i = 0; i < transactions.size(); i++) {
            profit += prices[transactions.get(i).sell] - prices[transactions.get(i).buy];
        }
        return profit;
    }

    private void absorbOneTransaction(List<Transaction> transactions, int[] prices) {
        int minProfit = Integer.MAX_VALUE;
        int minProfitIndex = 0;
        boolean absorb = false;
        for (int i = 0; i < transactions.size(); i++) {
            int currProfit = prices[transactions.get(i).sell] - prices[transactions.get(i).buy];
            if (currProfit < minProfit) {
                minProfit = currProfit;
                minProfitIndex = i;
            }
        }
        for (int i = 0; i < transactions.size() - 1; i++) {
            int currProfit = prices[transactions.get(i).sell] - prices[transactions.get(i + 1).buy];
            if (currProfit < minProfit) {
                minProfit = currProfit;
                minProfitIndex = i;
                absorb = true;
            }
        }
        removeTransaction(transactions, minProfitIndex, absorb);
    }

    private void removeTransaction(List<Transaction> transactions, int index, boolean absorb) {
        if (absorb) {
            transactions.get(index + 1).buy = transactions.get(index).buy;
        }
        transactions.remove(index);
    }
}
