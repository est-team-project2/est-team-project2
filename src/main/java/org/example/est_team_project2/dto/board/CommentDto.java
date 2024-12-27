package org.example.est_team_project2.dto.board;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.est_team_project2.domain.board.Comment;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto {

    private Long id;
    private String contents;
    private Long memberId;
    private String memberNickName;
    private Long postId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean deleted;
    private LocalDateTime deletedAt;

    private String formattedCreatedAt;
    private Boolean canModify = false;

    public static CommentDto from(Comment comment) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        return CommentDto.builder()
            .id(comment.getId())
            .contents(comment.getContents())
            .memberId(comment.getMember().getId())
            .memberNickName(comment.getMember().getNickName())
            .postId(comment.getPost().getId())
            .createdAt(comment.getCreatedAt())
            .updatedAt(comment.getUpdatedAt())
            .deleted(comment.getDeleted())
            .deletedAt(comment.getDeletedAt())
            .formattedCreatedAt(comment.getCreatedAt().format(formatter))
            .build();
    }
}

