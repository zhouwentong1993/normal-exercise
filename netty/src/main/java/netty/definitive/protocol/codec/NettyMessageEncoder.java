package netty.definitive.protocol.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.util.CharsetUtil;
import netty.definitive.protocol.NettyMessage;
import netty.definitive.protocol.util.MarshallingEncoder;

import java.util.List;
import java.util.Map;

public class NettyMessageEncoder extends MessageToMessageEncoder<NettyMessage> {

    @Override
    protected void encode(ChannelHandlerContext ctx, NettyMessage msg, List<Object> out) throws Exception {
        ByteBuf sendBuffer = Unpooled.buffer();
        sendBuffer.writeInt(msg.getHeader().getCrcCode());
        sendBuffer.writeInt(msg.getHeader().getLength());
        sendBuffer.writeLong(msg.getHeader().getSessionId());
        sendBuffer.writeByte(msg.getHeader().getType());
        sendBuffer.writeByte(msg.getHeader().getPriority());

        for (Map.Entry<String, Object> param : msg.getHeader().getAttachment().entrySet()) {
            String key = param.getKey();
            Object value = param.getValue();
            byte[] keyInfo = key.getBytes(CharsetUtil.UTF_8);
            sendBuffer.writeInt(keyInfo.length);
            sendBuffer.writeBytes(keyInfo);
            new MarshallingEncoder().encode(value, sendBuffer);
        }

        if (msg.getBody() != null) {
            new MarshallingEncoder().encode(msg.getBody(), sendBuffer);
        } else {
            sendBuffer.writeInt(0);
        }
        sendBuffer.setInt(4, sendBuffer.readableBytes());
    }
}
