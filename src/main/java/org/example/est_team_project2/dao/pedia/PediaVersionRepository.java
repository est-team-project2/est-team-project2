package org.example.est_team_project2.dao.pedia;

import java.util.Optional;
import org.example.est_team_project2.domain.pedia.PediaVersion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PediaVersionRepository extends JpaRepository<PediaVersion, Integer> {

    Optional<PediaVersion> findByPediaVersionCode(String pediaVersionCode);
}
