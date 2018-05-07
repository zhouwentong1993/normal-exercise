package com.finup.demo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.LinkedList;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhouwentong on 2018/5/7.
 * 连接池
 */
public class ConnectionPool {
    private final LinkedList<Connection> pool = new LinkedList<>();

    public void init(int size) {
        for (int i = 0; i < size; i++) {
            pool.add(ConnectionManager.createNewConnection());
        }
    }

    public void releaseConnection(Connection connection) {
        if (connection != null) {
            synchronized (pool) {
                pool.addLast(connection);
                pool.notifyAll();
            }
        }
    }

    public Connection fetchConnection(long mills) throws InterruptedException {
        synchronized (pool) {
            if (mills <= 0) {
                while (pool.isEmpty()) {
                    pool.wait();
                }
                return pool.removeFirst();
            } else {
                long future = System.currentTimeMillis() + mills;
                long remain = mills;
                while (pool.isEmpty() && remain > 0) {
                    pool.wait(remain);
                    remain = future - System.currentTimeMillis();
                }
                if (pool.isEmpty()) {
                    throw new IllegalStateException("池中无对象");
                } else {
                    return pool.removeFirst();
                }
            }
        }
    }

    static class ConnectionManager implements InvocationHandler {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (Objects.equals("commit", method.getName())) {
                TimeUnit.SECONDS.sleep(1);
            }
            return null;
        }

        static  Connection createNewConnection() {
            return (Connection) Proxy.newProxyInstance(ConnectionManager.class.getClassLoader(), new Class[]{Connection.class}, new ConnectionManager());
        }
    }

}
