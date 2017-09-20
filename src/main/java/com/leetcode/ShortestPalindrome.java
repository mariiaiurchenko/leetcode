package com.leetcode;

/*
Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it. Find and return the shortest palindrome you can find by performing this transformation.
For example:
Given "aacecaaa", return "aaacecaaa".
Given "abcd", return "dcbabcd".
 */
public class ShortestPalindrome {
    public static String shortestPalindrome(String s) {
        if (s.equals("") || s.length() == 1) {
            return s;
        }

        StringBuilder reverse = new StringBuilder(s).reverse();
        int[] zta = ztaFunction(reverse.toString(), s);
        int indexPolindrom = 1;
        for (int i = s.length(); i < 2 * s.length() - 1; i++) {
            if (zta[i] == 2 * s.length() - i) {
                indexPolindrom = zta[i];
                break;
            }
        }
        return reverse.substring(0, s.length() - indexPolindrom).concat(s).toString();
    }

    private static int[] ztaFunction(String s, String p) {
        // Z-ta function
        String str = p + s;
        int[] countArray = new int[str.length()];
        int count = 0;
        int index = 1;
        while (index < str.length()) {
            while (index + count < str.length() && str.charAt(index + count) == str.charAt(count)) {
                count++;
            }
            countArray[index] = count;
            int start = index++;
            boolean updateCount = true;
            for (int j = 1; j < count; j++) {
                if (count - j - 1 <= str.length() - j - 1 && countArray[j] >= count - j) {
                    count = count - j - 1;
                    updateCount = false;
                    break;
                } else {
                    countArray[start + j] = countArray[j];
                    index++;
                }
            }
            if (updateCount) {
                count = 0;
            }
        }
        return countArray;
    }

    public static void main(String... args) {
        String s;
        String polindrom;
        String answer;
        s = "abbacd";
        answer = "dcabbacd";
        polindrom = shortestPalindrome(s);
        System.out.println("3 " + s + " polindrom is " + polindrom + "  " + polindrom.equals(answer));
        s = "abcd";
        answer = "dcbabcd";
        polindrom = shortestPalindrome(s);
        System.out.println("2 " + s + " polindrom is " + polindrom + "  " + polindrom.equals(answer));
        s = "aacecaaa";
        answer = "aaacecaaa";
        polindrom = shortestPalindrome(s);
        System.out.println("1 " + s + " polindrom is " + polindrom + "  " + polindrom.equals(answer));
    }
}
