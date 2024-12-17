package org.example.est_team_project2.service.pedia;

import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.est_team_project2.dao.pedia.PediaEditRequestRepository;
import org.example.est_team_project2.domain.member.Member;
import org.example.est_team_project2.domain.pedia.PediaEditRequest;
import org.example.est_team_project2.dto.pedia.PediaEditRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PediaEditRequestService {

    private final PediaEditRequestRepository pediaEditRequestRepository;

    public PediaEditRequestDto save(PediaEditRequestDto pediaEditRequestDto) {
        return PediaEditRequestDto.from(
            pediaEditRequestRepository.save(PediaEditRequest.from(pediaEditRequestDto)));
    }

    public PediaEditRequestDto acceptEditRequest(String code, Member member) {
        PediaEditRequest findRequest = getPediaEditRequestByCode(code);
        // 요청 승인 -> 버전 관리 로직 추가 필요
        findRequest.closeRequest(member);

        return PediaEditRequestDto.from(findRequest);
    }

    public PediaEditRequestDto rejectEditRequest(String code, Member member) {
        PediaEditRequest findRequest = getPediaEditRequestByCode(code);
        // 요청 거절 -> 버전 관리 로직 추가 필요
        findRequest.closeRequest(member);

        return PediaEditRequestDto.from(findRequest);
    }

    private PediaEditRequest getPediaEditRequestByCode(String code) {
        return pediaEditRequestRepository.findByPediaEditRequestCode(code).orElseThrow(
            () -> new NoSuchElementException("")
        );
    }
}
