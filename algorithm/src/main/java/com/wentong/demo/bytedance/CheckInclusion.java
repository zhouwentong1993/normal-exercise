package com.wentong.demo.bytedance;

public class CheckInclusion {

    public static void main(String[] args) {
        boolean b = checkInclusion("ab", "eidboaoo");
        System.out.println(b);
    }

    public static boolean checkInclusion(String s1, String s2) {
        return s2.contains(s1) || s2.contains(new StringBuilder(s1).reverse().toString());
    }

}
