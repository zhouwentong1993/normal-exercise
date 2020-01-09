package netty.definitive.marshalling;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class SubRequestClientHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        SubscribeResponse response = (SubscribeResponse) msg;
        System.out.println(response);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 10; i++) {
            SubscribeRequest request = new SubscribeRequest();
            request.setAddress("address" + i);
            request.setPhoneNumber("phoneNumber" + i);
            request.setProductName("productName" + i);
            request.setSubReqId(i);
            request.setUserName("username" + i);
            ctx.write(request);
        }
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
