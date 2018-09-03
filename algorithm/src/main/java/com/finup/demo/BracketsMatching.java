package com.finup.demo;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.Stack;

/**
 * Created by zhouwentong on 2018/5/21.
 * 字符串匹配算法
 */
public class BracketsMatching {

    private static BiMap<String, String> biMap = HashBiMap.create();

    static {
        biMap.put("[", "]");
        biMap.put("(", ")");
        biMap.put("{", "}");
    }

    public static void main(String[] args) {
        System.out.println(match("(***)-[{-------}]")); //true
        System.out.println(match("(2+4)*a[5]")); //true
        System.out.println(match("({}[]]])")); //false
        System.out.println(match("())))")); //false
    }

    public static boolean match(String source) {
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < source.length(); i++) {
            String s = String.valueOf(source.charAt(i));
            if (biMap.containsKey(s)) {
                stack.push(s);
            } else if (biMap.containsValue(s)){
                if (biMap.get(biMap.inverse().get(s)).equals(s) && !stack.empty()) {
                    stack.pop();
                } else if (stack.empty()) {
                    return false;
                }

            }
        }
        return stack.empty();
    }
}
