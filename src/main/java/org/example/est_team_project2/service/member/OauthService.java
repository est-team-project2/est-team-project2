package org.example.est_team_project2.service.member;

import lombok.RequiredArgsConstructor;
import org.example.est_team_project2.dao.member.MemberRepository;
import org.example.est_team_project2.domain.member.Member;
import org.example.est_team_project2.domain.member.memberEnums.SocialType;
import org.example.est_team_project2.dto.member.MemberDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class OauthService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

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
                            .nickName(memberDetails.getNickname() + genNickName())
                            .role(memberDetails.getRole())
                            .build();
                    return memberRepository.save(member);
                }
        );

        if (findMember.getSocialType().equals(eSocialType)) {
            memberDetails.setNickname(findMember.getNickName());
            memberDetails.setRole(findMember.getRole());
            return memberDetails;
        } else {
            throw new RuntimeException();
        }
    }

    public static String genNickName() {
        String characters = "abcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder nickname = new StringBuilder(8);
        Random random = new Random();

        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(characters.length());
            nickname.append(characters.charAt(index));
        }

        return nickname.toString();
    }

}
