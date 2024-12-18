package org.example.est_team_project2.dto.Pedia;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PediaDTO {
    private Long pediaId;
    private Long memberId;
    private Long pediaContentId;
    private int pediaVersion; // 버전

}
