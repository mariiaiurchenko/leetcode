package com.leetcode;

import java.util.LinkedList;
import java.util.List;

/*
Given an integer matrix, find the length of the longest increasing path.
From each cell, you can either move to four directions:
left, right, up or down. You may NOT move diagonally or
move outside of the boundary (i.e. wrap-around is not allowed).

Example 1:

nums = [
  [9,9,4],
  [6,6,8],
  [2,1,1]
]
Return 4
The longest increasing path is [1, 2, 6, 9].

Example 2:

nums = [
  [3,4,5],
  [3,2,6],
  [2,2,1]
]
Return 4
The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 */
public class LongestIncreasingPathInAMatrix {
    public static void main(String[] args) {
        int[][] nums = {{3, 4, 5}, {3, 2, 6}, {2, 2, 1}};
        System.out.println(longestIncreasingPath(nums));
    }

    public static int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }

        int hieght = matrix.length;
        int width = matrix[0].length;
        int[][] res = new int[hieght][width];
        for (int i = 0; i < hieght; i++) {
            for (int j = 0; j < width; j++) {
                res[i][j] = -1;
            }
        }
        for (int i = 0; i < hieght; i++) {
            for (int j = 0; j < width; j++) {
                if (res[i][j] == -1) {
                    calcPath(i, j, matrix, res);
                }
            }
        }

        int maxPath = 0;
        for (int i = 0; i < hieght; i++) {
            for (int j = 0; j < width; j++) {
                if (res[i][j] > maxPath) {
                    maxPath = res[i][j];
                }
            }
        }

        return maxPath;
    }

    private static int calcPath(int row, int col, int[][] matrix, int[][] res) {
        int hieght = matrix.length;
        int width = matrix[0].length;
        if (res[row][col] == -1) {
            List<Integer> path = new LinkedList<Integer>();
            if (row > 0 && matrix[row - 1][col] > matrix[row][col]) {
                path.add(calcPath(row - 1, col, matrix, res));
            }

            if (row < hieght - 1 && matrix[row + 1][col] > matrix[row][col]) {
                path.add(calcPath(row + 1, col, matrix, res));
            }
            if (col < width - 1 && matrix[row][col + 1] > matrix[row][col]) {
                path.add(calcPath(row, col + 1, matrix, res));
            }

            if (col > 0 && matrix[row][col - 1] > matrix[row][col]) {
                path.add(calcPath(row, col - 1, matrix, res));
            }

            int max = 0;
            for (int el : path) {
                if (el > max) {
                    max = el;
                }
            }
            res[row][col] = max + 1;
        }
        return res[row][col];
    }
}
