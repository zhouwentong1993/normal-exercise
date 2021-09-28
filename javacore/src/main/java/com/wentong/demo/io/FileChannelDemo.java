package com.wentong.demo.io;

import java.io.ByteArrayOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public class FileChannelDemo {

    public static void main(String[] args) throws Exception {
        try (RandomAccessFile reader = new RandomAccessFile("/Users/finup123/IdeaProjects/normal-exercise/javacore/src/test/resources/test_read.in", "rw");
             FileChannel channel = reader.getChannel();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            int bufferSize = 1024;
            if (bufferSize > channel.size()) {
                bufferSize = (int) channel.size();
            }
            ByteBuffer buff = ByteBuffer.allocate(bufferSize);

            while (channel.read(buff) > 0) {
                out.write(buff.array(), 0, buff.position());
                buff.clear();
            }

            String fileContent = new String(out.toByteArray(), StandardCharsets.UTF_8);
            System.out.println(fileContent);
            channel.read(buff);

            ByteBuffer writeBuf = ByteBuffer.wrap(("Write Hello world!" + System.lineSeparator()).getBytes(StandardCharsets.UTF_8));
            channel.write(writeBuf);
        }
    }

}
