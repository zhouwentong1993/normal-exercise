package com.wentong.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4jDemo1 {
    
    public static void main(String[] args) {
        Logger LOGGER = LoggerFactory.getLogger(Slf4jDemo1.class);
        LOGGER.info("hello world");
        LOGGER.warn("hello world waring");
    }
}
