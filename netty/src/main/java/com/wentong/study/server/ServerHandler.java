package com.wentong.study.server;

import com.wentong.study.protocol.command.Packet;
import com.wentong.study.protocol.command.request.LoginRequestPacket;
import com.wentong.study.protocol.command.PacketCodec;
import com.wentong.study.protocol.command.response.LoginResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println("验证登录开始");
        ByteBuf byteBuf = (ByteBuf) msg;

        Packet packet = PacketCodec.INSTANCE.decode(byteBuf);
        if (packet instanceof LoginRequestPacket) {
            LoginRequestPacket decode = (LoginRequestPacket) packet;
            int userId = decode.getUserId();
            String username = decode.getUsername();
            String password = decode.getPassword();

            System.out.println("userId:" + userId + " username:" + username + " password:" + password);

            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
            loginResponsePacket.setSuccess(true);

            ByteBuf encode = PacketCodec.INSTANCE.encode(ctx.alloc(), loginResponsePacket);
            ctx.channel().writeAndFlush(encode);
        } else {
            throw new IllegalArgumentException("不识别的 class：" + packet);
        }
    }

}
