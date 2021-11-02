package com.wentong.demo;

public class Test {

    // abcdefg  mnbcdrst
    public static void main(String[] args) {
        System.out.println(longestSubString("abcdefg", "mnbcdrst"));
    }

    private static String longestSubString(String s1, String s2) {
        String[][] dp = new String[s1.length()][s2.length()];
        char c11 = s1.charAt(0);
        char c22 = s2.charAt(0);
        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            dp[i][0] = (c == c22) ? String.valueOf(c) : "";
        }

        for (int i = 0; i < s2.length(); i++) {
            char c = s2.charAt(i);
            dp[0][i] = (c == c11) ? String.valueOf(c) : "";
        }

        for (int i = 1; i < s1.length(); i++) {
            for (int j = 1; j < s2.length(); j++) {
                char c1 = s1.charAt(i);
                char c2 = s2.charAt(j);
                if (c1 == c2) {
                    dp[i][j] = dp[i][j - 1] + c1;
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[s1.length() - 1][s2.length() - 1];
    }

}
