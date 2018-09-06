package com.zxl.commons.emuns;

/**
 * 性别
 * @author zxl
 * @date 2018/9/5
 */
public enum UserSexEnum {
    MAN(0, "男"), WOMAN(1, "女");

    private int code;
    private String name;

    UserSexEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

}
