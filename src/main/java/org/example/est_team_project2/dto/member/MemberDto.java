package org.example.est_team_project2.dto.member;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.est_team_project2.domain.member.Member;
import org.example.est_team_project2.domain.member.memberEnums.MemberType;
import org.example.est_team_project2.domain.member.memberEnums.SocialType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDto implements UserDetails {


    @Email
    @NotBlank(message = "이메일을 입력 해주세요") //공백도 허용 안할거라서 Blank를 사용한다.
    private String email;

    @NotBlank(message = "닉네임을 입력 해주세요")
    @Size(min = 2, max = 20, message = "닉네임을 제대로 입력 해주세요")
    private String nickName;

    @NotBlank(message = "비밀번호를 입력 해주세요")
    @Size(min = 8, max = 15, message = "비밀번호를 제대로 입력 해주세요.")
    private String password;

    private MemberType role = MemberType.USER;

    private SocialType socialType = SocialType.FORMBASED;

    private LocalDateTime updateAt = LocalDateTime.now();


    // Service 의 loadUserByUsername 에서 사용
    public MemberDto (Member member) {
        this.email = member.getEmail();
        this.nickName = member.getNickName();
        this.password = member.getPassword();
        this.role = member.getRole();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.toString()));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.nickName;
    }

}
