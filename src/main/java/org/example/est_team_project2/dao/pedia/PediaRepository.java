package org.example.est_team_project2.dao.pedia;

import java.util.List;
import java.util.Optional;

import org.example.est_team_project2.domain.board.Post;
import org.example.est_team_project2.domain.pedia.Pedia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PediaRepository extends JpaRepository<Pedia, Long> {

    Optional<Pedia> findByTitle(String title);

    Page<Pedia> findAll(Pageable pageable);

}
