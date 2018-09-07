package com.zxl.service;

import com.zxl.entity.mongo.primary.BookFileInfo;

/**
 * @Auther: ZXL
 * @Date: 2018/9/7
 * @Description: 图书service
 */
public interface IBookFileService {

    void saveBookFile(BookFileInfo bookFileInfo);
}
