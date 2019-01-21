package com.zhxu.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        // 配置登录页/login 并允许访问
        .formLogin().permitAll()
        // 登出页
        .and().logout().logoutUrl("/logout").logoutSuccessUrl("/")
        // 其它所有请求全部需要鉴权
        .and().authorizeRequests().anyRequest().authenticated()
        // 由于使用的是JWT，我们这里不需要csrf
        .and().csrf().disable();
    }
}
