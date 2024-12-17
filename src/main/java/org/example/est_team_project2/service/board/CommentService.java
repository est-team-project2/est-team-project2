package org.example.est_team_project2.service.board;

import org.example.est_team_project2.dao.board.CommentRepository;
import org.example.est_team_project2.dao.board.PostRepository;
import org.example.est_team_project2.dao.member.MemberRepository;
import org.example.est_team_project2.domain.board.Comment;
import org.example.est_team_project2.domain.board.Post;
import org.example.est_team_project2.domain.member.Member;
import org.example.est_team_project2.dto.board.CommentDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository, MemberRepository memberRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.memberRepository = memberRepository;
    }

    public CommentDto addComment(Long memberId, Long postId, String contents) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException("Member not found"));
        Comment comment = Comment.builder()
                .post(post)
                .member(member)
                .contents(contents)
                .build();
        commentRepository.save(comment);
        return CommentDto.from(comment);
    }

    public List<CommentDto> getCommentsByPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        List<Comment> comments = commentRepository.findByPostAndDeletedFalse(post);
        return comments.stream().map(CommentDto::from).collect(Collectors.toList());
    }

    public CommentDto deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new RuntimeException("Comment not found"));
        comment.softDelete();
        commentRepository.save(comment);
        return CommentDto.from(comment);
    }
}
