package com.example.learning.java;

public class Test34 {
    public static void main(String[] args) {
        int[] nums = {9, 0, 1, 2, 3};

        Utils.println(new Solution4().search(nums, 0));
    }
}

class Solution4 {
    public int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        int start;
        int n;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            n = nums[mid];

            if (n == target) {
                return mid;
            }

            start = nums[low];

            if (n >= start) {
                if (start <= target && target < n) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                if (n < target & target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }
}