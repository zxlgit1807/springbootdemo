package com.zxl.service;

import com.zxl.entity.ZxlUser;
import org.apache.catalina.LifecycleState;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * @Description TODD
 * @Author zhangxiaolei
 * @Date 2018/8/21
 **/
public interface IZxlUserService {

    ZxlUser saveUser(ZxlUser user);

    ZxlUser getUser(String loginName);

    ZxlUser getUserById(Integer id);

    void delUserById(Integer id);

    List<ZxlUser> listUsers();
}
