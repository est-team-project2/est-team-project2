package org.example.est_team_project2.dto.pedia;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.est_team_project2.domain.member.Member;
import org.example.est_team_project2.domain.pedia.PediaEditRequest;
import org.example.est_team_project2.domain.pedia.PediaVersion;
import org.example.est_team_project2.domain.pedia.requestEnums.RequestStatus;

import java.time.LocalDateTime;

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
