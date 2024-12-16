package org.example.est_team_project2.dao;

import com.nimbusds.oauth2.sdk.token.RefreshToken;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.example.est_team_project2.domain.Member;
import org.example.est_team_project2.domain.RefToken;
import org.example.est_team_project2.domain.RefTokenBlackList;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RefTokenRepoAdapter implements TokenRepo {

    private final RefTokenRepo refTokenRepo;
    private final RefTokenBlackListRepo refTokenBlackListRepo;

    private final EntityManager em;


    @Override
    public RefToken save(Member member, String token) {
        return refTokenRepo.save(
                RefToken.builder()
                        .refreshToken(token)
                        .member(member)
                        .build());
    }

    @Override
    public Optional<RefToken> findValidRefTokenByToken(String token) {
        Optional<RefToken> refTokenOptional = refTokenRepo.findByRefToken(token);

        if (refTokenOptional.isEmpty()) return refTokenOptional;

        RefToken findToken = refTokenOptional.get();

        boolean isBanned = isBannedRefToken(findToken);

        if (isBanned) {
            return Optional.empty();
        } else{
            return refTokenOptional;
        }
    }

    @Override
    public Optional<RefToken> findValidRefTokenByMemberId(Long memberId) {
        return em.createQuery(
                        "select rf from RefToken rf left join RefTokenBlackList rtb on rtb.refToken = rf where rf.member.id = :memberId and rtb.id is null"
                        , RefToken.class)
                .setParameter("memberId", memberId)
                .getResultStream().findFirst();
    }

    @Override
    public RefToken appendBlackList(RefToken token) {
        refTokenBlackListRepo.save(
                RefTokenBlackList.builder()
                        .refToken(token)
                        .build()
        );
        return token;
    }

    public boolean isBannedRefToken(RefToken token) {
        return refTokenBlackListRepo.existsByRefToken(token);
    }
}
