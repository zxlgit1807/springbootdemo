package com.zxl.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxl.commons.utils.PagingResult;
import com.zxl.commons.utils.ResultResponse;
import com.zxl.entity.ZxlUser;
import com.zxl.service.IZxlUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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

    /**
     * 统一异常处理测试
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/error")
    public String error() {
        String a = null;
        a.split(".");
        return "a";
    }

    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public String saveUser(ZxlUser user) {
        userService.saveUser(user);
        return "/zxlUserController/toLogin";
    }

    @RequestMapping(value = "/find")
    public ResultResponse findUser(String loginName) {
        ZxlUser user = userService.getUser( loginName );
        ResultResponse resultResponse = new ResultResponse();
        resultResponse.setObj(user);
        return resultResponse;
    }

    /**
     * 不知道有有没有更好的跳controller方式
     * @param loginName
     * @param loginPwd
     * @return
     */
    @RequestMapping("/login")
    public String login(String loginName, String loginPwd) throws Exception{
        UsernamePasswordToken token = new UsernamePasswordToken(loginName, loginPwd);
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.login(token);
        if (currentUser.isAuthenticated()) {
            return "redirect:/zxlUserController/toIndex";
        }
        return "redirect:/zxlUserController/toLogin";
    }

    @RequestMapping("/listUsers")
    @ResponseBody
    public ResultResponse listUsers(Integer pageNo, Integer pageSize) throws Exception{
        PageHelper.startPage(pageNo, pageSize);
        List<ZxlUser> users = userService.listUsers();
        PageInfo info = new PageInfo(users);

        ResultResponse resultResponse = new ResultResponse();
        resultResponse.setObj(users);
        PagingResult pageResult = new PagingResult();
        pageResult.setPageNo(info.getPageNum());
        pageResult.setPageSize(info.getPageSize());
        pageResult.setTotalCount(info.getTotal());
        pageResult.setTotalPage(info.getPages());
        resultResponse.setPagingResult(pageResult);
        return resultResponse;
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
