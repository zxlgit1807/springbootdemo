package com.zxl.dao;

import com.zxl.commons.emuns.UserSexEnum;
import com.zxl.entity.ZxlUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 用户mapper
 */
@Mapper
public interface ZxlUserMapper {

    @Insert("insert into zxl_user (user_name, login_name, loign_pwd, birth_day, create_time)\n" +
            " values (#{userName,jdbcType=VARCHAR}, #{loginName,jdbcType=VARCHAR}, \n" +
            " #{loignPwd,jdbcType=VARCHAR}, #{birthDay,jdbcType=DATE}, #{createTime,jdbcType=TIMESTAMP})")
    void saveUser(ZxlUser user);

    /**
     * 根据登录名查询用户
     * @Result 修饰返回的结果集，关联实体类属性和数据库字段一一对应，如果实体类属性和数据库属性名保持一致，就不需要这个属性来修饰。
     * @param loginName
     * @return
     */
    @Select( "SELECT * FROM zxl_user us WHERE us.login_name = #{loginName}" )
    @Results({
            @Result(property = "sex",  column = "sex", javaType = UserSexEnum.class),
    })
    ZxlUser listUsers(String loginName);

    @Delete("DELETE FROM zxl_user WHERE id = #{id}")
    void deleteUserById(Integer id);
}