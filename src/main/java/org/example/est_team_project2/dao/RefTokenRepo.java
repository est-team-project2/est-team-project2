package org.example.est_team_project2.dao;

import org.example.est_team_project2.domain.RefToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefTokenRepo extends JpaRepository<RefToken, Long> {

    Optional<RefToken> findByRefToken(String refToken);
}
