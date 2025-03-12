package com.emptrack.empTrack.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()
            )
            .csrf().disable(); // CSRF 비활성화
            /*.authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/**").permitAll()  // 공개 경로 허용
                .anyRequest().authenticated() // 그 외 요청 인증 필요
            )
            .formLogin(login -> login
                .loginPage("/login")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .permitAll()
            );*/

        return http.build();
    }
}
