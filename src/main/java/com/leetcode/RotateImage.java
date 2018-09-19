package com.leetcode;

/*
You are given an n x n 2D matrix representing an image.

Rotate the image by 90 degrees (clockwise).

Note:

You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.

Example 1:

Given input matrix =
[
  [1,2,3],
  [4,5,6],
  [7,8,9]
],

rotate the input matrix in-place such that it becomes:
[
  [7,4,1],
  [8,5,2],
  [9,6,3]
]
Example 2:

Given input matrix =
[
  [ 5, 1, 9,11],
  [ 2, 4, 8,10],
  [13, 3, 6, 7],
  [15,14,12,16]
],

rotate the input matrix in-place such that it becomes:
[
  [15,13, 2, 5],
  [14, 3, 4, 1],
  [12, 6, 8, 9],
  [16, 7,10,11]
]
 */
public class RotateImage {

    public static void main(String[] args) {
        RotateImage rotateImage = new RotateImage();
        int[][] arr = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9,10,11,12}, {13,14,15,16}};
//        int[][] arr = new int[][] {{1, 2},{3, 4}};
        rotateImage.rotate(arr);
    }


    public void rotate(int[][] matrix) {
        int iterations = matrix.length / 2;
        for (int i = 0; i < iterations; i++) {
            int sqrSize = matrix[0].length - i * 2;
            for (int j = 0; j < sqrSize - 1; j++) {
                int lastIndex = i + sqrSize - 1;
                int one = matrix[i][j + i];
                int two = matrix[j + i][lastIndex];
                int three = matrix[lastIndex][lastIndex - j];
                int four = matrix[lastIndex - j][i];
                matrix[j + i][lastIndex] = one;
                matrix[lastIndex][lastIndex - j] = two;
                matrix[lastIndex - j][i] = three;
                matrix[i][j + i] = four;
            }
        }
    }
}
