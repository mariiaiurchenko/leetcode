package com.leetcode;

import java.util.HashSet;
import java.util.Set;

/*
Given an input string, reverse the string word by word.
For example,
Given s = "the sky is blue",
return "blue is sky the".
 */
public class ReverseWordsInAString {
    public String reverseWords(String s) {
        StringBuilder str = new StringBuilder(s.trim().replaceAll("( )+", " "));
        Set<Character> separators = new HashSet<>();
        separators.add(' ');
        int i = 0;
        int j = str.length() - 1;
        while (true) {
            int startF = i;
            while (i < str.length() && !separators.contains(str.charAt(i))) {
                i++;
            }
            int endF = i;
            int endL = j + 1;
            while (j >= 0 && !separators.contains(str.charAt(j))) {
                j--;
            }
            int startL = j + 1;
            if (i <= j) {
                swap(str, startF, endF, startL, endL);
                i = startF + endL - startL;
                j += endL - startL - (endF - startF);
            } else {
                break;
            }
            while (i < str.length() && separators.contains(str.charAt(i))) {
                i++;
            }
            while (j >= 0 && separators.contains(str.charAt(j))) {
                j--;
            }
        }
        return str.toString();
    }

    private static void swap(StringBuilder str, int startF, int endF, int startL, int endL) {
        String lastWord = str.substring(startL, endL);
        String firstWord = str.substring(startF, endF);
        str.replace(startL, endL, "");
        str.insert(startL, firstWord);
        str.replace(startF, endF, "");
        str.insert(startF, lastWord);
    }
}
