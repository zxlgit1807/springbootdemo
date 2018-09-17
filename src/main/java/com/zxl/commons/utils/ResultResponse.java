package com.zxl.commons.utils;

import lombok.Data;

/**
 * @Auther: ZXL
 * @Date: 2018/9/10
 * @Description: 统一返回类
 */
@Data
public class ResultResponse {

    private boolean success = true;

    private PagingResult pagingResult;

    private String msg;

    private Object obj;

    public static ResultResponse error(){
        ResultResponse r = new ResultResponse();
        r.setSuccess(false);
        r.setMsg("系统异常，请稍后重试");
        return r;
    }
    public static ResultResponse error(String msg){
        ResultResponse r = new ResultResponse();
        r.setSuccess(false);
        r.setMsg(msg == null ? "系统异常，请稍后重试" : msg);
        return r;
    }
}
