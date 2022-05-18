package com.wentong.tutorial;

import java.util.concurrent.TimeUnit;

public final class Util {

    private Util(){}

    @lombok.SneakyThrows
    public static void sleepSeconds(int seconds) {
        TimeUnit.SECONDS.sleep(seconds);
    }

}
