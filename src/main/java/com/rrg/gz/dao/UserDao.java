package com.rrg.gz.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author huangsz  2018/10/19 0019
 */
@Mapper
public interface UserDao {

    /**
     * 根据用户名获取角色
     *
     * @param username
     * @return
     */
    @Select("SELECT\n" +
            "\trole_name\n" +
            "FROM\n" +
            "\trole r\n" +
            "LEFT JOIN user_role ur ON r.role_id = ur.role_id\n" +
            "LEFT JOIN USER u ON ur.user_id = u.user_id where u.username = #{username}")
    List<String> getRoleByUserName(String username);

    /**
     * 根据用户名获取密码
     *
     * @param username
     * @return
     */
    @Select("select password from user where username = #{username}")
    String getPasswordByUserName(String username);
}
