package com.wentong.demo.bytedance;

import java.util.Arrays;

public class StringMulti {

    public static void main(String[] args) {
        System.out.println(multiply("1234", "567"));
    }

    public static String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        int len1 = num1.length();
        int len2 = num2.length();
        int[] arr = new int[len1 + len2];
        for (int i = len1 - 1; i >= 0; i--) {
            int x = num1.charAt(i) - '0';
            for (int j = len2 - 1; j >= 0; j--) {
                int y = num2.charAt(j) - '0';
                arr[i + j + 1] += x * y;
            }
        }

        for (int i = len1 + len2 - 1; i > 0; i--) {
            arr[i - 1] += arr[i] / 10;
            arr[i] = arr[i] % 10;
        }

        StringBuilder sb = new StringBuilder(arr.length);
        for (int i = 0; i < arr.length; i++) {
            if (i == 0 && arr[0] == 0) {
                continue;
            }
            sb.append(arr[i]);
        }
        return sb.toString();
    }

}
