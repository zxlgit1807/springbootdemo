package com.zxl.dao;

import com.zxl.entity.ZxlUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ZxlUserMapper {

    @Insert("insert into zxl_user (user_name, login_name, loign_pwd, birth_day, create_time)\n" +
            " values (#{userName,jdbcType=VARCHAR}, #{loginName,jdbcType=VARCHAR}, \n" +
            " #{loignPwd,jdbcType=VARCHAR}, #{birthDay,jdbcType=DATE}, #{createTime,jdbcType=TIMESTAMP})")
    void saveUser(ZxlUser user);

    @Select( "SELECT * FROM zxl_user us WHERE us.login_name = #{loginName}" )
    ZxlUser listUsers(String loginName);
}