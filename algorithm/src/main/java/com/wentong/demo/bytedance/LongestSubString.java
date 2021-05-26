package com.wentong.demo.bytedance;

import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Map;

public class LongestSubString {
    public static void main(String[] args) {
        int count = lengthOfLongestSubstring("uqinntq");
        System.out.println(count);
    }

    public static int lengthOfLongestSubstring(String s) {
        int max = 0;
        int left = 0;
        int length = s.length();
        Map<Character,Integer> map = new HashMap<>(length);
        for (int i = 0; i < length; i++) {
            char key = s.charAt(i);
            if (map.containsKey(key)) {
                left = Math.max(left, map.get(key) + 1);
            }
            map.put(key, i);
            max = Math.max(max, i - left + 1);
        }
        return max;
    }
}
