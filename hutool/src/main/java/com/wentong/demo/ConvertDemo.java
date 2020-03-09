package com.wentong.demo;

import cn.hutool.core.convert.Convert;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;

public class ConvertDemo {
    public static void main(String[] args) {
        String tes = Convert.toStr(new Object(), "tes");
        System.out.println(tes);
        // 转换数组
        int[] arr = {1, 2, 3, 4, 56};
        String[] strings = Convert.toStrArray(arr);
        String s = Arrays.toString(strings);
        System.out.println(s);

        // 时间转换
        String dateStr = "2020-02-02";
        Date date = Convert.toDate(dateStr);
        System.out.println(date);

        // 转换成十六进制
        String str = "测试一个字符串 abc";
        String hex = Convert.toHex(str, StandardCharsets.UTF_8);
        System.out.println(hex);
        System.out.println(Convert.hexToStr(hex, StandardCharsets.UTF_8));

        // 金额转换
        double a = 12789213.123;
        System.out.println(Convert.digitToChinese(a));
    }
}
