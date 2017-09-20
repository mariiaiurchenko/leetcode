package com.leetcode;

import java.util.HashSet;
import java.util.Set;

/*
Validate if a given string is numeric.
Some examples:
"0" => true
" 0.1 " => true
"abc" => false
"1 a" => false
"2e10" => true
 */
public class ValidNumber {
    private static final char SEPARATOR = '.';
    private static final char EXPONENT = 'e';
    private static final Set<Character> SIGN = new HashSet<>();
    private static final Set<Character> SPACE = new HashSet<>();
    private static Set<Character> NUMBER = new HashSet<>();

    static {
        for (int i = 0; i < 10; i++) {
            NUMBER.add((char) ('0' + i));
        }
        SIGN.add('+');
        SIGN.add('-');
        SPACE.add(' ');
    }

    public static boolean isNumber(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        int from = remodeSpacesFromStart(s);
        int to = remodeSpacesFromEnd(s);
        return isNumber(s, from, to, false, false);
    }

    private static boolean isNumber(String s, int from, int to,
                                    boolean meetSeparator, boolean meetExponent) {
        int index = from;
        if (index < to && SIGN.contains(s.charAt(index))) {
            index++;
        }
        if (index == to) {
            return false;
        }
        boolean first = true;
        boolean justmeetExponent = meetExponent;
        for (; index < to; index++) {
            char ch = s.charAt(index);
            if (ch == SEPARATOR && !meetSeparator && !meetExponent) {
                meetSeparator = true;
            } else if (!first && ch == EXPONENT && !meetExponent) {
                return isNumber(s, ++index, to, true, true);
            } else if (!NUMBER.contains(ch)) {
                return false;
            } else {
                first = false;
                justmeetExponent = false;
            }
        }
        return !first && !justmeetExponent;
    }

    private static int remodeSpacesFromStart(String s) {
        int index = 0;
        while (index < s.length() && SPACE.contains(s.charAt(index))) {
            index++;
        }
        return index;
    }

    private static int remodeSpacesFromEnd(String s) {
        int index = s.length() - 1;
        while (index >= 0 && SPACE.contains(s.charAt(index))) {
            index--;
        }
        return index + 1;
    }

    public static void main(String[] args) {
        String s;
        boolean answer;
        s = "005047e+6";
        answer = true;
        System.out.println("12 " + s + " " + " answer = " + answer + "   " + (isNumber(s) == answer ? "+" : "!"));
        s = "6e6.5";
        answer = false;
        System.out.println("11 " + s + " " + " answer = " + answer + "   " + (isNumber(s) == answer ? "+" : "!"));
        s = "46.e3";
        answer = true;
        System.out.println("10 " + s + " " + " answer = " + answer + "   " + (isNumber(s) == answer ? "+" : "!"));
        s = "0e";
        answer = false;
        System.out.println("9 " + s + " " + " answer = " + answer + "   " + (isNumber(s) == answer ? "+" : "!"));
        s = ".1";
        answer = true;
        System.out.println("8 " + s + " " + " answer = " + answer + "   " + (isNumber(s) == answer ? "+" : "!"));
        s = ".";
        answer = false;
        System.out.println("7 " + s + " " + " answer = " + answer + "   " + (isNumber(s) == answer ? "+" : "!"));
        s = " ";
        answer = false;
        System.out.println("6 " + s + " " + " answer = " + answer + "   " + (isNumber(s) == answer ? "+" : "!"));
        s = "0";
        answer = true;
        System.out.println("5 " + s + " " + " answer = " + answer + "   " + (isNumber(s) == answer ? "+" : "!"));
        s = " 0.1 ";
        answer = true;
        System.out.println("4 " + s + " " + " answer = " + answer + "   " + (isNumber(s) == answer ? "+" : "!"));
        s = "abc";
        answer = false;
        System.out.println("3 " + s + " " + " answer = " + answer + "   " + (isNumber(s) == answer ? "+" : "!"));
        s = "1 a";
        answer = false;
        System.out.println("2 " + s + " " + " answer = " + answer + "   " + (isNumber(s) == answer ? "+" : "!"));
        s = "2e10";
        answer = true;
        System.out.println("1 " + s + " " + " answer = " + answer + "   " + (isNumber(s) == answer ? "+" : "!"));
    }
}
