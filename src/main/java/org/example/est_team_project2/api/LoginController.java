package org.example.est_team_project2.api;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.est_team_project2.domain.Member;
import org.example.est_team_project2.dto.MemberDto;
import org.example.est_team_project2.service.MemberService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signUp() {
        return "signup";
    }

    @PostMapping("/signup")
    public String processSignUp (MemberDto memberDto) {
        // 이메일이 있는지 없는지 일단 체크
        String checkEmailonController = memberService.emailCheck(memberDto);

        // null이 아니면 저장 가능
        if (checkEmailonController != null) {
            memberService.save(memberDto);
            return "index";
        } else {
            //null이면 저장불가
            return "signup";
        }
    }



}

