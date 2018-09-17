package com.zxl.other;

import com.zxl.entity.ZxlUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description 测试redis
 * @Author zhangxiaolei
 * @Date 2018/9/3
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    /**
     * StringRedisTemplate 相当于 RedisTemplate<String, String> 的实现
     */
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public void beginTest() {
//        stringRedisTemplate.opsForValue().set("aa", "123");
//        String s = stringRedisTemplate.opsForValue().get("aa");
//        System.out.println("redis中aa的值为"+ s);
    }
}
