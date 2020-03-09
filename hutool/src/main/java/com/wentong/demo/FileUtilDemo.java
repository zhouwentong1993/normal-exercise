package com.wentong.demo;

import cn.hutool.core.io.FileUtil;

import java.io.File;

/**
 * 针对 Hutool 中的 FileUtil 测试
 * https://www.hutool.cn/docs/#/core/IO/%E6%96%87%E4%BB%B6%E5%B7%A5%E5%85%B7%E7%B1%BB-FileUtil
 */
public class FileUtilDemo {

    public static void main(String[] args) {
        // 与 Linux 类似
        File[] ls = FileUtil.ls("/Users/finup123");
        for (File l : ls) {
//            System.out.println(l.getName());
        }
        // 复制
        FileUtil.copy(new File("/Users/finup123/Desktop/test"), new File("/Users/finup123/Desktop/t1"), true);
        File[] ls1 = FileUtil.ls("/Users/finup123/Desktop/t1/test");
        for (File l : ls1) {
            System.out.println(l.getName());
        }

        // 获取文件扩展名 Hutool 有两种方式，第一种是通过读取流文件，获取前几个字节的英文来判断。第二种是通过后缀名判断。
        System.out.println(FileUtil.getType(new File("/Users/finup123/Desktop/contract_center.sql")));
    }
}
