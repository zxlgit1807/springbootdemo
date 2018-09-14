package com.zxl.dao.mysql.two;

import com.zxl.entity.ZxlUser;
import org.apache.ibatis.annotations.*;

/**
 * 用户mapper
 */
@Mapper
public interface TwoZxlUserMapper {

    @Insert("insert into zxl_user (user_name, login_name, login_pwd,sex, birth_day, create_time)\n" +
            " values (#{userName,jdbcType=VARCHAR}, #{loginName,jdbcType=VARCHAR}, \n" +
            " #{loginPwd,jdbcType=VARCHAR},#{sex}, #{birthDay,jdbcType=DATE}, #{createTime,jdbcType=TIMESTAMP})")
    void saveUser(ZxlUser user);

    /**
     * 根据登录名查询用户
     * @Result 修饰返回的结果集，关联实体类属性和数据库字段一一对应，如果实体类属性和数据库属性名保持一致，就不需要这个属性来修饰。
     * 或者直接用别名
     * @param loginName
     * @return
     */
    @Select( "SELECT * FROM zxl_user us WHERE us.login_name = #{loginName}" )
    @Results({
            @Result(property = "userName",  column = "user_name"),
            @Result(property = "loginName",  column = "login_name"),
            @Result(property = "loginPwd",  column = "login_pwd"),
            @Result(property = "birthDay",  column = "birth_day"),
            @Result(property = "createTime",  column = "create_time"),
            @Result(property = "updateTime",  column = "update_time"),
    })
    ZxlUser getUser(@Param("loginName") String loginName);

    @Delete("DELETE FROM zxl_user WHERE id = #{id}")
    void deleteUserById(Integer id);
}