-- 获取到第一个锁
--1567664403.359295 [0 127.0.0.1:64040] "EVAL" "if (redis.call('exists', KEYS[1]) == 0) then redis.call('hset', KEYS[1], ARGV[2], 1); redis.call('pexpire', KEYS[1], ARGV[1]); return nil; end; if (redis.call('hexists', KEYS[1], ARGV[2]) == 1) then redis.call('hincrby', KEYS[1], ARGV[2], 1); redis.call('pexpire', KEYS[1], ARGV[1]); return nil; end; return redis.call('pttl', KEYS[1]);" "1" "lock1" "30000" "b4629b88-63a6-456a-b39c-7e06b2fab7a5:1"
--1567664403.361853 [0 lua] "exists" "lock1"
--1567664403.361910 [0 lua] "hset" "lock1" "b4629b88-63a6-456a-b39c-7e06b2fab7a5:1" "1"
--1567664403.361946 [0 lua] "pexpire" "lock1" "30000"
--
-- 获取到第二个锁
--1567664403.411895 [0 127.0.0.1:64041] "EVAL" "if (redis.call('exists', KEYS[1]) == 0) then redis.call('hset', KEYS[1], ARGV[2], 1); redis.call('pexpire', KEYS[1], ARGV[1]); return nil; end; if (redis.call('hexists', KEYS[1], ARGV[2]) == 1) then redis.call('hincrby', KEYS[1], ARGV[2], 1); redis.call('pexpire', KEYS[1], ARGV[1]); return nil; end; return redis.call('pttl', KEYS[1]);" "1" "lock2" "30000" "b4629b88-63a6-456a-b39c-7e06b2fab7a5:1"
--1567664403.412012 [0 lua] "exists" "lock2"
--1567664403.412026 [0 lua] "hset" "lock2" "b4629b88-63a6-456a-b39c-7e06b2fab7a5:1" "1"
--1567664403.412049 [0 lua] "pexpire" "lock2" "30000"
--
--获取到第三个锁
--1567664403.414904 [0 127.0.0.1:64029] "EVAL" "if (redis.call('exists', KEYS[1]) == 0) then redis.call('hset', KEYS[1], ARGV[2], 1); redis.call('pexpire', KEYS[1], ARGV[1]); return nil; end; if (redis.call('hexists', KEYS[1], ARGV[2]) == 1) then redis.call('hincrby', KEYS[1], ARGV[2], 1); redis.call('pexpire', KEYS[1], ARGV[1]); return nil; end; return redis.call('pttl', KEYS[1]);" "1" "lock3" "30000" "b4629b88-63a6-456a-b39c-7e06b2fab7a5:1"
--1567664403.415063 [0 lua] "exists" "lock3"
--1567664403.415077 [0 lua] "hset" "lock3" "b4629b88-63a6-456a-b39c-7e06b2fab7a5:1" "1"
--1567664403.415100 [0 lua] "pexpire" "lock3" "30000"
--
--子线程尝试获取 lock1「b4629b88-63a6-456a-b39c-7e06b2fab7a5:28」
--1567664403.420020 [0 127.0.0.1:64033] "EVAL" "if (redis.call('exists', KEYS[1]) == 0) then redis.call('hset', KEYS[1], ARGV[2], 1); redis.call('pexpire', KEYS[1], ARGV[1]); return nil; end; if (redis.call('hexists', KEYS[1], ARGV[2]) == 1) then redis.call('hincrby', KEYS[1], ARGV[2], 1); redis.call('pexpire', KEYS[1], ARGV[1]); return nil; end; return redis.call('pttl', KEYS[1]);" "1" "lock1" "30000" "b4629b88-63a6-456a-b39c-7e06b2fab7a5:28"
--1567664403.420095 [0 lua] "exists" "lock1"
--1567664403.420102 [0 lua] "hexists" "lock1" "b4629b88-63a6-456a-b39c-7e06b2fab7a5:28"
--1567664403.420113 [0 lua] "pttl" "lock1"
--
--继续尝试  2019-09-05 14:20:03
--1567664403.429256 [0 127.0.0.1:64039] "SUBSCRIBE" "redisson_lock__channel:{lock1}"
--1567664403.473507 [0 127.0.0.1:64036] "EVAL" "if (redis.call('exists', KEYS[1]) == 0) then redis.call('hset', KEYS[1], ARGV[2], 1); redis.call('pexpire', KEYS[1], ARGV[1]); return nil; end; if (redis.call('hexists', KEYS[1], ARGV[2]) == 1) then redis.call('hincrby', KEYS[1], ARGV[2], 1); redis.call('pexpire', KEYS[1], ARGV[1]); return nil; end; return redis.call('pttl', KEYS[1]);" "1" "lock1" "30000" "b4629b88-63a6-456a-b39c-7e06b2fab7a5:28"
--1567664403.473634 [0 lua] "exists" "lock1"
--1567664403.473646 [0 lua] "hexists" "lock1" "b4629b88-63a6-456a-b39c-7e06b2fab7a5:28"
--1567664403.473662 [0 lua] "pttl" "lock1"
--
--
--继续尝试
--1567664413.399927 [0 127.0.0.1:64035] "EVAL" "if (redis.call('hexists', KEYS[1], ARGV[2]) == 1) then redis.call('pexpire', KEYS[1], ARGV[1]); return 1; end; return 0;" "1" "lock1" "30000" "b4629b88-63a6-456a-b39c-7e06b2fab7a5:1"
--1567664413.400037 [0 lua] "hexists" "lock1" "b4629b88-63a6-456a-b39c-7e06b2fab7a5:1"
--1567664413.400057 [0 lua] "pexpire" "lock1" "30000"
--
--释放 lock1
--1567664413.424592 [0 127.0.0.1:64027] "EVAL" "if (redis.call('exists', KEYS[1]) == 0) then redis.call('publish', KEYS[2], ARGV[1]); return 1; end;if (redis.call('hexists', KEYS[1], ARGV[3]) == 0) then return nil;end; local counter = redis.call('hincrby', KEYS[1], ARGV[3], -1); if (counter > 0) then redis.call('pexpire', KEYS[1], ARGV[2]); return 0; else redis.call('del', KEYS[1]); redis.call('publish', KEYS[2], ARGV[1]); return 1; end; return nil;" "2" "lock1" "redisson_lock__channel:{lock1}" "0" "30000" "b4629b88-63a6-456a-b39c-7e06b2fab7a5:1"
--1567664413.424739 [0 lua] "exists" "lock1"
--1567664413.424762 [0 lua] "hexists" "lock1" "b4629b88-63a6-456a-b39c-7e06b2fab7a5:1"
--1567664413.424787 [0 lua] "hincrby" "lock1" "b4629b88-63a6-456a-b39c-7e06b2fab7a5:1" "-1"
--1567664413.424809 [0 lua] "del" "lock1"
--1567664413.424828 [0 lua] "publish" "redisson_lock__channel:{lock1}" "0"
--
--释放 lock2
-- 2019-09-05 14:20:13
--1567664413.425105 [0 127.0.0.1:64031] "EVAL" "if (redis.call('exists', KEYS[1]) == 0) then redis.call('publish', KEYS[2], ARGV[1]); return 1; end;if (redis.call('hexists', KEYS[1], ARGV[3]) == 0) then return nil;end; local counter = redis.call('hincrby', KEYS[1], ARGV[3], -1); if (counter > 0) then redis.call('pexpire', KEYS[1], ARGV[2]); return 0; else redis.call('del', KEYS[1]); redis.call('publish', KEYS[2], ARGV[1]); return 1; end; return nil;" "2" "lock2" "redisson_lock__channel:{lock2}" "0" "30000" "b4629b88-63a6-456a-b39c-7e06b2fab7a5:1"
--1567664413.427403 [0 lua] "exists" "lock2"
--1567664413.427435 [0 lua] "hexists" "lock2" "b4629b88-63a6-456a-b39c-7e06b2fab7a5:1"
--1567664413.427472 [0 lua] "hincrby" "lock2" "b4629b88-63a6-456a-b39c-7e06b2fab7a5:1" "-1"
--1567664413.427569 [0 lua] "del" "lock2"
--1567664413.427620 [0 lua] "publish" "redisson_lock__channel:{lock2}" "0"
--
--释放 lock3
--1567664413.427837 [0 127.0.0.1:64025] "EVAL" "if (redis.call('exists', KEYS[1]) == 0) then redis.call('publish', KEYS[2], ARGV[1]); return 1; end;if (redis.call('hexists', KEYS[1], ARGV[3]) == 0) then return nil;end; local counter = redis.call('hincrby', KEYS[1], ARGV[3], -1); if (counter > 0) then redis.call('pexpire', KEYS[1], ARGV[2]); return 0; else redis.call('del', KEYS[1]); redis.call('publish', KEYS[2], ARGV[1]); return 1; end; return nil;" "2" "lock3" "redisson_lock__channel:{lock3}" "0" "30000" "b4629b88-63a6-456a-b39c-7e06b2fab7a5:1"
--1567664413.427985 [0 lua] "exists" "lock3"
--1567664413.427998 [0 lua] "hexists" "lock3" "b4629b88-63a6-456a-b39c-7e06b2fab7a5:1"
--1567664413.428138 [0 lua] "hincrby" "lock3" "b4629b88-63a6-456a-b39c-7e06b2fab7a5:1" "-1"
--1567664413.428227 [0 lua] "del" "lock3"
--1567664413.428242 [0 lua] "publish" "redisson_lock__channel:{lock3}" "0"
--
--获取 lock1
--1567664413.430681 [0 127.0.0.1:64042] "EVAL" "if (redis.call('exists', KEYS[1]) == 0) then redis.call('hset', KEYS[1], ARGV[2], 1); redis.call('pexpire', KEYS[1], ARGV[1]); return nil; end; if (redis.call('hexists', KEYS[1], ARGV[2]) == 1) then redis.call('hincrby', KEYS[1], ARGV[2], 1); redis.call('pexpire', KEYS[1], ARGV[1]); return nil; end; return redis.call('pttl', KEYS[1]);" "1" "lock1" "30000" "b4629b88-63a6-456a-b39c-7e06b2fab7a5:28"
--1567664413.430863 [0 lua] "exists" "lock1"
--1567664413.430882 [0 lua] "hset" "lock1" "b4629b88-63a6-456a-b39c-7e06b2fab7a5:28" "1"
--1567664413.430905 [0 lua] "pexpire" "lock1" "30000"
--
--释放 lock1
--1567664413.432969 [0 127.0.0.1:64039] "UNSUBSCRIBE" "redisson_lock__channel:{lock1}"
--1567664413.434377 [0 127.0.0.1:64028] "EVAL" "if (redis.call('exists', KEYS[1]) == 0) then redis.call('publish', KEYS[2], ARGV[1]); return 1; end;if (redis.call('hexists', KEYS[1], ARGV[3]) == 0) then return nil;end; local counter = redis.call('hincrby', KEYS[1], ARGV[3], -1); if (counter > 0) then redis.call('pexpire', KEYS[1], ARGV[2]); return 0; else redis.call('del', KEYS[1]); redis.call('publish', KEYS[2], ARGV[1]); return 1; end; return nil;" "2" "lock1" "redisson_lock__channel:{lock1}" "0" "30000" "b4629b88-63a6-456a-b39c-7e06b2fab7a5:28"
--1567664413.434463 [0 lua] "exists" "lock1"
--1567664413.434471 [0 lua] "hexists" "lock1" "b4629b88-63a6-456a-b39c-7e06b2fab7a5:28"
--1567664413.434485 [0 lua] "hincrby" "lock1" "b4629b88-63a6-456a-b39c-7e06b2fab7a5:28" "-1"
--1567664413.434503 [0 lua] "del" "lock1"
--1567664413.434511 [0 lua] "publish" "redisson_lock__channel:{lock1}" "0"