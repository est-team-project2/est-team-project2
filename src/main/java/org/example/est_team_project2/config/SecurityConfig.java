package org.example.est_team_project2.config;

import lombok.RequiredArgsConstructor;

import org.example.est_team_project2.service.member.OauthService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


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
//                // 비활성화
//                .csrf(csrf -> csrf.disable())
            // CSRF 활성화
            .csrf(csrf -> csrf
                .requireCsrfProtectionMatcher(new AntPathRequestMatcher("/signup", "POST"))
            )

            .sessionManagement(session -> session
                .sessionFixation(sessionFixation -> sessionFixation.migrateSession())
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true)
                .expiredUrl("/logout")
            )

            .oauth2Login(oauth2 -> oauth2
                .loginPage("/signin")
                .defaultSuccessUrl("/", true)
                .failureUrl("/")
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
//              //권한 미부여
//            .authorizeHttpRequests(auth -> auth
//                .requestMatchers("/signin")
//                .permitAll() // 로그인 페이지 접근 허용
//                .requestMatchers("/profile")
//                .authenticated()
//                .anyRequest()
//                .permitAll() // 나머지 요청은 모두 허용
//            )

                //권한부여
                .authorizeHttpRequests(auth -> auth
                        // 모든 사용자
                        .requestMatchers(
                                //CSS, JS, Fragments
                                "/CSS/**", "/JS/**", "/styles/**", "/images/**", "/uploads/**","/fragments/**",
                                //로그인 관련
                                "/", "/signin", "/signup/**", "/find-password", "/logout",
                                //백과 관련
                                "/pedia", "/pedia/detail/**", "/pedia/history/**", "/pedia/version/**",
                                //도구 관련
                                "/tools/**")
                        .permitAll()

                        //회원, 전문가, 관리자
                        .requestMatchers(
                                //회원 정보 관련
                                "/my","/my/update-nickname","/my/update-password",
                                //백과 수정 요청 관련
                                "/pedia/edit-request/**", "/sendEditInfo/**",
                                //게시판 관련
                                "/posts/**", "/comments/**")
                        .hasAnyAuthority("USER", "EXPERT", "ADMIN")

                        // 전문가,관리자
                        .requestMatchers(
                                //백과 수정 요청 검토 관련
                                "/view-edit-request", "/view-edit-request/detail/**", "/RequestAccept", "/RequestDecline")
                        .hasAnyAuthority("EXPERT", "ADMIN")

                        // 관리자
                        .requestMatchers(
                                //관리자
                                "/admin/**", "/registerOnlyBreed")
                        .hasAnyAuthority("ADMIN")

                        .anyRequest().denyAll()
                )
                .exceptionHandling(exception -> exception
                        .accessDeniedHandler((request, response, accessDeniedException) ->
                                response.sendRedirect("/"))
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
