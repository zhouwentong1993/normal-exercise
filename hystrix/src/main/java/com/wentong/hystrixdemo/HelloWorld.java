package com.wentong.hystrixdemo;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import rx.Observable;
import rx.Observer;

import java.util.concurrent.Future;

public class HelloWorld extends HystrixCommand<String> {

    private String name;

    public HelloWorld(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }

    public static void main(String[] args) throws Exception {
        HelloWorld helloWorld = new HelloWorld("你好");
//        String result = helloWorld.execute();
//        System.out.println(result);
        Future<String> future = helloWorld.queue();
        String s = future.get();
        System.out.println(s);


        Observable<String> observe = helloWorld.observe();
        observe.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("HelloWorld.onCompleted");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("HelloWorld.onError");
            }

            @Override
            public void onNext(String s) {
                System.out.println("HelloWorld.onNext");
            }
        });
    }

    @Override
    protected String run() throws Exception {
        return "hello" + name + "world";
    }
}
