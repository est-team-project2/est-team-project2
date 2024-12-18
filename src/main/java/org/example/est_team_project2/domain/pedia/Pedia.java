package org.example.est_team_project2.domain.pedia;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Pedia")
public class Pedia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pedia_id") // 데이터베이스 컬럼 매핑
    private Long id; // Pedia ID

    @Column(name = "member_id", nullable = false) // 필수값 설정
    private Long memberId; // 회원 ID

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt; // 생성 일시

    @Column(name = "updated_at")
    private LocalDateTime updatedAt; // 수정 일시

    @Column(name = "title", nullable = false)
    private String title; // 제목

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now(); // 생성 시점 설정
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now(); // 수정 시점 설정
    }
}
