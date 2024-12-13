package org.example.est_team_project2.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Pedia {

    @Id
    @Column(name = "pedia_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
