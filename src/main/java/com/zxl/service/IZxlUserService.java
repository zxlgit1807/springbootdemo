package com.zxl.service;

import com.zxl.entity.ZxlUser;
import org.springframework.cache.annotation.Cacheable;

/**
 * @Description TODD
 * @Author zhangxiaolei
 * @Date 2018/8/21
 **/
public interface IZxlUserService {

    void saveUser(ZxlUser user);

    ZxlUser listUsers(String loginName);
}
