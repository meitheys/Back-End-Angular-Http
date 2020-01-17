package com.hbsis.http.security.jwt;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

public class JwtResponse {

    private String type = "Bearer";
    private String token;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtResponse(String token, Collection<? extends GrantedAuthority> authorities){
        this.token = token;
        this.authorities = authorities;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
