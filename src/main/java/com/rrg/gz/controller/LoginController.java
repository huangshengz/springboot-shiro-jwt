package com.rrg.gz.controller;

import com.rrg.gz.dao.UserDao;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String test(){
        return "测试通过！";
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "notLogin", method = RequestMethod.GET)
    public String notLogin(){
        return "您尚未登录！";
    }

    @RequestMapping(value = "notRole", method = RequestMethod.GET)
    public String notRole(){
        return "您没有权限！";
    }


    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        // 注销
        subject.logout();
        return "成功注销！";
    }


    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(String username, String password){
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 执行登录
        subject.login(token);
        // 根据权限，返回指定数据
        String role = userDao.getRoleByUserName(username);
        if ("user".equals(role)) {
            return "欢迎登录！";
        }
        if ("admin".equals(role)) {
            return "欢迎来到管理员界面";
        }
        return "权限错误！";
    }
}
