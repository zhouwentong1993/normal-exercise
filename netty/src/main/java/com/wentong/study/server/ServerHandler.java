package com.wentong.study.server;

import com.wentong.study.protocol.command.Packet;
import com.wentong.study.protocol.command.PacketCodec;
import com.wentong.study.protocol.command.request.LoginRequestPacket;
import com.wentong.study.protocol.command.request.MessageRequestPacket;
import com.wentong.study.protocol.command.response.LoginResponsePacket;
import com.wentong.study.protocol.command.response.MessageResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class ServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf byteBuf = (ByteBuf) msg;

        Packet packet = PacketCodec.INSTANCE.decode(byteBuf);
        if (packet instanceof LoginRequestPacket) {
            System.out.println("验证登录开始");
            LoginRequestPacket decode = (LoginRequestPacket) packet;
            int userId = decode.getUserId();
            String username = decode.getUsername();
            String password = decode.getPassword();

            if (isValidUser(username, password)) {
                LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
                loginResponsePacket.setSuccess(true);

                ByteBuf encode = PacketCodec.INSTANCE.encode(ctx.alloc(), loginResponsePacket);
                ctx.channel().writeAndFlush(encode);
            }
        } else if (packet instanceof MessageRequestPacket) {
            MessageRequestPacket decode = (MessageRequestPacket) packet;
            System.out.println("收到客户端数据：" + decode.getMessage());

            MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
            String reverse = StringUtils.reverse(decode.getMessage());
            System.out.println("回复信息：" + reverse);
            messageResponsePacket.setMessage(reverse);
            ByteBuf encode = PacketCodec.INSTANCE.encode(ctx.alloc(), messageResponsePacket);
            ctx.channel().writeAndFlush(encode);
        } else {
            throw new IllegalArgumentException("不识别的 class：" + packet);
        }
    }

    private boolean isValidUser(String username, String password) {
        return Objects.equals(username, "admin") && Objects.equals(password, "admin");
    }

}
