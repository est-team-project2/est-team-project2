package org.example.est_team_project2.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.est_team_project2.domain.pedia.Pedia;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PediaDto {

    private String title;
    private String currentVersionCode;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static PediaDto from(Pedia pedia) {
        PediaDto pediaDto = new PediaDto();

        pediaDto.setTitle(pedia.getTitle());
        pediaDto.setCurrentVersionCode(pedia.getCurrentVersionCode());
        pediaDto.setCreatedAt(pedia.getCreatedAt());
        pediaDto.setUpdatedAt(pedia.getUpdatedAt());

        return pediaDto;
    }
}
