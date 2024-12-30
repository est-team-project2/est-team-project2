package org.example.est_team_project2.api.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.est_team_project2.dao.board.CommentRepository;
import org.example.est_team_project2.dao.board.PostRepository;
import org.example.est_team_project2.dao.member.MemberRepository;

import org.example.est_team_project2.dao.pedia.PediaRepository;
import org.example.est_team_project2.dao.pedia.PediaVersionRepository;
import org.example.est_team_project2.domain.board.Comment;
import org.example.est_team_project2.domain.board.Post;
import org.example.est_team_project2.domain.member.Member;
import org.example.est_team_project2.domain.member.memberEnums.MemberType;
import org.example.est_team_project2.domain.pedia.Pedia;
import org.example.est_team_project2.domain.pedia.PediaVersion;

import org.example.est_team_project2.service.admin.AdminService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final MemberRepository memberRepository;
    private final PediaRepository pediaRepository;
    private final PediaVersionRepository pediaVersionRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final AdminService adminService;

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
    public String manageMemberDetail(
            @PathVariable Long id,
            @PageableDefault(size = 10) Pageable pageable,
            Model model) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid member ID: " + id));
        model.addAttribute("member", member);

        Page<Post> posts = postRepository.findByMemberId(pageable, id);
        model.addAttribute("posts", posts);
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
    public String managePedia(@PageableDefault(size = 10) Pageable pageable, Model model) {

        Page<Pedia> pedias = pediaRepository.findAll(pageable);
        model.addAttribute("pedias", pedias);

        return "admin/managePedia";
    }

    @GetMapping("/managePedia/{id}")
    public String managePediaVersion(
            @PathVariable Long id,
            @PageableDefault(size = 10) Pageable pageable,
            Model model
    ){
        Page<PediaVersion> pediaVersions = pediaVersionRepository.findByPediaId(pageable, id);
        model.addAttribute("pediaVersions", pediaVersions);
        return "admin/managePediaVersion";
    }

    @GetMapping("/managePedia/detail/{id}")
    public String managePediaContent(
            @PathVariable Long id,
            @PageableDefault(size = 10) Pageable pageable,
            Model model
    ){
        Page<PediaVersion> pediaVersions = pediaVersionRepository.findByPediaId(pageable, id);
        model.addAttribute("pediaVersions", pediaVersions);
        return "admin/managePediaVersion";
    }

    @GetMapping("/managePost")
    public String managePost(@PageableDefault(size = 10) Pageable pageable, Model model) {
        Page<Post> posts = postRepository.findAll(pageable);
        model.addAttribute("posts", posts);
        return "admin/managePost";
    }

    @GetMapping("/managePost/{id}")
    public String managePostDetail(
            @PathVariable Long id,
            @PageableDefault(size = 10) Pageable pageable,
            Model model) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post ID: " + id));
        model.addAttribute("post", post);

        Page<Comment> comments = commentRepository.findByPostId(pageable, id);
        model.addAttribute("comments", comments);

        return "admin/managePostDetail";
    }

    @PostMapping("/managePost/{id}/updateDeleted")
    public String updateDeletedStatus(@PathVariable Long id, @RequestParam boolean deleted) {
        adminService.updateDeletedStatus(id, deleted);
        return "redirect:/admin/managePost";
    }
}