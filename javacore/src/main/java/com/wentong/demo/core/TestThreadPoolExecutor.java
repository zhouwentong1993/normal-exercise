package com.wentong.demo.core;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemErrRule;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestThreadPoolExecutor {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestThreadPoolExecutor.class);

    /**
     * 引入SystemOutRule，监听程序日志输出
     */
    @Rule
    public SystemOutRule systemOutRule = new SystemOutRule().enableLog();
    @Rule
    public SystemErrRule systemErrRule = new SystemErrRule().enableLog();

    private static final ExecutorService executorService = new ThreadPoolExecutor(2, 2, 0L, TimeUnit.MICROSECONDS,
            new LinkedBlockingDeque<>(), new ThreadFactoryBuilder().setNameFormat("queryAssetDetail-%s").build());

    @Test
    public void test() throws Exception {
        executorService.submit(this::exceptionMethod);
        TimeUnit.SECONDS.sleep(2);
        Assert.assertFalse(systemOutRule.getLog().contains("出错了"));
        Assert.assertFalse(systemErrRule.getLog().contains("出错了"));
    }

    public void exceptionMethod() {
        LOGGER.info("出错了");
        throw new IllegalArgumentException("出错了");
    }
}
