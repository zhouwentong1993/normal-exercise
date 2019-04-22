package com.wentong.demo;

import org.junit.Test;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class TestByteBuffer {

    @Test
    public void testReadAndWrite() throws Exception {
        RandomAccessFile file = new RandomAccessFile("/Users/finup123/IdeaProjects/exercise/sourcecode/src/test/java/com/wentong/demo/TestByteBuffer.java", "rw");
        FileChannel channel = file.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        int read = channel.read(byteBuffer);
        System.out.println("after read");
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.position());
//        System.err.println(read);
        while (read != -1) {
            byteBuffer.flip();
            System.out.println("after flip");
            System.out.println(byteBuffer.limit());
            System.out.println(byteBuffer.position());
            while (byteBuffer.hasRemaining()) {
//                System.out.print((char) byteBuffer.get());
                byteBuffer.get();
            }
            byteBuffer.clear();
            System.out.println("after clear");
            System.out.println(byteBuffer.limit());
            System.out.println(byteBuffer.position());
            read = channel.read(byteBuffer);
//            System.err.println(read);
        }

        file.close();
    }

    @Test
    public void testWriteDataToBuffer() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put((byte) 123);
        // 转换模式
        byteBuffer.flip();
        System.out.println(byteBuffer.get());
    }

}
