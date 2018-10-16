package com.wentong.netty.command;

public class PacketCodecTest {

//    @Test
//    public void encode() {
//
//        Serializer serializer = new JsonSerializer();
//        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
//
//        loginRequestPacket.setVersion(((byte) 1));
//        loginRequestPacket.setUserId(123);
//        loginRequestPacket.setUsername("zhangsan");
//        loginRequestPacket.setPassword("password");
//
//        PacketCodec packetCodeC = new PacketCodec();
//        ByteBuf byteBuf = packetCodeC.encode(loginRequestPacket);
//        Packet decodedPacket = packetCodeC.decode(byteBuf);
//
//        Assert.assertArrayEquals(serializer.serializer(loginRequestPacket), serializer.serializer(decodedPacket));
//    }
}
