package com.leetcode;

/*
Write a function that takes an unsigned integer and returns the number of '1' bits it has (also known as the Hamming weight).

Example 1:

Input: 11
Output: 3
Explanation: Integer 11 has binary representation 00000000000000000000000000001011
Example 2:

Input: 128
Output: 1
Explanation: Integer 128 has binary representation 00000000000000000000000010000000
 */
public class Numberof1Bits {

    public static void main(String[] args) {
        Numberof1Bits numberof1Bits = new Numberof1Bits();
            System.out.println(numberof1Bits.hammingWeight(4294967295L));
    }

    // you need to treat n as an unsigned value
    public int hammingWeight(long n) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            count += (n & (1 << i)) != 0 ? 1 : 0;
        }
        return count;
    }
}
