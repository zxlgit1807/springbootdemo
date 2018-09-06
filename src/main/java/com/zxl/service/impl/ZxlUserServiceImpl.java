package com.zxl.service.impl;

import com.zxl.dao.ZxlUserMapper;
import com.zxl.entity.ZxlUser;
import com.zxl.service.IZxlUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Description TODD
 * @Author zhangxiaolei
 * @Date 2018/8/21
 **/
@Service
public class ZxlUserServiceImpl implements IZxlUserService {

    @Autowired
    private ZxlUserMapper userMapper;

    @Override
    public void saveUser(ZxlUser user) {
        user.setCreateTime(new Date());
        userMapper.saveUser(user);
    }


    @Override
    @Cacheable(value="zxlUserServiceImpl_getUser", key = "#loginName", unless = "#result == null")
    public ZxlUser getUser(String loginName) {
        return userMapper.getUser( loginName );
    }
}
