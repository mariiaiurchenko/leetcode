package com.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/*
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Note that an empty string is also considered valid.

Example 1:

Input: "()"
Output: true
Example 2:

Input: "()[]{}"
Output: true
Example 3:

Input: "(]"
Output: false
Example 4:

Input: "([)]"
Output: false
Example 5:

Input: "{[]}"
Output: true
 */
public class ValidParentheses {

    private static Map<Character, Character> bracketsMap = createBracketsMap();

    public static void main(String[] args) {
        ValidParentheses validParentheses = new ValidParentheses();
        validParentheses.isValid("{[]}");
    }

    public boolean isValid(String s) {
        LinkedList<Character> openBrackets = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char bracket = s.charAt(i);
            if (bracketsMap.containsKey(bracket)) {
                openBrackets.push(bracket);
            } else {
                if (openBrackets.isEmpty() || !bracketsMap.get(openBrackets.poll()).equals(bracket)) {
                    return false;
                }
            }
        }

        return openBrackets.isEmpty() ? true : false;
    }

    private static Map<Character, Character> createBracketsMap() {
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
        return map;
    }
}
