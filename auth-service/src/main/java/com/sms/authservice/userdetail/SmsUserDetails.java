package com.sms.authservice.userdetail;

import com.sms.pojo.user_management.UserDetailsPojo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class SmsUserDetails implements UserDetails {

    private static final Logger LOGGER= LoggerFactory.getLogger(SmsUserDetails.class);
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
           LOGGER.trace("Authority set: "+authority);
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
