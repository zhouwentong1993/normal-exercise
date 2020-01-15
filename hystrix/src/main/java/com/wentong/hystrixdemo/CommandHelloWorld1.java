package com.wentong.hystrixdemo;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import rx.Observable;

import java.util.concurrent.TimeUnit;

public class CommandHelloWorld1 extends HystrixCommand<String> {

    private String name;

    public CommandHelloWorld1(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("example"));
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        return "hello" + name;
    }

    public static void main(String[] args) throws Exception {
        CommandHelloWorld1 helloWorld1 = new CommandHelloWorld1("周文童");
        Observable<String> observe = helloWorld1.observe();
        observe.subscribe(s -> {
            System.out.println("CommandHelloWorld1.call");
            System.out.println(s);
        });
        TimeUnit.SECONDS.sleep(1);
//        helloWorld1.execute();
    }
}
