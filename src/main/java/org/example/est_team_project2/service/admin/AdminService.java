package org.example.est_team_project2.service.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.est_team_project2.dao.board.PostRepository;
import org.example.est_team_project2.dao.member.MemberRepository;
import org.example.est_team_project2.domain.board.Post;
import org.example.est_team_project2.domain.member.Member;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminService {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;


    //Member
    public List<Member> getMembers() {
        List<Member> members = new ArrayList<Member>();
        return members;
    }

    //Post
    public Post findById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Post ID: " + postId));
    }

    public void updateDeletedStatus(Long postId, boolean deleted) {
        Post post = findById(postId);
        post.adminSoftDelete(deleted);
        postRepository.save(post);
    }
}
