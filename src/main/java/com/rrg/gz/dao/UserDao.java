package com.rrg.gz.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author huangsz  2018/10/19 0019
 */
@Mapper
public interface UserDao {

    /**
     * 根据用户名获取角色
     * @param username
     * @return
     */
    @Select("select role from user where username = #{username}")
    String getRoleByUserName(String username);

    /**
     * 根据用户名获取密码
     * @param username
     * @return
     */
    @Select("select password from user where username = #{username}")
    String getPasswordByUserName(String username);
}
