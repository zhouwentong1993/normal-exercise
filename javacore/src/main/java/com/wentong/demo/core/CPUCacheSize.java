package com.wentong.demo.core;

import fr.ujm.tse.lt2c.satin.cache.size.CacheInfo;
import fr.ujm.tse.lt2c.satin.cache.size.CacheLevel;
import fr.ujm.tse.lt2c.satin.cache.size.CacheLevelInfo;
import fr.ujm.tse.lt2c.satin.cache.size.CacheType;

public class CPUCacheSize {

    /**
     * 该类可用，但是执行报错，缺少文件
     * 该类能得到 CPU Cache 的信息
     */
    public static void main(String[] args) {
        CacheInfo instance = CacheInfo.getInstance();
        CacheLevelInfo cacheInformation = instance.getCacheInformation(CacheLevel.L1, CacheType.DATA_CACHE);
        System.out.println(cacheInformation);
    }

}
