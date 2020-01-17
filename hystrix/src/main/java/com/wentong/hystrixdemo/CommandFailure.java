package com.wentong.hystrixdemo;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;

public class CommandFailure extends HystrixCommand<String> {

    private String name;

    public CommandFailure(String name) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("example")).andCommandKey(HystrixCommandKey.Factory.asKey("commandKey")));
        this.name = name;
    }

    public static void main(String[] args) {
        CommandFailure commandFailure = new CommandFailure("周文童");
        String run = commandFailure.execute();
        System.out.println(run);
    }

    @Override
    protected String run() throws Exception {
        throw new IllegalArgumentException("test");
    }

    @Override
    protected String getFallback() {
        return "hello world";
    }


}
