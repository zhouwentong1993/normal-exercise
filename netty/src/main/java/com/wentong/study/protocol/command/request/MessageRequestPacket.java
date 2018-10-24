package com.wentong.study.protocol.command.request;

import com.wentong.study.protocol.command.Command;
import com.wentong.study.protocol.command.Packet;
import lombok.Data;

@Data
public class MessageRequestPacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }
}
