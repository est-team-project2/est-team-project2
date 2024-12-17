package org.example.est_team_project2.dao.pedia;

import org.example.est_team_project2.domain.pedia.PediaContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PediaContentRepository extends JpaRepository<PediaContent, Long> {
    List<PediaContent> findByBreedContaining(String breed);
}