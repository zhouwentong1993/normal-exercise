package com.wentong.springbootredisson.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Component
@Aspect
public class LockAspect {
    @Autowired
    private RedissonClient redissonClient;

    @Around(value = "@annotation(com.wentong.springbootredisson.aspect.DistributeLock)")
    public Object lock(ProceedingJoinPoint jp) throws Throwable {
        Object object = null;
        MethodSignature signature = (MethodSignature) jp.getSignature();
        Method method = signature.getMethod();
        DistributeLock distributeLock = method.getAnnotation(DistributeLock.class);

        Object[] args = jp.getArgs();
        Annotation[][] annotations = method.getParameterAnnotations();
        return object;
    }
}
