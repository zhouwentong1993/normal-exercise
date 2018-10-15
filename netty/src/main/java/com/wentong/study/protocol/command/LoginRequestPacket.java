package com.wentong.study.protocol.command;

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
