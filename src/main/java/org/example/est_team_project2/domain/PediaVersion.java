package org.example.est_team_project2.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity
public class PediaVersion {

    @Id
    @Column(name = "pedia_version_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pedia_id")
    private Pedia pedia;

    @OneToOne
    @JoinColumn(name = "pedia_content_id")
    private PediaContent pediaContent;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member editor;

    @Column(unique = true)
    private String pediaVersionCode;
    
    private LocalDateTime createdAt;
    private CommonStatus status;
}
