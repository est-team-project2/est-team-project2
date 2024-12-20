package org.example.est_team_project2.service.member;

import lombok.extern.slf4j.Slf4j;
import org.example.est_team_project2.domain.member.memberEnums.MemberType;
import org.example.est_team_project2.domain.member.memberEnums.SocialType;
import org.example.est_team_project2.dto.member.MemberDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;
import java.util.Random;

@Slf4j
public class MemberDetailsFactory {

    public static MemberDetails memberDetails(
            SocialType provider, OAuth2User oauth2User
    ){
        Map<String, Object> attributes = oauth2User.getAttributes();

        switch (provider){
            case SocialType.GOOGLE -> {
                return MemberDetails.builder()
                        .name(attributes.get("name").toString())
                        .email(attributes.get("email").toString())
                        .password(attributes.get("email").toString())
                        .socialType(provider)
                        .role(MemberType.USER)
                        .attributes(attributes)
                        .build();
            }
            case SocialType.KAKAO-> {
                Map<String, String> getNickname = (Map<String, String>) attributes.get("properties");
                Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
                String getEmail = (String) kakaoAccount.get("email");
                return MemberDetails.builder()
                        .name(getNickname.get("nickname"))
                        .email(getEmail)
                        .password(getEmail)
                        .socialType(provider)
                        .role(MemberType.USER)
                        .attributes(attributes)
                        .build();
            }

            case SocialType.NAVER -> {
                Map<String, String> properties = (Map<String, String>) attributes.get("response");
                return MemberDetails.builder()
                        .name(properties.get("name"))
                        .email(properties.get("email"))
                        .password(properties.get("email"))
                        .socialType(provider)
                        .role(MemberType.USER)
                        .attributes(attributes)
                        .build();
            }
            default ->
                throw new IllegalArgumentException("Unknown provider: " + provider);
        }
    }


}
