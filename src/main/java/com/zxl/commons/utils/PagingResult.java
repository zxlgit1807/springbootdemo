package com.zxl.commons.utils;

import lombok.Data;

/**
 * @Auther: ZXL
 * @Date: 2018/9/17
 * @Description: 分页参数
 */
@Data
public class PagingResult {
    private int pageNo;
    private int pageSize;
    private long totalPage;
    private long totalCount;
}
