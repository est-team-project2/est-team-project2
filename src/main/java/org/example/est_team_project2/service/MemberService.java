package org.example.est_team_project2.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.est_team_project2.dao.MemberRepository;
import org.example.est_team_project2.domain.Member;
import org.example.est_team_project2.dto.MemberDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.example.est_team_project2.domain.enums.SocialType;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Optional;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public void save(MemberDto memberDto) {
        //객체 생성 해서 저장
        Member member = Member.builder()
                .email(memberDto.getEmail())
                .nickName(memberDto.getPassword())
                .password(memberDto.getPassword())
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
    
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        String socialType = userRequest.getClientRegistration().getRegistrationId().toUpperCase();

        //이넘타임으로 바꾸기
        SocialType eSocialType = SocialType.valueOf(socialType);

        MemberDetails memberDetails = MemberDetailsFactory.memberDetails(eSocialType, oAuth2User);

        Optional<Member> memberOptional = memberRepository.findByEmail(memberDetails.getEmail());

        //찾아서 값이 있다면 반환 없다면 안에 람다식 반환
        Member findMember = memberOptional.orElseGet(
                () -> {
                    Member member = Member.builder()
                            .email(memberDetails.getEmail())
                            .password(memberDetails.getPassword())
                            .socialType(memberDetails.getSocialType())
                            .nickName(memberDetails.getNickname())
                            .build();
                    return memberRepository.save(member);
                }
        );

        if (findMember.getSocialType().equals(eSocialType)) {
            memberDetails.setMemberType(findMember.getRole());
            return memberDetails;
        } else {
            throw new RuntimeException();
        }
    }

    public String emailCheck(MemberDto memberDto) {
        Optional<Member> checkEmail = memberRepository.findByEmail(memberDto.getEmail());

        if (checkEmail.isPresent()) {
            // 조회 결과가 있다 사용불가
            return null;
        } else {
            // 조회 결과가 없다 사용가능
            return "ok";
        }
    }

}
