package com.leetcode;

import java.util.LinkedList;
import java.util.List;

/*
 '?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "*") → true
isMatch("aa", "a*") → true
isMatch("ab", "?*") → true
isMatch("aab", "c*a*b") → false
 */
public class WildcardMatching {

    public boolean isMatch(String s, String p) {
        List<String> patterns = split(p, '*');
        if (patterns.isEmpty() && !p.isEmpty() || s.isEmpty() && p.isEmpty()) {
            return true;
        } else if (!s.isEmpty() && p.isEmpty()) {
            return false;
        }
        int strIndex = 0;
        int patternIndex = 0;
        if (p.charAt(0) != '*') {
            strIndex = findPattern(s, 0, patterns.get(0));
            if (strIndex == -1 || strIndex > patterns.get(0).length()) {
                return false;
            }
            patternIndex = 1;
        }
        int[] search = isMatch(s, patterns, strIndex, patternIndex);

        String lastPattern = patterns.get(patterns.size() - 1);
        if (search[1] == patterns.size() && (search[0] == s.length()
            || search[0] == s.length() + 1 &&
            (p.charAt(p.length() - 1) == '*'
                || (lastPattern.length() < p.length()
                && findPattern(s, s.length() - lastPattern.length(), lastPattern) == s.length())))) {
            return true;
        }
        return false;
    }

    private static int[] isMatch(String str, List<String> patternList,
                                 int strIndex, int patternListIndex) {
        int[] res = new int[2];
        res[1] = patternListIndex;
        if (patternListIndex == patternList.size() && strIndex < str.length()) {
            res[0] = str.length() + 1;
        } else if (strIndex >= str.length() && patternListIndex == patternList.size()) {
            res[0] = strIndex;
        } else if (strIndex == str.length() && patternListIndex < patternList.size()) {
            res[0] = -1;
        } else {
            int patternEnd = findPattern(str, strIndex, patternList.get(patternListIndex));
            if (patternEnd == -1) {
                res[0] = -1;
            } else {
                res = isMatch(str, patternList, patternEnd, patternListIndex + 1);
            }
        }
        return res;
    }

    private static int findPattern(String str, int from, String p) {
        return findPattern(str, from, p, 0);
    }

    private static int findPattern(String str, int from, String p, int pFrom) {
        if (from >= str.length() && pFrom < p.length()) {
            return -1;
        } else if (pFrom >= p.length()) {
            return from;
        }
        if (p.charAt(pFrom) == '?') {
            return findPattern(str, from + 1, p, pFrom + 1);
        } else {
            int i = pFrom + 1;
            while (i < p.length() && p.charAt(i) != '?') {
                i++;
            }
            int z = ztaFunction(str, from, p.substring(pFrom, i));
            if (z > -1 && (z < str.length() || i < p.length())) {
                return findPattern(str, z, p, i);
            } else {
                return z;
            }
        }
    }

    private static int ztaFunction(String str, int from, String p) {
        // Z-ta function
        if (str.length() - from < p.length()) {
            return -1;
        }
        String s = p + str.substring(from);
        int[] countArray = new int[s.length()];
        int count = 0;
        int index = 1;
        while (true) {
            while (index + count < s.length() && s.charAt(index + count) == s.charAt(count)) {
                count++;
            }
            if (count >= p.length() && index >= p.length()) {
                return index + from;
            }
            countArray[index] = count;
            int start = index++;
            boolean updateCount = true;
            for (int j = 1; j < count; j++) {
                boolean shouldBreak = false;
                updateCount = true;
                if (countArray[j] + start >= s.length()) {
                    if (count - j - 1 <= s.length() - j - 1) {
                        count = count - j - 1;
                        updateCount = false;
                        shouldBreak = true;
                    } else {
                        countArray[start + j] = s.length() - j - 1;
                        index++;
                    }
                } else if (countArray[j] < count - j) {
                    countArray[start + j] = countArray[j];
                    index++;
                } else {
                    count = count - j - 1;
                    updateCount = true;
                    shouldBreak = true;
                }
                if (countArray[start + j] >= p.length() && start + j >= p.length()) {
                    return start + j + from;
                }
                if (shouldBreak) {
                    break;
                }
                if (updateCount) {
                    count = 0;
                }
            }
            if (index >= s.length()) {
                return -1;
            }
            if (updateCount) {
                count = 0;
            }
        }
    }

    private static List<String> split(String s, char separator) {
        List<String> tokens = new LinkedList<>();
        if (s == null || s.length() == 0) {
            return tokens;
        }
        int start = -1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == separator && start != -1) {
                tokens.add(s.substring(start, i));
                start = -1;
            } else if (s.charAt(i) != separator && start == -1) {
                start = i;
            }
        }
        if (start != -1) {
            tokens.add(s.substring(start, s.length()));
        }
        return tokens;
    }
}
