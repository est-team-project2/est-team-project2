package org.example.est_team_project2.dao.pedia;

import java.util.Optional;

import org.example.est_team_project2.domain.pedia.Pedia;
import org.example.est_team_project2.domain.pedia.PediaContent;
import org.example.est_team_project2.domain.pedia.PediaVersion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PediaVersionRepository extends JpaRepository<PediaVersion, Integer> {

    Optional<PediaVersion> findByPediaVersionCode(String pediaVersionCode);

    Page<PediaVersion> findByPediaId(Pageable pageable, Long id);
}