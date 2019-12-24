package com.wentong.nio;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class GroupChatServer implements Closeable {

    private final int port = 9090;
    private ServerSocketChannel serverSocketChannel;
    private Selector selector;

    public GroupChatServer() throws Exception {
        selector = Selector.open();

        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(port));
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    public void start() throws Exception {
        while (true) {
            int count = selector.select();
            if (count > 0) {
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    if (selectionKey.isAcceptable()) {
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                        System.out.println("客户端:" + getClientInfo((InetSocketAddress) socketChannel.getRemoteAddress()) + " 上线了");
                    } else if (selectionKey.isReadable()) {
                        SocketChannel channel = (SocketChannel) selectionKey.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        StringBuilder message = new StringBuilder();
                        while (channel.read(buffer) > 0) {
                            String channelInputMessage = new String(buffer.array());
                            message.append(channelInputMessage.trim());
                            buffer.clear();
                        }
                        String messageWithClientName = getClientInfo((InetSocketAddress) channel.getRemoteAddress()) + "说：" + message;
                        System.out.println(messageWithClientName);
                        Set<SelectionKey> keys = selector.keys();
                        for (SelectionKey key : keys) {
                            Channel target = key.channel();
                            if (target instanceof SocketChannel && target != channel) {
                                SocketChannel dest = (SocketChannel) target;
                                dest.write(ByteBuffer.wrap(messageWithClientName.getBytes()));
                            }
                        }
                    }
                    // 删除这个事件，防止误操作
                    iterator.remove();
                }
            }
        }
    }

    private String getClientInfo(InetSocketAddress inetSocketAddress) {
        return inetSocketAddress.getHostString() + ":" + inetSocketAddress.getPort();
    }

    public void stop() throws Exception {
        serverSocketChannel.close();
        selector.close();
    }

    public static void main(String[] args) {
        try {
            try (GroupChatServer groupChatServer = new GroupChatServer()) {
                System.out.println("server 启动~");
                groupChatServer.start();
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Override
    public void close() throws IOException {
        try {
            this.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
