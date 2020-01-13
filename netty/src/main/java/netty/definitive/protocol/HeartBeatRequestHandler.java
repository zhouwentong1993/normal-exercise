package netty.definitive.protocol;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import netty.definitive.protocol.consant.Constant;

import java.util.concurrent.TimeUnit;

import static netty.definitive.protocol.consant.Constant.HEART_BEAT_REQUEST;

public class HeartBeatRequestHandler extends ChannelHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("HeartBeatRequestHandler.channelRead");
        NettyMessage loginResponse = (NettyMessage) msg;
        if (loginResponse.getHeader() != null && loginResponse.getHeader().getType() == Constant.LOGIN_RESPONSE) {
            int code = (int) loginResponse.getBody();
            // 代表握手成功
            if (code == 1) {
                ctx.executor().scheduleAtFixedRate(new HeartBeatTask(ctx), 0, 5, TimeUnit.SECONDS);
            } else {
                ctx.fireChannelRead(msg);
            }
        } else if (loginResponse.getHeader() != null && loginResponse.getHeader().getType() == Constant.HEART_BEAT_RESPONSE) {
            System.out.println("receive server ping response");
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    static class HeartBeatTask implements Runnable {

        private ChannelHandlerContext context;

        public HeartBeatTask(ChannelHandlerContext context) {
            this.context = context;
        }

        @Override
        public void run() {
            NettyMessage nettyMessage = buildHeartBeatNettyMessage();
            context.writeAndFlush(nettyMessage);
        }

        private static NettyMessage buildHeartBeatNettyMessage() {
            NettyMessage nettyMessage = new NettyMessage();
            Header header = new Header();
            header.setType(HEART_BEAT_REQUEST);
            nettyMessage.setHeader(header);
            return nettyMessage;
        }
    }


}
