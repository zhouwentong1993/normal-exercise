package com.wentong.study.utils;


import com.wentong.study.attribute.Attributes;
import io.netty.channel.Channel;
import io.netty.util.Attribute;

public final class LoginUtil {
    private LoginUtil() {}

    public static void login(Channel channel) {
        channel.attr(Attributes.LOGIN).set(true);
    }

    public static boolean hasLogin(Channel channel) {
        Attribute<Boolean> attr = channel.attr(Attributes.LOGIN);
        return attr.get() != null;
    }
}
