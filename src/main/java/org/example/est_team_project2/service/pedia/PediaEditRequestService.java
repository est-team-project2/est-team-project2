package org.example.est_team_project2.service.pedia;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.est_team_project2.dao.pedia.PediaEditRequestRepository;
import org.example.est_team_project2.domain.member.Member;
import org.example.est_team_project2.domain.pedia.Pedia;
import org.example.est_team_project2.domain.pedia.PediaContent;
import org.example.est_team_project2.domain.pedia.PediaEditRequest;
import org.example.est_team_project2.dto.pedia.PediaContentDto;
import org.example.est_team_project2.dto.pedia.VersionRequestDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PediaEditRequestService {

    private final PediaEditRequestRepository pediaEditRequestRepository;

    public PediaEditRequest save(PediaEditRequest pediaEditRequest) {
        return pediaEditRequestRepository.save(pediaEditRequest);
    }

    public PediaEditRequest closePediaEditRequest(String code, Member member) {
        PediaEditRequest findRequest = getPediaEditRequestByCode(code);
        findRequest.closeRequest(member);

        return findRequest;
    }

    public PediaEditRequest getPediaEditRequestByCode(String code) {
        return pediaEditRequestRepository.findByPediaEditRequestCode(code).orElseThrow(
            () -> new NoSuchElementException("Pedia Edit Request By Code Not Found")
        );
    }

    //수정요청 전체 조회
    public List<PediaEditRequest> findAllPediaEditRequests() {
        return pediaEditRequestRepository.findAll();
    }

    public PediaEditRequest findPediaEditRequestById(Long id) {
        return pediaEditRequestRepository.findById(id).orElseThrow();

    }


}
