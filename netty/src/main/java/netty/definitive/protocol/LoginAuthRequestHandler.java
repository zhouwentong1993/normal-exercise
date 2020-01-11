package netty.definitive.protocol;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import netty.definitive.protocol.consant.Constant;

public class LoginAuthRequestHandler extends ChannelHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        NettyMessage nettyMessage = new NettyMessage();
        Header header = new Header();
        header.setType(Constant.LOGIN_REQUEST);
        nettyMessage.setHeader(header);
        ctx.writeAndFlush(nettyMessage);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

    }
}
