package execrise01;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by zhouwentong on 2018/6/20.
 * NIO 形式的 server
 */
public class NIOServer {
    public static void main(String[] args) throws Exception {
        Selector serverSelector = Selector.open();
        Selector clientSelector = Selector.open();

        new Thread(() -> {
            try {
                ServerSocketChannel listenerServer = ServerSocketChannel.open();
                listenerServer.socket().bind(new InetSocketAddress(8888));
                listenerServer.configureBlocking(false);
                listenerServer.register(serverSelector, SelectionKey.OP_ACCEPT);

                while (true) {
                    while (serverSelector.select(1) > 0) {
                        Set<SelectionKey> selectionKeys = serverSelector.selectedKeys();
                        Iterator<SelectionKey> iterator = selectionKeys.iterator();
                        while (iterator.hasNext()) {
                            SelectionKey next = iterator.next();
                            if (next.isAcceptable()) {
                                try {
                                    SocketChannel accept = ((ServerSocketChannel) next.channel()).accept();
                                    accept.configureBlocking(false);
                                    accept.register(clientSelector, SelectionKey.OP_READ);
                                } finally {
                                    iterator.remove();
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }).start();

        new Thread(() -> {
            try {
                while (true) {
                    if (clientSelector.select(1) > 0) {
                        Set<SelectionKey> selectionKeys = clientSelector.selectedKeys();
                        Iterator<SelectionKey> iterator = selectionKeys.iterator();
                        while (iterator.hasNext()) {
                            SelectionKey next = iterator.next();
                            try {

                                if (next.isReadable()) {
                                    SocketChannel channel = (SocketChannel) next.channel();
                                    // 工厂，好
                                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                                    channel.read(byteBuffer);
                                    byteBuffer.flip();
                                    String s = Charset.defaultCharset().newDecoder().decode(byteBuffer).toString();
                                    System.out.println(s);
                                    System.out.println();
                                }
                            } finally {
                                iterator.remove();
                                next.interestOps(SelectionKey.OP_READ);
                            }
                        }
                    }
                }
            } catch (Exception e) {
            }
        }).start();

    }
}
