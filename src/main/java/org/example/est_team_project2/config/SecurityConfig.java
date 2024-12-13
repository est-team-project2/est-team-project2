package org.example.est_team_project2.config;

import org.example.est_team_project2.domain.eunm.MemberType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.Customizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 비밀번호 암호화 비활성화
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // CSRF 비활성화
                .oauth2Login(Customizer.withDefaults())
                .formLogin(form -> form
                        .loginPage("/login") // 사용자 정의 로그인 페이지
                        .defaultSuccessUrl("/", true) // 성공 시 이동할 URL
                        .usernameParameter("email") // 사용자명 대신 이메일 필드를 사용하도록 설정
                        .failureUrl("/login") // 실패 시 이동할 URL
                        .permitAll() // 로그인 페이지 접근 허용
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login")
                        .permitAll() // 로그인 페이지 접근 허용


//                        .requestMatchers("/user/**")
//                        .hasAnyAuthority(MemberType.USER.getAuthority()) // USER 권한 필요
//                        .requestMatchers("/expert/**")
//                        .hasAnyAuthority(MemberType.EXPERT.getAuthority(), MemberType.ADMIN.getAuthority()) // EXPERT 또는 ADMIN 권한 필요
//                        .requestMatchers("/admin/**")
//                        .hasAnyAuthority(MemberType.ADMIN.getAuthority()) // ADMIN 권한 필요


                        .anyRequest()
                        .permitAll() // 나머지 요청은 모두 허용
                )
                .build();
    }

}
