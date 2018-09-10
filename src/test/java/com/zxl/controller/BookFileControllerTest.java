package com.zxl.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @Auther: ZXL
 * @Date: 2018/9/10
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration    //开启Web上下文测试
public class BookFileControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    /**
     * 设置mockMvc
     */
    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    /**
     * fileupad 已经是废弃了，此处不知道最新的上传方式是啥
     */
    @Test
    public void uploadBook() {

    }
}
