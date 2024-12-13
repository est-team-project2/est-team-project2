package org.example.est_team_project2.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.est_team_project2.domain.enums.MemberType;
import org.example.est_team_project2.domain.enums.SocialType;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private LocalDateTime createAt = LocalDateTime.now(); // 언제 만들어 졌는지

    private LocalDateTime updateAt = LocalDateTime.now(); //언제 수정되었는지

    @Column(unique = true)
    private String email; //아이디 겸 이메일

    @Enumerated(EnumType.STRING)
    private SocialType socialType;  // 어떤 소셜 타입인지

    private String password; //비밀번호

    @Enumerated(EnumType.STRING)
    private MemberType role = MemberType.USER;

    private String nickName; // 활동명


    @Builder
    public Member(String email, String password, SocialType socialType, MemberType role, String nickName) {
        this.email = email;
        this.password = password;
        this.socialType = socialType;
        this.nickName = nickName;

    }

}
