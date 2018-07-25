package com.finup.service;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created by zhouwentong on 2018/7/25.
 */
@Service
public class VoteService {

    @Autowired
    private RedisTemplate redisTemplate;

    private static final long ONE_WEEK = 1000L * 60 * 60 * 24 * 7;

    private static final Integer SINGLE_SCORE = 423;

    public void articleVote(long userId, long articleId) {
        if (!isArticleUpToDate(articleId)) {
            if (redisTemplate.opsForSet().add("voted:" + articleId, userId) > 0) {
                redisTemplate.opsForZSet().incrementScore("socre:", "article:" + articleId, SINGLE_SCORE);
                redisTemplate.opsForSet().add("votes", 1);
            }
        }
    }

    private boolean isArticleUpToDate(long articleId) {
        long endTime = System.currentTimeMillis() - ONE_WEEK;
        double time = redisTemplate.opsForZSet().score("time", articleId);
        return time < endTime;
    }

    public void postAnArticle(long userId) {
        Long articleId = redisTemplate.opsForValue().increment("article:", 1);
        String voted = "voted:" + articleId;
        redisTemplate.opsForSet().add(voted, userId);
        redisTemplate.expire(voted, ONE_WEEK, TimeUnit.MILLISECONDS);

//        redisTemplate.opsForSet().add()
    }

}
