package org.example.est_team_project2.dto.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class MemberInfo {
    private String email;
    private String nickName;
    private String password;
    private String role;
}
