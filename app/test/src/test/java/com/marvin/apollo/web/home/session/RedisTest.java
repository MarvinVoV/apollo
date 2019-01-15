package com.marvin.apollo.web.home.session;

import com.marvin.apollo.web.home.boot.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import static org.junit.Assert.assertEquals;

/**
 * @author hufeng
 * @version RedisTest.java, v 0.1 2019-01-16 00:58 Exp $
 */

public class RedisTest extends BaseTest {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void testRedis(){
        redisTemplate.opsForValue().set("a", "hello");
        assertEquals("hello", redisTemplate.opsForValue().get("a"));
    }

}
