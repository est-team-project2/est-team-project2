package org.example.est_team_project2.dao.pedia;

import org.example.est_team_project2.domain.pedia.PediaContent;
import org.example.est_team_project2.domain.pedia.requestEnums.CommonStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface PediaContentRepository extends JpaRepository<PediaContent, Long> {


    Optional<PediaContent> findById(Long id);


    @Override
    @Query("select r from PediaContent r where r.status = 'ACTIVE' ")
    List<PediaContent> findAll();

    @Query("select pc from PediaContent pc where pc.status = 'ACTIVE' ")
    Page<PediaContent> findTop8ByStatusOrderByIdDesc(Pageable pageable);

}