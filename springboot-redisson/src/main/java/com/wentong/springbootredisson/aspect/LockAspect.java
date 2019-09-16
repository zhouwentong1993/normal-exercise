package com.wentong.springbootredisson.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@Component
@Aspect
public class LockAspect {
    @Autowired
    private RedissonClient redissonClient;

    private ExpressionParser spelExpressionParser = new SpelExpressionParser();

    private LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();

    private static final Logger LOGGER = LoggerFactory.getLogger(LockAspect.class);

    @Around(value = "@annotation(com.wentong.springbootredisson.aspect.DistributeLock)")
    public Object lock(ProceedingJoinPoint jp) throws Throwable {
        MethodSignature signature = (MethodSignature) jp.getSignature();
        Method method = signature.getMethod();
        DistributeLock distributeLock = method.getAnnotation(DistributeLock.class);

        String uniqueKey = distributeLock.lockUniqueKey();
        int expireTime = distributeLock.expireTime();
        long timeOut = distributeLock.timeOut();

        Object[] args = jp.getArgs();
        String[] params = discoverer.getParameterNames(method);
        EvaluationContext context = new StandardEvaluationContext();
        for (int len = 0; len < params.length; len++) {
            context.setVariable(params[len], args[len]);
        }
        Expression expression = spelExpressionParser.parseExpression(uniqueKey);
        Object value = expression.getValue(context);
        String lockKey = uniqueKey + value;
        RLock lock = redissonClient.getLock(lockKey);
        boolean locked = lock.tryLock(timeOut, expireTime, TimeUnit.SECONDS);
        if (locked) {
            try {
                LOGGER.info("Thread:{} get lock,key is:{}", Thread.currentThread().getName(), lockKey);
                return jp.proceed();
            } finally {
                lock.unlock();
            }
        } else {
            LOGGER.info("Thread:{} can't get lock,key is:{}", Thread.currentThread().getName(), lockKey);
            throw new IllegalStateException();
        }
    }
}
