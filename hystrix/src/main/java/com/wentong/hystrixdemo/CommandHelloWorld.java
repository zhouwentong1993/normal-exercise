package com.wentong.hystrixdemo;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import rx.Observable;
import rx.schedulers.Schedulers;

public class CommandHelloWorld extends HystrixObservableCommand<String> {

    private String name;

    public CommandHelloWorld(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("exampleGroup"));
        this.name = name;
    }

    @Override
    protected Observable<String> construct() {
        return Observable.create((Observable.OnSubscribe<String>) observer -> {
            try {
                if (!observer.isUnsubscribed()) {
                    // a real example would do work like a network call here
                    observer.onNext("Hello" + name + "!");
                    observer.onCompleted();
                }
            } catch (Exception e) {
                observer.onError(e);
            }
        }).subscribeOn(Schedulers.io());
    }

    public static void main(String[] args) throws Exception {
        // 同步方式，异步方式就在 toFuture 上。
        CommandHelloWorld jack = new CommandHelloWorld("jack");
        String s = jack.toObservable().toBlocking().toFuture().get();
        System.out.println(s);

    }

}
