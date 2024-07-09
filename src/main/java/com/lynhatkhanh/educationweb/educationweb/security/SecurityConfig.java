//package com.lynhatkhanh.educationweb.educationweb.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.provisioning.JdbcUserDetailsManager;
//import org.springframework.security.provisioning.UserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public UserDetailsManager userDetailsManager(DataSource dataSource) {
//
//        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//
//        jdbcUserDetailsManager.setUsersByUsernameQuery(
//                "SELECT user_id, password, active FROM member WHERE user_id=?"
//        );
//
//        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
//                "SELECT user_id, role FROM role WHERE user_id=?"
//        );
//
//        return jdbcUserDetailsManager;
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.authorizeHttpRequests(configurer ->
//                        configurer
//                                .requestMatchers(HttpMethod.GET, "/admin").hasRole("ADMIN")
//                                .requestMatchers(HttpMethod.GET, "/admin/**").hasRole("ADMIN")
//                                .requestMatchers(HttpMethod.POST, "/admin/**").hasRole("ADMIN")
//                                .requestMatchers(HttpMethod.DELETE, "/admin/**").hasRole("ADMIN")
//                                .anyRequest().permitAll()
//                )
//                .formLogin(form ->
//                        form
//                                .loginPage("/showMyLoginPage")
//                                .loginProcessingUrl("/authenticateTheUser")
//                                .permitAll()
//                )
//                .logout(logout ->
//                        logout
//                                .permitAll())
//                .exceptionHandling(configurer ->
//                        configurer
//                                .accessDeniedPage("/access-denied"));
//
//        return httpSecurity.build();
//    }
//
//}
