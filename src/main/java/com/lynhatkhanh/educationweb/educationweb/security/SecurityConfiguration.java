package com.lynhatkhanh.educationweb.educationweb.security;

import com.lynhatkhanh.educationweb.educationweb.service.implement.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private CustomUserDetailService customUserDetailService;

    @Autowired
    public SecurityConfiguration(CustomUserDetailService customUserDetailService) {
        this.customUserDetailService = customUserDetailService;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.userDetailsService(customUserDetailService)
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers("/*").permitAll()
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
                )
                .formLogin(login ->
                        login
                                .loginPage("/showMyLoginPage")
                                .loginProcessingUrl("/authenticateTheUser")
//                                .usernameParameter("username")
//                                .passwordParameter("password")
//                                .defaultSuccessUrl("/admin", true)
                                .permitAll()
                )
                .logout(logout ->
                        logout
                                .permitAll())
                .exceptionHandling(handle ->
                        handle
                                .accessDeniedPage("/access-denied")
                );

        return httpSecurity.build();
    }

    /*asking Spring Security to ignore DispatcherServletDelegating [ant = Ant [pattern='/static/**']*/
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web -> web.ignoring().requestMatchers("/static/**", "assets/**"));
    }

}
