package org.example.est_team_project2.dto.Pedia;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PediaContentDTO {
    private Long id; // PediaContent의 ID (optional, 등록 시 필요 없을 수 있음)
    private String image;
    private String breed; // 품종
    private String origin; // 원산지
    private String size; // 크기
    private String detail; // 상세 정보
    private String geneticDisease; // 유전병
    private String feature; // 특징
    private String status;

}
