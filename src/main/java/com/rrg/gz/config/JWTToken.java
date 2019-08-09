package com.rrg.gz.config;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author ====> huangsz
 * @date ====> 2019/7/15
 */
public class JWTToken implements AuthenticationToken {

    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

}
