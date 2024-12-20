package org.example.est_team_project2.domain.pedia.requestEnums;

import lombok.Getter;

@Getter
public enum RequestStatus {
    OPENED("처리 대기"),
    CLOSED("처리 완료");

    private final String description;

    RequestStatus(String description) {
        this.description = description;
    }
}