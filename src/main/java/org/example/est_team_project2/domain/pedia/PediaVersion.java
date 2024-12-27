package org.example.est_team_project2.domain.pedia;

import jakarta.persistence.*;
import lombok.*;
import org.example.est_team_project2.domain.member.Member;
import org.example.est_team_project2.domain.pedia.requestEnums.CommonStatus;
import org.example.est_team_project2.dto.pedia.PediaVersionDto;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PediaVersion {

    @Id
    @Column(name = "pedia_version_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pedia_id")
    private Pedia pedia;

    @OneToOne
    @JoinColumn(name = "pedia_content_id")
    private PediaContent pediaContent;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member editor;

    @Column(unique = true)
    @Setter
    private String pediaVersionCode = null;

    @Setter(AccessLevel.PRIVATE)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Setter
    @Enumerated(EnumType.STRING)
    private CommonStatus status = CommonStatus.DEACTIVE;

    @Builder
    public PediaVersion(Pedia pedia, PediaContent pediaContent, Member editor) {
        this.pedia = pedia;
        this.pediaContent = pediaContent;
        this.editor = editor;
    }

    public static PediaVersion from(PediaVersionDto pediaVersionDto) {
        PediaVersion pediaVersion = PediaVersion.builder()
                .pedia(pediaVersionDto.getPedia())
                .pediaContent(pediaVersionDto.getPediaContent())
                .editor(pediaVersionDto.getEditor())
                .build();

        pediaVersion.setPediaVersionCode(pediaVersionDto.getPediaVersionCode());
        pediaVersion.setCreatedAt(pediaVersionDto.getCreatedAt());
        pediaVersion.setStatus(pediaVersionDto.getStatus());

        return pediaVersion;
    }
}

   
