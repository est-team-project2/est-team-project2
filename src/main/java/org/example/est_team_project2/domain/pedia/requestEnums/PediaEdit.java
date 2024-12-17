package org.example.est_team_project2.domain.pedia.requestEnums;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PediaEdit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pediaEditRequestId;

    private Long pediaContentId; // PediaContent ID
    private Long memberId; // 회원 ID
    private int pediaVersion; // 버전

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime closedAt; // 요청 종료 시간
    private String status; // 상태 (예: 요청 수락, 거부 등)
}
