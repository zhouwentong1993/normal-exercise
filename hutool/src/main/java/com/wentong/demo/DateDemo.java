package com.wentong.demo;

import cn.hutool.core.date.DateUtil;

import java.util.Date;

public class DateDemo {
    public static void main(String[] args) {
        String yyyyMMddHHmm = DateUtil.format(new Date(), "yyyyMMddHHmm");
        System.out.println(yyyyMMddHHmm);
    }
}
