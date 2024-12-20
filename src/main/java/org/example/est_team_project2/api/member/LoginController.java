package org.example.est_team_project2.api.member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.est_team_project2.dto.member.MemberDto;
import org.example.est_team_project2.service.member.FindPassService;
import org.example.est_team_project2.service.member.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;


@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;
    private final FindPassService findPassService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/signin")
    public String login() {
        return "member/signin";
    }

    @GetMapping("/signup")
    public String signUp(Model model) {
        model.addAttribute("memberDto", new MemberDto());
        return "member/signup";
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

            return "signup";

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

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/index";
    }

    @GetMapping("/find-password")
    public String findPassword() {
        return "member/findPassword";
    }

    @PostMapping("/find-password")
    public ResponseEntity<String> processFindPassword(@RequestParam String email) {
        try {
            // 임시 비밀번호 생성 및 업데이트
            String tempPassword = findPassService.createTempPassword(email);

            // 이메일로 임시 비밀번호 전송
            findPassService.sendTempPassword(email, tempPassword);

            return ResponseEntity.ok("임시 비밀번호가 이메일로 전송되었습니다.");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 이메일을 가진 사용자를 찾을 수 없습니다.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("임시 비밀번호 발송 중 오류가 발생했습니다.");
        }
    }


}