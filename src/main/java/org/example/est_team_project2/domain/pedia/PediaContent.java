package org.example.est_team_project2.domain.pedia;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PediaContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pedia_content_id")
    private Long pediaContentId;
    @Column(name = "image_uri")
    public String imageUri;
    private String breed;
    private String origin;
    private String size;
    private String detail;
    private String geneticDisease;
    private String feature;
    private String status;
}
