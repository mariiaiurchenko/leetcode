package com.leetcode;

/*
Given a 32-bit signed integer, reverse digits of an integer.

Example 1:

Input: 123
Output: 321
Example 2:

Input: -123
Output: -321
Example 3:

Input: 120
Output: 21
Note:
Assume we are dealing with an environment which could only store integers within
 the 32-bit signed integer range: [−231,  231 − 1].
  For the purpose of this problem, assume that your
  function returns 0 when the reversed integer overflows.
 */
public class ReverseInteger {

    public static void main(String[] args) {
        ReverseInteger reverseInteger = new ReverseInteger();
        System.out.println(reverseInteger.reverse(-2147483648));
    }

    public int reverse(int x) {
        int sign = x < 0 ? -1 : 1;
        long orig = (long)x * sign;
        int res = 0;
        while (orig != 0) {
            long temp = res * 10L + orig % 10;
            if (temp > Integer.MAX_VALUE || temp * sign < Integer.MIN_VALUE) {
                return 0;
            }
            res = (int) temp;
            orig = orig / 10;
        }
        return res * sign;
    }
}
