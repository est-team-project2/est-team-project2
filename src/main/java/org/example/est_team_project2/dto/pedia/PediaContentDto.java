package org.example.est_team_project2.dto.pedia;

import lombok.*;
import org.example.est_team_project2.domain.pedia.PediaContent;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PediaContentDto {

    private Long id; // PediaContent의 ID (optional, 등록 시 필요 없을 수 있음)

    private String imageUri;

    private String breed; // 품종

    private String origin; // 원산지

    private String size; // 크기

    private String detail; // 상세 정보

    private String geneticDisease; // 유전병

    private String feature; // 특징

    private Long memberId;


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
