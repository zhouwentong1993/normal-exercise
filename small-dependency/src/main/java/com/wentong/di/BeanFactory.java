package com.wentong.di;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.SneakyThrows;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class BeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    private Map<String, Object> objectMap = new ConcurrentHashMap<>();


    public void register(List<BeanDefinition> beanDefinitionList) {
        beanDefinitionList.forEach(item -> beanDefinitionMap.put(item.getId(), item));
    }

    public Object getBean(String id) {
        BeanDefinition beanDefinition = beanDefinitionMap.get(id);
        if (beanDefinition != null) {
            if (beanDefinition.needSingleton()) {
                Object o = objectMap.get(id);
                if (o == null) {
                    o = createBean(beanDefinition);
                    objectMap.put(id, o);
                }
                return o;
            } else {
                return createBean(beanDefinition);
            }
        } else {
            return null;
        }
    }

    @SneakyThrows
    private Object createBean(BeanDefinition beanDefinition) {
        String className = beanDefinition.getClassName();
        Class<?> aClass = Class.forName(className);
        if (CollUtil.isEmpty(beanDefinition.getArgs())) {
            return aClass.newInstance();
        } else {
            List<ConstructorArg> args = beanDefinition.getArgs();
            int size = args.size();
            Class[] classes = new Class[size];
            Object[] objects = new Object[size];
            for (int i = 0; i < size; i++) {
                ConstructorArg constructorArg = args.get(i);
                if (StrUtil.isNotBlank(constructorArg.getRefId())) { // 代表引用了其他的 bean
                    BeanDefinition beanDefinition1 = beanDefinitionMap.get(constructorArg.getRefId());
                    Objects.requireNonNull(beanDefinition1);
                    classes[i] = Class.forName(beanDefinition1.getClassName());
                    objects[i] = createBean(beanDefinition1);
                } else {
                    classes[i] = constructorArg.getArgType();
                    objects[i] = constructorArg.getArg();
                }
            }
            return aClass.getConstructor(classes).newInstance(objects);
        }
    }
}
