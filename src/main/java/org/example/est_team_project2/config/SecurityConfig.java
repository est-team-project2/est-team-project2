package org.example.est_team_project2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .formLogin(form -> form.disable())
                .oauth2Login(Customizer.withDefaults())
                .authorizeHttpRequests(
                        auth -> auth.requestMatchers("/")
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                )
                .build();
    }
}
