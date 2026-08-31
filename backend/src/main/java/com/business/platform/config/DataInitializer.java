package com.business.platform.config;

import com.business.platform.user.AppUser;
import com.business.platform.user.AppUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner init(AppUserRepository repo, PasswordEncoder encoder) {

        return args -> {

            if (repo.findByUsername("admin").isEmpty()) {

                AppUser user = new AppUser();

                user.setUsername("admin");
                user.setPassword(encoder.encode("Admin@123"));
                user.setRole("ADMIN");
                user.setEnabled(true);

                // createdAt is automatically set by @PrePersist in AppUser
                repo.save(user);
            }
        };
    }
}
