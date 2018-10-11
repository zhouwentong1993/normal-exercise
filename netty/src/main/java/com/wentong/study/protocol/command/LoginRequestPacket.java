package com.wentong.study.protocol.command;

public class LoginRequestPacket extends Packet {

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
