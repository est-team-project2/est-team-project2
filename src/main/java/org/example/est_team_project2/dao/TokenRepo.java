package org.example.est_team_project2.dao;


import org.example.est_team_project2.domain.Member;
import org.example.est_team_project2.domain.RefToken;

import java.util.Optional;


public interface TokenRepo {
    RefToken save(Member member, String token);
    Optional<RefToken> findValidRefTokenByToken(String token);
    Optional<RefToken> findValidRefTokenByMemberId(Long memberId);
    RefToken appendBlackList(RefToken token);
}