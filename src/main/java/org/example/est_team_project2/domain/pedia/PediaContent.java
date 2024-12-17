package org.example.est_team_project2.domain.pedia;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.est_team_project2.domain.pedia.requestEnums.CommonStatus;

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

    private String body;
    // 수정 예정

    @Setter
    private CommonStatus status = CommonStatus.ACTIVE;

    @Builder
    public PediaContent(String body) {
        this.body = body;
    }
}
