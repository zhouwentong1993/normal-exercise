package com.wentong.eventbus;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.*;

/**
 * 注册中心
 */
public class ObserverRegistry {

    // 保存 method 的 class 入参的集合
    private Map<Class<?>, Set<SubscribeAction>> registry = new HashMap<>();

    /**
     * 向注册中心注册对象
     *
     * @param object 待注册对象
     */
    public void register(@NotNull Object object) {
        Map<Class<?>, Set<SubscribeAction>> allAnnotation = findAllAnnotation(object);
        allAnnotation.forEach((key, value) -> {
            Set<SubscribeAction> subscribeActions = registry.get(key);
            if (subscribeActions == null) {
                registry.put(key, value);
            } else {
                subscribeActions.addAll(value);
            }
        });
    }

    /**
     * 获取指定对象下的 SubscribeAction
     *
     * @param object 指定对象，在此处为方法参数
     * @return List<SubscribeAction>
     */
    public List<SubscribeAction> fetchSubscribeActions(@NotNull Object object) {
        List<SubscribeAction> list = new ArrayList<>();
        Set<Class<?>> classes = registry.keySet();
        Class<?> parameterType = object.getClass();
        for (Class<?> aClass : classes) {
            if (parameterType.isAssignableFrom(aClass)) {
                list.addAll(registry.get(aClass));
            }
        }
        return list;
    }


    private Map<Class<?>, Set<SubscribeAction>> findAllAnnotation(Object object) {
        Map<Class<?>, Set<SubscribeAction>> map = Maps.newHashMap();
        Class<?> clazz = object.getClass();
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            Subscribe subscribe = method.getAnnotation(Subscribe.class);
            if (subscribe != null) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length != 1) {
                    throw new IllegalArgumentException("标注了 Subscribe 的方法参数为：" + parameterTypes.length + "，不符合要求：1");
                }
                Class<?> parameterType = parameterTypes[0];
                map.putIfAbsent(parameterType, Sets.newHashSet());
                Set<SubscribeAction> subscribeActions = map.get(parameterType);
                subscribeActions.add(new SubscribeAction(object, method));
            }
        }
        return map;
    }
}
