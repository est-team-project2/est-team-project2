package org.example.est_team_project2.api.member;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.est_team_project2.dto.member.*;
import org.example.est_team_project2.service.member.UpdateMemberService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Slf4j
@Controller
@RequestMapping("/my")
@RequiredArgsConstructor
public class MemberInfoController {

    private final UpdateMemberService updateMemberService;


    @GetMapping
    public String my(Model model, Principal principal) {
        addUserAttributesToModel(principal, model);
        return "member/profile";
    }

    @PostMapping("/update-nickname")
    public String updateNickname(
            @Valid UpdateNicknameReq request,
            RedirectAttributes redirectAttributes
    ) {
        updateMemberService.updateNickname(request);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Object principal = auth.getPrincipal();
        if (principal instanceof MemberDetails memberDetails) {
            memberDetails.setNickname(request.getNewNickname());
            OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) auth;
            Authentication newAuth = new OAuth2AuthenticationToken(
                    memberDetails,
                    oauthToken.getAuthorities(),
                    oauthToken.getAuthorizedClientRegistrationId()
            );
            SecurityContextHolder.getContext().setAuthentication(newAuth);
        } else if (principal instanceof MemberDto memberDto) {
            memberDto.setNickName(request.getNewNickname());
            Authentication newAuth = new UsernamePasswordAuthenticationToken(principal, auth.getCredentials(), auth.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(newAuth);
        }

        redirectAttributes.addFlashAttribute("message", "닉네임이 업데이트되었습니다.");
        return "redirect:/my";
    }


    @GetMapping("/update-password")
    public String showUpdatePasswordForm(Model model, Principal principal) {
        addUserAttributesToModel(principal, model);
        return "member/update-password";
    }

    @PostMapping("/update-password")
    public String updatePassword(
            @Valid UpdatePassReq request,
            RedirectAttributes redirectAttributes
    ) {
        log.info("request = {}", request);
        log.info("checkCurrentPassword= {}", updateMemberService.checkCurrentPassword(request));
        if (updateMemberService.checkCurrentPassword(request)){
            updateMemberService.updatePassword(request);
            redirectAttributes.addFlashAttribute("message", "비밀번호가 변경되었습니다.");
            return "redirect:/my";
        } else {
            redirectAttributes.addFlashAttribute("message", "비밀번호 변경에 실패하였습니다.");
            return "redirect:/my";
        }
    }

    // 공통 처리 메서드
    private void addUserAttributesToModel(Principal principal, Model model) {
        log.info("principal = {}", principal);
        if (principal instanceof OAuth2AuthenticationToken token) {
            MemberDetails memberDetails = (MemberDetails) token.getPrincipal();
            log.info("attributes = {}", memberDetails);
            model.addAttribute("email", memberDetails.getEmail());
            model.addAttribute("nickName", memberDetails.getNickname());
            model.addAttribute("password", memberDetails.getPassword());
            model.addAttribute("role", memberDetails.getRole());
        } else if (principal instanceof UsernamePasswordAuthenticationToken token) {
            MemberDto memberDto = (MemberDto) token.getPrincipal();
            log.info("memberDto = {}", memberDto);
            model.addAttribute("email", memberDto.getEmail());
            model.addAttribute("nickName", memberDto.getNickName());
            model.addAttribute("password", memberDto.getPassword());
            model.addAttribute("role", memberDto.getRole());
        }
    }


}


