package org.example.est_team_project2.dao.board;

import java.time.LocalDateTime;
import java.util.List;
import org.example.est_team_project2.domain.board.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findAllByDeletedFalse(Pageable pageable);


    @Query(value = "SELECT p FROM Post p WHERE p.deleted = false AND p.createdAt >= :oneMonthAgo ORDER BY p.views DESC")
    List<Post> findTop3ByDeletedFalseOrderByViewsDesc(LocalDateTime oneMonthAgo, Pageable pageable);

    Page<Post> findAll(Pageable pageable);

    Page<Post> findByMemberId(Pageable pageable, Long id);


}
