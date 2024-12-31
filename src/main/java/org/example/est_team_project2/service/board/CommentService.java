package org.example.est_team_project2.service.board;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.example.est_team_project2.dao.board.CommentRepository;
import org.example.est_team_project2.domain.board.Comment;
import org.example.est_team_project2.domain.board.Post;
import org.example.est_team_project2.domain.member.Member;
import org.example.est_team_project2.dto.board.CommentDto;
import org.example.est_team_project2.service.member.MemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostService postService;
    private final MemberService memberService;

    public CommentDto addCommentByNickName(String nickName, Long postId, String contents) {
        Post post = postService.getPostById(postId);
        Member member = memberService.getMemberByNickName(nickName);
        Comment comment = Comment.builder()
            .post(post)
            .member(member)
            .contents(contents)
            .build();
        commentRepository.save(comment);
        return CommentDto.from(comment);
    }

    public List<CommentDto> getCommentsByPost(Long postId) {
        Post post = postService.getPostById(postId);
        List<Comment> comments = commentRepository.findByPostAndDeletedFalse(post);
        return comments.stream().map(CommentDto::from).collect(Collectors.toList());
    }

    public CommentDto deleteComment(Long commentId) {
        Comment comment = getCommentById(commentId);
        comment.softDelete();
        commentRepository.save(comment);
        return CommentDto.from(comment);
    }

    public Comment getCommentById(Long commentId) {
        return commentRepository.findById(commentId)
            .orElseThrow(() -> new RuntimeException("Comment By Id Not Found"));
    }

    public CommentDto updateComment(Long commentId, String contents) {
        Comment comment = getCommentById(commentId);
        comment.update(contents);

        return CommentDto.from(comment);
    }
}
