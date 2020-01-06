package netty.definitive.messagepack;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class EchoServerHandler extends ChannelHandlerAdapter {

    int counter = 0;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(msg);
        UserInfo[] userInfo = (UserInfo[]) msg;
        System.out.println("This is " + ++counter + " times receive client: [" + userInfo + "]");
        // 后置的也行吗？？
//        body += "$_";
        ctx.writeAndFlush(userInfo);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("EchoServerHandler.channelActive");
    }
}
