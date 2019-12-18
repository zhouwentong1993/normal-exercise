package com.wentong.netty;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NioServerZeroCopy {
    public static void main(String[] args) throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket socket = serverSocketChannel.socket();
        socket.bind(new InetSocketAddress(8999));

        ByteBuffer byteBuffer = ByteBuffer.allocate(4096);

        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            System.out.println("lianshang");
            while (socketChannel.read(byteBuffer) != -1) {
                // 倒带
                byteBuffer.rewind();
            }
            System.out.println("ok!");
        }
    }
}
