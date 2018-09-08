package com.zxl.config;

import com.zxl.entity.ZxlUser;
import com.zxl.service.IZxlUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Auther: ZXL
 * @Date: 2018/9/8
 * @Description: 自定义realm类，重写获取用户信息
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private IZxlUserService zxlUserService;

    /**
     * 权限验证（不做）
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 登陸验证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String loginName = String.valueOf(authenticationToken.getPrincipal());
        ZxlUser user = zxlUserService.getUser(loginName);
        if(user == null){
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user, //用户名
                user.getLoginPwd(), //密码
                ByteSource.Util.bytes(user.getLoginName()),//salt=loginName
                getName()  //realm name
        );
        return authenticationInfo;
    }
}
