package org.example.est_team_project2.dto.pedia;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.est_team_project2.domain.pedia.requestEnums.RequestStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class TestDto {

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

    private Long id; // PediaContent의 ID (optional, 등록 시 필요 없을 수 있음)

    private String imageUri;

    private String breed; // 품종

    private String origin; // 원산지

    private String size; // 크기

    private String detail; // 상세 정보

    private String geneticDisease; // 유전병

    private String feature; // 특징

    private Long memberId;

}
