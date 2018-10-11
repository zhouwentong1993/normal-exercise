package com.wentong.study.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

public class FirstServerHandler extends ChannelInboundHandlerAdapter {

    // 当开始读取数据时触发
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println("开始读取数据");
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println(byteBuf.toString(Charset.forName("utf-8")));
        System.out.println("服务端开始写出数据");

        byte[] bytes = "你好，这是服务器".getBytes(Charset.forName("utf-8"));
        ByteBuf byteBuf1 = ctx.alloc().buffer().writeBytes(bytes);
        ctx.writeAndFlush(byteBuf1);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("读取数据完毕");
        super.channelReadComplete(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("FirstServerHandler.channelActive");
        super.channelActive(ctx);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("FirstServerHandler.channelRegistered");
        super.channelRegistered(ctx);
    }
}
