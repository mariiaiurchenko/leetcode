package com.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Amazon_retrieveMostFrequentlyUsedWords {

    List<String> retrieveMostFrequentlyUsedWords(String literatureText,
                                                 List<String> wordsToExclude) {
        Set<String> excludeSet = new HashSet<>(wordsToExclude);
        String[] words = literatureText.split(" ");
        HashMap<String, Integer> wordCount = new HashMap<>();
        int maxFrequent = 0;
        for (String word : words) {
            if (!excludeSet.contains(word)) {
                int frequent = wordCount.getOrDefault(word, 0);
                wordCount.put(word, frequent + 1);
                maxFrequent = Integer.max(maxFrequent, frequent + 1);
            }
        }
        List<String> result = new LinkedList<>();
        for (String word : wordCount.keySet()) {
            if (wordCount.get(word) == maxFrequent) {
                result.add(word);
            }
        }
        return result;
    }
}
