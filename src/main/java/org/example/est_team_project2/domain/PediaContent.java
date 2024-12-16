package org.example.est_team_project2.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PediaContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pediaContentId;
    private int imageNumber;
    private String breed;
    private String origin;
    private String size;
    private String detail;
    private String geneticDisease;
    private String feature;

    public PediaContent() {
        // 기본 생성자
    }

    public Long getPediaContentId() {
        return pediaContentId;
    }

    public void setPediaContentId(Long pediaContentId) {
        this.pediaContentId = pediaContentId;
    }

    public int getImageNumber() {
        return imageNumber;
    }

    public void setImageNumber(int imageNumber) {
        this.imageNumber = imageNumber;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getGeneticDisease() {
        return geneticDisease;
    }

    public void setGeneticDisease(String geneticDisease) {
        this.geneticDisease = geneticDisease;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }
}
