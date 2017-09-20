package com.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/*
Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
A region is captured by flipping all 'O's into 'X's in that surrounded region.

For example,
X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X
 */
public class SurroundedRegions {

    private static char X = 'X';
    private static char O = 'O';

    private static int covertIndex(int col, int row, int size) {
        return col * size + row;
    }

    private static int covertCol(int index, int size) {
        return index / size;
    }

    private static int covertRow(int index, int size) {
        return index % size;
    }

    public void solve(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        Deque<Integer> deque = new LinkedList<>();
        int size = Math.max(board.length, board[0].length);
        for (int i = 0; i < board.length; i++) {
            if (board[i][0] == O) {
                deque.add(covertIndex(i, 0, size));
            }
            if (board[i][board[0].length - 1] == O) {
                deque.add(covertIndex(i, board[0].length - 1, size));
            }
        }
        for (int i = 0; i < board[0].length; i++) {
            if (board[0][i] == O) {
                deque.add(covertIndex(0, i, size));
            }
            if (board[board.length - 1][i] == O) {
                deque.add(covertIndex(board.length - 1, i, size));
            }
        }
        char newMark = '1';
        while (!deque.isEmpty()) {
            int el = deque.removeLast();
            int col = covertCol(el, size);
            int row = covertRow(el, size);
            if (board[col][row] == O) {
                board[col][row] = newMark;
                if (col - 1 >= 0 && board[col - 1][row] == O) {
                    deque.add(covertIndex(col - 1, row, size));
                }
                if (col + 1 < board.length && board[col + 1][row] == O) {
                    deque.add(covertIndex(col + 1, row, size));
                }
                if (row - 1 >= 0 && board[col][row - 1] == O) {
                    deque.add(covertIndex(col, row - 1, size));
                }
                if (row + 1 < board[0].length && board[col][row + 1] == O) {
                    deque.add(covertIndex(col, row + 1, size));
                }
            }
        }
        for (int col = 0; col < board.length; col++) {
            for (int row = 0; row < board[0].length; row++) {
                if (board[col][row] == O) {
                    board[col][row] = X;
                } else if (board[col][row] == '1') {
                    board[col][row] = O;
                }
            }
        }
    }
}
