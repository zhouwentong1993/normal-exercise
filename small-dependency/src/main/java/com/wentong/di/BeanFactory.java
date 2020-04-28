package com.wentong.di;

import java.util.List;
import java.util.Map;
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

    private Object createBean(BeanDefinition beanDefinition) {
        return null;
    }
}
