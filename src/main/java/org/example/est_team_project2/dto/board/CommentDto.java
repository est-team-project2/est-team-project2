package org.example.est_team_project2.dto.board;

import lombok.*;
import org.example.est_team_project2.domain.board.Comment;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto {

    private Long id;
    private String contents;
    private Long memberId;
    private String memberNickname;
    private Long postId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean deleted;
    private LocalDateTime deletedAt;

    public static CommentDto from(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .contents(comment.getContents())
                .memberId(comment.getMember().getId())
                .memberNickname(comment.getMember().getNickname())
                .postId(comment.getPost().getId())
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getUpdatedAt())
                .deleted(comment.getDeleted())
                .deletedAt(comment.getDeletedAt())
                .build();
    }
}

