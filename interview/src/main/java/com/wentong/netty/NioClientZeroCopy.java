package com.wentong.netty;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * 零拷贝
 * 发送的字节数：25531541耗时：48ms
 */
public class NioClientZeroCopy {
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        try (FileChannel channel = new FileInputStream("1.pdf").getChannel();
             SocketChannel socketChannel = SocketChannel.open()) {
            socketChannel.connect(new InetSocketAddress("localhost", 8999));
            long transferCount = channel.transferTo(0, channel.size(), socketChannel);
            System.out.println("发送的字节数：" + transferCount + "耗时：" + (System.currentTimeMillis() - start) + "ms");
        }
    }
}
