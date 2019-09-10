package com.wentong.springbootredisson.aspect;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DistributeLock {

    String lockUniqueKey() default "";// redis 锁key的前缀

    long timeOut() default 1000;// 轮询锁的时间

    int expireTime() default 1000;// key在redis里存在的时间，1000S

}
