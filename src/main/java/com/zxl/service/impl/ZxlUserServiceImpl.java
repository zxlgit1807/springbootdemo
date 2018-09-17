package com.zxl.service.impl;

import com.zxl.commons.emuns.CryptoUtils;
import com.zxl.dao.mysql.one.ZxlUserMapper;
import com.zxl.entity.ZxlUser;
import com.zxl.service.IZxlUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description TODD
 * @Author zhangxiaolei
 * @Date 2018/8/21
 **/
@Service
public class ZxlUserServiceImpl implements IZxlUserService {

    @Autowired
    private ZxlUserMapper userMapper;
    // CachePut 不会检查缓存中是否存在，而是每次都存入
    @Override
    @CachePut(value="zxlUserServiceImpl", key = "#user.id", unless = "#result == null")
    public ZxlUser saveUser(ZxlUser user) {
        user.setCreateTime(new Date());
        user.setLoginPwd(CryptoUtils.encoderByMd5(user.getLoginName(), user.getLoginPwd()));
        userMapper.saveUser(user);
        return user;
    }


    @Override
    public ZxlUser getUser(String loginName) {
        return userMapper.getUser( loginName );
    }

    @Cacheable(value="zxlUserServiceImpl", key = "#id", unless = "#result == null")
    @Override
    public ZxlUser getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    @CacheEvict(value = "zxlUserServiceImpl", key = "#id")
    public void delUserById(Integer id) {
        if (id != null) {
            userMapper.deleteUserById(id);
        }
    }

    @Override
    public List<ZxlUser> listUsers() {
        return userMapper.listUsers();
    }
}
