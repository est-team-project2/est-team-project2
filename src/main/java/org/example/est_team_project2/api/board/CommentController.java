package org.example.est_team_project2.api.board;

import lombok.RequiredArgsConstructor;
import org.example.est_team_project2.dto.board.CommentDto;
import org.example.est_team_project2.service.board.CommentService;
import org.example.est_team_project2.service.member.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;
    private final MemberService memberService;

    // 댓글 작성
    @PostMapping("/{postId}/new")
    public String addComment(@PathVariable Long postId,
        @RequestParam String contents) {

        String nickName = memberService.getSignedInMemberNickName();

        // 임시로 부여 => 나중에 접속 정보에서 가져옴
        nickName = "nickName1";

        commentService.addCommentByNickName(nickName, postId, contents);

        return "redirect:/posts/" + postId;
    }

    // 댓글 삭제
    @PostMapping("/{commentId}/delete")
    public String deleteComment(@PathVariable Long commentId) {
        CommentDto comment = commentService.deleteComment(commentId);
        return "redirect:/posts/" + comment.getPostId();
    }
}