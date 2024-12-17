package org.example.est_team_project2.domain.pedia;

import jakarta.persistence.*;
import lombok.*;
import org.example.est_team_project2.domain.member.Member;
import org.example.est_team_project2.domain.pedia.requestEnums.CommonStatus;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class PediaVersion {

    @Id
    @Column(name = "pedia_version_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pedia_id")
    private final Pedia pedia;

    @OneToOne
    @JoinColumn(name = "pedia_content_id")
    private PediaContent pediaContent;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member editor;

    @Column(unique = true)
    @Setter
    private String pediaVersionCode;

    private final LocalDateTime createdAt = LocalDateTime.now();

    private CommonStatus status;

    @Builder
    public PediaVersion(Pedia pedia, PediaContent pediaContent, Member editor) {
        this.pedia = pedia;
        this.pediaContent = pediaContent;
        this.editor = editor;
    }

}

