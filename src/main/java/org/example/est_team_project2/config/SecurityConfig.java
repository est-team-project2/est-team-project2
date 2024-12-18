package org.example.est_team_project2.config;

import lombok.RequiredArgsConstructor;
import org.example.est_team_project2.service.member.OauthService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final OauthService oauthService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 비밀번호 암호화 비활성화
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                )// CSRF 활성화

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
                        .loginPage("/login") // 사용자 정의 로그인 페이지
                        .defaultSuccessUrl("/", true) // 성공 시 이동할 URL
                        .usernameParameter("email") // 사용자명 대신 이메일 필드를 사용하도록 설정
                        .failureUrl("/login") // 실패 시 이동할 URL
                        .permitAll() // 로그인 페이지 접근 허용
                )

                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                )

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login")
                        .permitAll() // 로그인 페이지 접근 허용
                        .requestMatchers("/profile")
                        .authenticated()
                        .anyRequest()
                        .permitAll() // 나머지 요청은 모두 허용
                )



                .build();
    }
}
