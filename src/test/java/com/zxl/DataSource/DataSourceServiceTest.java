package com.zxl.DataSource;

import com.zxl.dao.mysql.one.ZxlUserMapper;
import com.zxl.dao.mysql.two.TwoZxlUserMapper;
import com.zxl.entity.ZxlUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Auther: ZXL
 * @Date: 2018/9/14
 * @Description: 多数据源测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DataSourceServiceTest {

    @Autowired
    private ZxlUserMapper oneUserMapper;

    @Autowired
    private TwoZxlUserMapper twoZxlUserMapper;

    @Test
    public void find() {
        System.out.println("第一个数据源"+oneUserMapper.getUser("admin"));
        System.out.println("第二个数据源"+twoZxlUserMapper.getUser("admin"));
    }

    @Test
    public void save() {
        ZxlUser oneUser = new ZxlUser();
        oneUser.setUserName("李四");
        oneUser.setLoginName("lisi");
        oneUser.setLoginPwd("lisi");
        oneUser.setSex(0);
        oneUser.setBirthDay(new Date());
        oneUser.setCreateTime(new Date());
        oneUserMapper.saveUser(oneUser);

        ZxlUser twoUser = new ZxlUser();
        twoUser.setUserName("李四");
        twoUser.setLoginName("lisi");
        twoUser.setLoginPwd("lisi");
        twoUser.setSex(0);
        twoUser.setBirthDay(new Date());
        twoUser.setCreateTime(new Date());
        twoZxlUserMapper.saveUser(twoUser);
    }

    /**
     * 单元测试会默认事物回滚
     */
    @Transactional
    @Test
    public void saveTwo() {
        ZxlUser oneUser = new ZxlUser();
        oneUser.setUserName("李四");
        oneUser.setLoginName("lisi");
        oneUser.setLoginPwd("lisi");
        oneUser.setSex(0);
        oneUser.setBirthDay(new Date());
        oneUser.setCreateTime(new Date());
        oneUserMapper.saveUser(oneUser);

        twoZxlUserMapper.saveUser(oneUser);
//        String a = null;
//        if (a.equals("f")) {
//            System.out.println("111111");
//        }
    }
}
