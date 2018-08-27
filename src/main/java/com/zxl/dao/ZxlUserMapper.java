package com.zxl.dao;

import com.zxl.entity.ZxlUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ZxlUserMapper {

    @Insert("insert into zxl_user (user_name, login_name, loign_pwd, birth_day, create_time)\n" +
            " values (#{userName,jdbcType=VARCHAR}, #{loginName,jdbcType=VARCHAR}, \n" +
            " #{loignPwd,jdbcType=VARCHAR}, #{birthDay,jdbcType=DATE}, #{createTime,jdbcType=TIMESTAMP})")
    void saveUser(ZxlUser user);

}