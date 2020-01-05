package netty.definitive.tcpstick;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;

public class TimeServerHandler extends ChannelHandlerAdapter {

    int counter = 0;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);
        String req = new String(bytes);
        String requestBody = req.substring(0, bytes.length - System.getProperty("line.separator").length());
        System.out.println("收到请求：" + requestBody + " counter:" + ++counter);
        String response = requestBody.equalsIgnoreCase("QUERY TIME ORDER") ? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
        response = response + System.getProperty("line.separator");
        ByteBuf respBuf = Unpooled.copiedBuffer(response.getBytes());
        ctx.writeAndFlush(respBuf);
    }
}
