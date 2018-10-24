package com.wentong.study.protocol.command;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public abstract class Packet {

    @JSONField(deserialize = false,serialize = false)
    private Byte version = 1;

    public abstract Byte getCommand();
}
