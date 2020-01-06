package netty.definitive.delimiterandfixedlength;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class EchoServerHandler extends ChannelHandlerAdapter {

    int counter = 0;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String) msg;
        System.out.println("This is " + ++counter + " times receive client: [" + body + "]");
        // 后置的也行吗？？
//        body += "$_";
        ByteBuf buf = Unpooled.copiedBuffer(body.getBytes());
        ctx.writeAndFlush(buf);
    }
}
