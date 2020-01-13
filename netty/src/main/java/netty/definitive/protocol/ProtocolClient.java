package netty.definitive.protocol;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;
import netty.definitive.protocol.codec.MarshallingCodeCFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ProtocolClient {

    private static ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    private static final int MAX_RETRY_TIME = 10;

    private static AtomicInteger count = new AtomicInteger();

    public static void main(String[] args) throws Exception {
        connect();
    }

    private static void connect() throws Exception {
        try (EventLoopGroup group = new NioEventLoopGroup()) {

            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
                            ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
                            // Netty 内部的超时 Handler，通过构造函数传入的值，如果在 N 个时间单位内没有收到任何请求
                            // 该 Handler 就会抛出一个读超时异常。和下面的心跳检测一起用，可以达到目的。
                            ch.pipeline().addLast("readTimeoutHandler", new ReadTimeoutHandler(30));
                            ch.pipeline().addLast(new LoginAuthRequestHandler());
                            ch.pipeline().addLast(new HeartBeatRequestHandler());
                        }
                    });
            ChannelFuture channelFuture = bootstrap.connect("localhost", 9090).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            executor.execute(() -> {
                try {
                    if (count.getAndIncrement() <= MAX_RETRY_TIME) {
                        // 默认 5s 后重连，需要考虑两个方面：1. 是否采用指数退避算法。2. 是否限制重连次数
                        TimeUnit.SECONDS.sleep(5);
                        connect();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

}
