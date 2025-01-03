package org.example.est_team_project2.dao.member;

import org.example.est_team_project2.domain.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
    Optional<Member> findByNickName(String nickName);
    Page<Member> findAll(Pageable pageable);
}
