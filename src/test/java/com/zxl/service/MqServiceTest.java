package com.zxl.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Auther: ZXL
 * @Date: 2018/9/20
 * @Description: 消息测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MqServiceTest {

    @Autowired
    private IZxlUserService zxlUserService;

    @Test
    public void hello() throws Exception {
        zxlUserService.sendUserByMq();
    }
}
