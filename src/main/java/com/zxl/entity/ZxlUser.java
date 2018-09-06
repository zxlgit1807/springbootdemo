package com.zxl.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class ZxlUser implements Serializable {
    private Integer id;

    private String userName;

    private String loginName;

    private String loginPwd;

    private Integer sex;
    //将接收到的String转为date
    //@JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date birthDay;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

}