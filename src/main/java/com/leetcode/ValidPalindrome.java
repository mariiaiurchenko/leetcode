package com.leetcode;

/*
Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

Note: For the purpose of this problem, we define empty string as valid palindrome.

Example 1:

Input: "A man, a plan, a canal: Panama"
Output: true
Example 2:

Input: "race a car"
Output: false
 */
public class ValidPalindrome {

    public static void main(String[] args) {
        ValidPalindrome validPalindrome = new ValidPalindrome();
        System.out.println(validPalindrome.isPalindrome("A man, a plan, a canal: Panama"));
    }

    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        int i = nextLetter(s, 0, true);
        int j = nextLetter(s, s.length() - 1, false);
        while (i < j) {
            char start = Character.toLowerCase(s.charAt(i));
            char end = Character.toLowerCase(s.charAt(j));
            if (start != end) {
                return false;
            }
            i = nextLetter(s, i + 1, true);
            j = nextLetter(s, j - 1, false);
        }
        return true;
    }

    private int nextLetter(String s, int index, boolean leftRight) {
        int i = index;
        while (i >= 0 && i < s.length() && !isAlphanumeric(s.charAt(i))) {
            if (leftRight) {
                i++;
            } else {
                i--;
            }
        }
        return i;
    }

    private boolean isAlphanumeric(char ch) {
        return ch >= 'a' && ch <= 'z'
            || ch >= 'A' && ch <= 'Z'
            || ch >= '0' && ch <= '9';
    }
}
