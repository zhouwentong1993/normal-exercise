package com.wentong.springbootredisson.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

@Component
@Aspect
public class LockAspect {
    @Autowired
    private RedissonClient redissonClient;

    private ExpressionParser spelExpressionParser = new SpelExpressionParser();

    private LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();


    @Around(value = "@annotation(com.wentong.springbootredisson.aspect.DistributeLock)")
    public Object lock(ProceedingJoinPoint jp) throws Throwable {
        Object object = null;
        MethodSignature signature = (MethodSignature) jp.getSignature();
        Method method = signature.getMethod();
        DistributeLock distributeLock = method.getAnnotation(DistributeLock.class);

        String uniqueKey = distributeLock.lockUniqueKey();
        Object[] args = jp.getArgs();
        Parameter[] parameters = method.getParameters();
        String[] params = discoverer.getParameterNames(method);
        EvaluationContext context = new StandardEvaluationContext();
        for (int len = 0; len < params.length; len++) {
            context.setVariable(params[len], args[len]);
        }
        Expression expression = spelExpressionParser.parseExpression(uniqueKey);
        Object value = expression.getValue(context);

        Annotation[][] annotations = method.getParameterAnnotations();
        return object;
    }
}
