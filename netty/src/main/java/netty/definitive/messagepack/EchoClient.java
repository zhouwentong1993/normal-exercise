package netty.definitive.messagepack;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class EchoClient {

    public static void main(String[] args) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(bossGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
//                        ByteBuf buf = Unpooled.copiedBuffer("$_".getBytes());
//                        ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, buf));
//                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast("msgpack decoder", new MsgpackDecoder());
                        ch.pipeline().addLast("msgpack encoder", new MsgpackEncoder());
                        ch.pipeline().addLast(new EchoClientHandler());
                    }
                });
        ChannelFuture sync = bootstrap.connect("127.0.0.1", 8080).sync();
        sync.channel().closeFuture().sync();
    }

}
