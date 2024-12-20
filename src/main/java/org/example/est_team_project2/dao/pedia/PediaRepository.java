package org.example.est_team_project2.dao.pedia;

import org.example.est_team_project2.domain.pedia.Pedia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PediaRepository extends JpaRepository<Pedia, Long> {

    Optional<Pedia> findByTitle(String title);
}