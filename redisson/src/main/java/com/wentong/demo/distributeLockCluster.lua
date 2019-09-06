--1567753059.511206 [0 127.0.0.1:53053] "EVAL" "if (redis.call('exists', KEYS[1]) == 0) then redis.call('hset', KEYS[1], ARGV[2], 1); redis.call('pexpire', KEYS[1], ARGV[1]); return nil; end; if (redis.call('hexists', KEYS[1], ARGV[2]) == 1) then redis.call('hincrby', KEYS[1], ARGV[2], 1); redis.call('pexpire', KEYS[1], ARGV[1]); return nil; end; return redis.call('pttl', KEYS[1]);" "1" "testCluster" "30000" "cec5b708-b212-4ba8-9b1e-24000a48cf56:1"
if (redis.call('exists', KEYS[1]) == 0) then
    redis.call('hset', KEYS[1], ARGV[2], 1);
    redis.call('pexpire', KEYS[1], ARGV[1]);
    return nil;
end ;
if (redis.call('hexists', KEYS[1], ARGV[2]) == 1) then
    redis.call('hincrby', KEYS[1], ARGV[2], 1);
    redis.call('pexpire', KEYS[1], ARGV[1]);
    return nil;
end ;
return redis.call('pttl', KEYS[1]);

--1567753059.511288 [0 lua] "exists" "testCluster"
--1567753059.511303 [0 lua] "hset" "testCluster" "cec5b708-b212-4ba8-9b1e-24000a48cf56:1" "1"
--1567753059.511381 [0 lua] "pexpire" "testCluster" "30000"
--1567753059.513980 [0 127.0.0.1:53052] "EVAL" "if (redis.call('exists', KEYS[1]) == 0) then redis.call('publish', KEYS[2], ARGV[1]); return 1; end;if (redis.call('hexists', KEYS[1], ARGV[3]) == 0) then return nil;end; local counter = redis.call('hincrby', KEYS[1], ARGV[3], -1); if (counter > 0) then redis.call('pexpire', KEYS[1], ARGV[2]); return 0; else redis.call('del', KEYS[1]); redis.call('publish', KEYS[2], ARGV[1]); return 1; end; return nil;" "2" "testCluster" "redisson_lock__channel:{testCluster}" "0" "30000" "cec5b708-b212-4ba8-9b1e-24000a48cf56:1"
--1567753059.514060 [0 lua] "exists" "testCluster"
--1567753059.514068 [0 lua] "hexists" "testCluster" "cec5b708-b212-4ba8-9b1e-24000a48cf56:1"
--1567753059.514080 [0 lua] "hincrby" "testCluster" "cec5b708-b212-4ba8-9b1e-24000a48cf56:1" "-1"
--1567753059.514091 [0 lua] "del" "testCluster"
--1567753059.514108 [0 lua] "publish" "redisson_lock__channel:{testCluster}" "0"



--1567753276.062750 [0 127.0.0.1:53777] "EVAL" "if (redis.call('exists', KEYS[1]) == 0) then redis.call('hset', KEYS[1], ARGV[2], 1); redis.call('pexpire', KEYS[1], ARGV[1]); return nil; end; if (redis.call('hexists', KEYS[1], ARGV[2]) == 1) then redis.call('hincrby', KEYS[1], ARGV[2], 1); redis.call('pexpire', KEYS[1], ARGV[1]); return nil; end; return redis.call('pttl', KEYS[1]);" "1" "testCluster" "30000" "433cae30-7058-41a2-9c5b-7d521451853b:1"
if (redis.call('exists', KEYS[1]) == 0) then
    redis.call('hset', KEYS[1], ARGV[2], 1);
    redis.call('pexpire', KEYS[1], ARGV[1]);
    return nil;
end ;
if (redis.call('hexists', KEYS[1], ARGV[2]) == 1) then
    redis.call('hincrby', KEYS[1], ARGV[2], 1);
    redis.call('pexpire', KEYS[1], ARGV[1]);
    return nil;
end ;
return redis.call('pttl', KEYS[1]);
--1567753276.062822 [0 lua] "exists" "testCluster"
--1567753276.062832 [0 lua] "hset" "testCluster" "433cae30-7058-41a2-9c5b-7d521451853b:1" "1"
--1567753276.062846 [0 lua] "pexpire" "testCluster" "30000"
--1567753276.065293 [0 127.0.0.1:53771] "EVAL" "if (redis.call('exists', KEYS[1]) == 0) then redis.call('publish', KEYS[2], ARGV[1]); return 1; end;if (redis.call('hexists', KEYS[1], ARGV[3]) == 0) then return nil;end; local counter = redis.call('hincrby', KEYS[1], ARGV[3], -1); if (counter > 0) then redis.call('pexpire', KEYS[1], ARGV[2]); return 0; else redis.call('del', KEYS[1]); redis.call('publish', KEYS[2], ARGV[1]); return 1; end; return nil;" "2" "testCluster" "redisson_lock__channel:{testCluster}" "0" "30000" "433cae30-7058-41a2-9c5b-7d521451853b:1"
--1567753276.065418 [0 lua] "exists" "testCluster"
--1567753276.065425 [0 lua] "hexists" "testCluster" "433cae30-7058-41a2-9c5b-7d521451853b:1"
--1567753276.065437 [0 lua] "hincrby" "testCluster" "433cae30-7058-41a2-9c5b-7d521451853b:1" "-1"
--1567753276.065448 [0 lua] "del" "testCluster"
--1567753276.065454 [0 lua] "publish" "redisson_lock__channel:{testCluster}" "0"
