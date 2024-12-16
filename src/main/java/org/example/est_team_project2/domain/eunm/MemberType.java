package org.example.est_team_project2.domain.eunm;


import org.springframework.security.core.GrantedAuthority;

public enum MemberType implements GrantedAuthority {

    ADMIN,
    EXPERT,
    USER;

    @Override
    public String getAuthority() {
        return name(); // Enum 값 그대로 반환 ("ADMIN", "EXPERT", "USER")
    }

}
