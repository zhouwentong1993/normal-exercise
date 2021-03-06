package com.wentong.study.protocol.command;

import com.wentong.study.protocol.command.request.LoginRequestPacket;
import com.wentong.study.protocol.command.request.MessageRequestPacket;
import com.wentong.study.protocol.command.response.LoginResponsePacket;
import com.wentong.study.protocol.command.response.MessageResponsePacket;
import com.wentong.study.serialize.Serializer;
import com.wentong.study.serialize.SerializerAlgorithm;
import com.wentong.study.serialize.impl.JsonSerializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.util.HashMap;
import java.util.Map;

/**
 * 针对自定义协议的编解码
 * 自定义协议包括：4 字节的魔数、1 字节的版本号、1 字节的序列化算法、1 字节的指令、4 字节的数据长度、N 字节的具体数据报文
 */
public class PacketCodec {
    private static final int MAGIC_NUMBER = 0x12345678;

    private  Map<Byte, Serializer> serializerMap;
    private  Map<Byte, Class<? extends Packet>> packetTypeMap;

    public static final PacketCodec INSTANCE = new PacketCodec();

    private PacketCodec() {
        serializerMap = new HashMap<>();
        serializerMap.put(SerializerAlgorithm.JSON_ALGORITHM, new JsonSerializer());

        // todo
        //  在类启动的时候，全局扫描带有注解的 command，这样添加一个，这里也要添加，太麻烦了
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(Command.LOGIN_REQUEST, LoginRequestPacket.class);
        packetTypeMap.put(Command.LOGIN_RESPONSE, LoginResponsePacket.class);
        packetTypeMap.put(Command.MESSAGE_REQUEST, MessageRequestPacket.class);
        packetTypeMap.put(Command.MESSAGE_RESPONSE, MessageResponsePacket.class);
    }

    public ByteBuf encode(ByteBufAllocator byteBufAllocator,Packet packet) {
        ByteBuf byteBuf = byteBufAllocator.ioBuffer();
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        Byte serializerAlgorithm = Serializer.DEFAULT.getSerializerAlgorithm();
        byteBuf.writeByte(serializerAlgorithm);
        byteBuf.writeByte(packet.getCommand());
        Serializer serializer = serializerMap.get(serializerAlgorithm);
        byte[] encodeData = serializer.serializer(packet);
        byteBuf.writeInt(encodeData.length);
        byteBuf.writeBytes(encodeData);
        return byteBuf;
    }

    public Packet decode(ByteBuf byteBuf) {
        int magicNumber = byteBuf.readInt();
        if (magicNumber != MAGIC_NUMBER) {
            throw new IllegalArgumentException("非法的魔数：" + magicNumber);
        }
        // 跳过版本号
        byteBuf.skipBytes(1);
        byte serializerAlgorithm = byteBuf.readByte();
        byte command = byteBuf.readByte();
        int length = byteBuf.readInt();
        byte[] bytes = new byte[length];

        byteBuf.readBytes(bytes);
        Serializer serializer = serializerMap.get(serializerAlgorithm);
        Class<? extends Packet> clazzByCommand = getClazzByCommand(command);
        if (serializer != null && clazzByCommand != null) {
            return serializer.deserialize(clazzByCommand, bytes);
        } else {
            return null;
        }
    }

    private Class<? extends Packet> getClazzByCommand(byte command) {
        return packetTypeMap.get(command);
    }

}
