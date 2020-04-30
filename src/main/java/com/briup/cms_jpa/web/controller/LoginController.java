package com.briup.cms_jpa.web.controller;

import com.briup.cms_jpa.util.Message;
import com.briup.cms_jpa.util.MessageUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;
/*
    SpringConfig以及配置过
    此处对应逻辑代码
    当没有携带token时，提醒登陆拿token值
 */
@RestController
@ApiIgnore
public class LoginController {
    @GetMapping("/authenticaion/login")
    public Message login(){
        return MessageUtil.unAuthorized("该用户没有登陆");
    }
}
