package org.example.est_team_project2.config;

import lombok.RequiredArgsConstructor;
import org.example.est_team_project2.service.member.OauthService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.session.HttpSessionEventPublisher;


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
                // 비활성화
                .csrf(csrf -> csrf.disable())
                // CSRF 활성화
//                .csrf(csrf -> csrf
//                        .csrfTokenRepository(new HttpSessionCsrfTokenRepository())
//                        .ignoringRequestMatchers("/signin")
//                )
                .sessionManagement(session -> session
                        .sessionFixation(sessionFixation -> sessionFixation.migrateSession())
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(true)
                        .expiredUrl("/logout")
                )

            .oauth2Login(oauth2 -> oauth2
                .loginPage("/signin")
                .defaultSuccessUrl("/", true)
                .failureUrl("/signin")
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



            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
            )

            .build();
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }
}
