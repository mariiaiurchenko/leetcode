package com.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/*
Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
If the fractional part is repeating, enclose the repeating part in parentheses.
For example,
Given numerator = 1, denominator = 2, return "0.5".
Given numerator = 2, denominator = 1, return "2".
Given numerator = 2, denominator = 3, return "0.(6)".
 */
public class FractionToRecurringDecimal {

    private String createString(List<Long> quotients, Long repeatIndex, boolean isNegative) {
        StringBuffer decimal = new StringBuffer("");
        if (quotients.size() == 0) {
            return "0";
        } else {
            if (isNegative) {
                decimal.append('-');
            }
            decimal.append(quotients.get(0));
            if (quotients.size() != 1) {
                decimal.append('.');
            }
        }
        boolean braceOpen = false;
        for (int i = 1; i < quotients.size(); i++) {
            if (i == repeatIndex) {
                braceOpen = true;
                decimal.append('(');
            }
            decimal.append(quotients.get(i));
        }
        if (braceOpen) {
            decimal.append(')');
        }
        return decimal.toString();
    }

    public String fractionToDecimal(int numerator, int denominator) {
        long modulo = Math.abs((long) numerator);
        long divider = Math.abs((long) denominator);
        Map<Long, Long> indexByModulo = new HashMap<>();
        List<Long> quotients = new LinkedList<>();
        while (modulo != 0 && !indexByModulo.containsKey(modulo)) {
            quotients.add(modulo / divider);
            indexByModulo.put(modulo, new Long(quotients.size() - 1));
            modulo = (modulo % divider) * 10;
        }
        long repeatIndex = -1;
        if (modulo != 0) {
            repeatIndex = indexByModulo.get(modulo);
        }
        return createString(quotients, repeatIndex, (numerator < 0) ^ (denominator < 0));
    }
}
