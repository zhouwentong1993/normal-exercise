package netty.definitive.protocol.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.util.CharsetUtil;
import netty.definitive.protocol.Header;
import netty.definitive.protocol.NettyMessage;
import netty.definitive.protocol.util.MarshallingDecoder;

import java.util.HashMap;
import java.util.Map;

public class NettyMessageDecoder extends LengthFieldBasedFrameDecoder {

    public NettyMessageDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {

        ByteBuf frame = (ByteBuf) super.decode(ctx, in);
        if (frame == null) {
            return null;
        }
        NettyMessage nettyMessage = new NettyMessage();
        Header header = new Header();
        header.setCrcCode(in.readInt());
        header.setLength(in.readInt());
        header.setSessionId(in.readLong());
        header.setType(in.readByte());
        header.setPriority(in.readByte());
        int size = in.readInt();
        // 证明有 attachment 存在，就遍历解码
        MarshallingDecoder marshallingDecoder = new MarshallingDecoder();

        if (size > 0) {
            Map<String,Object> attachment = new HashMap<>(size);
            for (int i = 0; i < size; i++) {
                int keyLength = in.readInt();
                byte[] keyByte = new byte[keyLength];
                in.readBytes(keyByte);
                attachment.put(new String(keyByte, CharsetUtil.UTF_8.name()), marshallingDecoder.decode(in));
            }
            header.setAttachment(attachment);
        }
        nettyMessage.setHeader(header);
        // 如果有 body
        if (in.readableBytes() > 4) {
            nettyMessage.setBody(marshallingDecoder.decode(in));
        }
        return nettyMessage;
    }
}
