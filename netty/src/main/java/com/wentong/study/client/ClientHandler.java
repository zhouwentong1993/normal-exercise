package com.wentong.study.client;

import com.wentong.study.protocol.command.Packet;
import com.wentong.study.protocol.command.request.LoginRequestPacket;
import com.wentong.study.protocol.command.PacketCodec;
import com.wentong.study.protocol.command.response.LoginResponsePacket;
import com.wentong.study.protocol.command.response.MessageResponsePacket;
import com.wentong.study.utils.LoginUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("开始登录");
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(1);
        loginRequestPacket.setUsername("admin");
        loginRequestPacket.setPassword("admin");

        ByteBuf encode = PacketCodec.INSTANCE.encode(ctx.alloc(), loginRequestPacket);
        ctx.channel().writeAndFlush(encode);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf byteBuf = (ByteBuf) msg;
        Packet packet = PacketCodec.INSTANCE.decode(byteBuf);
        if (packet instanceof LoginResponsePacket) {
            LoginResponsePacket loginResponsePacket = (LoginResponsePacket) packet;
            boolean success = loginResponsePacket.isSuccess();
            if (success) {
                LoginUtil.login(ctx.channel());
                System.out.println("登录成功");
            }
        } else if (packet instanceof MessageResponsePacket) {
            MessageResponsePacket messageResponsePacket = (MessageResponsePacket) packet;
            String message = messageResponsePacket.getMessage();
            System.out.println("收到回执：" + message);
        }
    }
}
