package org.example.est_team_project2.domain.pedia;

import jakarta.persistence.*;
import lombok.*;
import org.example.est_team_project2.domain.pedia.requestEnums.CommonStatus;
import org.example.est_team_project2.dto.pedia.PediaContentDto;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PediaContent {

    @Id
    @Column(name = "pedia_content_id")
    private Long id;

    @Column(name = "image_uri")
    public String imageUri;

    // 품종
    private String breed;

    // 원산지
    private String origin;

    //크기
    private String size;

    //상세정보
    private String detail;

    //유전병
    private String geneticDisease;

    //특징
    private String feature;

    //상태
    @Setter
    @Enumerated(EnumType.STRING)
    private CommonStatus status;

    @Builder
    public PediaContent(String imageUri, String breed, String origin, String size, String detail,
                        String geneticDisease, String feature , CommonStatus status) {

        this.imageUri = imageUri;
        this.breed = breed;
        this.origin = origin;
        this.size = size;
        this.detail = detail;
        this.geneticDisease = geneticDisease;
        this.feature = feature;
        this.status = status;

    }

    public static PediaContent from(PediaContentDto pediaContentDto) {

        return PediaContent.builder()
                .imageUri(pediaContentDto.getImageUri())
                .breed(pediaContentDto.getBreed())
                .origin(pediaContentDto.getOrigin())
                .size(pediaContentDto.getSize())
                .detail(pediaContentDto.getDetail())
                .geneticDisease(pediaContentDto.getGeneticDisease())
                .feature(pediaContentDto.getFeature())
                .build();
    }


}
