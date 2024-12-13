package org.example.est_team_project2.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PediaContent {

    @Id
    @Column(name = "pedia_content_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String body;
    // 수정 예정
}
