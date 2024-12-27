package org.example.est_team_project2.service.admin;

import lombok.RequiredArgsConstructor;
import org.example.est_team_project2.dao.member.MemberRepository;
import org.example.est_team_project2.domain.member.Member;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final MemberRepository memberRepository;



    public List<Member> getMembers() {
        List<Member> members = new ArrayList<Member>();
        return members;
    }
}
