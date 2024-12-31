package org.example.est_team_project2.domain.pedia.requestEnums;

import lombok.Getter;

@Getter
public enum CommonStatus {
    ACTIVE("활성 상태"),
    DEACTIVE("비활성 상태"),
    DELETED("삭제됨"),
    ARCHIVED("보관됨");

    private final String description;

    CommonStatus(String description) {
        this.description = description;
    }
}
