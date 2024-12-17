package org.example.est_team_project2.dto.pedia;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.est_team_project2.domain.member.Member;
import org.example.est_team_project2.domain.pedia.Pedia;
import org.example.est_team_project2.domain.pedia.PediaContent;
import org.example.est_team_project2.domain.pedia.PediaVersion;
import org.example.est_team_project2.domain.pedia.requestEnums.CommonStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PediaVersionDto {

    private Pedia pedia;
    private PediaContent pediaContent;
    private Member editor;
    private String pediaVersionCode;
    private LocalDateTime createdAt;
    private CommonStatus status;

    public static PediaVersionDto from(PediaVersion pediaVersion) {
        PediaVersionDto pediaVersionDto = new PediaVersionDto();
        pediaVersionDto.setPedia(pediaVersion.getPedia());
        pediaVersionDto.setPediaContent(pediaVersion.getPediaContent());
        pediaVersionDto.setEditor(pediaVersion.getEditor());
        pediaVersionDto.setPediaVersionCode(pediaVersion.getPediaVersionCode());
        pediaVersionDto.setCreatedAt(pediaVersion.getCreatedAt());
        pediaVersionDto.setStatus(pediaVersion.getStatus());

        return pediaVersionDto;
    }
}