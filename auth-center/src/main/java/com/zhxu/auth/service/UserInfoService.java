package com.zhxu.auth.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@FeignClient("SYSTEM-INFO")
public class UserInfoService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 调用FeignClient查询用户

        // 调用FeignClient查询角色

        // 获取用户权限列表

        // 返回带有用户权限信息的User

        return null;
    }
}
