package org.example.est_team_project2.api.member;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.est_team_project2.dto.member.MemberDto;
import org.example.est_team_project2.service.member.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;

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
        return "signup";
    }


    // 회원가입
    @PostMapping("/signup")
    public String processSignUp (@Valid MemberDto memberDto , BindingResult bindingResult, Model model) {

        String checkEmail = memberService.checkDuplicateEmail(memberDto);
        String checkNickName = memberService.checkDuplicateNickName(memberDto);

        log.info("memberDto = {}", memberDto);
        // 벨리데이션 에러가 있는거나 이메일이 있거나 닉네임이 있다면 다시 signup 페이지로

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

            return "signup";

        } else {
            // 위의 조건에 해당하지 않으면 db에 멤버 정보를 저장 후 index 페이지로
            memberService.save(memberDto);
            return "index";
        }

    }

    // 비동기 통신 -> 이메일이 있는지 없는지 체크
    @PostMapping("/signup/duplicateCheckEmail")
    @ResponseBody
    public String processCheckDuplicateEmail (@RequestBody MemberDto memberDto) {
        // 이메일이 있는지 없는지 일단 체크

        String checkEmail = memberService.checkDuplicateEmail(memberDto);
        return checkEmail;

    }
    // 비동기 통신 -> 닉네임이 있는지 없는지 체크
    @PostMapping("/signup/duplicateCheckNickName")
    @ResponseBody
    public String processCheckDuplicateNickName (@RequestBody MemberDto memberDto) {
        // 닉네임이 있는지 없는지 일단 체크

        String checkNickName = memberService.checkDuplicateNickName(memberDto);
        return checkNickName;

    }

}