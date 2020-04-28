package com.wentong.di.object;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RedisCounter {
    private String host;
    private int port;
}
