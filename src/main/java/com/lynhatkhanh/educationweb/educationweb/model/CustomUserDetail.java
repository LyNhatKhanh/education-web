package com.lynhatkhanh.educationweb.educationweb.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class CustomUserDetail implements UserDetails {

    private UserAccount userAccount;
    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetail() {
        super();
    }

    public CustomUserDetail(UserAccount userAccount, Collection<? extends GrantedAuthority> authorities) {
        super();
        this.userAccount = userAccount;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        /*List<SimpleGrantedAuthority> arr = new ArrayList<>();

        Set<UserRole> roles = this.userAccount.getUserRole();
        for (UserRole userRole: roles) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRoleService.findUserRoleAndRoleByUserRoleId(userRole.getId()).getRole().getName());
            arr.add(authority);
        }*/
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return userAccount.getPassword();
    }

    @Override
    public String getUsername() {
        return userAccount.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
//        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
//        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
//        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return userAccount.getEnabled();
//        return UserDetails.super.isEnabled();
    }
}
