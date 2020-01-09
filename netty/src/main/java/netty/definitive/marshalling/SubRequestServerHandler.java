package netty.definitive.marshalling;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class SubRequestServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        SubscribeRequest request = (SubscribeRequest) msg;
        System.out.println(request);
        SubscribeResponse subscribeResponse = new SubscribeResponse();
        subscribeResponse.setDesc("desc");
        subscribeResponse.setRespCode(200);
        subscribeResponse.setSubReqId(123);
        ctx.writeAndFlush(subscribeResponse);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("SubRequestServerHandler.channelActive");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
