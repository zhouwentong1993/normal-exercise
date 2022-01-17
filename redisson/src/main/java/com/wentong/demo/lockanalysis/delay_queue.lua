--  "delay_queue" "redisson_delay_queue_timeout:{delay_queue}" "redisson_delay_queue:{delay_queue}" "1642383686319" "100"
-- 获取 redisson_delay_queue_timeout:{delay_queue} 中已经过期的数据，取 100 条。
local expiredValues = redis.call('zrangebyscore', KEYS[2], 0, ARGV[1], 'limit', 0, ARGV[2]);
if #expiredValues > 0 then
    for i, v in ipairs(expiredValues) do
        local randomId, value = struct.unpack('dLc0', v);
        -- rush 到 delay_queue 中？这是什么意思，没有看到这个啊。
        redis.call('rpush', KEYS[1], value);
        -- 从左侧删除 redisson_delay_queue:{delay_queue} 中的该数据
        redis.call('lrem', KEYS[3], 1, v);
    end ;
    -- 移除有序列表中的该元素。
    redis.call('zrem', KEYS[2], unpack(expiredValues));
end ;
local v = redis.call('zrange', KEYS[2], 0, 0, 'WITHSCORES');
if v[1] ~= nil then
    return v[2];
end
return nil;