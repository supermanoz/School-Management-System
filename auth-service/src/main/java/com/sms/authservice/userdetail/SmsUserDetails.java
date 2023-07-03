package com.sms.authservice.userdetail;

import com.sms.model.user_management.User;
import com.sms.pojo.UserDetailsPojo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class SmsUserDetails implements UserDetails {

    private UserDetailsPojo user;

    public SmsUserDetails(UserDetailsPojo user){
        this.user=user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<String> authoritiesList = Arrays.asList(user.getAuthorities().split("\\s*,\\s*"));
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authoritiesList.forEach(authority -> {
            authorities.add(new SimpleGrantedAuthority(authority));
            System.out.println("Authority set: "+authority);
        });
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
