package com.wentong.demo.algorithm;

public class SumOfArray {
    public int[] runningSum(int[] nums) {
        int[] result = new int[nums.length];
        int temp = nums[0];
        result[0] = temp;
        for (int i = 1; i < nums.length; i++) {
            result[i] = temp + nums[i];
            temp += nums[i];
        }
        return result;
    }
}
