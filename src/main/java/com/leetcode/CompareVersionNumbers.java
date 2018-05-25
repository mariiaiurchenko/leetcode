package com.leetcode;

/**
 * Compare two version numbers version1 and version2.
 If version1 > version2 return 1; if version1 < version2 return -1;otherwise return 0.

 You may assume that the version strings are non-empty and contain only digits and the . character.
 The . character does not represent a decimal point and is used to separate number sequences.
 For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.

 Example 1:

 Input: version1 = "0.1", version2 = "1.1"
 Output: -1
 Example 2:

 Input: version1 = "1.0.1", version2 = "1"
 Output: 1
 Example 3:

 Input: version1 = "7.5.2.4", version2 = "7.5.3"
 Output: -1
 */
public class CompareVersionNumbers {

    private static final int VERSION_1 = 1;
    private static final int VERSION_2 = -1;
    private static final int NOT_COMPARABLE = 0;

    public static void main(String[] args) {
        CompareVersionNumbers compareVersionNumbers = new CompareVersionNumbers();
        System.out.println(compareVersionNumbers.compareVersion("1.1", "1.10") + "   = -1");
        System.out.println(compareVersionNumbers.compareVersion("1", "1.0") + "   = 0");
        System.out.println(compareVersionNumbers.compareVersion("1.2", "1.10") + "   = -1");
        System.out.println(compareVersionNumbers.compareVersion("1", "0") + "   = 1");
        System.out.println(compareVersionNumbers.compareVersion("0.1", "1.1") + "   = -1");
        System.out.println(compareVersionNumbers.compareVersion("1.0.1", "1") + "   = 1");
        System.out.println(compareVersionNumbers.compareVersion("7.5.2.4", "7.5.3") + "   = -1" );
        System.out.println(compareVersionNumbers.compareVersion("1", "01") + "   = 0");
    }

    public int compareVersion(String version1, String version2) {

        int i1 = 0;
        int i2 = 0;
        i1 = skipZero(version1, i1);
        i2 = skipZero(version2, i2);
        while (i1 < version1.length() && i2 < version2.length() ) {
            if (version1.charAt(i1) == version2.charAt(i2)) {
                if (version1.charAt(i1) == '.') {
                    i1++;
                    i2++;
                    i1 = skipZero(version1, i1);
                    i2 = skipZero(version2, i2);
                } else {
                    i1++;
                    i2++;
                }
            } else {
                int bigger = version1.charAt(i1) > version2.charAt(i2) ? VERSION_1 : VERSION_2;
                while (i1 < version1.length() && i2 < version2.length()
                    && version1.charAt(i1) != '.' && version2.charAt(i2) != '.') {
                  i1++;
                  i2++;
                }
                if ((i1 == version1.length() || version1.charAt(i1) == '.')
                    && (i2 == version2.length() || version2.charAt(i2) == '.')) {
                    return bigger;
                } else {
                }
                return i1 == version1.length() || version1.charAt(i1) == '.' ? VERSION_2 : VERSION_1;
            }

        }
        i1 = skipZeroAndDot(version1, i1);
        i2 = skipZeroAndDot(version2, i2);

        if (i1 < version1.length() || i2 < version2.length()) {
            return i1 < version1.length() ? VERSION_1 : VERSION_2;
        }

        return NOT_COMPARABLE;
    }

    private int skipZeroAndDot(String version, int i) {
        if (i<version.length() && version.charAt(i) == '.') {
            while (i < version.length() && (version.charAt(i) == '0' || version.charAt(i) == '.')) {
                i++;
            }
        }
        return i;
    }

    private int skipZero(String version1, int i1) {
        while (i1 < version1.length() && version1.charAt(i1) == '0') {
            i1++;
        }
        return i1;
    }
}
