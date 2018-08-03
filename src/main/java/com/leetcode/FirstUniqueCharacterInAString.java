package com.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.

Examples:

s = "leetcode"
return 0.

s = "loveleetcode",
return 2.
Note: You may assume the string contain only lowercase letters.
 */
public class FirstUniqueCharacterInAString {

    public static void main(String[] args) {
        FirstUniqueCharacterInAString woker = new FirstUniqueCharacterInAString();
        System.out.println(woker.firstUniqChar("leetcode"));
        Map<Integer, Integer> map = new HashMap<>();
    }

    public int firstUniqChar(String s) {
        Set<Character> meet = new HashSet<>();
        Set<Character> mult = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            Character ch = s.charAt(i);
            if (meet.contains(ch)) {
                mult.add(ch);
            } else {
                queue.add(i);
                meet.add(ch);
            }
        }
        while (!queue.isEmpty()) {
            int index = queue.poll();
            if (!mult.contains(s.charAt(index))) {
                return index;
            }
        }
        return -1;
    }
}
