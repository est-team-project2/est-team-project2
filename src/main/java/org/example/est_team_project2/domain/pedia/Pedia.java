package org.example.est_team_project2.domain.pedia;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Pedia {

    @Id
    @Column(name = "pedia_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
  
    private Long mediaId;
  
    private Long pediaCategoryId;
  
    private int pediaVersion;

    private String title;

    @Setter
    private String currentVersionCode = null;

    private final LocalDateTime createdAt = LocalDateTime.now();

    @Setter
    private LocalDateTime updatedAt;

    @Builder
    public Pedia(String title) {
       this.title = title;
    }
}
