package com.wentong.nio;

import java.nio.ByteBuffer;

public class BufferTest {
    static ByteBuffer buffer = ByteBuffer.allocate(1024);

    public static void main(String[] args) {

        printBufferInfo();

        buffer.put("hello".getBytes());
        printBufferInfo();
        buffer.flip();
        printBufferInfo();
        byte[] bytes = new byte[buffer.limit()];
        System.out.println(buffer.get(bytes));
        printBufferInfo();
        buffer.flip();
        printBufferInfo();
        buffer.put("hello".getBytes());
        printBufferInfo();

    }

    private static void printBufferInfo() {
        System.out.println("capacity:" + buffer.capacity());
        System.out.println("limit:" + buffer.limit());
        System.out.println("position:" + buffer.position());
        System.out.println("mark:" + buffer.mark());
        System.out.println();
    }

}
