package org.example.est_team_project2.dto.pedia;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PediaEditDTO {
    private Long pediaEditRequestId;
    private Long pediaContentId;
    private Long memberId;
    private int pediaVersion;
    private String status;
}