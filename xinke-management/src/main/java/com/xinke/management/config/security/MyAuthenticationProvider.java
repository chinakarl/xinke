package com.xinke.management.config.security;

import com.xinke.management.service.impl.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;

/**
 * @author: zhaihx
 * @description:
 * @date:2019/10/25
 */
public class MyAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    MyUserDetailService userService;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        MyUserDetail user = (MyUserDetail) userService.loadUserByUsername(username);

        //加盐
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String salPwd = passwordEncoder.encode(password);

        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

        return new UsernamePasswordAuthenticationToken(user, salPwd, authorities);
    }

    @Override
    public boolean supports(Class<?> aClass)
    {
        return true;
    }
}
