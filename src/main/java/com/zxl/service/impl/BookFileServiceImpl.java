package com.zxl.service.impl;

import com.zxl.dao.mongo.primary.IFileMongoDao;
import com.zxl.entity.mongo.primary.BookFileInfo;
import com.zxl.service.IBookFileService;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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

    @Override
    public void saveBookFileByFile(MultipartFile file) throws IOException {
        BookFileInfo bookFileInfo = new BookFileInfo();
        bookFileInfo.setDocumentname(file.getOriginalFilename());
        bookFileInfo.setContent(new Binary(file.getBytes()));
        bookFileInfo.setDocumentsize((int)file.getSize());
        bookFileInfo.setMimetype(file.getContentType());
        fileMongoDao.save(bookFileInfo);
    }
}
