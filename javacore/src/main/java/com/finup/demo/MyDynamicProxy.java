package com.finup.demo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 */
public class MyDynamicProxy {
    public static void main(String[] args) {
        Hello hello = new HelloImpl();
        DynamicProxy dynamicProxy = new DynamicProxy(hello);

        Hello o = (Hello) Proxy.newProxyInstance(MyDynamicProxy.class.getClassLoader(), HelloImpl.class.getInterfaces(), dynamicProxy);
        o.sayHello();
    }
}

class DynamicProxy implements InvocationHandler {

    private Object target;

    public DynamicProxy(Object target) {
        this.target = target;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().startsWith("s")) {
            System.out.printf("method name: %s has bean proxied", method.getName());
            System.out.println();
            return method.invoke(target, args);
        } else {
            return method.invoke(target, args);
        }
    }
}

class HelloImpl implements Hello {
    public void sayHello() {
        System.out.println("HelloImpl.sayHello");
    }

    public void haha() {
        System.out.println("HelloImpl.haha");
    }
}

interface Hello {
    void sayHello();
}
