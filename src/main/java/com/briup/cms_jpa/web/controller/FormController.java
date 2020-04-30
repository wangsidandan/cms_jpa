package com.briup.cms_jpa.web.controller;

import com.briup.cms_jpa.util.Message;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/*
    SecurityConfig已经配置了登陆入口
    此处需要对应的逻辑
    空实现，由SpringSecurity去处理
 */
@RestController
public class FormController {
    @ApiOperation("登陆逻辑")
    @PostMapping("/authentication/form")
    public void form(String username,String password){
    }
}
