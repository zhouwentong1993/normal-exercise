package com.wentong.study.protocol.command.request;

import com.wentong.study.protocol.command.Command;
import com.wentong.study.protocol.command.Packet;
import lombok.Data;

@Data
public class LoginRequestPacket extends Packet {

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }

    private int userId;
    private String username;
    private String password;
}
