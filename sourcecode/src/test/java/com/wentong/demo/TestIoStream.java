package com.wentong.demo;

import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.util.concurrent.TimeUnit;

/**
 * 测试 io Stream
 */
public class TestIoStream {


    /**
     * 当对文件进行操作时，如果有其他的进程也对文件内容操作，此时输出的内容还是原先的。
     */
    @Test
    public void testReadFileWhileWriteToThisFile() throws Exception{
        File file = new File("/Users/finup123/Desktop/code/c/input.c");
        FileReader fileReader = new FileReader(file);
        int s ;
        while ((s =fileReader.read()) != -1) {
            TimeUnit.SECONDS.sleep(1);
            System.out.print((char) s);
        }
    }

}
