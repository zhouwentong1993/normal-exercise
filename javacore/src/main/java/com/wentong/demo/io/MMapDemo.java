package com.wentong.demo.io;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MMapDemo {

    private static final int TEN_MB = 1024 * 1024 * 10;

    public static void main(String[] args) throws Exception {
        try (RandomAccessFile raf = new RandomAccessFile("/Users/finup123/1.pdf", "rw")) {
            MappedByteBuffer mappedByteBuffer = raf.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, TEN_MB);
            byte[] bytes = new byte[4096];
            mappedByteBuffer.put(bytes);
        }
    }

}
