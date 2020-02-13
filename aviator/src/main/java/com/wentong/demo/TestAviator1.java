package com.wentong.demo;

import com.google.common.collect.Maps;
import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.AviatorEvaluatorInstance;
import com.googlecode.aviator.Expression;
import com.googlecode.aviator.Options;
import com.googlecode.aviator.lexer.token.OperatorType;
import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorDouble;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.wentong.function.AddFunction;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestAviator1 {

    @Test
    public void test1() {
        long result = (long) AviatorEvaluator.execute("1+2*3");
        System.out.println(result);
    }

    @Test
    public void testMultiLineExpression() {
        long execute = (long) AviatorEvaluator.execute("print('hello world');1+2;1+3");
        System.out.println(execute);
    }

    @Test
    public void testVariable() {
        Map<String, Object> map = Maps.newHashMapWithExpectedSize(1);
        map.put("name", "周文童");
        AviatorEvaluatorInstance aviatorEvaluatorInstance = AviatorEvaluator.newInstance();
        String result = (String) aviatorEvaluatorInstance.execute("'hello' + name", map);
        System.out.println(result);
    }

    @Test
    public void testLambda() {
        long exec = (long) AviatorEvaluator.exec("(lambda(x,y)->x+y end)(x,y)", 1, 2);
        System.out.println(exec);
    }

    @Test
    public void testFunction() {
        AviatorEvaluator.addFunction(new AddFunction());
        double result = (double) AviatorEvaluator.execute("myAdd(1,3)");
        System.out.println(result);
    }

    @Test
    public void testImportFunction() throws Exception {
        // 引入外部函数
        AviatorEvaluator.addStaticFunctions("strUtil", StringUtils.class);
        boolean result = (boolean) AviatorEvaluator.execute("strUtil.isBlank('')");
        System.out.println(result);
    }

    @Test
    public void testImportJavaFunction() throws Exception {
        // 引入 Java 自己的函数，但是调用时需要类似于 go 的调用方式
        AviatorEvaluator.addInstanceFunctions("s", String.class);
        long result = (long) AviatorEvaluator.execute("s.indexOf('hello','l')");
        System.out.println(result);
    }

    @Test
    public void testImportBothStaticAndInstanceFunction() throws Exception {
        List<String> list = AviatorEvaluator.importFunctions(StringUtils.class);
        System.out.println(list);
    }

    @Test
    public void redefineOperation() {
        // 重载运算符，慎用
        AviatorEvaluator.addOpFunction(OperatorType.ADD, new AbstractFunction() {
            @Override
            public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2) {
                Number left = FunctionUtils.getNumberValue(arg1, env);
                Number right = FunctionUtils.getNumberValue(arg2, env);
                return new AviatorDouble(left.doubleValue() * right.doubleValue());
            }

            @Override
            public String getName() {
                return "+";
            }
        });
        double result = (double) AviatorEvaluator.execute("1+10");
        System.out.println(result);
    }

    @Test
    public void compileExpression() {
        // 先编译结果，然后再动态地赋值，这样不必每次都编译，也可以将编译结果 cache 起来
        Expression compile = AviatorEvaluator.compile("a-(b-c)>100");
        Map<String,Object> map = new HashMap<>();
        map.put("a", 10);
        map.put("b", 10);
        map.put("c", 10);
        boolean result = (boolean) compile.execute(map);
        System.out.println(result);
    }

    @Test
    public void testCollections() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        Map<String,Object> map = new HashMap<>();
        map.put("a", "a");
        map.put("b", "b");
        map.put("c", "c");

        int[] ints = new int[3];
        ints[1] = 1;
        ints[2] = 2;

        Map<String,Object> env = new HashMap<>();
        env.put("list", list);
        env.put("map", map);
        env.put("arrs", ints);

        System.out.println(AviatorEvaluator.execute("list[0]+list[1]+list[2]", env));
        System.out.println(AviatorEvaluator.execute("map.a+map.b+map.c", env));
        // 不知道为什么数组有问题
        System.out.println(AviatorEvaluator.execute("arrs[1]+arrs[2]"));
    }

    @Test
    public void testDecimal() throws Exception {
        AviatorEvaluator.setOption(Options.TRACE, true);
        AviatorEvaluator.setTraceOutputStream(new FileOutputStream(new File("aviator.log")));
        // 在数字后面增加 M 就代表是 BigDecimal 类型了。
        Object execute = AviatorEvaluator.execute("9223372036854775807100.356M * 2");
        System.out.println(execute);
        System.out.println(execute.getClass());
    }

}
