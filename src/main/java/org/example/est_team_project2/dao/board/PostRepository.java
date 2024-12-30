package org.example.est_team_project2.dao.board;

import org.example.est_team_project2.domain.board.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findAllByDeletedFalse(Pageable pageable);

    Page<Post> findAll(Pageable pageable);

    Page<Post> findByMemberId(Pageable pageable, Long id);

}
