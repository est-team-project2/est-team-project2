package org.example.est_team_project2.dao.pedia;

import org.example.est_team_project2.domain.pedia.PediaVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PediaVersionRepository extends JpaRepository<PediaVersion, Long> {
    // 추가적인 쿼리 메서드 정의 가능
}