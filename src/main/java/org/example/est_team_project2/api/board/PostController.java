package org.example.est_team_project2.api.board;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.est_team_project2.dto.board.CommentDto;
import org.example.est_team_project2.dto.board.PostDto;
import org.example.est_team_project2.service.board.CommentService;
import org.example.est_team_project2.service.board.PostService;
import org.example.est_team_project2.service.member.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final CommentService commentService;
    private final MemberService memberService;

    // 게시글 목록 페이지
    @GetMapping
    public String getAllPosts(Model model) {
        List<PostDto> posts = postService.getAllPosts();
        model.addAttribute("posts", posts);
        return "posts/list";
    }

    @GetMapping("/{postId}")
    public String getPost(@PathVariable Long postId, Model model) {
        PostDto post = postService.getPost(postId);
        post.setCanModify(memberService.getCanModify(post.getMemberNickName()));

        List<CommentDto> comments = commentService.getCommentsByPost(postId);
        comments.forEach(comment -> {
            comment.setCanModify(
                memberService.getCanModify(comment.getMemberNickName())
            );
        });

        model.addAttribute("post", post);
        model.addAttribute("comments", comments);

        return "posts/detail";
    }

    // 게시글 작성 페이지
    @GetMapping("/new")
    public String createPostForm(Model model) {
        return "posts/create";  // 게시글 작성 화면
    }

    // 게시글 작성
    @PostMapping("/new")
    public String createPost(@RequestParam String title, @RequestParam String contents) {

        String nickName = memberService.getSignedInMemberNickName();

        nickName = "nickName1";

        PostDto postDto = postService.createPost(nickName, title, contents);

        return "redirect:/posts/" + postDto.getId();  // 작성 후 해당 게시물로 이동
    }

    // 게시글 삭제
    @PostMapping("/{postId}/delete")
    public String deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return "redirect:/posts";  // 삭제 후 게시글 목록으로 리다이렉트
    }

    // 게시글 수정 페이지
    @GetMapping("/{postId}/update")
    public String updatePostForm(@PathVariable Long postId, Model model) {
        PostDto post = postService.getPost(postId);

        model.addAttribute("post", post);

        return "posts/update";
    }

    @PostMapping("/{postId}/update")
    public String updatePost(@PathVariable Long postId, @RequestParam String title,
        @RequestParam String contents) {

        postService.updatePost(postId, title, contents);

        return "redirect:/posts/" + postId;
    }
}
