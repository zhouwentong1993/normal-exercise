package com.wentong.study.client;

import com.wentong.study.protocol.command.PacketCodec;
import com.wentong.study.protocol.command.request.MessageRequestPacket;
import com.wentong.study.utils.LoginUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhouwentong on 2018/6/20.
 */
public class NettyClient {

    private static String HOST = "127.0.0.1";
    private static final int PORT = 8888;
    private static final int MAX_RETRY_COUNT = 6;

    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();
        bootstrap.group(nioEventLoopGroup)
                .channel(NioSocketChannel.class)
                // 指定超时时间
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel channel) {
                        channel.pipeline().addLast(new ClientHandler());
                    }
                });

        connect(bootstrap, MAX_RETRY_COUNT);
    }

    private static void connect(Bootstrap bootstrap, int retryLimit) {
        bootstrap.connect(HOST, PORT).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println(new Date() + ":连接服务器成功");
                Channel channel = ((ChannelFuture) future).channel();
                startConsoleThread(channel);
            } else {
                if (retryLimit > 0) {
                    int order = MAX_RETRY_COUNT - retryLimit + 1;
                    System.err.println("第 " + order + "连接失败，正在重试 …");
                    // 采用指数避让
                    int wait = 1 << order;
                    bootstrap.group().schedule(() -> connect(bootstrap, retryLimit - 1), wait, TimeUnit.SECONDS);
                } else {
                    System.err.println("连接 " + MAX_RETRY_COUNT + " 失败，连接结束");
                    throw new IllegalStateException("连接失败");
                }
            }
        });
    }

    /**
     * console 通信
     */
    private static void startConsoleThread(Channel channel) {
        new Thread(() -> {
            while (!Thread.interrupted()) {
                if (LoginUtil.hasLogin(channel)) {
                    System.out.println("输入消息：");
                    Scanner scanner = new Scanner(System.in);
                    String line = scanner.next();
                    if (!Objects.equals(line, "byebye")) {
                        MessageRequestPacket messageRequestPacket = new MessageRequestPacket();
                        messageRequestPacket.setMessage(line);
                        ByteBuf encode = PacketCodec.INSTANCE.encode(channel.alloc(), messageRequestPacket);
                        channel.writeAndFlush(encode);
                    }
                }
            }


        }).start();
    }


}
