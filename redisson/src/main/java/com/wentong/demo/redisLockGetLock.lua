-- 获取锁的流程
-- 如果不存在该 key，创建该 key，hset 结构，放入当前 UUID + 线程号
if (redis.call('exists', KEYS[1]) == 0) then
    redis.call('hset', KEYS[1], ARGV[2], 1);
    redis.call('pexpire', KEYS[1], ARGV[1]);
    return nil;
end ;
-- 可重入锁，还是当前线程的话，+1。
if (redis.call('hexists', KEYS[1], ARGV[2]) == 1) then
    redis.call('hincrby', KEYS[1], ARGV[2], 1);
    redis.call('pexpire', KEYS[1], ARGV[1]);
    return nil;
end ;
return redis.call('pttl', KEYS[1]);
-- Redis 执行 lua 脚本，顺序为「KEYS 在前,ARGV 在后」参数如下，1 代表着该脚本有一个 keys，testLock1 代表第一个参数
-- 注意在引用参数时下标从 1 开始，KEYS[1] = testLock1，剩下的就是 ARGV，也是通过数组的方式去引用。
--1
--testLock1
--10000
--9eaf186a - 1f8b - 4c1e - 916f - 9aa7a847548e:1