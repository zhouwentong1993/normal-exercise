package com.wentong.function;

import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorDouble;
import com.googlecode.aviator.runtime.type.AviatorObject;

import java.util.Map;

/**
 * 声明一个自定义函数，只需要实现 AbstractFunction 中的所需要方法即可
 */
public class AddFunction extends AbstractFunction {

    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2) {
        Number leftValue = FunctionUtils.getNumberValue(arg1, env);
        Number rightValue = FunctionUtils.getNumberValue(arg2, env);
        return new AviatorDouble(leftValue.doubleValue() + rightValue.doubleValue());
    }

    @Override
    public String getName() {
        return "myAdd";
    }
}
