package com.example.learning.java;

public class Test24 {
    public static void main(String[] args) {
        Utils.println(isPalindrome("abcba"));
    }

    private static boolean isPalindrome(String s) {
        int length = s.length();

        int start = 0;
        int end = length - 1;
        char c1 = ' ';
        char c2 = ' ';
        while (start < end) {
            while (start < length && !isAlphanumeric(c1 = s.charAt(start))) {
                start++;
            }

            if (start == length && end == length - 1) {
                return true;
            }

            while (end >= 0 && !isAlphanumeric(c2 = s.charAt(end))) {
                end--;
            }

            if (!equalsIgnoreCase(c1, c2)) {
                return false;
            }

            start++;
            end--;
        }

        return true;
    }

    private static boolean isAlphanumeric(char c) {
        return Character.isAlphabetic(c) || isNumeric(c);
    }

    private static boolean isNumeric(char c) {
        return c >= '0' && c <= '9';
    }

    private static boolean equalsIgnoreCase(char c1, char c2) {
        boolean numericC1 = isNumeric(c1);
        boolean numericC2 = isNumeric(c2);

        if (numericC1 && numericC2) {
            return c1 == c2;
        }

        if (numericC1 || numericC2) {
            return false;
        }

        return c1 == c2 || c1 - 'A' == c2 - 'a' || c1 - 'a' == c2 - 'A';
    }

    static class A {

    }
}
