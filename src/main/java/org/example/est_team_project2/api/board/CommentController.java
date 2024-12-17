package org.example.est_team_project2.api.board;

import org.example.est_team_project2.dto.board.CommentDto;
import org.example.est_team_project2.service.board.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // 댓글 작성
    @PostMapping("/new")
    public String addComment(@RequestParam Long memberId, @RequestParam Long postId, @RequestParam String contents) {
        commentService.addComment(memberId, postId, contents);
        return "redirect:/posts/" + postId;
    }

    // 댓글 삭제
    @PostMapping("/{commentId}/delete")
    public String deleteComment(@PathVariable Long commentId) {
        CommentDto comment = commentService.deleteComment(commentId);
        return "redirect:/posts/" + comment.getPostId();
    }
}

