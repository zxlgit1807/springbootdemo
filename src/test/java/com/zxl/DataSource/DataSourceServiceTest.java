package com.zxl.DataSource;

import com.zxl.dao.mysql.one.ZxlUserMapper;
import com.zxl.dao.mysql.two.TwoZxlUserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
        System.out.println(oneUserMapper.getUser("admin"));
        System.out.println(twoZxlUserMapper.getUser("admin"));
    }
}
