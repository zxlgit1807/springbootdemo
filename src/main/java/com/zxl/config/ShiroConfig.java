package com.zxl.config;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 自定义配置Shiro
 */
@Configuration
public class ShiroConfig {

    @Value("${spring.redis.host}")
    private String redis_host;
    @Value("${spring.redis.port}")
    private int redis_port;
    @Value("${spring.redis.password}")
    private String redis_pwd;
    /**
     * 配置拦截
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> map = new LinkedHashMap<String,String>();
        map.put("/logout", "logout");
        map.put("/zxlUserController/login", "anon");
        map.put("/zxlUserController/saveUser", "anon");
        map.put("/zxlUserController/error", "anon");
        // 配置不会被拦截的链接
        map.put("/login.html", "anon");
        //配置退出 过滤器,其中的具体的退出代码Shiro已经实现
        map.put("/logout", "logout");
        //过滤链定义，从上向下顺序执行，一般将/**放在最为下边
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        map.put("/**", "authc");
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/zxlUserController/toLogin");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index.html");
        // 未授权页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    /**
     * 这里注意，之前一直没设置加密策略，导致一直没有加密
     * 网上代码真坑
     * @return
     */
    @Bean
    public MyShiroRealm myShiroRealm(){
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm;
    }

    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        // 设置realm
        securityManager.setRealm(myShiroRealm());
        // session管理器
        securityManager.setSessionManager(sessionManager());
        // cookie 管理器
        securityManager.setRememberMeManager(rememberMeManager());
        // 缓存管理器
        securityManager.setCacheManager(cacheManager());
        return securityManager;
    }

    /**
     * 凭证匹配器,默认base64编码，注意一致
     * @return
     */
    @Bean(name = "hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));
        //storedCredentialsHexEncoded默·认是true，此时用的是密码加密用的是Hex编码；false时用Base64编码
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        return hashedCredentialsMatcher;
    }

    //	记住我的配置
    @Bean
    public RememberMeManager rememberMeManager() {
        Cookie cookie = new SimpleCookie("rememberMe");
        cookie.setHttpOnly(true); //通过js脚本将无法读取到cookie信息
        cookie.setMaxAge(60 * 60 * 24); //cookie保存一天
        CookieRememberMeManager manager=new CookieRememberMeManager();
        manager.setCookie(cookie);
        // 将cookie加密的秘钥
        manager.setCipherKey(Base64.decode("3AvVhmFLUs0KTA3Kprsdag=="));
        return manager;
    }


    /**
     * session 共享
     * @return
     */
    @Bean(name = "sessionManager")
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        return sessionManager;
    }

    /**
     * sessionDao层的实现 通过redis
     * 使用的是shiro-redis开源插件
     * @return
     */
    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    /**
     * 配置shiro redisManager
     * 用的是shiro-redis开源插件
     * @return
     */
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(redis_host);
        redisManager.setPort(redis_port);
        redisManager.setTimeout(1800);// 配置缓存过期时间
        redisManager.setPassword(redis_pwd);
        return redisManager;
    }

    /**
     * cacheManager 缓存 redis实现
     * 使用的是shiro-redis开源插件
     * @return
     */
    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }
}
