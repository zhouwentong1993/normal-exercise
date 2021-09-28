package com.wentong.demo.io;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public class FileChannelDemo1 {

    public static void main(String[] args) throws Exception {
        try (RandomAccessFile reader = new RandomAccessFile("/Users/finup123/IdeaProjects/normal-exercise/javacore/src/main/java/com/wentong/demo/io/io.txt", "rw");
             FileChannel channel = reader.getChannel()) {

            System.out.println(channel.position());
            int bufferSize = 1024;
            if (bufferSize > channel.size()) {
                bufferSize = (int) channel.size();
            }
            ByteBuffer buff = ByteBuffer.allocate(bufferSize);
            System.out.println(buff);
            // 把数据读入 ByteBuffer 中。此时 channel 的 position 移动到 buffer 的位置。
            channel.read(buff);
            System.out.println(channel.position());
            System.out.println(buff);
            ByteBuffer writeBuf = ByteBuffer.wrap(("Write Hello world!" + System.lineSeparator()).getBytes(StandardCharsets.UTF_8));
            channel.write(writeBuf, 20);
        }
    }

}
