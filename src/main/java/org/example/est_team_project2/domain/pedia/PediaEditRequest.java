package org.example.est_team_project2.domain.pedia;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.est_team_project2.domain.member.Member;
import org.example.est_team_project2.domain.pedia.requestEnums.RequestStatus;
import org.example.est_team_project2.dto.pedia.PediaEditRequestDto;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PediaEditRequest {

    private static final String REQ_PREFIX = "REQ-";

    @Id
    @Column(name = "pedia_edit_request_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Setter(AccessLevel.PRIVATE)
    private String pediaEditRequestCode;

    @OneToOne
    @JoinColumn(name = "pedia_version_id")
    private PediaVersion pediaVersion;

    @ManyToOne
    @JoinColumn(name = "responded_member_id")
    @Setter
    private Member respondedMember;

    @ManyToOne
    @JoinColumn(name = "requested_member_id")
    private Member requestedMember;

    @Setter
    private LocalDateTime closedAt;

    @Setter
    @Enumerated(EnumType.STRING)
    private RequestStatus status = RequestStatus.OPENED;

    @Builder
    public PediaEditRequest(PediaVersion pediaVersion, Member requestedMember) {
        this.pediaVersion = pediaVersion;
        this.requestedMember = requestedMember;
        this.pediaEditRequestCode = genPediaEditRequestCode();
    }

    public static PediaEditRequest from(PediaEditRequestDto pediaEditRequestDto) {
        PediaEditRequest pediaEditRequest = PediaEditRequest.builder()
            .pediaVersion(pediaEditRequestDto.getPediaVersion())
            .requestedMember(pediaEditRequestDto.getRequestedMember())
            .build();

        pediaEditRequest.setPediaEditRequestCode(pediaEditRequestDto.getPediaEditRequestCode());
        pediaEditRequest.setRespondedMember(pediaEditRequestDto.getRespondedMember());
        pediaEditRequest.setClosedAt(pediaEditRequestDto.getClosedAt());
        pediaEditRequest.setStatus(pediaEditRequestDto.getStatus());

        return pediaEditRequest;
    }

    public void closeRequest(Member member) {
        this.respondedMember = member;
        this.status = RequestStatus.CLOSED;
    }

    private String genPediaEditRequestCode() {
        return REQ_PREFIX + System.currentTimeMillis();
    }
}