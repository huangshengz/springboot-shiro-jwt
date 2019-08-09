package com.rrg.gz.controller;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangsz  2018/10/20 0020
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @PostMapping("/getInfo")
    @RequiresRoles("admin")
    public String getInfo() {
        return "您拥有管理员权限，可以获得该接口的信息！";
    }
}
