package org.example.est_team_project2.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PediaVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pediaVersionId;
    private Long pediaId;


    public PediaVersion() {

    }

    public Long getPediaVersionId() {
        return pediaVersionId;
    }

    public void setPediaVersionId(Long pediaVersionId) {
        this.pediaVersionId = pediaVersionId;
    }

    public Long getPediaId() {
        return pediaId;
    }

    public void setPediaId(Long pediaId) {
        this.pediaId = pediaId;
    }
}

