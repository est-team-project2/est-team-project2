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

    public VersionRequestDetails createNewEditRequest(VersionRequestDetails versionRequestDetails,
        PediaContentDto pediaContentDto) {

        Member findMember = memberService.getMemberByEmail(
            versionRequestDetails.getRequestedMemberEmail());
        PediaContent findPediaContent = pediaContentService.save(pediaContentDto);
        Pedia findPedia = pediaService.getPediaByTitle(versionRequestDetails.getTitle());

        PediaVersion pediaVersion = PediaVersion.builder()
            .pedia(findPedia)
            .pediaContent(findPediaContent)
            .editor(findMember).build();
        PediaVersion findPediaVersion = pediaVersionService.save(pediaVersion);

        PediaEditRequest pediaEditRequest = PediaEditRequest.builder()
            .pediaVersion(findPediaVersion)
            .requestedMember(findMember)
            .build();
        PediaEditRequest findPediaEditRequest = pediaEditRequestService.save(pediaEditRequest);

        return VersionRequestDetails.from(findPediaEditRequest);
    }

    public PediaContentDto readPediaContent(String code) {
        PediaEditRequest findPediaEditRequest = pediaEditRequestService.getPediaEditRequestByCode(
            code);

        return PediaContentDto.from(findPediaEditRequest.getPediaVersion().getPediaContent());
    }

    public VersionRequestDetails readPediaEditRequest(String code) {
        PediaEditRequest findPediaEditRequest = pediaEditRequestService.getPediaEditRequestByCode(
            code);

        return VersionRequestDetails.from(findPediaEditRequest);
    }

    public VersionRequestDetails acceptPediaEditRequest(String code, String memberEmail) {
        Member findMember = memberService.getMemberByEmail(memberEmail);

        PediaEditRequest findPediaEditRequest = pediaEditRequestService.getPediaEditRequestByCode(
            code);
        PediaVersion findPediaVersion = findPediaEditRequest.getPediaVersion();
        Pedia findPedia = findPediaVersion.getPedia();

        // 기존 pediaVersion 비활성화
        String currentPediaVersionCode = findPedia.getCurrentVersionCode();
        PediaVersion findCurrentPediaVersion = pediaVersionService.getPediaVersionByCode(
            currentPediaVersionCode);
        findCurrentPediaVersion.setStatus(CommonStatus.INACTIVE);

        // 새로운 pediaVersion 부여, pedia current 바꿔주기
        String nextPediaVersionCode = genNextPediaVersionCode(currentPediaVersionCode);
        findPediaVersion.setPediaVersionCode(nextPediaVersionCode);
        findPediaVersion.setStatus(CommonStatus.ACTIVE);
        findPedia.setCurrentVersionCode(nextPediaVersionCode);

        return VersionRequestDetails.from(
            pediaEditRequestService.closePediaEditRequest(code, findMember));
    }

    public VersionRequestDetails rejectPediaEditRequest(String code, String memberEmail) {
        Member findMember = memberService.getMemberByEmail(memberEmail);

        // 거절된 pediaVersionCode 부여
        PediaEditRequest findPediaEditRequest = pediaEditRequestService.getPediaEditRequestByCode(
            code);
        PediaVersion findPediaVersion = findPediaEditRequest.getPediaVersion();
        findPediaVersion.setPediaVersionCode(genRejectedPediaVersionCode());

        return VersionRequestDetails.from(
            pediaEditRequestService.closePediaEditRequest(code, findMember));
    }

    public String genNextPediaVersionCode(String currentVersionCode) {
        if (currentVersionCode == null) {
            return "0.1";
        }

        double currentVersionCodeDouble = Double.parseDouble(currentVersionCode) + 0.1;

        return String.format("%.1f", currentVersionCodeDouble);
    }

    public String genRejectedPediaVersionCode() {
        return "REJ-" + System.currentTimeMillis();
    }
}
