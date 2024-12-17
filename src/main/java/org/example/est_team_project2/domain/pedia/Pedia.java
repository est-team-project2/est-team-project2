package org.example.est_team_project2.domain.pedia;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Pedia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pediaId;

    private Long memberId; // 회원 ID
    private Long pediaContentId; // PediaContent ID
    private int pediaVersion; // 버전

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now(); // 생성 시 updatedAt도 현재 시간으로 설정
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now(); // 업데이트 시 updatedAt을 현재 시간으로 설정
    }


}
