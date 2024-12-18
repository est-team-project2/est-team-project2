package org.example.est_team_project2.service.board;

import org.example.est_team_project2.dao.board.PostRepository;
import org.example.est_team_project2.dao.member.MemberRepository;
import org.example.est_team_project2.domain.board.Post;
import org.example.est_team_project2.domain.member.Member;
import org.example.est_team_project2.dto.board.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public PostService(PostRepository postRepository, MemberRepository memberRepository) {
        this.postRepository = postRepository;
        this.memberRepository = memberRepository;
    }

    public PostDto createPost(String email, String title, String contents) {
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Member not found"));
        Post post = Post.builder()
                .member(member)
                .title(title)
                .contents(contents)
                .build();
        postRepository.save(post);
        return PostDto.from(post);
    }

    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepository.findByDeletedFalse();
        return posts.stream().map(PostDto::from).collect(Collectors.toList());
    }

    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        post.softDelete();
        postRepository.save(post);
    }

    public PostDto getPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        return PostDto.from(post);
    }
}
