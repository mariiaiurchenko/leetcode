package com.leetcode;

public class ztaFunction {
    public static int[] ztaFunction(String s, String p) {
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
}
