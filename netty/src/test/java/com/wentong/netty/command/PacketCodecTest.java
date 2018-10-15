package com.wentong.netty.command;

import com.wentong.study.protocol.command.LoginRequestPacket;
import com.wentong.study.protocol.command.Packet;
import com.wentong.study.protocol.command.PacketCodec;
import com.wentong.study.serialize.Serializer;
import com.wentong.study.serialize.impl.JsonSerializer;
import io.netty.buffer.ByteBuf;
import org.junit.Assert;
import org.junit.Test;

public class PacketCodecTest {

    @Test
    public void encode() {

        Serializer serializer = new JsonSerializer();
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

        loginRequestPacket.setVersion(((byte) 1));
        loginRequestPacket.setUserId(123);
        loginRequestPacket.setUsername("zhangsan");
        loginRequestPacket.setPassword("password");

        PacketCodec packetCodeC = new PacketCodec();
        ByteBuf byteBuf = packetCodeC.encode(loginRequestPacket);
        Packet decodedPacket = packetCodeC.decode(byteBuf);

        Assert.assertArrayEquals(serializer.serializer(loginRequestPacket), serializer.serializer(decodedPacket));
    }
}
