package org.example.est_team_project2.dto.member;



import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.est_team_project2.domain.member.Member;
import org.example.est_team_project2.domain.member.memberEnums.MemberType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
public class MemberDto implements UserDetails {

    private String email;
    private String password;
    private MemberType role;


    public MemberDto (Member member) {
        this.email = member.getEmail();
        this.password = member.getPassword();
        this.role = member.getRole();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

}
