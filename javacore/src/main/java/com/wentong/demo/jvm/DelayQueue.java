//package com.wentong.demo.jvm;
//
//import com.finup.newbrand.framework.MongodbService;
//import com.finup.newbrand.framework.RedisService;
//import com.finup.newbrand.service.delay.DelayContextHolder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.ZSetOperations;
//import org.springframework.stereotype.Component;
//
//import java.util.Iterator;
//import java.util.Set;
//import java.util.concurrent.CompletableFuture;
//import java.util.concurrent.TimeUnit;
//
///**
// * Created by dongchen on 2018/4/4.
// */
//@Component
//public class DelayQueue {
//
//    private static final String DELAY_QUEUE_KEY = "delay.newbrand.queue.web";
//
//
//    @Autowired
//    private RedisService redisService;
//
//    @Autowired
//    private MongodbService mongodbService;
//
//    @Autowired
//    private DelayContextHolder delayContextHolder;
//
//    /**
//     * 添加到延迟队列
//     * 如果更新会直接覆盖
//     * 延迟队列中的value为json  json中固定格式的类型属性为delayType
//     * dongChen
//     * logger
//     *
//     * @param delayTask
//     */
//    public void addDelayQueue(DelayTask delayTask) {
//        redisService.zadd(DELAY_QUEUE_KEY, delayTask.getValue(), delayTask.getDate().getTime());
//    }
//
//    /**
//     * 删除延迟队列
//     * dongChen
//     *
//     * @param delayTask
//     */
//    public void delElement(DelayTask delayTask) {
//        redisService.zrem(DELAY_QUEUE_KEY, delayTask.getValue());
//    }
//
//    /**
//     * 独立线程去take队列中的数据
//     * <p>
//     * dongChe
//     */
//    public void transferFromDelayQueue() {
//        while (true && 1 == 1) {
//            Set<ZSetOperations.TypedTuple> item = redisService.rangeWithScores(DELAY_QUEUE_KEY, 0l, -1l);
//            if (item != null && !item.isEmpty()) {
//                Iterator<ZSetOperations.TypedTuple> iterator = item.iterator();
//                while (iterator.hasNext()) {
//                    ZSetOperations.TypedTuple tuple = iterator.next();
//                    if (System.currentTimeMillis() >= tuple.getScore()) {
//                        String delayResult = tuple.getValue().toString();
//
//                        //删除zset元素
//                        // 如果成功删除了该元素，多个实例同时删除时，只有一个能删除成功。
//                        Long zrem = redisService.zrem(DELAY_QUEUE_KEY, delayResult);
//                        if (zrem != null && zrem > 0) {
//                            CompletableFuture.runAsync(() -> mongodbService.saveJson("delay_queue_removed_log", delayResult));
//                            //事件中的方法需要自己去实现 例子是下面的格式
//                            delayContextHolder.delayRoute(delayResult);
//                        }
//                    }
//                }
//            } else {
//                try {
//                    TimeUnit.SECONDS.sleep(5);
//                } catch (InterruptedException e) {
//                    Thread.currentThread().interrupt();
//                }
//            }
//        }
//    }
//
//
//
//
//
//}
