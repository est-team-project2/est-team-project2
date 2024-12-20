package org.example.est_team_project2.dao.pedia;

import org.example.est_team_project2.domain.pedia.PediaEditRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PediaEditRequestRepository extends JpaRepository<PediaEditRequest, Long> {

    Optional<PediaEditRequest> findByPediaEditRequestCode(
            String pediaEditRequestCode);
}

