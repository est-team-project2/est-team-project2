package org.example.est_team_project2.domain.pedia;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.est_team_project2.domain.pedia.requestEnums.CommonStatus;
import org.example.est_team_project2.dto.pedia.PediaContentDto;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PediaContent {

    @Id
    @Column(name = "pedia_content_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUri;
    private String breed;
    private String origin;
    private String size;
    private String detail;
    private String geneticDisease;
    private String feature;

    @Setter
    @Enumerated(EnumType.STRING)
    private CommonStatus status = CommonStatus.INACTIVE;

    @Builder
    public PediaContent(String imageUri, String breed, String origin, String size, String detail,
        String geneticDisease, String feature) {
        this.imageUri = imageUri;
        this.breed = breed;
        this.origin = origin;
        this.size = size;
        this.detail = detail;
        this.geneticDisease = geneticDisease;
        this.feature = feature;
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
