package com.wentong.jedis;

import redis.clients.jedis.Jedis;

/**
 * 测试 BitMap 的内存占用情况
 *
 * 执行之前的内存占用情况
 * used_memory:1045312
 * used_memory_human:1020.81K
 * used_memory_rss:811008
 * used_memory_rss_human:792.00K
 * used_memory_peak:107145040
 * used_memory_peak_human:102.18M
 * used_memory_peak_perc:0.98%
 * used_memory_overhead:1030438
 * used_memory_startup:980736
 * used_memory_dataset:14874
 * used_memory_dataset_perc:23.03%
 * total_system_memory:17179869184
 * total_system_memory_human:16.00G
 * used_memory_lua:37888
 * used_memory_lua_human:37.00K
 * maxmemory:0
 * maxmemory_human:0B
 * maxmemory_policy:noeviction
 * mem_fragmentation_ratio:0.78
 * mem_allocator:libc
 * active_defrag_running:0
 * lazyfree_pending_objects:0
 *
 * used_memory:1171120
 * used_memory_human:1.12M
 * used_memory_rss:1056768
 * used_memory_rss_human:1.01M
 * used_memory_peak:107145040
 * used_memory_peak_human:102.18M
 * used_memory_peak_perc:1.09%
 * used_memory_overhead:1030478
 * used_memory_startup:980736
 * used_memory_dataset:140642
 * used_memory_dataset_perc:73.87%
 * total_system_memory:17179869184
 * total_system_memory_human:16.00G
 * used_memory_lua:37888
 * used_memory_lua_human:37.00K
 * maxmemory:0
 * maxmemory_human:0B
 * maxmemory_policy:noeviction
 * mem_fragmentation_ratio:0.90
 * mem_allocator:libc
 * active_defrag_running:0
 * lazyfree_pending_objects:0
 *
 * 增加 126.07k 的内存占用
 */
public class BitMapTest {

    public static void main(String[] args) {
        Jedis jedis = new Jedis();

        jedis.setbit("test_bit", 1000000, "1");
        System.out.println(jedis.getbit("test_bit", 1000000));
    }

}
