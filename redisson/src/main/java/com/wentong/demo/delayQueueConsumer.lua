--1569396184.700047 [0 127.0.0.1:62600] "SUBSCRIBE" "redisson_delay_queue_channel:{delay_queue}"
--1569396184.711856 [0 127.0.0.1:62592] "EVAL" "local expiredValues = redis.call('zrangebyscore', KEYS[2], 0, ARGV[1], 'limit', 0, ARGV[2]); if #expiredValues > 0 then for i, v in ipairs(expiredValues) do local randomId, value = struct.unpack('dLc0', v);redis.call('rpush', KEYS[1], value);redis.call('lrem', KEYS[3], 1, v);end; redis.call('zrem', KEYS[2], unpack(expiredValues));end; local v = redis.call('zrange', KEYS[2], 0, 0, 'WITHSCORES'); if v[1] ~= nil then return v[2]; end return nil;" "3" "delay_queue" "redisson_delay_queue_timeout:{delay_queue}" "redisson_delay_queue:{delay_queue}" "1569396184707" "100"
--1569396184.711939 [0 lua] "zrangebyscore" "redisson_delay_queue_timeout:{delay_queue}" "0" "1569396184707" "limit" "0" "100"
--1569396184.711954 [0 lua] "zrange" "redisson_delay_queue_timeout:{delay_queue}" "0" "0" "WITHSCORES"
--1569396184.714154 [0 127.0.0.1:62589] "EVAL" "local value = struct.pack('dLc0', tonumber(ARGV[2]), string.len(ARGV[3]), ARGV[3]);redis.call('zadd', KEYS[2], ARGV[1], value);redis.call('rpush', KEYS[3], value);local v = redis.call('zrange', KEYS[2], 0, 0); if v[1] == value then redis.call('publish', KEYS[4], ARGV[1]); end;" "4" "delay_queue" "redisson_delay_queue_timeout:{delay_queue}" "redisson_delay_queue:{delay_queue}" "redisson_delay_queue_channel:{delay_queue}" "1569396194712" "5220409939115423359" "\"helloworld00\""
--1569396184.714230 [0 lua] "zadd" "redisson_delay_queue_timeout:{delay_queue}" "1569396194712" ".\xb9\x90\xcb\xa7\x1c\xd2C\x0e\x00\x00\x00\x00\x00\x00\x00\"helloworld00\""
--1569396184.714251 [0 lua] "rpush" "redisson_delay_queue:{delay_queue}" ".\xb9\x90\xcb\xa7\x1c\xd2C\x0e\x00\x00\x00\x00\x00\x00\x00\"helloworld00\""
--1569396184.714264 [0 lua] "zrange" "redisson_delay_queue_timeout:{delay_queue}" "0" "0"
--1569396184.714275 [0 lua] "publish" "redisson_delay_queue_channel:{delay_queue}" "1569396194712"
--1569396184.715695 [0 127.0.0.1:62598] "EVAL" "local value = struct.pack('dLc0', tonumber(ARGV[2]), string.len(ARGV[3]), ARGV[3]);redis.call('zadd', KEYS[2], ARGV[1], value);redis.call('rpush', KEYS[3], value);local v = redis.call('zrange', KEYS[2], 0, 0); if v[1] == value then redis.call('publish', KEYS[4], ARGV[1]); end;" "4" "delay_queue" "redisson_delay_queue_timeout:{delay_queue}" "redisson_delay_queue:{delay_queue}" "redisson_delay_queue_channel:{delay_queue}" "1569396204714" "-2779821945839011320" "\"helloworld01\""
--1569396184.715767 [0 lua] "zadd" "redisson_delay_queue_timeout:{delay_queue}" "1569396204714" "\xe3Hr\xaa\xf4I\xc3\xc3\x0e\x00\x00\x00\x00\x00\x00\x00\"helloworld01\""
--1569396184.715785 [0 lua] "rpush" "redisson_delay_queue:{delay_queue}" "\xe3Hr\xaa\xf4I\xc3\xc3\x0e\x00\x00\x00\x00\x00\x00\x00\"helloworld01\""
--1569396184.715798 [0 lua] "zrange" "redisson_delay_queue_timeout:{delay_queue}" "0" "0"
--1569396184.717417 [0 127.0.0.1:62587] "BLPOP" "delay_queue" "0"
--1569396194.786925 [0 127.0.0.1:62584] "EVAL" "local expiredValues = redis.call('zrangebyscore', KEYS[2], 0, ARGV[1], 'limit', 0, ARGV[2]); if #expiredValues > 0 then for i, v in ipairs(expiredValues) do local randomId, value = struct.unpack('dLc0', v);redis.call('rpush', KEYS[1], value);redis.call('lrem', KEYS[3], 1, v);end; redis.call('zrem', KEYS[2], unpack(expiredValues));end; local v = redis.call('zrange', KEYS[2], 0, 0, 'WITHSCORES'); if v[1] ~= nil then return v[2]; end return nil;" "3" "delay_queue" "redisson_delay_queue_timeout:{delay_queue}" "redisson_delay_queue:{delay_queue}" "1569396194785" "100"
--1569396194.787066 [0 lua] "zrangebyscore" "redisson_delay_queue_timeout:{delay_queue}" "0" "1569396194785" "limit" "0" "100"
--1569396194.787098 [0 lua] "rpush" "delay_queue" "\"helloworld00\""
--1569396194.787114 [0 lua] "lrem" "redisson_delay_queue:{delay_queue}" "1" ".\xb9\x90\xcb\xa7\x1c\xd2C\x0e\x00\x00\x00\x00\x00\x00\x00\"helloworld00\""
--1569396194.787133 [0 lua] "zrem" "redisson_delay_queue_timeout:{delay_queue}" ".\xb9\x90\xcb\xa7\x1c\xd2C\x0e\x00\x00\x00\x00\x00\x00\x00\"helloworld00\""
--1569396194.787151 [0 lua] "zrange" "redisson_delay_queue_timeout:{delay_queue}" "0" "0" "WITHSCORES"
--1569396194.790237 [0 127.0.0.1:62590] "BLPOP" "delay_queue" "0"
--1569396204.786945 [0 127.0.0.1:62594] "EVAL" "local expiredValues = redis.call('zrangebyscore', KEYS[2], 0, ARGV[1], 'limit', 0, ARGV[2]); if #expiredValues > 0 then for i, v in ipairs(expiredValues) do local randomId, value = struct.unpack('dLc0', v);redis.call('rpush', KEYS[1], value);redis.call('lrem', KEYS[3], 1, v);end; redis.call('zrem', KEYS[2], unpack(expiredValues));end; local v = redis.call('zrange', KEYS[2], 0, 0, 'WITHSCORES'); if v[1] ~= nil then return v[2]; end return nil;" "3" "delay_queue" "redisson_delay_queue_timeout:{delay_queue}" "redisson_delay_queue:{delay_queue}" "1569396204785" "100"
--1569396204.787031 [0 lua] "zrangebyscore" "redisson_delay_queue_timeout:{delay_queue}" "0" "1569396204785" "limit" "0" "100"
--1569396204.787055 [0 lua] "rpush" "delay_queue" "\"helloworld01\""
--1569396204.787077 [0 lua] "lrem" "redisson_delay_queue:{delay_queue}" "1" "\xe3Hr\xaa\xf4I\xc3\xc3\x0e\x00\x00\x00\x00\x00\x00\x00\"helloworld01\""
--1569396204.787093 [0 lua] "zrem" "redisson_delay_queue_timeout:{delay_queue}" "\xe3Hr\xaa\xf4I\xc3\xc3\x0e\x00\x00\x00\x00\x00\x00\x00\"helloworld01\""
--1569396204.787106 [0 lua] "zrange" "redisson_delay_queue_timeout:{delay_queue}" "0" "0" "WITHSCORES"
--1569396204.788734 [0 127.0.0.1:62580] "BLPOP" "delay_queue" "0"


-- zrangebyscore 命令简介：zrangebyscore key min max [WITHSCORES] [limit offset count]
-- 下面这个命令就是查找 redisson_delay_queue_timeout:{delay_queue} zset 下最小为 0，最大为当前时间毫秒数的前 100 条记录
local expiredValues = redis.call('zrangebyscore', KEYS[2], 0, ARGV[1], 'limit', 0, ARGV[2]);
if #expiredValues > 0 then
    for i, v in ipairs(expiredValues) do
        local randomId, value = struct.unpack('dLc0', v);
        redis.call('rpush', KEYS[1], value);
        redis.call('lrem', KEYS[3], 1, v);
    end ;
    redis.call('zrem', KEYS[2], unpack(expiredValues));
end ;
local v = redis.call('zrange', KEYS[2], 0, 0, 'WITHSCORES');
if v[1] ~= nil then
    return v[2];
end
return nil;

--"3" "delay_queue" "redisson_delay_queue_timeout:{delay_queue}" "redisson_delay_queue:{delay_queue}" "1569396184707" "100"