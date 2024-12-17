package org.example.est_team_project2.domain.pedia;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.est_team_project2.domain.member.Member;
import org.example.est_team_project2.domain.pedia.requestEnums.RequestStatus;
import org.example.est_team_project2.dto.Pedia.PediaEditRequestDto;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PediaEditRequest {

    @Id
    @Column(name = "pedia_edit_request_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    private RequestStatus status = RequestStatus.OPENED;

    @Builder
    public PediaEditRequest(PediaVersion pediaVersion, Member requestedMember) {
        this.pediaVersion = pediaVersion;
        this.requestedMember = requestedMember;
    }

    public static PediaEditRequest from(PediaEditRequestDto pediaEditRequestDto) {
        PediaEditRequest pediaEditRequest = new PediaEditRequest(

        );

        return pediaEditRequest;
    }
}
