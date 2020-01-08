package netty.definitive.protocol.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.util.CharsetUtil;
import netty.definitive.protocol.NettyMessage;

import java.util.List;
import java.util.Map;

public class NettyMessageEncoder extends MessageToMessageEncoder<NettyMessage> {

    @Override
    protected void encode(ChannelHandlerContext ctx, NettyMessage msg, List<Object> out) throws Exception {
        ByteBuf buffer = Unpooled.buffer();
        buffer.writeInt(msg.getHeader().getCrcCode());
        buffer.writeInt(msg.getHeader().getLength());
        buffer.writeLong(msg.getHeader().getSessionId());
        buffer.writeByte(msg.getHeader().getType());
        buffer.writeByte(msg.getHeader().getPriority());

        for (Map.Entry<String, Object> param : msg.getHeader().getAttachment().entrySet()) {
            String key = param.getKey();
            Object value = param.getValue();
            byte[] keyInfo = key.getBytes(CharsetUtil.UTF_8);
            buffer.writeInt(keyInfo.length);
            buffer.writeBytes(keyInfo);

        }
    }
}
