package org.example.est_team_project2.domain.member;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import lombok.*;
import org.example.est_team_project2.domain.member.memberEnums.MemberType;
import org.example.est_team_project2.domain.member.memberEnums.SocialType;
import org.example.est_team_project2.domain.pedia.PediaEditRequest;


@Getter
@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    //
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "requestedMember")
    List<PediaEditRequest> requestedPediaEditRequests;

    @OneToMany(mappedBy = "respondedMember")
    List<PediaEditRequest> respondedPediaEditRequests;

    private LocalDateTime createdAt = LocalDateTime.now(); // 언제 만들어 졌는지

    private LocalDateTime updateAt = LocalDateTime.now(); //언제 수정되었는지


    @Column(unique = true)
    private String email; //아이디 겸 이메일

    @Enumerated(EnumType.STRING)
    private SocialType socialType = SocialType.FORMBASED;  // 어떤 소셜 타입인지

    private String password; //비밀번호

    @Enumerated(EnumType.STRING) // DB에서 enum이라고 인식 할 수 있어서 스트링으로 받아준다
    private MemberType role = MemberType.USER;

    @Column(unique = true, name ="nick_name")
    private String nickName; // 활동명

    @Builder
    public Member(String email, String password, SocialType socialType, MemberType role,
        String nickName) {
        this.email = email;
        this.nickName = nickName;
        this.password = password;
        this.socialType = socialType;
        this.role = role;
    }

    public void changePassword(String encodedPassword) {
        this.password = encodedPassword;
    }

}
