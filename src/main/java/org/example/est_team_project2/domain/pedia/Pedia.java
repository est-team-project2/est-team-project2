package org.example.est_team_project2.domain.pedia;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Pedia")
public class Pedia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String breed;
    private String origin;
    private String size;
    private String detail;
    private String geneticDisease;
    private String feature;
    private Long memberId;
    private Long pediaContentId;
    private int pediaVersion;
}
