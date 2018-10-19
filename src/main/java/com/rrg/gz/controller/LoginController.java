package com.rrg.gz.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangsz  2018/10/19 0019
 */
@RestController
public class LoginController {

    @RequestMapping("/")
    public String test(){
        return "测试通过！";
    }
}
