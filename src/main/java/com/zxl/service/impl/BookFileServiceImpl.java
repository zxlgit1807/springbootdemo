package com.zxl.service.impl;

import com.zxl.dao.mongo.primary.IFileMongoDao;
import com.zxl.entity.mongo.primary.BookFileInfo;
import com.zxl.service.IBookFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: ZXL
 * @Date: 2018/9/7
 * @Description:
 */
@Service
public class BookFileServiceImpl implements IBookFileService {

    @Autowired
    private IFileMongoDao fileMongoDao;

    @Override
    public void saveBookFile(BookFileInfo bookFileInfo) {
        fileMongoDao.save(bookFileInfo);
    }
}
