package com.zxl.controller;

import com.zxl.entity.ZxlUser;
import com.zxl.service.IZxlUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description TODD
 * @Author zhangxiaolei
 * @Date 2018/8/21
 **/
// @RestController controller里面的方法都以json格式输出
@RestController
@RequestMapping("/zxlUserController")
public class ZxlUserController {
    @Autowired
    private IZxlUserService userService;

    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public void saveUser(ZxlUser user) {
        userService.saveUser(user);
    }
}
