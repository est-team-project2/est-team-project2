package org.example.est_team_project2.service.pedia;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.est_team_project2.domain.member.Member;
import org.example.est_team_project2.domain.pedia.Pedia;
import org.example.est_team_project2.domain.pedia.PediaContent;
import org.example.est_team_project2.domain.pedia.PediaEditRequest;
import org.example.est_team_project2.domain.pedia.PediaVersion;
import org.example.est_team_project2.domain.pedia.requestEnums.CommonStatus;
import org.example.est_team_project2.dto.pedia.PediaContentDto;
import org.example.est_team_project2.dto.pedia.VersionRequestDetails;
import org.example.est_team_project2.service.member.MemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class VersionRequestService {

    private final PediaContentService pediaContentService;
    private final PediaVersionService pediaVersionService;
    private final PediaEditRequestService pediaEditRequestService;
    private final PediaService pediaService;
    private final MemberService memberService;

    // 새로운 수정 요청 만들기
    public VersionRequestDetails createNewEditRequest(VersionRequestDetails versionRequestDetails,
        PediaContentDto pediaContentDto) {

        // 이메일로 Member 가져오기
        Member findMember = memberService.getMemberByEmail(
            versionRequestDetails.getRequestedMemberEmail());
        // pediaContent db에 저장
        PediaContent findPediaContent = pediaContentService.save(pediaContentDto);
        // Pedia title로 Pedia 가져오기
        Pedia findPedia = pediaService.getPediaByTitle(versionRequestDetails.getTitle());

        // 가져온 Pedia, PediaContent, Member로 PediaVersion 초기화, 저장
        PediaVersion pediaVersion = PediaVersion.builder()
            .pedia(findPedia)
            .pediaContent(findPediaContent)
            .editor(findMember).build();
        PediaVersion findPediaVersion = pediaVersionService.save(pediaVersion);

        // 생성한 PediaVersion으로 PediaEditRequest 초기화, 저장
        PediaEditRequest pediaEditRequest = PediaEditRequest.builder()
            .pediaVersion(findPediaVersion)
            .requestedMember(findMember)
            .build();
        PediaEditRequest findPediaEditRequest = pediaEditRequestService.save(pediaEditRequest);

        // 관련 정보 DTO로 반환
        return VersionRequestDetails.from(findPediaEditRequest);
    }

    // PediaEditRequestCode로 PediaContent 가져오기
    public PediaContentDto readPediaContentByCode(String code) {
        PediaEditRequest findPediaEditRequest = pediaEditRequestService.getPediaEditRequestByCode(
            code);

        return PediaContentDto.from(findPediaEditRequest.getPediaVersion().getPediaContent());
    }

    // PediaEditRequestCode로 관련 정보 가져오기
    public VersionRequestDetails readPediaEditRequestByCode(String code) {
        PediaEditRequest findPediaEditRequest = pediaEditRequestService.getPediaEditRequestByCode(
            code);

        return VersionRequestDetails.from(findPediaEditRequest);
    }

    // PediaEditRequest 요청 승인하기
    // code = PediaEditRequestCode, memberEmail = 관리자 이메일
    public VersionRequestDetails acceptPediaEditRequest(String code, String memberEmail) {

        // 입력된 파라미터로 Member, PediaEditRequest, PediaVersion, Pedia 불러오기
        Member findMember = memberService.getMemberByEmail(memberEmail);

        PediaEditRequest findPediaEditRequest = pediaEditRequestService.getPediaEditRequestByCode(
            code);
        PediaVersion findPediaVersion = findPediaEditRequest.getPediaVersion();
        Pedia findPedia = findPediaVersion.getPedia();

        // 기존 PediaVersion, PediaContent 비활성화 상태 변경
        String currentPediaVersionCode = findPedia.getCurrentVersionCode();
        PediaVersion findCurrentPediaVersion = pediaVersionService.getPediaVersionByCode(
            currentPediaVersionCode);
        findCurrentPediaVersion.setStatus(CommonStatus.INACTIVE);
        findCurrentPediaVersion.getPediaContent().setStatus(CommonStatus.INACTIVE);

        // 새로운 버젼 코드 생성 -> PediaVersion에 부여하고 PediaCurrentVersionCode 세팅
        // 새로운 PediaVersion 활성화 상태 변경
        String nextPediaVersionCode = genNextPediaVersionCode(currentPediaVersionCode);
        findPediaVersion.setPediaVersionCode(nextPediaVersionCode);
        findPediaVersion.setStatus(CommonStatus.ACTIVE);
        findPedia.setCurrentVersionCode(nextPediaVersionCode);

        // PediaEditRequest close 상태로 변경하고 관련 정보 반환
        return VersionRequestDetails.from(
            pediaEditRequestService.closePediaEditRequest(code, findMember));
    }

    public VersionRequestDetails rejectPediaEditRequest(String code, String memberEmail) {
        Member findMember = memberService.getMemberByEmail(memberEmail);

        // 요청이 거절된 경우는 PediaVersion에 REJ- 로 시작하는 코드를 부여
        PediaEditRequest findPediaEditRequest = pediaEditRequestService.getPediaEditRequestByCode(
            code);
        PediaVersion findPediaVersion = findPediaEditRequest.getPediaVersion();
        findPediaVersion.setPediaVersionCode(genRejectedPediaVersionCode());

        // PediaEditRequest close 상태로 변경하고 관련 정보 반환
        return VersionRequestDetails.from(
            pediaEditRequestService.closePediaEditRequest(code, findMember));
    }

    // 현재 버젼 바탕으로 다음 버젼 생성 (+0.1)
    // 기존 버젼이 없을 경우, 0.1에서 시작
    public String genNextPediaVersionCode(String currentVersionCode) {
        if (currentVersionCode == null) {
            return "0.1";
        }

        double currentVersionCodeDouble = Double.parseDouble(currentVersionCode) + 0.1;

        return String.format("%.1f", currentVersionCodeDouble);
    }

    // REJ- 로 시작하는 코드를 생성
    public String genRejectedPediaVersionCode() {
        return "REJ-" + System.currentTimeMillis();
    }
}
