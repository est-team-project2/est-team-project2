package org.example.est_team_project2.service.pedia;

import static org.assertj.core.api.Assertions.assertThat;

import org.example.est_team_project2.domain.pedia.Pedia;
import org.example.est_team_project2.domain.pedia.PediaVersion;
import org.example.est_team_project2.domain.pedia.requestEnums.CommonStatus;
import org.example.est_team_project2.domain.pedia.requestEnums.RequestStatus;
import org.example.est_team_project2.dto.pedia.PediaContentDto;
import org.example.est_team_project2.dto.pedia.PediaDto;
import org.example.est_team_project2.dto.pedia.VersionRequestDetails;
import org.example.est_team_project2.service.member.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class VersionRequestServiceTest {

    @Autowired
    private VersionRequestService versionRequestService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private PediaService pediaService;

    @Autowired
    private PediaVersionService pediaVersionService;

    private VersionRequestDetails versionRequestDetails1;
    private PediaContentDto pediaContentDto1;

    private VersionRequestDetails versionRequestDetails2;
    private PediaContentDto pediaContentDto2;

    private VersionRequestDetails versionRequestDetails3;
    private PediaContentDto pediaContentDto3;

    private VersionRequestDetails versionRequestDetails4;
    private PediaContentDto pediaContentDto4;

    @BeforeEach
    @Rollback
    void setUp() {
//        MemberDto memberDto1 = new MemberDto("user1@user.com", "nickName1", "password1",
//            MemberType.USER);
//
//        MemberDto memberDto2 = new MemberDto("user2@user.com", "nickName2", "password2",
//            MemberType.USER);
//
//        MemberDto memberDto3 = new MemberDto("admin@user.com", "nickName0", "password0",
//            MemberType.ADMIN);
//
//        memberService.save(memberDto1);
//        memberService.save(memberDto2);
//        memberService.save(memberDto3);

        Pedia pedia1 = Pedia.builder().title("푸들").build();

        Pedia pedia2 = Pedia.builder().title("말티즈").build();

        pediaService.save(PediaDto.from(pedia1));
        pediaService.save(PediaDto.from(pedia2));

        versionRequestDetails1 = VersionRequestDetails.builder().title("푸들")
            .requestedMemberEmail("user1@user.com").build();
        pediaContentDto1 = PediaContentDto.builder().imageUri("IMAGE01").breed("푸들")
            .origin("유럽").size("소중대형").detail("세부사항1").geneticDisease("유전병1").feature("특징1")
            .build();

        versionRequestDetails2 = VersionRequestDetails.builder().title("푸들")
            .requestedMemberEmail("user2@user.com").build();
        pediaContentDto2 = PediaContentDto.builder().imageUri("IMAGE02")
            .breed("푸들").origin("아무지역").size("소형").detail("세부사항2").geneticDisease("유전병2")
            .feature("특징2").build();

        versionRequestDetails3 = VersionRequestDetails.builder().title("말티즈")
            .requestedMemberEmail("user2@user.com").build();
        pediaContentDto3 = PediaContentDto.builder().imageUri("IMAGE02")
            .breed("푸들").origin("아무지역").size("소형").detail("세부사항2").geneticDisease("유전병2")
            .feature("특징2").build();

        versionRequestDetails4 = VersionRequestDetails.builder().title("말티즈")
            .requestedMemberEmail("user2@user.com").build();
        pediaContentDto4 = PediaContentDto.builder().imageUri("IMAGE02")
            .breed("푸들").origin("아무지역").size("소형").detail("세부사항2").geneticDisease("유전병2")
            .feature("특징2").build();
    }

    @Test
    @Rollback
    @DisplayName("새 수정 요청 생성 테스트")
    void createNewPediaEditRequestTest() throws Exception {
        VersionRequestDetails findDetails = versionRequestService.createNewEditRequest(
            versionRequestDetails1, pediaContentDto1);

        assertThat(findDetails.getTitle()).isEqualTo(versionRequestDetails1.getTitle());
        assertThat(findDetails.getRequestedMemberEmail()).isEqualTo(
            versionRequestDetails1.getRequestedMemberEmail());
    }

    @Test
    @Rollback
    @DisplayName("수정 요청에서 내용 읽기 테스트")
    void readPediaContentAndRequestByCodeTest() throws Exception {
        VersionRequestDetails findDetails1 = versionRequestService.createNewEditRequest(
            versionRequestDetails1, pediaContentDto1);

        assertThat(
            versionRequestService.readPediaContentByCode(findDetails1.getPediaEditRequestCode())
                .getImageUri()).isEqualTo(pediaContentDto1.getImageUri());
    }

    @Test
    @Rollback
    @DisplayName("수정 요청 거절 테스트")
    void rejectPediaEditRequestTest() throws Exception {
        VersionRequestDetails findDetails = versionRequestService.createNewEditRequest(
            versionRequestDetails1, pediaContentDto1);

        PediaVersion findPediaVersion = pediaVersionService.getPediaVersionByCode(
            findDetails.getPediaVersionCode());

        versionRequestService.rejectPediaEditRequest(findDetails.getPediaEditRequestCode(),
            "user1@user.com");

        // 요청이 CLOSED 상태로 바뀌었는지 확인
        assertThat(
            versionRequestService.readPediaEditRequestByCode(findDetails.getPediaEditRequestCode())
                .getStatus()).isEqualTo(RequestStatus.CLOSED);

        // 버전코드가 REJ로 시작하고, 상태가 INACTIVE인지 확인
        assertThat(findPediaVersion.getPediaVersionCode()).startsWith("REJ-");
        assertThat(findPediaVersion.getStatus()).isEqualTo(
            CommonStatus.INACTIVE);
    }

    @Test
    @Rollback
    @DisplayName("수정 요청 수락 테스트")
    void acceptPediaEditRequestTest() throws Exception {
        VersionRequestDetails findDetails = versionRequestService.createNewEditRequest(
            versionRequestDetails1, pediaContentDto1);

        PediaVersion findPediaVersion1 = pediaVersionService.getPediaVersionByCode(
            findDetails.getPediaVersionCode());

        versionRequestService.acceptPediaEditRequest(findDetails.getPediaEditRequestCode(),
            "admin@user.com");

        Pedia findPedia = pediaService.getPediaByTitle("푸들");

        // 수락한 버전이 백과에 적용되었는지 확인
        assertThat(findPedia.getCurrentVersionCode()).isEqualTo(
            findPediaVersion1.getPediaVersionCode());

        VersionRequestDetails findDetails2 = versionRequestService.createNewEditRequest(
            versionRequestDetails2, pediaContentDto2);

        PediaVersion findPediaVersion2 = pediaVersionService.getPediaVersionByCode(
            findDetails2.getPediaVersionCode()
        );

        versionRequestService.acceptPediaEditRequest(findDetails2.getPediaEditRequestCode(),
            "admin@user.com");

        // 새로운 버전이 백과에 적용되었는지, ACTIVE 되었는지 확인
        assertThat(findPedia.getCurrentVersionCode()).isEqualTo(
            findPediaVersion2.getPediaVersionCode());
        assertThat(findPediaVersion2.getStatus()).isEqualTo(CommonStatus.ACTIVE);

        // 지난 버젼이 INACTIVE 되었는지 확인
        assertThat(findPediaVersion1.getStatus()).isEqualTo(
            CommonStatus.INACTIVE);
    }
}