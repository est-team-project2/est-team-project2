package org.example.est_team_project2.dao.pedia;

import java.util.List;
import java.util.Optional;

import org.example.est_team_project2.domain.pedia.Pedia;
import org.example.est_team_project2.domain.pedia.PediaContent;
import org.example.est_team_project2.domain.pedia.PediaVersion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PediaVersionRepository extends JpaRepository<PediaVersion, Integer> {

    Optional<PediaVersion> findByPediaVersionCode(String pediaVersionCode);

    @Override
    @Query("SELECT r FROM PediaVersion r WHERE r.status = 'ACTIVE'")
    List<PediaVersion> findAll();

    PediaVersion findById(Long id);

    List<PediaVersion> findByPediaId(Long id);

    Page<PediaVersion> findByPediaId(Pageable pageable, Long id);

}