package com.lynhatkhanh.educationweb.educationweb.configuration;

import com.lynhatkhanh.educationweb.educationweb.constant.PredefinedRole;
import com.lynhatkhanh.educationweb.educationweb.dao.RoleRepository;
import com.lynhatkhanh.educationweb.educationweb.dao.UserRepository;
import com.lynhatkhanh.educationweb.educationweb.entity.Role;
import com.lynhatkhanh.educationweb.educationweb.entity.User;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j // inject logger at console by Lombok
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;

    @NonFinal
    static final String ADMIN_USER_NAME = "admin";

    @NonFinal
    static final String ADMIN_PASSWORD = "admin";

    @Bean
    /*@ConditionalOnProperty(
            prefix = "spring",
            value = "datasource.driver",
            havingValue = "com.mysql.jdbc.Driver")*/
    ApplicationRunner applicationRunner(UserRepository userRepository, RoleRepository roleRepository) {
        log.info("Initializing application.....");
        return args -> {
            if (userRepository.existsByUsername(ADMIN_USER_NAME)) {
                if (!roleRepository.existsById(PredefinedRole.USER_ROLE)) {
                    roleRepository.save(Role.builder()
                            .name(PredefinedRole.USER_ROLE)
                            .description("User role!")
                            .build());
                }

                Role adminRole = adminRole = roleRepository.findById(PredefinedRole.ADMIN_ROLE)
                        .orElse(roleRepository.save(Role.builder()
                                .name(PredefinedRole.ADMIN_ROLE)
                                .description("Admin role")
                                .build()));

                var roles = new HashSet<Role>();
                roles.add(adminRole);

                User admin = User.builder()
                        .username(ADMIN_USER_NAME)
                        .password(passwordEncoder.encode(ADMIN_PASSWORD))
                        .roles(roles)
                        .enabled(true)
                        .build();

                userRepository.save(admin);
                log.warn("admin user has been created with default password: admin, please change it");
            }
            log.info("Application initialization completed .....");
        };
    }

}
