package org.example.est_team_project2.dto.pedia;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.est_team_project2.domain.pedia.PediaContent;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PediaContentDto {

    private String imageUri;
    private String breed;
    private String origin;
    private String size;
    private String detail;
    private String geneticDisease;
    private String feature;

    public static PediaContentDto from(PediaContent pediaContent) {
        PediaContentDto pediaContentDto = new PediaContentDto();

        pediaContentDto.setImageUri(pediaContent.getImageUri());
        pediaContentDto.setBreed(pediaContent.getBreed());
        pediaContentDto.setOrigin(pediaContent.getOrigin());
        pediaContentDto.setSize(pediaContent.getSize());
        pediaContentDto.setDetail(pediaContent.getDetail());
        pediaContentDto.setGeneticDisease(pediaContent.getGeneticDisease());
        pediaContentDto.setFeature(pediaContent.getFeature());

        return pediaContentDto;
    }
}
