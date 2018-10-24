package com.wentong.study.attribute;

import io.netty.util.AttributeKey;

/**
 * 绑定在 netty channel 上面的属性
 */
public interface Attributes {
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
}
