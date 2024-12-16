package org.example.est_team_project2.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.web.bind.annotation.GetMapping;

@Entity
public class Pedia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pediaId;
    private Long mediaId;
    private Long pediaCategoryId;
    private int pediaVersion;

    public Pedia() {
        // 기본 생성자
    }

    public Long getPediaId() {
        return pediaId;
    }

    public void setPediaId(Long pediaId) {
        this.pediaId = pediaId;
    }

    public Long getMediaId() {
        return mediaId;
    }

    public void setMediaId(Long mediaId) {
        this.mediaId = mediaId;
    }

    public Long getPediaCategoryId() {
        return pediaCategoryId;
    }

    public void setPediaCategoryId(Long pediaCategoryId) {
        this.pediaCategoryId = pediaCategoryId;
    }

    public int getPediaVersion() {
        return pediaVersion;
    }

    public void setPediaVersion(int pediaVersion) {
        this.pediaVersion = pediaVersion;
    }
}

