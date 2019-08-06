package com.example.learning.java;

public class Test35 {
    public static void main(String[] args) {
        Utils.println(new Solution5().romanToInt("MMMCMXCIX"));
    }
}

class Solution5 {
    public int romanToInt(String s) {
        int sum = 0;

        int[] a = new int[26];
        a['I' - 'A'] = 1;
        a['V' - 'A'] = 5;
        a['X' - 'A'] = 10;
        a['L' - 'A'] = 50;
        a['C' - 'A'] = 100;
        a['D' - 'A'] = 500;
        a['M' - 'A'] = 1000;

        char pre = 'A';
        for (char c : s.toCharArray()) {
            if (a[c - 'A'] > a[pre - 'A']) {
                sum -= 2 * a[pre - 'A'];
            }
            sum += a[c - 'A'];
            pre = c;
        }

        return sum;
    }
}