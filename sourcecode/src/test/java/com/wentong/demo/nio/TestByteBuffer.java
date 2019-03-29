package com.wentong.demo.nio;

import java.nio.ByteBuffer;

public class TestByteBuffer {

    public static void main(String[] args) {
        ByteBuffer allocate = ByteBuffer.allocate(10);
        for (int i = 0; i < allocate.capacity(); i++) {
            allocate.put((byte) i);
        }

        allocate.position(3);
        allocate.limit(7);

        ByteBuffer slice = allocate.slice();
        System.out.println(slice);
        System.out.println(allocate);
    }

}
