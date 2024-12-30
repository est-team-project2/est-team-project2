package org.example.est_team_project2.service.board;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.example.est_team_project2.dao.board.PostRepository;
import org.example.est_team_project2.domain.board.Post;
import org.example.est_team_project2.domain.member.Member;
import org.example.est_team_project2.dto.board.PostDto;
import org.example.est_team_project2.service.member.MemberService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private static final int PAGE_SIZE = 10;

    private final PostRepository postRepository;
    private final MemberService memberService;

    public PostDto createPost(String nickName, String title, String contents) {
        Member member = memberService.getMemberByNickName(nickName);
        Post post = Post.builder()
            .member(member)
            .title(title)
            .contents(contents)
            .build();
        postRepository.save(post);
        return PostDto.from(post);
    }

    public PostDto updatePost(Long postId, String title, String contents) {
        Post post = getPostById(postId);
        post.update(title, contents);

        return PostDto.from(post);
    }

    public void deletePost(Long postId) {
        Post post = getPostById(postId);
        post.softDelete();
        postRepository.save(post);
    }

    public PostDto getPost(Long postId) {
        Post post = getPostById(postId);
        return PostDto.from(post);
    }

    public Post getPostById(Long postId) {
        return postRepository.findById(postId)
            .orElseThrow(() -> new NoSuchElementException("Post by Id not found"));
    }

    public void increaseViews(Long postId) {
        Post post = getPostById(postId);
        post.increaseViews();
    }

    public Page<PostDto> getList(int page, String sortType) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc(sortType));

        Pageable pageable = PageRequest.of(page, PAGE_SIZE, Sort.by(sorts));

        Page<Post> allByDeletedFalse = this.postRepository.findAllByDeletedFalse(pageable);

        return allByDeletedFalse.map(PostDto::from);

    }

    public List<PostDto> findTopPostsByViews() {
        LocalDateTime oneMonthAgo = LocalDateTime.now().minusMonths(1);
        Pageable pageable = PageRequest.of(0, 3);
        return postRepository.findTop3ByDeletedFalseOrderByViewsDesc(oneMonthAgo, pageable).stream()
            .map(PostDto::from)
            .toList();
    }
}
