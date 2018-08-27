package com.zxl.controller;

import com.zxl.entity.ZxlUser;
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

import java.util.Date;

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
            .param("userName","admin")
            .param("loginName","admin")
            .param("loignPwd","admin")
            .param("birthDay","2018-08-08")
        );
    }

}
