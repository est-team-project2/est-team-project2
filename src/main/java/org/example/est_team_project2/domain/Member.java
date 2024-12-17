package org.example.est_team_project2.domain;

import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.est_team_project2.domain.eunm.MemberType;
import org.example.est_team_project2.domain.eunm.SocialType;
import org.example.est_team_project2.dto.MemberDto;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "respondedMember")
    List<PediaEditRequest> respondedPediaEditRequests;

    @OneToMany(mappedBy = "editor")
    List<PediaVersion> editedPediaVersions;

    @Column(unique = true)
    private String email; //아이디 겸 이메일

    private String password;

    @Enumerated(EnumType.STRING)
    private SocialType socialType = SocialType.FORMBASED;  // 어떤 소셜 타입인지
  
    @Enumerated(EnumType.STRING) // DB에서 enum이라고 인식 할 수 있어서 스트링으로 받아준다
    private MemberType role = MemberType.USER;
  
    private String nickname;

    private LocalDateTime createAt = LocalDateTime.now(); // 언제 만들어 졌는지

    private LocalDateTime updateAt; //언제 수정되었는지

    @Builder
    public Member(String email, String password, SocialType socialType, MemberType role, String nickName) {
        this.email = email;
        this.password = password;
        this.socialType = socialType;
        this.nickName = nickName;

    }
}
