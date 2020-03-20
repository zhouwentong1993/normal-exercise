package com.wentong.eventbus;

import com.google.common.util.concurrent.MoreExecutors;

import java.util.List;
import java.util.concurrent.Executor;

public class EventBus {

    private ObserverRegistry observerRegistry = new ObserverRegistry();
    private Executor executor;

    public EventBus(Executor executor) {
        this.executor = executor;
    }

    public EventBus() {
        // 该 Executor 是单线程的，也就是 eventbus 默认是阻塞方法调用
        this.executor = MoreExecutors.directExecutor();
    }

    public void post(Object object) {
        List<SubscribeAction> subscribeActions = observerRegistry.fetchSubscribeActions(object);
        for (SubscribeAction subscribeAction : subscribeActions) {
            executor.execute(() -> subscribeAction.execute(object));
        }
    }

    public void register(Object object) {
        observerRegistry.register(object);
    }

}
