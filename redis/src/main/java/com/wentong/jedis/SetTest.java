package com.wentong.jedis;

import redis.clients.jedis.Jedis;

/**
 * 测试 set 插入 100w 条数据
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
 * 执行 100w 条数据插入后
 *
 * used_memory:57560064
 * used_memory_human:54.89M
 * used_memory_rss:37273600
 * used_memory_rss_human:35.55M
 * used_memory_peak:107145040
 * used_memory_peak_human:102.18M
 * used_memory_peak_perc:53.72%
 * used_memory_overhead:1030518
 * used_memory_startup:980736
 * used_memory_dataset:56529546
 * used_memory_dataset_perc:99.91%
 * total_system_memory:17179869184
 * total_system_memory_human:16.00G
 * used_memory_lua:37888
 * used_memory_lua_human:37.00K
 * maxmemory:0
 * maxmemory_human:0B
 * maxmemory_policy:noeviction
 * mem_fragmentation_ratio:0.65
 * mem_allocator:libc
 * active_defrag_running:0
 * lazyfree_pending_objects:0
 *
 * 占用了 50M 多
 */
public class SetTest {

    public static void main(String[] args) {
        Jedis jedis = new Jedis();
        for (int i = 0; i < 1000000; i++) {
            jedis.sadd("test_set", String.valueOf(i));
        }
        System.out.println(jedis.scard("test_set"));
    }

}
