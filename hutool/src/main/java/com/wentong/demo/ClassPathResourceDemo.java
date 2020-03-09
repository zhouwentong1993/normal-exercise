package com.wentong.demo;

import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.lang.Console;

import java.util.Properties;

/**
 * ClassPathResource
 */
public class ClassPathResourceDemo {

    public static void main(String[] args) throws Exception {
        ClassPathResource classPathResource = new ClassPathResource("test.properties");
        Properties properties = new Properties();
        properties.load(classPathResource.getStream());
        Console.log(properties);
    }

}
