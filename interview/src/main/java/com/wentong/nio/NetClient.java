package com.wentong.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

// 可以连接上 io.c 提供的网络服务
public class NetClient {
    public static void main(String[] args) {
        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 8001));

            socketChannel.configureBlocking(true);

            ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
            writeBuffer.put("hello world!".getBytes());
            writeBuffer.flip();

            socketChannel.write(writeBuffer);
            socketChannel.close();
        } catch (IOException e) {
        }
    }
}
