package org.example.est_team_project2.dto.pedia;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.est_team_project2.domain.pedia.requestEnums.CommonStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PediaDetails {

    // Pedia
    private String pediaTitle;
    private String pediaCurrentVersionCode;
    private LocalDateTime pediaCreatedAt;
    private LocalDateTime pediaUpdatedAt;

    // PediaVersion
    private String pediaVersionCode;
    private String pediaVersionCreatedAt;
    private CommonStatus pediaVersionStatus;
    private String pediaVersionEditorEmail;

    // PediaEditRequest
    private String pediaEditRequestCode;
    private String pediaEditRequestClosedAt;
    private String pediaEditRequestStatus;
    private String requestedMemberEmail;
    private String respondedMemberEmail;
}
