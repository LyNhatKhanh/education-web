package com.lynhatkhanh.educationweb.educationweb.service.exclude.implement;

import com.lynhatkhanh.educationweb.educationweb.model.UserAccount;
import com.lynhatkhanh.educationweb.educationweb.model.UserRole;
import com.lynhatkhanh.educationweb.educationweb.model.CustomUserDetail;
import com.lynhatkhanh.educationweb.educationweb.service.exclude.UserAccountService;
import com.lynhatkhanh.educationweb.educationweb.service.exclude.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private UserAccountService userAccountService;
    private UserRoleService userRoleService;

    @Autowired
    public CustomUserDetailService(UserAccountService userAccountService, UserRoleService userRoleService) {
        this.userAccountService = userAccountService;
        this.userRoleService = userRoleService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount user = userAccountService.findUserAndUserRoleByUsername(username);
        System.out.println(username);
//        System.out.println(user);
        if (user == null)
            throw new UsernameNotFoundException("Invalid username");

        Collection<GrantedAuthority> grantedAuthorities = new HashSet<>();
        Set<UserRole> roles = user.getUserRole();

        for (UserRole userRole : roles)
//            grantedAuthorities.add(new SimpleGrantedAuthority(userRole.getRole().getName()));
            grantedAuthorities.add(new SimpleGrantedAuthority(userRoleService.findUserRoleAndRoleByUserRoleId(userRole.getId()).getRole().getName()));
        return new CustomUserDetail(user, grantedAuthorities);

    }
}
