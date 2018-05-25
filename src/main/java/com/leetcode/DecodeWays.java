package com.leetcode;

/**
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:

 'A' -> 1
 'B' -> 2
 ...
 'Z' -> 26
 Given a non-empty string containing only digits, determine the total number of ways to decode it.

 Example 1:

 Input: "12"
 Output: 2
 Explanation: It could be decoded as "AB" (1 2) or "L" (12).
 Example 2:

 Input: "226"
 Output: 3
 Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).

 */
public class DecodeWays {

    public static void main(String[] args) {
        DecodeWays decodeWays = new DecodeWays();
        System.out.println(decodeWays.numDecodings("110"));
        System.out.println(decodeWays.numDecodings("17"));

    }

    public int numDecodings(String s) {
        if (s == null || s.isEmpty() || s.charAt(0) == '0') {
            return 0;
        }
        int lesLetterCount = 1;
        int count = 1;
        for (int i = 0; i < s.length(); i++) {
            int newCount = count;
            if (s.charAt(i) == '0') {
                if (s.charAt(i - 1) != '1' && s.charAt(i - 1) != '2') {
                    return 0;
                }
                count  = lesLetterCount;
                lesLetterCount = 0;
                continue;
            }
            if (i > 0
                && (s.charAt(i - 1) == '1'
                   || (s.charAt(i - 1) == '2') && s.charAt(i) - '1' < 6)) {
                newCount += lesLetterCount;
            }
            lesLetterCount = count;
            count = newCount;
        }
        return count;
    }

}
