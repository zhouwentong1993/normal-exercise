package netty.definitive.protocol;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import netty.definitive.protocol.consant.Constant;

import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class LoginAuthResponseHandler extends ChannelHandlerAdapter {

    private Map<String, Boolean> loginCheck = new ConcurrentHashMap<>();
    private static final List<String> WHITE_LIST = Arrays.asList("127.0.0.1", "192.168.0.1");

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        NettyMessage nettyMessage = (NettyMessage) msg;
        System.out.println(nettyMessage);
        // 如果是登录请求，校验
        if (nettyMessage.getHeader() != null && nettyMessage.getHeader().getType() == Constant.LOGIN_REQUEST) {
            String localAddress = ctx.channel().remoteAddress().toString();
            // 代表重复请求
            if (loginCheck.containsKey(localAddress)) {
                ctx.writeAndFlush(buildResponse(Constant.LOGIN_RESPONSE, -1));
            } else {
                InetSocketAddress inetSocketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
                String clientHost = inetSocketAddress.getAddress().getHostAddress();
                if (notInWhiteList(clientHost)) {
                    ctx.writeAndFlush(buildResponse(Constant.LOGIN_RESPONSE, -1));
                } else {
                    loginCheck.put(localAddress, true);
                    ctx.writeAndFlush(buildResponse(Constant.LOGIN_RESPONSE, 1));
                    System.out.println("client:" + localAddress + "登录");
                }
            }
            // 其他请求放行，让其它的 handler 处理
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("LoginAuthResponseHandler.channelActive");
    }

    private NettyMessage buildResponse(byte type, int code) {
        NettyMessage nettyMessage = new NettyMessage();
        Header header = new Header();
        header.setType(type);
        nettyMessage.setHeader(header);
        nettyMessage.setBody(code);
        return nettyMessage;
    }

    private boolean notInWhiteList(String host) {
        Objects.requireNonNull(host);
        for (String whiteIp : WHITE_LIST) {
            if (host.equals(whiteIp)) {
                return false;
            }
        }
        return true;
    }
}
