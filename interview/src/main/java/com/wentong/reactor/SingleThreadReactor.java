package com.wentong.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class SingleThreadReactor implements Runnable{

    final Selector selector;
    final ServerSocketChannel serverSocketChannel;

    public SingleThreadReactor(int port) throws Exception {
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(8090));
        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        selectionKey.attach(new Acceptor());
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                dispatch(iterator);
            }
        }
    }

    // 将请求分发
    private void dispatch(Iterator<SelectionKey> iterator) {
        SelectionKey next = iterator.next();
        Runnable runnable = (Runnable) next.attachment();
        if (runnable != null) {
            runnable.run();
        }
    }
    class Acceptor implements Runnable {

        @Override
        public void run() {
            try {
                SocketChannel socketChannel = serverSocketChannel.accept();
                if (socketChannel != null) {
                    new Handler(selector, socketChannel);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(1 << 2);
    }

    class Handler implements Runnable {
        private SocketChannel socketChannel;
        private SelectionKey selectionKey;

        public Handler(Selector selector, SocketChannel socketChannel) throws IOException {
            this.socketChannel = socketChannel;
            this.socketChannel.configureBlocking(false);
            // 注册 0 是为什么
            selectionKey = socketChannel.register(selector, 0);
            selectionKey.attach(this); // 将 Handler 绑定到 SelectionKey 上
            selectionKey.interestOps(SelectionKey.OP_READ);
            selector.wakeup();
        }

        @Override
        public void run() {

        }
    }

}
