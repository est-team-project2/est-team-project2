package org.example.est_team_project2.api.pedia;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.est_team_project2.domain.member.Member;
import org.example.est_team_project2.domain.pedia.Pedia;
import org.example.est_team_project2.domain.pedia.PediaContent;
import org.example.est_team_project2.domain.pedia.PediaEditRequest;
import org.example.est_team_project2.domain.pedia.requestEnums.CommonStatus;
import org.example.est_team_project2.dto.member.MemberDetails;
import org.example.est_team_project2.dto.member.MemberDto;
import org.example.est_team_project2.dto.pedia.PediaContentDto;
import org.example.est_team_project2.dto.pedia.PediaEditRequestDto;
import org.example.est_team_project2.dto.pedia.PediaFetchDto;
import org.example.est_team_project2.dto.pedia.VersionRequestDetails;
import org.example.est_team_project2.service.member.MemberService;
import org.example.est_team_project2.service.pedia.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PediaContentController {
    private final PediaContentService pediaContentService;
    private final PediaService pediaService;
    private final VersionRequestService versionRequestService;
    private final PediaEditRequestService pediaEditRequestService;
    private final MemberService memberService;



    //백과 리스트 조회 페이지
    @GetMapping("/pedia")
    public String pedia(Model model,PediaContentDto pediaContentDto) {
        List<PediaContent> contents = pediaContentService.findAll();
        model.addAttribute("pediaContents", contents);
        return "/pedia";
    }


//    백과 세부 내용 조회(버튼이동)
    @GetMapping("/pedia/detail/{id}")
    public String showPostById(@PathVariable Long id,Model model) {

        PediaContent content = pediaContentService.findById(id);

        model.addAttribute("content", content);

        return "pedia/viewPediaDetail";
    }


    // 관리자용 수정 요청 리스트 조회
    @GetMapping("/view-edit-request")
    public String viewEditRequest(Model model) {

        List<PediaEditRequest> requestList = pediaEditRequestService.findAllPediaEditRequests();

        log.info("requestList = {}", requestList);
        model.addAttribute("editRequest", requestList);
        return "/pedia/viewEditRequest";  // 템플릿 파일을 반환
    }


    // 백과 수정 요청 조회  (버튼 이동)
    @GetMapping("/pedia/edit-request/{id}")
    public String pediaContentEdit(@PathVariable Long id,Model model,Principal principal) {
        System.out.println("수정페이지 get 도착");

        log.info("id = {}", id);

        addUserAttributesToModel(principal, model);
        log.info("principal = {}", principal);

        PediaContent byId = pediaContentService.findById(id);


        model.addAttribute("content", byId);
        model.addAttribute("versionRequestDetails", new VersionRequestDetails());
        model.addAttribute("pediaContentDto", new PediaContentDto());


        return "/pedia/editRequest";
    }



    //  수정 요청 전달 페이지(버튼) / 컨트롤러 분리 작업 필요
    @PostMapping("/sendEditInfo/{id}")
    public String processPediaContentEdit(@PathVariable Long id,
                                          PediaContentDto pediaContentDto, Principal principal) {

        System.out.println("수정페이지 컨트롤러 도착");

        PediaContent byId = pediaContentService.findById(id);
        log.info("byId = {}", byId);

        String breed = byId.getBreed();

        Pedia pediaByTitle = pediaService.getPediaByTitle(byId.getBreed());
        log.info("pediaByTitle = {}", pediaByTitle);


        String name = principal.getName();
        log.info("name = {}", name);

        Member memberByNickName = memberService.getMemberByNickName(name);
        log.info("memberByNickName = {}", memberByNickName);

        String email = memberByNickName.getEmail();
        log.info("email = {}", email);

        VersionRequestDetails versionRequestDetails = VersionRequestDetails.builder()
                .requestedMemberEmail(email)
                .title(breed)
                .build();


        versionRequestService.createNewEditRequest(versionRequestDetails, pediaContentDto);

        System.out.println(" 로직 수행후");
        return "redirect:/view-edit-request";
    }

    
    //관리자용 강아지 종류 삽입
    @GetMapping("/registerOnlyBreed")
    public String registerOnlyBreed(Model model) {


        return "/pedia/registerOnlyBreed";

    }

    //관리자용 강아지 종류 삽입 db 저장 페이지(버튼 이동)
    @PostMapping("/registerOnlyBreed")
    public String processRegisterOnlyBreed(PediaContentDto pediaContentDTO) {



        String breed = pediaContentDTO.getBreed();
        log.info("breed = {}", breed);


        pediaContentService.registerOnlySaveBreed(pediaContentDTO);
        pediaService.save(breed);

        // 관리자 페이지로 넘어가게 리턴 변경
        return "redirect:/pedia";
    }



    // 4번 수정 요청 승인  컨트롤러 작동O
    @PostMapping("/RequestAccept")
    public String processRequestAccept(
//            String code,
//            String memberEmail
            @RequestBody PediaFetchDto req
    ) {

        // 수정 요청 승인 처리
        System.out.println("요청 수락 처리 컨트롤러 도달");
        System.out.println("엔드포인트 도달 로직 수행전");

//        System.out.println(code);
//        System.out.println(memberEmail);

        log.info("req.getCode() = {}", req.getCode());
        log.info("req.getMemberEmail() = {}", req.getMemberEmail());

        versionRequestService.acceptPediaEditRequest(req.getCode(), req.getMemberEmail());
        System.out.println(" 로직 수행후");

        return "redirect:/view-edit-request"; // 성공 페이지 반환
    }



    // 5번 수정 요청 거절  컨트롤러 작동O
    @PostMapping("/RequestDecline")
    public String processRequestDecline(@RequestBody PediaFetchDto req) {

        System.out.println("요청 거절 처리");
        System.out.println("엔드포인트 도달 로직 수행전 컨트롤러 도달");
        log.info("req.getCode() = {}", req.getCode());
        log.info("req.getMemberEmail() = {}", req.getMemberEmail());


        versionRequestService.rejectPediaEditRequest(req.getCode(), req.getMemberEmail());

        System.out.println(" 로직 수행후");
        return "redirect:/view-edit-request"; // 거절 페이지 반환
    }

    // 수정요청 상세 확인 이동 컨트롤러 작동 //컨트롤러 서비스 분리X
    @GetMapping("/view-edit-request/detail/{id}")
    public String viewEditRequest(Model model,@PathVariable Long id ,PediaContentDto pediaContentDto) {
        System.out.println("viewEditRequestDetail 로직도착");

        model.addAttribute(pediaContentDto);
        log.info("id = {}", id);

        PediaContent byId = pediaContentService.findById(id);
        log.info("byId = {}", byId);

        model.addAttribute("editRequest", byId);

        List<PediaContent> all = pediaContentService.findAll();
        log.info("all = {}", all);
        //3개

        String breed = byId.getBreed();
        log.info("breed = {}", breed);

        int a = all.size();
        log.info("a = {}", a);

        int b = a - 1;
        log.info("b = {}", b);




        PediaContent beforinfo = null;

        for (int i = b; i >= 0; i--) {
            System.out.println("for문 도착");


                if (breed.equals(all.get(i).getBreed())) {
                    beforinfo = pediaContentService.findById((long) i + 1);
                    log.info("beforinfo = {}", beforinfo);
                    break;
                }


        }
        if (beforinfo != null) {
            model.addAttribute("beforinfo",beforinfo);
        }
        log.info("beforinfo = {}", beforinfo);


        System.out.println("로직 수행 완료");
        return "/pedia/viewEditRequestDetail";
    }


    // 컨텐츠 조회 사용 X
    private boolean addUserAttributesToModel(Principal principal, Model model) {
        if (principal instanceof OAuth2AuthenticationToken token) {
            MemberDetails memberDetails = (MemberDetails) token.getPrincipal();
            model.addAttribute("email", memberDetails.getEmail());
            return true;
        } else if (principal instanceof UsernamePasswordAuthenticationToken token) {
            MemberDto memberDto = (MemberDto) token.getPrincipal();
            model.addAttribute("email", memberDto.getEmail());
            return true;
        }
        return false;
    }




}