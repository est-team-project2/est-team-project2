package org.example.est_team_project2.service.member;

import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.example.est_team_project2.dao.member.MemberRepository;
import org.example.est_team_project2.domain.member.Member;

import org.example.est_team_project2.dto.member.MemberDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void save(MemberDto memberDto) {
        //객체 생성 해서 저장
        log.info("memberDto = {}", memberDto);
        Member member = Member.builder()
                .email(memberDto.getEmail())
                .nickName(memberDto.getNickName())
                .password(passwordEncoder.encode(memberDto.getPassword()))
                .build();
        memberRepository.save(member);
    }

    // 필터 체인에서 테이블의 정보를 확인
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Member> byEmail = memberRepository.findByEmail(email);
        Member member = byEmail.orElseThrow();
        return new MemberDto(member);
    }

    public String checkDuplicateEmail(MemberDto memberDto) {

        Optional<Member> checkEmail = memberRepository.findByEmail(memberDto.getEmail());

        if (checkEmail.isPresent()) {
            // 조회 결과가 있다 사용불가
            System.out.println("fail");
            return null;

        } else {
            // 조회 결과가 없다 사용가능
            return "ok";
        }
    }


    public Member getMemberByEmail(String email) {
        return memberRepository.findByEmail(email).orElseThrow(
            () -> new NoSuchElementException("Member By Email Not Found")
        ); 
    }

    public String checkDuplicateNickName(MemberDto memberDto) {

        Optional<Member> checkEmail = memberRepository.findByNickName(memberDto.getNickName());

        if (checkEmail.isPresent()) {
            // 조회 결과가 있다 사용불가
            System.out.println("fail");
            return null;

        } else {
            // 조회 결과가 없다 사용가능
            return "ok";
        }
    }
}

