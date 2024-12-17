package org.example.est_team_project2.dao;

import org.example.est_team_project2.domain.RefToken;
import org.example.est_team_project2.domain.RefTokenBlackList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefTokenBlackListRepo extends JpaRepository<RefTokenBlackList, Long> {

    boolean existsByRefToken(RefToken refToken);
}
