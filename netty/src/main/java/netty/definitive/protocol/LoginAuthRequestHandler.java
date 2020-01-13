package netty.definitive.protocol;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import netty.definitive.protocol.consant.Constant;


// 不重写 channelRead 方法，让 pipeline 的下一个实现 channelRead 方法，服务端返回的值就会由下一个处理
// 这样能实现握手的功能，但是会出现别的 Handler 处理另外 Handler 应该处理业务的问题。
// 不知道 Netty 是否提供了类似方法，可以指定某一个 Handler 处理

/**
 * 回答上面的问题：
 *      * A {@link Channel} received a message.
 *      *
 *      * This will result in having the {@link ChannelHandler#channelRead(ChannelHandlerContext, Object)}
 *      * method called of the next {@link ChannelHandler} contained in the {@link ChannelPipeline} of the
 *      * {@link Channel}.
 *ChannelHandlerContext fireChannelRead(Object msg);
 */
public class LoginAuthRequestHandler extends ChannelHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("LoginAuthRequestHandler.channelActive");
        NettyMessage nettyMessage = new NettyMessage();
        Header header = new Header();
        header.setType(Constant.LOGIN_REQUEST);
        nettyMessage.setHeader(header);
        ctx.writeAndFlush(nettyMessage);
    }
}
