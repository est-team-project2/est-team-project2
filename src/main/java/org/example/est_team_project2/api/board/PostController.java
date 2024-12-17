package org.example.est_team_project2.api.board;

import lombok.extern.slf4j.Slf4j;
import org.example.est_team_project2.dto.board.CommentDto;
import org.example.est_team_project2.dto.board.PostDto;
import org.example.est_team_project2.service.board.CommentService;
import org.example.est_team_project2.service.board.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final CommentService commentService;

    public PostController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

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
        List<CommentDto> comments = commentService.getCommentsByPost(postId);
        model.addAttribute("post", post);
        model.addAttribute("comments", comments);
        return "posts/detail";
    }

    // 게시글 작성
    @GetMapping("/new")
    public String createPostForm() {
        return "posts/create.html";  // 게시글 작성 화면
    }

    // 게시글 작성
    @PostMapping("/new")
    public String createPost(@RequestParam String email, @RequestParam String title, @RequestParam String contents) {
        postService.createPost(email, title, contents);
        return "redirect:/posts";  // 작성 후 게시글 목록으로 리다이렉트
    }

    // 게시글 삭제
    @PostMapping("/{postId}/delete")
    public String deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return "redirect:/posts";  // 삭제 후 게시글 목록으로 리다이렉트
    }
}

