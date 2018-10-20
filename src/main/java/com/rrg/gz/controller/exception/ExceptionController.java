package com.rrg.gz.controller.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.channels.AcceptPendingException;

/**
 * 捕捉异常
 * @author huangsz  2018/10/20 0020
 */
@RestControllerAdvice
public class ExceptionController {

    /**
     * 捕捉 CustomRealm 抛出的异常
     * @param e
     * @return
     */
    @ExceptionHandler(AcceptPendingException.class)
    public String handleShiroException(Exception e){
        return e.getMessage();
    }
}
