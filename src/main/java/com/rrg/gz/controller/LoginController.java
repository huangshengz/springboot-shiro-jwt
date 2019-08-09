package com.rrg.gz.controller;

import com.rrg.gz.JwtUtil;
import com.rrg.gz.dao.UserDao;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author huangsz  2018/10/19 0019
 */
@RestController
public class LoginController {

    @Autowired
    private UserDao userDao;

    @RequestMapping("/")
    public String test() {
        return "测试通过！";
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        // 注销
        subject.logout();
        return "成功注销！";
    }


    /**
     * 登录成功之后返回token
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(String username, String password) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return "用户名或密码为空";
        }
        String realPassword = userDao.getPasswordByUserName(username);
        if (realPassword == null) {
            return "用户名错误";
        } else if (!realPassword.equals(password)) {
            return "密码错误";
        } else {
            return JwtUtil.createToken(username);
        }
    }

    @RequestMapping(path = "/unauthorized")
    public String unauthorized(String message) {
        System.out.println(message);
        return message;
    }
}
