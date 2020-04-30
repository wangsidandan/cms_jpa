package com.briup.cms_jpa.config;

import com.briup.cms_jpa.web.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //产生加密实例
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Autowired
    @Qualifier("customerService")
    private UserDetailsService userDetailService;
    @Bean
    public JwtAuthenticationTokenFilter getauthenticationTokenFilterBean() {
        return new JwtAuthenticationTokenFilter();
    }
    @Bean
    public LoginSuccessHandler getLoginSuccessHandler() {
        return new LoginSuccessHandler();
    }
    @Bean
    public LoginFailHandler getLoginFailHandler() {
        return new LoginFailHandler();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                //配置：如果没有携带token就访问该路径
                .loginPage("/authenticaion/login")
                //配置由该入口进行登陆获取token
                .loginProcessingUrl("/authentication/form")
                //配置登陆成功后处理页面：产生token并放入请求头中
                .successHandler(getLoginSuccessHandler())
                //配置登陆失败后处理页面：返回错误信息
                .failureHandler(getLoginFailHandler())
                .and()
                .csrf().disable() //使用jwt，不需要csrf
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //基于token，不需要session
                .and()
                .authorizeRequests()
                // 设置允许访问的资源
                .antMatchers("/authenticaion/login").permitAll()
                .antMatchers("/authentication/form").permitAll()
                .antMatchers("/index/**").permitAll()
                .antMatchers("/article/download").permitAll()
                // 设置允许访问的资源
                .antMatchers("/webjars/**").permitAll()
                .antMatchers(
                        "/v2/api-docs",
                        "/swagger-resources",
                        "/swagger-resources/**",
                        "/configuration/ui",
                        "/configuration/security",
                        "/swagger-ui.html/**",
                        "/webjars/**"
                ).permitAll()
                .anyRequest().authenticated();
        // 禁用缓存
        http.headers().cacheControl();
        // 添加JWT filter
        http.addFilterBefore(getauthenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
    }
}

