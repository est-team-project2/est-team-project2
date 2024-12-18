package org.example.est_team_project2.api.member;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import org.example.est_team_project2.dto.member.MemberDto;
import org.example.est_team_project2.service.member.MemberService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;



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

    @GetMapping("/signin")
    public String login() {
        return "signin";
    }

    @GetMapping("/signup")
    public String signUp(Model model) {
        model.addAttribute("memberDto", new MemberDto());
        return "signup-re";
    }

    @PostMapping("/signup")
    public String processSignUp (@Valid MemberDto memberDto , BindingResult bindingResult, Model model) {
        // 이메일이 있는지 없는지 일단 체크
        String checkEmail = memberService.checkDuplicateEmail(memberDto);
        String checkNickName = memberService.checkDuplicateNickName(memberDto);

        log.info("memberDto = {}", memberDto);

        if (bindingResult.hasErrors() || checkEmail == null || checkNickName == null) {

            if (bindingResult.hasErrors()) {
                model.addAttribute("checkError", "error");
            }

            if ( checkEmail == null ) {
                model.addAttribute("checkEmail", "fail");
            }

            if ( checkNickName == null ) {
                model.addAttribute("checkNickName", "fail");
            }

            return "signup-re";

        } else {
            memberService.save(memberDto);
            return "index";
        }

    }

    @PostMapping("/signup/duplicateCheckEmail")
    @ResponseBody
    public String processCheckDuplicateEmail (@RequestBody MemberDto memberDto) {
        // 이메일이 있는지 없는지 일단 체크

        String checkEmail = memberService.checkDuplicateEmail(memberDto);
        return checkEmail;

    }

    @PostMapping("/signup/duplicateCheckNickName")
    @ResponseBody
    public String processCheckDuplicateNickName (@RequestBody MemberDto memberDto) {
        // 닉네임이 있는지 없는지 일단 체크

        String checkNickName = memberService.checkDuplicateNickName(memberDto);
        return checkNickName;

    }


}