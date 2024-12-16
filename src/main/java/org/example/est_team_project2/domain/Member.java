package org.example.est_team_project2.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "respondedMember")
    List<PediaEditRequest> respondedPediaEditRequests;

    @OneToMany(mappedBy = "requestedMember")
    List<PediaEditRequest> requestedPediaEditRequests;

    @OneToMany(mappedBy = "editor")
    List<PediaVersion> editedPediaVersions;

    private String email;

    private String password;

    private String nickname;

    private CommonStatus status;
    // socialType, role 추가 필요
}
