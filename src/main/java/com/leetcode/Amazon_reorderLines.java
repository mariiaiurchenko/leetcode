package com.leetcode;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Amazon_reorderLines {

    public List<String> reorderLines(int logFileSize, List<String> logfile) {

        List<String> numLineList = new LinkedList<>();
        List<String> letterLineList = new LinkedList<>();
        for (String line : logfile) {
            String[] token = line.split(" ");
            if (Character.isLetter(token[1].charAt(0))) {
                letterLineList.add(line);
            } else {
                numLineList.add(line);
            }
        }
        Collections.sort(letterLineList, new LineComparator());
        letterLineList.addAll(numLineList);
        return letterLineList;
    }

    private class LineComparator implements Comparator<String> {

        @Override
        public int compare(String line1, String line2) {
            String identifier1 = line1.substring(0, line1.indexOf(" "));
            String word1 = line1.substring(line1.indexOf(" ") + 1);

            String identifier2 = line2.substring(0, line2.indexOf(" "));
            String word2 = line2.substring(line2.indexOf(" ") + 1);

            int compareWords = word1.compareToIgnoreCase(word2);

            return compareWords == 0 ? identifier1.compareToIgnoreCase(identifier2) : compareWords;
        }
    }

}
