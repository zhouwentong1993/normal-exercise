package netty.definitive.tcpstick;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class TimeClientHandler extends ChannelHandlerAdapter {

    int counter = 0;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);
        String time = new String(bytes);
        System.out.println("now is " + time + " count is " + ++counter);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 100; i++) {
            String req = "QUERY TIME ORDER" + System.getProperty("line.separator");
            ByteBuf buffer = Unpooled.buffer(req.getBytes().length);
            buffer.writeBytes(req.getBytes());
            ctx.writeAndFlush(buffer);
        }
    }
}
