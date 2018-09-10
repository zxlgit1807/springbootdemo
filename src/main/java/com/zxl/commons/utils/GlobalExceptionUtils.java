package com.zxl.commons.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: ZXL
 * @Date: 2018/9/10
 * @Description: 全局异常捕获类，应该如果更好的去应用
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionUtils {

    @ExceptionHandler(value = Exception.class)
    public ResultResponse  defaultErrorHandler(HttpServletRequest req, Exception e)  {
        if (e instanceof HttpRequestMethodNotSupportedException){
            return ResultResponse.error("不支持的请求方式/"+e.getMessage());
        }
        log.error("【未知异常】："+e.getMessage(), e);
        return ResultResponse.error();
    }
}
