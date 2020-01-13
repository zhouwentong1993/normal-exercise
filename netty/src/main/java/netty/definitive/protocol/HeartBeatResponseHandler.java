package netty.definitive.protocol;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import netty.definitive.protocol.consant.Constant;

public class HeartBeatResponseHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("HeartBeatResponseHandler.channelRead");
        NettyMessage heartBeatRequest = (NettyMessage) msg;
        if (heartBeatRequest.getHeader() != null && heartBeatRequest.getHeader().getType() == Constant.HEART_BEAT_REQUEST) {
            NettyMessage nettyMessage = new NettyMessage();
            Header header = new Header();
            header.setType(Constant.HEART_BEAT_RESPONSE);
            nettyMessage.setHeader(header);
            ctx.writeAndFlush(nettyMessage);
        } else {
            ctx.fireChannelRead(msg);
        }
    }
}
