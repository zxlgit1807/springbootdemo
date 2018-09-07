package com.zxl.service;

import com.zxl.dao.mongo.primary.IFileMongoDao;
import com.zxl.entity.mongo.primary.BookFileInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Auther: ZXL
 * @Date: 2018/9/7
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookFileServiceImplTest {

    @Autowired
    private IFileMongoDao fileMongoDao;

    @Test
    public void saveBookFile() {
        BookFileInfo bookFileInfo = new BookFileInfo();
        bookFileInfo.setId("123");
        bookFileInfo.setDocumentname("123");
        fileMongoDao.save(bookFileInfo);
    }
}
