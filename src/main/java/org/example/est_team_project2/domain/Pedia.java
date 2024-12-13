package org.example.est_team_project2.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Pedia {

    @Id
    @Column(name = "pedia_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String currentVersionCode;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
