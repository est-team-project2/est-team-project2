package org.example.est_team_project2.domain.pedia;

import jakarta.persistence.*;
import lombok.*;
import org.example.est_team_project2.dto.pedia.PediaDto;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
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
    private LocalDateTime updatedAt = LocalDateTime.now();

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
