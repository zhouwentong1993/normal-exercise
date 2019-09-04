-- 释放锁
-- 如果不存在该 key，发布事件
if (redis.call('exists', KEYS[1]) == 0) then
    redis.call('publish', KEYS[2], ARGV[1]);
    return 1;
end ;
-- 如果当前线程没有被锁定
if (redis.call('hexists', KEYS[1], ARGV[3]) == 0) then
    return nil;
end ;
-- 当存在时，将该 set 的值 - 1
local counter = redis.call('hincrby', KEYS[1], ARGV[3], -1);
-- 如果仍然有 count，重新设置过期时间
if (counter > 0) then
    redis.call('pexpire', KEYS[1], ARGV[2]);
    return 0;
else
    -- 删除该 key，发布事件，返回成功。
    redis.call('del', KEYS[1]);
    redis.call('publish', KEYS[2], ARGV[1]);
    return 1;
end ;
return nil;

--"2"
--"testLock1"
--"redisson_lock__channel:{testLock1}"
--"0"
--"32000"
--"3b0396e1-2266-4bca-845d-1451cf6a87e6:1"