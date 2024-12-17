package org.example.est_team_project2.dto.member;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.est_team_project2.domain.member.memberEnums.MemberType;
import org.example.est_team_project2.domain.member.memberEnums.SocialType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Getter
public class MemberDetails implements OAuth2User {

    private Long id;

    private String email;

    private SocialType socialType;

    private String password;

    @Setter
    private MemberType memberType;


    private String nickname;

    private Map<String, Object> attributes;

    @Builder
    public MemberDetails(Map<String, Object> attributes, String email, String name, String password,SocialType socialType, MemberType memberType) {
        this.attributes = attributes;
        this.email = email;
        this.password = password;
        this.nickname = name;
        this.socialType = socialType;
        this.memberType = memberType;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getName() {
        return nickname;
    }
}
