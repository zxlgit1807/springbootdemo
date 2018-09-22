package com.zxl.service;

import com.zxl.entity.ZxlUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @Auther: ZXL
 * @Date: 2018/9/17
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ZxlUserServiceImplTest {

    @Autowired
    private IZxlUserService zxlUserService;

    @Test
    public void saveUser() {
        ZxlUser oneUser = new ZxlUser();
        oneUser.setUserName("李四");
        oneUser.setLoginName("lisi");
        oneUser.setLoginPwd("lisi");
        oneUser.setSex(0);
        oneUser.setBirthDay(new Date());
        zxlUserService.saveUser(oneUser);
    }

    @Test
    public void getUserById() {
        System.out.println(zxlUserService.getUserById(45));
    }

    @Test
    public void delUserById() {
        zxlUserService.delUserById(45);
    }
}
