package com.briup.cms_jpa.aop;

import com.briup.cms_jpa.exception.CustomerException;
import com.briup.cms_jpa.util.Message;
import com.briup.cms_jpa.util.MessageUtil;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomerExceptionHandler {
    @ExceptionHandler(value =  Exception.class) // 捕获 Controller 中抛出的指定类型的异常，也可以指定其他异常
    public  Message handler(Exception exception){
        System.out.println(exception.getClass());
        exception.printStackTrace();
        if(exception instanceof CustomerException){
            return MessageUtil.error(exception.getMessage());
        }
        return MessageUtil.error("后台接口异常！");
    }
}
