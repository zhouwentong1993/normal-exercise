package netty.definitive.messagepack;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

public class EchoServer {
    public static void main(String[] args) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG,1024)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
//                        ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
////                         初始化 delimiters(定界符)
//                        ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, delimiter));
//                        ch.pipeline().addLast(new FixedLengthFrameDecoder(12));
//                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast("frameDecoder", new LengthFieldBasedFrameDecoder(65535, 0, 2, 0, 2));
                        ch.pipeline().addLast("msgpack decoder", new MsgpackDecoder());
                        ch.pipeline().addLast("frameEncoder", new LengthFieldPrepender(2));
                        ch.pipeline().addLast("msgpack encoder", new MsgpackEncoder());
                        ch.pipeline().addLast(new EchoServerHandler());
                    }
                });
        ChannelFuture sync = bootstrap.bind(8080).sync();
        sync.channel().closeFuture().sync();
    }
}
