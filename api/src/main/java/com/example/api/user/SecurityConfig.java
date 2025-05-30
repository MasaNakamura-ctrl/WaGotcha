package com.example.api.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable()) // CSRF無効化（Postmanなどでテストするため）
        .authorizeHttpRequests(authz -> authz
            .requestMatchers("/api/login").permitAll() // ← loginは誰でもアクセス可能
            .anyRequest().authenticated() // その他は認証必須
        )
        .httpBasic(httpBasic -> {}); // 必要ならBasic認証有効化

        return http.build();
    }
}