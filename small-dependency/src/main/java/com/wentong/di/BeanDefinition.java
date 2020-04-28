package com.wentong.di;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BeanDefinition {
    // 唯一标识
    private String id;
    private String className;
    private Scope scope;
    private boolean lazyInit;
    private List<ConstructorArg> args = new ArrayList<>();

    public boolean needSingleton() {
        return scope == Scope.SINGLETON;
    }
}

enum Scope {
    PROTOTYPE,SINGLETON
}

@Data
class ConstructorArg {
    private String refId;
    private Class argType;
    private Object arg;
}
