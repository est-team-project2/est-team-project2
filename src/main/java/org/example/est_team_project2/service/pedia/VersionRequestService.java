package org.example.est_team_project2.service.pedia;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
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
@ToString
public class VersionRequestService {

    private final PediaContentService pediaContentService;
    private final PediaVersionService pediaVersionService;
    private final PediaEditRequestService pediaEditRequestService;
    private final PediaService pediaService;
    private final MemberService memberService;

    // 1번 RequestEdit
    // 페이지 수정 페이지 에서 Post로 보내면 됨
    // 새로운 수정 요청 만들기
    public VersionRequestDetails createNewEditRequest(VersionRequestDetails versionRequestDetails,
        PediaContentDto pediaContentDto) {
        log.info("versionRequestDetails = {}", versionRequestDetails.getRequestedMemberEmail());

        // 이메일로 Member 가져오기
        Member findMember = memberService.getMemberByEmail(
            versionRequestDetails.getRequestedMemberEmail());
        // pediaContent db에 저장
        PediaContent findPediaContent = pediaContentService.saveEdit(pediaContentDto);
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

    // 2번 수정요청의 내용만 확인 CheckContents
    // 수정 페이지 -> 진돗개 요청코드 생성
    // 관리자 요청을 확인을 받거나 거절페이지 먼저 정보가 들어가 있어야지->
    // 관리자 페이지 되어야 할 듯 합니다.
    // PediaEditRequestCode로 PediaContent 가져오기
    // pediaEditRequestCode를 가져온다.
    // 내용만 불러오는거 get
    public PediaContentDto readPediaContentByCode(String code) {
        PediaEditRequest findPediaEditRequest = pediaEditRequestService.getPediaEditRequestByCode(
            code);

        return PediaContentDto.from(findPediaEditRequest.getPediaVersion().getPediaContent());
    }

    // 3번 수정요청의 자세한 내용 확인 checkAllContents
    // 관리자 페이지 되어야 할 듯 합니다.
    // PediaEditRequestCode로 관련 정보 가져오기
    // 요청에 관련된 정보들을 전체적으로 불러오는거
    public VersionRequestDetails readPediaEditRequestByCode(String code) {
        PediaEditRequest findPediaEditRequest = pediaEditRequestService.getPediaEditRequestByCode(
            code);

        return VersionRequestDetails.from(findPediaEditRequest);
    }

    //
    // 4번 RequestAccept
    // 관리자 페이지 수정 요청 승인 post
    // PediaEditRequest 요청 승인하기
    // code = PediaEditRequestCode, memberEmail = 관리자 이메일
    public VersionRequestDetails acceptPediaEditRequest(String code, String memberEmail) {

        System.out.println("수정 요청 승인 서비스 도달");
        // 입력된 파라미터로 Member, PediaEditRequest, PediaVersion, Pedia 불러오기
        Member findMember = memberService.getMemberByEmail(memberEmail);
        log.info("findMember = {}", findMember);

        PediaEditRequest findPediaEditRequest = pediaEditRequestService.getPediaEditRequestByCode(
            code);
        log.info("findPediaEditRequest = {}", findPediaEditRequest);

        PediaVersion findPediaVersion = findPediaEditRequest.getPediaVersion();
        log.info("findPediaVersion = {}", findPediaVersion);

        Pedia findPedia = findPediaVersion.getPedia();
        log.info("findPedia = {}", findPedia);

        // 기존 PediaVersion, PediaContent 비활성화 상태 변경
        String currentPediaVersionCode = findPedia.getCurrentVersionCode();
        log.info("currentPediaVersionCode = {}", currentPediaVersionCode);

        if (currentPediaVersionCode != null) {
            PediaVersion findCurrentPediaVersion = pediaVersionService.getPediaVersionByCode(
                    currentPediaVersionCode);
            findCurrentPediaVersion.setStatus(CommonStatus.DEACTIVE);
            findCurrentPediaVersion.getPediaContent().setStatus(CommonStatus.DEACTIVE);
        }

        // 새로운 버젼 코드 생성 -> PediaVersion에 부여하고 PediaCurrentVersionCode 세팅
        // 새로운 PediaVersion 활성화 상태 변경
        String nextPediaVersionCode = genNextPediaVersionCode(findPedia.getId(),currentPediaVersionCode);
        findPediaVersion.setPediaVersionCode(nextPediaVersionCode);
        findPediaVersion.setStatus(CommonStatus.ACTIVE);
        findPediaVersion.getPediaContent().setStatus(CommonStatus.ACTIVE);
        findPedia.setCurrentVersionCode(nextPediaVersionCode);

        // PediaEditRequest close 상태로 변경하고 관련 정보 반환
        return VersionRequestDetails.from(
            pediaEditRequestService.closePediaEditRequest(code, findMember));
    }

    // 5번 RequestDecline post
    //관리자 페이지 수정 요청 거절
    public VersionRequestDetails rejectPediaEditRequest(String code, String memberEmail) {
        Member findMember = memberService.getMemberByEmail(memberEmail);

        System.out.println("수정 요청 거절 서비스 도달");
        // 요청이 거절된 경우는 PediaVersion에 REJ- 로 시작하는 코드를 부여
        PediaEditRequest findPediaEditRequest = pediaEditRequestService.getPediaEditRequestByCode(
            code);
        log.info("findPediaEditRequest = {}", findPediaEditRequest);

        PediaVersion findPediaVersion = findPediaEditRequest.getPediaVersion();
        log.info("findPediaVersion = {}", findPediaVersion);

        findPediaVersion.setPediaVersionCode(genRejectedPediaVersionCode());


        // PediaEditeRequest close 상태로 변경하고 관련 정보 반환
        return VersionRequestDetails.from(
            pediaEditRequestService.closePediaEditRequest(code, findMember));
    }

    // 컨트롤러에서는 엮어줄 필요가 없음
    // 현재 버젼 바탕으로 다음 버젼 생성 (+0.1)
    // 기존 버젼이 없을 경우, 0.1에서 시작
    public String genNextPediaVersionCode(Long pediaId, String currentVersionCode) {
        if (currentVersionCode == null) {
            return pediaId + "-0.1";
        }

        String currentVersionNumber = currentVersionCode.split("-")[1];
        double currentVersionCodeDouble = Double.parseDouble(currentVersionNumber) + 0.1;

        return pediaId + String.format("-%.1f", currentVersionCodeDouble);
    }


    // 수정 요청 전달 페이지에서 받은 email과 breed로  객체 생성 후 반환
    public VersionRequestDetails create(String email,String breed) {

        VersionRequestDetails versionRequestDetails = VersionRequestDetails.builder()
                .requestedMemberEmail(email)
                .title(breed)
                .build();

        return versionRequestDetails;
    }


    // REJ- 로 시작하는 코드를 생성
    public String genRejectedPediaVersionCode() {
        return "REJ-" + System.currentTimeMillis();
    }
}
