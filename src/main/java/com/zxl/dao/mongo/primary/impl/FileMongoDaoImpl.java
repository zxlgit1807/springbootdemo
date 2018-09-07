package com.zxl.dao.mongo.primary.impl;

import com.zxl.dao.common.MongoDao;
import com.zxl.dao.mongo.primary.IFileMongoDao;
import com.zxl.entity.mongo.primary.BookFileInfo;
import org.springframework.stereotype.Repository;

/**
 * @Auther: ZXL
 * @Date: 2018/9/7
 * @Description:
 */
@Repository
public class FileMongoDaoImpl  extends MongoDao<BookFileInfo> implements IFileMongoDao {
}
