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
import org.springframework.security.web.access.AccessDeniedHandler;

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
                .csrf().disable()
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers("/*").permitAll()
                                .requestMatchers("/api/*").permitAll()
                                .requestMatchers(HttpMethod.GET, "/admin/**").hasAnyRole("INSTRUCTOR", "ADMIN")
                                .requestMatchers(HttpMethod.POST, "/admin/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
                )
                .formLogin(login ->
                        login
                                .loginPage("/showMyLoginPage")
                                .loginProcessingUrl("/authenticateTheUser")
                                .permitAll()
                )
                .logout(logout ->
                        logout
                                .permitAll())
                .exceptionHandling(handle ->
                        handle
//                                .accessDeniedPage("/access-denied")
                                .accessDeniedHandler(customAccessDeniedHandler())
                );

        return httpSecurity.build();
    }

    private AccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    /*asking Spring Security to ignore DispatcherServletDelegating [ant = Ant [pattern='/static/**']*/
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web -> web.ignoring().requestMatchers("/static/**", "assets/**"));
    }

}
