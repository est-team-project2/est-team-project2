package org.example.est_team_project2.config;

import lombok.RequiredArgsConstructor;
import org.example.est_team_project2.service.member.OauthService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final OauthService oauthService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // CSRF 비활성화

                .sessionManagement(session -> session
                        .sessionFixation(sessionFixation -> sessionFixation.migrateSession())
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(true)
                )

                .oauth2Login( oauth2 -> oauth2
                        .loginPage("/signin")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/signin?error=true")
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(oauthService)
                        )
                )

                .formLogin(form -> form
                        .loginPage("/signin") // 사용자 정의 로그인 페이지
                        .defaultSuccessUrl("/", true) // 성공 시 이동할 URL
                        .usernameParameter("email") // 사용자명 대신 이메일 필드를 사용하도록 설정
                        .failureUrl("/signin") // 실패 시 이동할 URL
                        .permitAll() // 로그인 페이지 접근 허용
                )

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/signin")
                        .permitAll() // 로그인 페이지 접근 허용
                        .requestMatchers("/profile")
                        .authenticated()
                        .anyRequest()
                        .permitAll() // 나머지 요청은 모두 허용
                )

                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                )

                .build();
    }

}
