package org.example.est_team_project2.api.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

//    private final MemberService memberService;
//    private final PasswordEncoder passwordEncoder;
//
//    @GetMapping("/")
//    public String index() {
//        return "index";
//    }
//
//    @GetMapping("/login")
//    public String login() {
//        return "login";
//    }
//
//    @GetMapping("/signup")
//    public String signUp() {
//        return "signup";
//    }
//
//    @GetMapping("/signin")
//    public String signIn() {
//        return "signin";
//    }
//
//    @PostMapping("/signup")
//    public String processSignUp (MemberDto memberDto) {
//        // 이메일이 있는지 없는지 일단 체크
//        String checkEmailonController = memberService.emailCheck(memberDto);
//
//        // null이 아니면 저장 가능
//        if (checkEmailonController != null) {
//            memberService.save(memberDto);
//            return "index";
//        } else {
//            //null이면 저장불가
//            return "signup";
//        }
//    }
//
//    //test
//    @GetMapping("/profile")
//    public String profile(Model model, Authentication authentication) {
//
//        MemberDetails memberDetails = (MemberDetails) authentication.getPrincipal();
//
//        log.info("memberDetails = {}", memberDetails);
//
//        model.addAttribute("nickname", memberDetails.getName());
//        model.addAttribute("email", memberDetails.getEmail());
//        model.addAttribute("socialType", memberDetails.getSocialType());
//        model.addAttribute("memberType", memberDetails.getMemberType());
//
//
//        return "profile";
//    }
}