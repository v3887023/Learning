package com.example.learning.java;

public class Test36 {
    public static void main(String[] args) {
        Utils.println(new Solution6().intToRoman(3999));
    }
}

class Solution6 {
    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();

        int[] a = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        int length = a.length;
        String[] b = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        for (int i = 0; i < length; i++) {
            while (num >= a[i]) {
                sb.append(b[i]);
                num -= a[i];
            }
        }

        return sb.toString();
    }
}