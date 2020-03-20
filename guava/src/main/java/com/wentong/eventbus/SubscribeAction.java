package com.wentong.eventbus;

import com.google.common.base.Objects;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 封装调用方法，反射用
 */
public class SubscribeAction {

    private Object target;
    private Method method;

    public SubscribeAction(Object target, Method method) {
        this.target = target;
        this.method = method;
    }

    public void execute(Object param) {
        try {
            method.invoke(target, param);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SubscribeAction that = (SubscribeAction) o;
        return Objects.equal(target, that.target) &&
                Objects.equal(method, that.method);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(target, method);
    }
}
