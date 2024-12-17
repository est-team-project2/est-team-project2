package org.example.est_team_project2.dao.board;

import org.example.est_team_project2.domain.board.Comment;
import org.example.est_team_project2.domain.board.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostAndDeletedFalse(Post post);
}
