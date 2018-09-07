package com.zxl.entity.mongo.primary;

import lombok.Data;
import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Auther: ZXL
 * @Date: 2018/9/7
 * @Description: 图书类
 */
@Data
@Document(collection = "BOOKFILE")
public class BookFileInfo {

    private String id;

    /** 文档类型 */
    private String mimetype;

    /** 文档大小 */
    private Integer documentsize;

    /** 文档内容 */
    private Binary content;

    /** 文档名称 */
    private String documentname;
}
