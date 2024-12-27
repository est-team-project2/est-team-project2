package org.example.est_team_project2.api.admin;

import lombok.RequiredArgsConstructor;
import org.example.est_team_project2.dao.member.MemberRepository;
import org.example.est_team_project2.domain.member.Member;
import org.example.est_team_project2.domain.member.memberEnums.MemberType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final MemberRepository memberRepository;

    @GetMapping
    public String adminHome() {
        return "admin/admin";
    }

    @GetMapping("/manageMember")
    public String manageMember(@PageableDefault(size = 10) Pageable pageable, Model model) {
        Page<Member> members = memberRepository.findAll(pageable);
        model.addAttribute("members", members);
        return "admin/manageMember";
    }

    @GetMapping("/manageMember/{id}")
    public String manageMemberDetail(@PathVariable Long id, Model model) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid member ID: " + id));
        model.addAttribute("member", member);
        return "admin/manageMemberDetail";
    }

    @PostMapping("/members/{id}/update-role")
    public String updateMemberRole(@PathVariable Long id, @RequestParam("role") MemberType role) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("잉? 그럴리가"));

        try {
            MemberType newRole = role;
            member.changeRole(newRole);
            memberRepository.save(member);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid role: " + role);
        }

        return "redirect:/admin/manageMember";
    }

    @GetMapping("/managePedia")
    public String managePedia(Model model) {
        // 백과 관리 데이터를 추가
        model.addAttribute("pedias", List.of()); // 실제 데이터로 교체
        return "admin/managePedia";
    }

    @GetMapping("/managePost")
    public String managePost(Model model) {
        // 게시글 관리 데이터를 추가
        model.addAttribute("posts", List.of()); // 실제 데이터로 교체
        return "admin/managePost";
    }


}