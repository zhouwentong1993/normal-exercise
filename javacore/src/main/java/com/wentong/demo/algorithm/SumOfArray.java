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

    public static void main(String[] args) {
        try {
            int i = 1 / 0;
        } finally {
            System.out.println("aa");
        }
        System.out.println("bb");
    }
}
