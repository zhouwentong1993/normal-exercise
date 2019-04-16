package com.wentong.demo.registration;

import java.util.Set;

public final class Utils {
    private Utils(){}


    /**
     * 为 set 增加元素，若元素不存在，添加 return true，若元素存在，替换原来的，return false
     */
    public static <T> boolean setReplace(Set<T> set,T t) {
        if (!set.add(t)) {
            set.remove(t);
            set.add(t);
            return false;
        } else {
            return true;
        }
    }
}
