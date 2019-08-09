package com.rrg.gz.controller;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User权限
 *
 * @author huangsz  2018/10/20 0020
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping("/getInfo")
    @RequiresRoles(value={"user","admin"},logical= Logical.OR)
    @RequiresPermissions(value={"save"},logical= Logical.OR)
    public String getInfo() {
        return "您拥有用户权限，可以获得该接口的信息！";
    }
}
