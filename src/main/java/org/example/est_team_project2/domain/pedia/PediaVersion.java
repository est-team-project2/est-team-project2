package org.example.est_team_project2.domain.pedia;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class PediaVersion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pedia_id")
    private Pedia pedia;

    @ManyToOne
    @JoinColumn(name = "pedia_content_id")
    private PediaContent pediaContent;

    private Long memberId; // 회원 ID
    private int pediaVersionCode; // 버전 코드

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt; // 작성 일시

    private String status; // 상태

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now(); // 생성 시점 설정
    }
}

   
