package netty.definitive.messagepack;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class EchoClientHandler extends ChannelHandlerAdapter {

    int counter = 0;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("EchoClientHandler.channelActive");
        UserInfo[] allUsers = getAllUsers();
        for (UserInfo user : allUsers) {
            ctx.write(user);
        }
        ctx.flush();
//        ctx.writeAndFlush(allUsers);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("This is " + ++counter + " times receive server msg:[ " + msg + " ]");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    private UserInfo[] getAllUsers() {
        UserInfo[] userInfos = new UserInfo[10];
        for (int i = 0; i < 10; i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.setAge(i);
            userInfo.setName("test" + i);
            userInfos[i] = userInfo;
        }
        return userInfos;
    }
}
