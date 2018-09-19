package com.leetcode;

/*
Implement strStr().

Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

Example 1:

Input: haystack = "hello", needle = "ll"
Output: 2
Example 2:

Input: haystack = "aaaaa", needle = "bba"
Output: -1
Clarification:

What should we return when needle is an empty string? This is a great question to ask during an interview.

For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().
 */
public class ImplementStrStr {

    public static void main(String[] args) {
        ImplementStrStr implementStrStr = new ImplementStrStr();
        System.out.println(implementStrStr.strStr("hello", "ll"));
        System.out.println(implementStrStr.strStr("aaaaa",  "bba"));
        System.out.println(implementStrStr.strStr("mississippi", "mississippi"));

    }

    public int strStr(String haystack, String needle) {
        if (haystack.length() == 0 && needle.length() == 0) {
            return 0;
        }
        String searchStr = needle + haystack;
        int[] count = new int[searchStr.length()];

        int i = 1;
        int beg = 0;
        while (i < searchStr.length()) {
            int match  = countMatch(searchStr, i, beg);
            count[i] = match;
            int j = 1;
            while (j < match && count[j] < match - j) {
                count[i + j] = count[j];
                j++;
            }
            i = i + j;
        }
        int index = needle.length();
        for (; index < count.length; index++) {
            if (count[index] >= needle.length()) {
                break;
            }
        }
        return index == count.length ? -1: index - needle.length();
    }

    private int countMatch(String str, int mid, int beg) {
        int i = mid;
        int j = beg;
        while (i < str.length() && str.charAt(i) == str.charAt(j)) {
            i++;
            j++;
        }
        return j;
    }
}
