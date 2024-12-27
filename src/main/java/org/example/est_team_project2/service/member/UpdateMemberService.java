package org.example.est_team_project2.service.member;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.est_team_project2.dao.member.MemberRepository;
import org.example.est_team_project2.domain.member.Member;
import org.example.est_team_project2.dto.member.MemberDetails;
import org.example.est_team_project2.dto.member.UpdateNicknameReq;
import org.example.est_team_project2.dto.member.UpdatePassReq;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UpdateMemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private Member findMemberByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("No member found with email: " + email));
    }

    public void updateNickname(UpdateNicknameReq request) {
        Member member = findMemberByEmail(request.getEmail());
        member.changeNickname(request.getNewNickname());
        member.updateTime();
        memberRepository.save(member);
    }

    public void updatePassword(UpdatePassReq request) {
        Member member = findMemberByEmail(request.getEmail());
        member.changePassword(bCryptPasswordEncoder.encode(request.getNewPassword()));
        member.updateTime();
    }

    public boolean checkCurrentPassword(UpdatePassReq request) {
        Member member = findMemberByEmail(request.getEmail());
        String findCurrentPassword = member.getPassword();
        return bCryptPasswordEncoder.matches(request.getCurrentPassword(), findCurrentPassword);
    }
}


