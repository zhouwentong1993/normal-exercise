--"2" "testLock1" "redisson_lock__channel:{testLock1}" "0" "32000" "5c284c47-546c-4847-b9c9-b6f47d89696d:1"
if (redis.call('exists', KEYS[1]) == 0) then
    redis.call('publish', KEYS[2], ARGV[1]);
    return 1;
end ;
if (redis.call('hexists', KEYS[1], ARGV[3]) == 0) then
    return nil;
end ;
local counter = redis.call('hincrby', KEYS[1], ARGV[3], -1);
if (counter > 0) then
    redis.call('pexpire', KEYS[1], ARGV[2]);
    return 0;
else
    redis.call('del', KEYS[1]);
    redis.call('publish', KEYS[2], ARGV[1]);
    return 1;
end ;
return nil;