package com.zxl.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * @Description TODD
 * @Author zhangxiaolei
 * @Date 2018/8/21
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration    //开启Web上下文测试
public class ZxlUserControllerTest {

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
     * 测试保存user
     * @throws Exception
     */
    @Test
    public void saveUser() throws Exception{
        mockMvc.perform(post("/zxlUserController/saveUser")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("userName","张三")
            .param("loginName","zhangsan")
            .param("loginPwd","zhangsan")
            .param("sex","1")
            .param("birthDay","2018-08-08")
        );
    }

    @Test
    public void findUser() throws Exception{
        mockMvc.perform(post("/zxlUserController/find")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("loginName","admin")
        );
    }

    @Test
    public void login() throws Exception{
        mockMvc.perform(post("/zxlUserController/login")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("loginName","admin")
                .param("loginName","123")
        );
    }
}
