package com.briup.cms_jpa.config;

import com.briup.cms_jpa.exception.CustomerException;
import com.briup.cms_jpa.util.JwtTokenUtils;
import com.briup.cms_jpa.util.MessageUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    @Qualifier("customerService")
    private UserDetailsService userDetailsService;
    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        /*
            产生token 参数需要UserDetail和isRembler
            UserDetail：注入实现了UserDetailsService的类，调用loadByUserName方法
                loadByUsername需要传字符串name,可以通过authentication调用.getName方法获得。
         */
        try {
            String token = JwtTokenUtils.createToken(userDetailsService.loadUserByUsername(authentication.getName()), false);
            //拼接前缀 Bearer+ token
            token=JwtTokenUtils.TOKEN_PREFIX+token;
            //设置请求头 Authorization:token
            response.setHeader(JwtTokenUtils.TOKEN_HEADER,token);
            //将信息写回json  但是此处不是SpringMVC，需要手动转json，注入ObjectMapper对象
            response.getWriter().write(objectMapper.writeValueAsString(MessageUtil.success(token, "登陆成功")));
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println(objectMapper.writeValueAsString(MessageUtil.unAuthorized("登陆失败")));
        }
    }
}
