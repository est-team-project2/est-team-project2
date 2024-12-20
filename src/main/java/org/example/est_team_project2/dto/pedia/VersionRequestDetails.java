package org.example.est_team_project2.dto.pedia;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.est_team_project2.domain.pedia.PediaEditRequest;
import org.example.est_team_project2.domain.pedia.requestEnums.RequestStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VersionRequestDetails {


    //contents 제목 -> pedia?
    // Contents
    private String title;

    // 버전의 코드
    private String pediaVersionCode;

    //수정 요청 코드??
    private String pediaEditRequestCode;

    //작성자 이메일  -> Member값 조회
    private String requestedMemberEmail;

    private String respondedMemberEmail;

    private LocalDateTime createdAt;

    private LocalDateTime closedAt;

    private RequestStatus status;

    public static VersionRequestDetails from(PediaEditRequest pediaEditRequest) {

        VersionRequestDetails versionRequestDetails = new VersionRequestDetails();

        // 버전 제목
        versionRequestDetails.setTitle(pediaEditRequest.getPediaVersion().getPedia().getTitle());

        // 버전 코드
        versionRequestDetails.setPediaVersionCode(
                pediaEditRequest.getPediaVersion().getPediaVersionCode());

        // 요청 코드
        versionRequestDetails.setPediaEditRequestCode(
                pediaEditRequest.getPediaEditRequestCode());

        //멤버 이메일 가져오기
        versionRequestDetails.setRequestedMemberEmail(
                pediaEditRequest.getRequestedMember().getEmail());

        //
        if (pediaEditRequest.getRespondedMember() != null) {
            versionRequestDetails.setRespondedMemberEmail(
                    pediaEditRequest.getRespondedMember().getEmail());
        }

        // 버전 만든날짜 가져오기
        versionRequestDetails.setCreatedAt(
                pediaEditRequest.getPediaVersion().getCreatedAt());

        //
        versionRequestDetails.setClosedAt(
                pediaEditRequest.getClosedAt());

        versionRequestDetails.setStatus(pediaEditRequest.getStatus());

        return versionRequestDetails;
    }
}
