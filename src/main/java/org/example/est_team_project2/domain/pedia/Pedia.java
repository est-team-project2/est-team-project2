package org.example.est_team_project2.domain.pedia;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.est_team_project2.dto.PediaDto;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Pedia {

    @Id
    @Column(name = "pedia_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String title;

    @Setter
    private String currentVersionCode = null;

    @Setter(AccessLevel.PRIVATE)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Setter
    private LocalDateTime updatedAt;

    @Builder
    public Pedia(String title) {
        this.title = title;
    }

    public static Pedia from(PediaDto pediaDto) {
        Pedia pedia = Pedia.builder()
            .title(pediaDto.getTitle())
            .build();

        pedia.setCurrentVersionCode(pediaDto.getCurrentVersionCode());
        pedia.setCreatedAt(pediaDto.getCreatedAt());
        pedia.setUpdatedAt(pediaDto.getUpdatedAt());

        return pedia;
    }
}
