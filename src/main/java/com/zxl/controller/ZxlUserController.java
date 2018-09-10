package com.zxl.controller;

import com.zxl.entity.ZxlUser;
import com.zxl.service.IZxlUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description TODD
 * @Author zhangxiaolei
 * @Date 2018/8/21
 **/
// @RestController controller里面的方法都以json格式输出
@Controller
@RequestMapping("/zxlUserController")
@Slf4j
public class ZxlUserController {
    @Autowired
    private IZxlUserService userService;

    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public String saveUser(ZxlUser user) {
        userService.saveUser(user);
        return "/zxlUserController/toLogin";
    }

    @RequestMapping(value = "/find")
    public void findUser(String loginName) {
        ZxlUser user = userService.getUser( loginName );
    }

    /**
     * 不知道有有没有更好的跳controller方式
     * @param loginName
     * @param loginPwd
     * @return
     */
    @RequestMapping("/login")
    public String login(String loginName, String loginPwd) {
        UsernamePasswordToken token = new UsernamePasswordToken(loginName, loginPwd);
        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.login(token);
        } catch (UnknownAccountException e) {
            log.error("账号不存在");
        } catch (IncorrectCredentialsException e) {
            log.error("密码不正确");
        } catch (Exception e) {
            log.error("其它错误{}", e.getMessage());
        }
        if (currentUser.isAuthenticated()) {
            return "redirect:/zxlUserController/toIndex";
        }
        return "redirect:/zxlUserController/toLogin";
    }

    @RequestMapping(value = "/toLogin")
    private String toLogin() {
        return "login";
    }

    @RequestMapping(value = "/toIndex")
    private String toIndex() {
        return "index";
    }
}
