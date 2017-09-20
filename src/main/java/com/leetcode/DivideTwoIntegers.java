package com.leetcode;

/*
Divide two integers without using multiplication, division and mod operator.
If it is overflow, return MAX_INT.
 */
public class DivideTwoIntegers {
    public int divide(long dividend, long divisor) {
        boolean isNegative = dividend < 0 ^ divisor < 0;
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);
        long tmp_divid = dividend;
        long offset = 0;
        int count = 0;
        while (tmp_divid >= divisor) {
            offset <<= 1;
            offset += tmp_divid & 1;
            tmp_divid >>= 1;
            count++;
        }
        long quotient = 0;
        while (count > 0) {
            count--;
            tmp_divid <<= 1;
            tmp_divid += offset & 1;
            offset >>= 1;
            quotient <<= 1;
            if (tmp_divid >= divisor) {
                quotient ^= 1;
                tmp_divid -= divisor;
            }
        }
        if (isNegative) {
            quotient = 0 - quotient;
        }
        return (quotient > Integer.MAX_VALUE) ? Integer.MAX_VALUE : (int) quotient;
    }
}
