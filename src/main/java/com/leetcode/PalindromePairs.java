package com.leetcode;

import java.util.LinkedList;
import java.util.List;

/*
Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.

Example 1:
Given words = ["bat", "tab", "cat"]
Return [[0, 1], [1, 0]]
The palindromes are ["battab", "tabbat"]
Example 2:
Given words = ["abcd", "dcba", "lls", "s", "sssll"]
Return [[0, 1], [1, 0], [3, 2], [2, 4]]
The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]

["a",""] -> [[0,1],[1,0]]
["a","abc","aba",""] -> [[0,3],[3,0],[2,3],[3,2]]

Credits:
Special thanks to @dietpepsi for adding this problem and creating all test cases.
 */
public class PalindromePairs {

    public static void main(String[] args) {
        String[] words = new String[]{"a", "abc", "aba", ""};
        List<List<Integer>> pairs = palindromePairs(words);
        for (List<Integer> pair : pairs) {
            System.out.println("[" + pair.get(0) + ", "+ pair.get(1)+ "]");
        }
    }

    public static List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> result = new LinkedList<>();
        TryTree root = initTryTree(words);

        for (int wordIndex = 0; wordIndex < words.length; wordIndex++) {
            List<Integer> indexes = root.findMatch(words[wordIndex]);
            result.addAll(makePairs(wordIndex, indexes));
        }

        return result;
    }

    private static List<List<Integer>> makePairs(int wordIndex, List<Integer> indexes) {
        List<List<Integer>> pairs = new LinkedList<>();
        for (Integer i : indexes) {
            if (i != wordIndex) {
                List<Integer> pair = new LinkedList<>();
                pair.add(wordIndex);
                pair.add(i);
                pairs.add(pair);
            }
        }
        return pairs;
    }

    private static TryTree initTryTree(String[] words) {
        TryTree tryTree = new TryTree();
        for (int i = 0; i < words.length; i++) {
            String reverted = revertWord(words[i]);
            tryTree.addWord(reverted, i);
        }
        return tryTree;
    }

    private static String revertWord(String word) {
        StringBuilder reverted = new StringBuilder();
        for (int i = word.length() - 1; i >= 0 ; i--) {
            reverted.append(word.charAt(i));
        }
        return reverted.toString();
    }

    private static class TryTree {
        private static final int NUM_LETTER = 26;
        TryTree[] nodes = new TryTree[NUM_LETTER];
        List<Integer> palindromicSuffix = new LinkedList<>();
        Integer wordEndIndex = -1;

        public List<Integer> findMatch(String word) {
            TryTree curNode = this;
            List<Integer> res = new LinkedList<>();
            if (this.isWordEnd() && isPalindromicSuffix(word, 0)) {
                res.add(curNode.wordEndIndex);
            }
            for (int i = 0 ; i < word.length() && curNode != null; i++) {
                char ch = word.charAt(i);
                curNode = curNode.getCharNode(ch);
                if (curNode != null && curNode.isWordEnd() && isPalindromicSuffix(word, i+1)) {
                    res.add(curNode.wordEndIndex);
                }
            }
            if (curNode != null) {
                res.addAll(curNode.palindromicSuffix);
            }

            return res;
        }

        private boolean isWordEnd() {
            return wordEndIndex != -1;
        }

        private boolean hasCharNode(char ch) {
            return nodes[ch - 'a'] != null;
        }

        private TryTree getCharNode(char ch) {
            return nodes[ch - 'a'];
        }


        private void addWord(String word,  int wordIndex) {
            TryTree curNode = this;
            if (isPalindromicSuffix(word, 0)) {
                curNode.palindromicSuffix.add(wordIndex);
            }
            for (int i = 0; i < word.length(); i++) {
                int charIndex = word.charAt(i) - 'a';
                if (!curNode.hasCharNode(word.charAt(i))) {
                    curNode.nodes[charIndex] = new TryTree();
                }
                curNode = curNode.nodes[charIndex];
                if (i != word.length() - 1 && isPalindromicSuffix(word, i + 1)) {
                    curNode.palindromicSuffix.add(wordIndex);
                }
            }
            curNode.wordEndIndex = wordIndex;
        }

        private boolean isPalindromicSuffix(String word, int i) {
            int begin = i;
            int end = word.length() - 1;
            while (begin < end) {
                if (word.charAt(begin) != word.charAt(end)) {
                    return false;
                }
                begin++;
                end--;
            }
            return true;
        }
    }
}
