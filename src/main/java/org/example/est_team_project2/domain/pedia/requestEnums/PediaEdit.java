package org.example.est_team_project2.domain.pedia.requestEnums;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "pedia_edit")
public class PediaEdit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pediaEditRequestId;
    private Long pediaContentId;
    private Long memberId;
    private int pediaVersion;
    private String status;
    private LocalDateTime createdAt = LocalDateTime.now(); // 현재 시간으로 초기화
}