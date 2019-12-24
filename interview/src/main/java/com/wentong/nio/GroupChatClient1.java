package com.wentong.nio;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class GroupChatClient1 implements Closeable {

    private static SocketChannel socketChannel;
    private static Selector selector;
    private static final String HOST = "localhost";
    private static final int PORT = 9090;

    public GroupChatClient1() throws Exception {
        selector = Selector.open();
        socketChannel = SocketChannel.open(new InetSocketAddress(HOST, PORT));
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);

    }

    public static void main(String[] args) throws Exception {
        try (GroupChatClient1 groupChatClient = new GroupChatClient1()) {
            System.out.println("客户端启动~");
            new Thread(GroupChatClient1::readInfoFromServer).start();
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                write(scanner.nextLine());
            }
        }
    }

    private static void write(String message) throws IOException {
        socketChannel.write(ByteBuffer.wrap(message.getBytes()));
        System.out.println("客户端发送：" + message);
    }

    private static void readInfoFromServer() {
        while (true) {
            try {
                int count = selector.select();
                if (count > 0) {
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        if (key.isReadable()) {
                            SocketChannel channel = (SocketChannel) key.channel();
                            ByteBuffer buffer = ByteBuffer.allocate(1024);
                            StringBuilder message = new StringBuilder();
                            while (channel.read(buffer) > 0) {
                                message.append(new String(buffer.array()).trim());
                            }
                            System.out.println("收到消息：" + message);
                        }
                        iterator.remove();
                    }
                } else {
                    System.out.println("没消息");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            sleep(2);
        }

    }

    private static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws IOException {
        socketChannel.close();
        selector.close();
    }
}
