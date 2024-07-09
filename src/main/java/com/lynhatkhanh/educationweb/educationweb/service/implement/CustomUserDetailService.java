package com.lynhatkhanh.educationweb.educationweb.service.implement;

import com.lynhatkhanh.educationweb.educationweb.model.UserAccount;
import com.lynhatkhanh.educationweb.educationweb.model.UserRole;
import com.lynhatkhanh.educationweb.educationweb.model.CustomUserDetail;
import com.lynhatkhanh.educationweb.educationweb.service.UserAccountService;
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

    @Autowired
    public CustomUserDetailService(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount user = userAccountService.findByUserName(username);
        System.out.println(username);
//        System.out.println(user);
        if (user == null)
            throw new UsernameNotFoundException("Invalid username");

        Collection<GrantedAuthority> grantedAuthorities = new HashSet<>();
        Set<UserRole> roles = user.getUserRole();

        for (UserRole userRole : roles)
            grantedAuthorities.add(new SimpleGrantedAuthority(userRole.getRole().getName()));
        return new CustomUserDetail(user, grantedAuthorities);

    }
}
