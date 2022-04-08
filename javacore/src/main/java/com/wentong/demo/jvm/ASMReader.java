package com.wentong.demo.jvm;

import org.apache.commons.io.FileUtils;

import java.io.File;

public class ASMReader {

    public static void main(String[] args) throws Exception {
        byte[] bytes = FileUtils.readFileToByteArray(new File("/Users/renmai/IdeaProjects/normal-exercise/javacore/target/classes/com/wentong/demo/jvm/Counter.class"));

    }

}
