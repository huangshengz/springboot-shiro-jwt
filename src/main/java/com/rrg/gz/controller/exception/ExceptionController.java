package com.rrg.gz.controller.exception;

import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author ====> huangsz
 * @date ====> 2019/7/26
 */
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(ShiroException.class)
    public String ex500(Exception e){
        return e.getMessage();
    }

    @ExceptionHandler(AuthenticationException.class)
    public String tokenError(Exception e){
        return e.getMessage();
    }
}
