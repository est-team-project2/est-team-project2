package org.example.est_team_project2.dto.board;

import lombok.*;
import org.example.est_team_project2.domain.board.Post;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDto {

    private Long id;
    private String title;
    private String contents;
    private Long memberId;
    private String memberNickname;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean deleted;
    private LocalDateTime deletedAt;

    public static PostDto from(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
//                .contents(post.getContents())
//                .memberId(post.getMember().getId())
//                .memberNickname(post.getMember().getNickName()
//                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .deleted(post.getDeleted())
                .deletedAt(post.getDeletedAt())
                .build();
    }
}
