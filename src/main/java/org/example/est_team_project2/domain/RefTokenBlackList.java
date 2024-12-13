package org.example.est_team_project2.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefTokenBlackList {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "refresh_token_id")
    private RefToken refToken;

    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder
    public RefTokenBlackList(RefToken refToken) {
        this.refToken = refToken;
    }
}
