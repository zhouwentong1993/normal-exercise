package com.wentong.jedis;

import redis.clients.jedis.Jedis;

/**
 * 测试 HyperLogLog
 * 执行命令之前空间占用情况
 * used_memory:1032416
 * used_memory_human:1008.22K
 * used_memory_rss:29151232
 * used_memory_rss_human:27.80M
 * used_memory_peak:107145040
 * used_memory_peak_human:102.18M
 * used_memory_peak_perc:0.96%
 * used_memory_overhead:1030366
 * used_memory_startup:980736
 * used_memory_dataset:2050
 * used_memory_dataset_perc:3.97%
 * total_system_memory:17179869184
 * total_system_memory_human:16.00G
 * used_memory_lua:37888
 * used_memory_lua_human:37.00K
 * maxmemory:0
 * maxmemory_human:0B
 * maxmemory_policy:noeviction
 * mem_fragmentation_ratio:28.23
 * mem_allocator:libc
 * active_defrag_running:0
 * lazyfree_pending_objects:0
 *
 * 执行插入 1000000 条数据之后
 *
 * used_memory:1045312
 * used_memory_human:1020.81K
 * used_memory_rss:29143040
 * used_memory_rss_human:27.79M
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
 * mem_fragmentation_ratio:27.88
 * mem_allocator:libc
 * active_defrag_running:0
 * lazyfree_pending_objects:0
 */
public class HyperLogLogTest {

    public static void main(String[] args) {
        Jedis jedis = new Jedis();
        for (int i = 0; i < 1000000; i++) {
            jedis.pfadd("test_pf", String.valueOf(i));
        }
        System.out.println(jedis.pfcount("test_pf"));
    }

}
