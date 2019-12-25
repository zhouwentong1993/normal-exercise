package com.wentong.nio;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

// Selector 的基本用法
public class SelectorTest {
    public static void main(String[] args) throws Exception {
        try (Selector selector = Selector.open();
             ServerSocketChannel socketChannel = ServerSocketChannel.open()) {
            socketChannel.bind(new InetSocketAddress(9090));
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
            // 这个方法是阻塞的，直到有连接建立或者 wakeup 调用或者线程中断。
            int count = selector.select();
            if (count > 0) {
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    if (selectionKey.isAcceptable()) {
                        SocketChannel accept = socketChannel.accept();
                        accept.register(selector, SelectionKey.OP_READ);
                    }
                }
            }
        }
    }
}
