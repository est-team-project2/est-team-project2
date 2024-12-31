package org.example.est_team_project2.dao.pedia;

import org.example.est_team_project2.domain.pedia.PediaEditRequest;
import org.example.est_team_project2.domain.pedia.PediaVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PediaEditRequestRepository extends JpaRepository<PediaEditRequest, Long> {

    Optional<PediaEditRequest> findByPediaEditRequestCode(
        String pediaEditRequestCode);

    @Override
    @Query("SELECT r FROM PediaEditRequest r WHERE r.status = 'OPENED'")
    List<PediaEditRequest> findAll();

}
