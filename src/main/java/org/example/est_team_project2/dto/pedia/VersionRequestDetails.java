package org.example.est_team_project2.dto.pedia;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.est_team_project2.domain.pedia.PediaEditRequest;
import org.example.est_team_project2.domain.pedia.requestEnums.RequestStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VersionRequestDetails {

    private String title;
    private String pediaVersionCode;
    private String pediaEditRequestCode;
    private String requestedMemberEmail;
    private String respondedMemberEmail;
    private LocalDateTime createdAt;
    private LocalDateTime closedAt;
    private RequestStatus status;

    public static VersionRequestDetails from(PediaEditRequest pediaEditRequest) {
        VersionRequestDetails versionRequestDetails = new VersionRequestDetails();

        versionRequestDetails.setTitle(pediaEditRequest.getPediaVersion().getPedia().getTitle());
        versionRequestDetails.setPediaVersionCode(
            pediaEditRequest.getPediaVersion().getPediaVersionCode());
        versionRequestDetails.setPediaEditRequestCode(
            pediaEditRequest.getPediaEditRequestCode());
        versionRequestDetails.setRequestedMemberEmail(
            pediaEditRequest.getRequestedMember().getEmail());
        versionRequestDetails.setRespondedMemberEmail(
            pediaEditRequest.getRespondedMember().getEmail());
        versionRequestDetails.setCreatedAt(
            pediaEditRequest.getPediaVersion().getCreatedAt());
        versionRequestDetails.setClosedAt(
            pediaEditRequest.getClosedAt());
        versionRequestDetails.setStatus(pediaEditRequest.getStatus());

        return versionRequestDetails;
    }
}
