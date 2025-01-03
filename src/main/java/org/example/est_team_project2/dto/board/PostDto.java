package org.example.est_team_project2.dto.board;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.est_team_project2.domain.board.Post;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDto {

    private Long id;
    private String title;
    private String contents;
    private Long memberId;
    private String memberNickName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean deleted;
    private LocalDateTime deletedAt;
    private int views;

    private int commentCount;
    private String formattedCreatedAt;
    private Boolean canModify = false;

    public static PostDto from(Post post) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        return PostDto.builder()
            .id(post.getId())
            .title(post.getTitle())
            .contents(post.getContents())
            .memberId(post.getMember().getId())
            .memberNickName(post.getMember().getNickName())
            .createdAt(post.getCreatedAt())
            .updatedAt(post.getUpdatedAt())
            .deleted(post.getDeleted())
            .deletedAt(post.getDeletedAt())
            .views(post.getViews())
            .formattedCreatedAt(post.getCreatedAt().format(formatter))
            .commentCount((int) post.getComments().stream().filter(
                comment -> !comment.getDeleted()
            ).count())
            .build();
    }
}
