package com.leetcode;

import java.util.HashMap;
import java.util.Map;

public class LongestCommonPrefixTwoWords {
    public static void main(String[] args) {
        LongestCommonPrefixTwoWords longestCommonPrefixTwoWords = new LongestCommonPrefixTwoWords();
        String[] words = {"flower", "flow", "flight"};
        System.out.println(longestCommonPrefixTwoWords.longestCommonPrefix(words));
    }

    public String longestCommonPrefix(String[] strs) {
        PrefixTree root = new PrefixTree();
        String maxPrefix = "";
        for (String word : strs) {
            String prefix = root.addWord(word);
            if (maxPrefix.length() < prefix.length()) {
                maxPrefix = prefix;
            }
        }
        return maxPrefix;
    }

    private static class PrefixTree {
        Character letter;
        Map<Character, PrefixTree> twigs = new HashMap<>();

        public PrefixTree() {
            this.letter = null;
        }

        public PrefixTree(Character letter) {
            this.letter = letter;
        }

        public String addWord(String word) {
            return addWord(word, 0);
        }

        private String addWord(String word, int curIndex) {
            String commonPrefix = "";
            char currChar = word.charAt(curIndex);
            boolean twigExistBefore = !this.addNewTwig(currChar);
            if (curIndex < word.length() - 1) {
                String prefix = twigs.get(currChar).addWord(word, curIndex + 1);
                if (twigExistBefore) {
                    commonPrefix = String.valueOf(currChar) + prefix;
                }
            }
            return commonPrefix;
        }

        public boolean addNewTwig(char ch) {
            if (twigs.containsKey(ch)) {
                return false;
            } else {
                twigs.put(ch, new PrefixTree(ch));
            }
            return true;
        }

    }
}
