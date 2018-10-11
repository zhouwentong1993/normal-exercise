package com.wentong.study.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

public class FirstClientHandler extends ChannelInboundHandlerAdapter {

    // 当客户端建立链接后调用
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println(new Date() + ": 客户端写出数据");

        ByteBuf buffer = ctx.alloc().buffer();
        byte[] bytes = "你好，周文童".getBytes(Charset.forName("utf-8"));
        buffer.writeBytes(bytes);

        ctx.channel().writeAndFlush(buffer);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        String s = byteBuf.toString(Charset.forName("utf-8"));
        System.out.println("客户端收到响应：" + s);
        super.channelRead(ctx, msg);
    }
}
