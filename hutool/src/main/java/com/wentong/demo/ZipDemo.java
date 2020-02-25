package com.wentong.demo;

import cn.hutool.core.util.ZipUtil;
import cn.hutool.crypto.digest.DigestUtil;

import java.io.File;

public class ZipDemo {

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10; i++) {

            File file = new File("/Users/finup123/Desktop/压缩/" + i + ".zip");
            long start = System.currentTimeMillis();
            File zip = ZipUtil.zip(file, true, new File("/Users/finup123/Desktop/test"));
            String s = DigestUtil.sha256Hex(zip);
            System.out.println(s);
            System.out.println((System.currentTimeMillis() - start));
        }

    }

}

