package com.wentong.study.protocol.command.response;

import com.wentong.study.protocol.command.Packet;
import lombok.Data;

import static com.wentong.study.protocol.command.Command.LOGIN_RESPONSE;

@Data
public class LoginResponsePacket extends Packet {

    private boolean success;
    private String reason;

    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
