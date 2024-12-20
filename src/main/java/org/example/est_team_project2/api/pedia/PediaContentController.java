package org.example.est_team_project2.api.pedia;

import lombok.RequiredArgsConstructor;
import org.example.est_team_project2.domain.pedia.PediaContent;
import org.example.est_team_project2.dto.pedia.PediaContentDto;
import org.example.est_team_project2.dto.pedia.PediaEditRequestDto;
import org.example.est_team_project2.dto.pedia.VersionRequestDetails;
import org.example.est_team_project2.service.pedia.PediaContentService;
import org.example.est_team_project2.service.pedia.PediaService;
import org.example.est_team_project2.service.pedia.PediaVersionService;
import org.example.est_team_project2.service.pedia.VersionRequestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@RequestMapping("/pediacontent")
@RequiredArgsConstructor
public class PediaContentController {
    private final PediaContentService pediaContentService;
    private final PediaService pediaService;
    private final PediaVersionService pediaVersionService;
    private final VersionRequestService versionRequestService;


    @GetMapping("/register")
    public String registerContents() {

        return "register";
    }

    @PostMapping("/register")
    public String processRegisterContents(PediaContentDto pediaContentDTO) {

        pediaContentService.save(pediaContentDTO);

        return "index";
    }

    @GetMapping("/registerOnlyBreed")
    public String registerOnlyBreed() {

        return "register_only_breed";

    }


    @PostMapping("/registerOnlyBreed")
    public String processRegisterOnlyBreed(PediaContentDto pediaContentDTO) {

        pediaContentService.registerOnlyBreed(pediaContentDTO);

        return "index";
    }

    //  경돈님 서비스 컨트롤러 맵핑
    // 1번 수정페이지 해결!
    @GetMapping("/pediaContentEdit")
    public String pediaContentEdit(Model model) {

        // 이것 또한 하나로 합친 객체를 getter로 넣는다.
        model.addAttribute("versionRequestDetails", new VersionRequestDetails());
        model.addAttribute("pediaContentDto", new PediaContentDto());

        //일단 페이지가 없음 임시로 대강 두자
        return "page1";
    }


    // 1번 - 2 수정페이지
    // 하나의 객체로 만드는데 2개를 참조 참조 하면 받기가 힘드니까 해결
    @PostMapping("/pediaContentEdit")
    public String processPediaContentEdit(VersionRequestDetails versionRequestDetails,
                                          PediaContentDto pediaContentDto) {

        System.out.println(versionRequestDetails.toString());
        System.out.println("엔드포인트 도달 로직 수행전");

        versionRequestService.createNewEditRequest(versionRequestDetails, pediaContentDto);

        System.out.println(" 로직 수행후");
        return "page1";
    }


    // 2번 수정요청의 content 내용만 확인 (관리자) 해결!
    @GetMapping("/checkContents")
    public String checkContents(PediaEditRequestDto requestDto) {

        versionRequestService.readPediaContentByCode(requestDto.getPediaEditRequestCode());

        //일단 페이지가 없음 임시로 대강 두자
        return "page1";
    }


    // 3번 수정요청의 자세한 내용 확인 (관리자) 해결!
    @GetMapping("/checkAllContents")
    public String checkAllContents(PediaEditRequestDto requestDto) {

        System.out.println("엔드포인트 도달 로직 수행전");
        System.out.println(requestDto);
        System.out.println("getPediaEditRequestCode = " + requestDto.getPediaEditRequestCode());
        System.out.println("-----");

        versionRequestService.readPediaEditRequestByCode(requestDto.getPediaEditRequestCode());

        System.out.println(" 로직 수행후");
        //일단 페이지가 없음 임시로 대강 두자
        return "page1";
    }

    // 4번 수정 요청 승인 해결!
    @PostMapping("/RequestAccept")
    public String processRequestAccept(String code, String memberEmail) {

        // 수정 요청 승인 처리
        System.out.println("엔드포인트 도달 로직 수행전");
        System.out.println(code);
        System.out.println(memberEmail);

        versionRequestService.acceptPediaEditRequest(code, memberEmail);
        System.out.println(" 로직 수행후");

        return "page1"; // 성공 페이지 반환
    }


    // 5번 수정 요청 거절 해결!
    @PostMapping("/RequestDecline")
    public String processRequestDecline(String code, String memberEmail) {

        System.out.println("엔드포인트 도달 로직 수행전");
        // 요청 거절 처리
        versionRequestService.rejectPediaEditRequest(code, memberEmail);
        System.out.println(" 로직 수행후");
        return "page1"; // 거절 페이지 반환
    }


    @GetMapping("/view")
    public String viewPediaContent(Model model) {
        List<PediaContent> contents = pediaContentService.findAll();
        model.addAttribute("pediaContents", contents);
        return "pedia_view";  // 템플릿 파일을 반환
    }

}