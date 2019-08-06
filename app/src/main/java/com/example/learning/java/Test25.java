package com.example.learning.java;

public class Test25 {
    public static void main(String[] args) {
        int[] nums = new int[]{9, 5, 4, 0, 9, 1, 7, 6, 5, 1, 100};
        int target = 0;
        Utils.println(checkSubarraySum(nums, target));
    }

    private static boolean checkSubarraySum(int[] nums, int k) {
        int sum = nums[0];

        int length = nums.length;
        int start = 0;
        int count = 1;
        for (int i = 1; i < length; ) {
            if (sum == k && count > 0) {
                break;
            }

            sum += nums[i];

            if (sum < k) {
                count++;
                i++;
            }

            while (sum > k && start < length) {
                sum -= nums[start];
                count--;
                start++;
            }
        }

        if (sum == k) {
            return true;
        }

        return false;
    }
}
