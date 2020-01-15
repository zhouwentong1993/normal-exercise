package com.wentong.hystrixdemo;

import com.netflix.hystrix.HystrixCommand;

public class HystrixDemo1 {

    // 构建 HystrixCommand，了解其中的方法，execute/queue 等
    public static void main(String[] args) throws Exception {
        HystrixCommand hystrixCommand = new HelloWorld("ExampleGroup");

        String run = ((HelloWorld) hystrixCommand).run();
        System.out.println(run);
        System.out.println(hystrixCommand.execute());
    }

}
