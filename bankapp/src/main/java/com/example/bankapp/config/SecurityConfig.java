
package com.example.bankapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Tắt CSRF để test dễ
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/**").permitAll() // Mở quyền truy cập API
                        .anyRequest().permitAll() // Còn lại cũng cho qua hết
                );
        return http.build();
    }
}
