package com.springSecurity.config;

import com.springSecurity.entity.User;
import com.springSecurity.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserInitializer {

    @Bean
    public CommandLineRunner insertDefaultUser(UserRepository userRepository,
                                               PasswordEncoder passwordEncoder) {

        return args -> {
            if (userRepository.findByUserName("admin").isEmpty()) {
                User user = new User();
                user.setUserName("admin");
                user.setPassword(passwordEncoder.encode("admin123"));
                user.setRole("ROLE_ADMIN");

                userRepository.save(user);

                System.out.println("Default user inserted.");
            }
        };
    }
}