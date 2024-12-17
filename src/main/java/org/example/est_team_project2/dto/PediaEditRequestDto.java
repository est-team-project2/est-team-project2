package org.example.est_team_project2.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.est_team_project2.domain.Member;
import org.example.est_team_project2.domain.PediaEditRequest;
import org.example.est_team_project2.domain.PediaVersion;
import org.example.est_team_project2.domain.RequestStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PediaEditRequestDto {

    private PediaVersion pediaVersion;
    private Member respondedMember;
    private Member requestedMember;
    private LocalDateTime closedAt;
    private RequestStatus status;

    public PediaEditRequestDto from(PediaEditRequest pediaEditRequest) {
        PediaEditRequestDto pediaEditRequestDto = new PediaEditRequestDto();

        pediaEditRequestDto.pediaVersion = pediaEditRequest.getPediaVersion();
        pediaEditRequestDto.respondedMember = pediaEditRequest.getRespondedMember();
        pediaEditRequestDto.requestedMember = pediaEditRequest.getRequestedMember();
        pediaEditRequestDto.closedAt = pediaEditRequest.getClosedAt();
        pediaEditRequestDto.status = pediaEditRequest.getStatus();

        return pediaEditRequestDto;
    }
}
