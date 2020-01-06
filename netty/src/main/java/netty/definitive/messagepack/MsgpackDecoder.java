package netty.definitive.messagepack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.msgpack.MessagePack;

import java.util.List;

public class MsgpackDecoder extends MessageToMessageDecoder<ByteBuf> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        int length = msg.readableBytes();
        byte[] bytes = new byte[length];
        msg.getBytes(msg.readerIndex(), bytes, 0, length);
        MessagePack messagePack = new MessagePack();
        out.add(messagePack.read(bytes, UserInfo[].class));
    }
}
