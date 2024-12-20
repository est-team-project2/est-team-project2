package org.example.est_team_project2.api.pedia;

import org.example.est_team_project2.domain.pedia.Pedia;
import org.example.est_team_project2.domain.pedia.PediaContent;
import org.example.est_team_project2.dto.pedia.PediaContentDTO;
import org.example.est_team_project2.service.pedia.PediaContentService;
import org.example.est_team_project2.service.pedia.PediaService;
import org.example.est_team_project2.service.pedia.PediaVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/pediacontent")
public class PediaContentController {
    private final PediaContentService pediaContentService;
    private final PediaService pediaService;
    private final PediaVersionService pediaVersionService;

    @Autowired
    public PediaContentController(PediaContentService pediaContentService, PediaService pediaService, PediaVersionService pediaVersionService) {
        this.pediaContentService = pediaContentService;
        this.pediaService = pediaService;
        this.pediaVersionService = pediaVersionService;
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        return "pedia_content";
    }

    @PostMapping("/register")
    public ResponseEntity<PediaContent> register(@RequestBody PediaContentDTO pediaContentDTO) {
        // PediaContent 저장
        PediaContent pediaContent = new PediaContent();
        pediaContent.setImageUri(pediaContentDTO.getImage());
        pediaContent.setBreed(pediaContentDTO.getBreed());
        pediaContent.setOrigin(pediaContentDTO.getOrigin());
        pediaContent.setSize(pediaContentDTO.getSize());
        pediaContent.setDetail(pediaContentDTO.getDetail());
        pediaContent.setGeneticDisease(pediaContentDTO.getGeneticDisease());
        pediaContent.setFeature(pediaContentDTO.getFeature());
        pediaContent.setStatus(pediaContentDTO.getStatus());

        PediaContent savedContent = pediaContentService.save(pediaContent);

        // Pedia 저장
        Pedia pedia = new Pedia();
        pedia.setMemberId(pediaContentDTO.getMemberId());
        pedia.setCreatedAt(LocalDateTime.now());
        pedia.setUpdatedAt(LocalDateTime.now());
        pedia.setTitle(pediaContentDTO.getBreed());

        Pedia savedPedia = pediaService.save(pedia);

//        // PediaVersion 저장
//        PediaVersion pediaVersion = new PediaVersion();
//        pediaVersion.setPedia(savedPedia);
//        pediaVersion.setPediaContent(savedContent);
//        pediaVersion.setMemberId(pediaContentDTO.getMemberId());
//        pediaVersion.setCreatedAt(LocalDateTime.now());
//        pediaVersion.setStatus("ACTIVE");
//        pediaVersion.setPediaVersionCode(1); // 버전 코드 설정

//        pediaVersionService.save(pediaVersion);

        return ResponseEntity.ok(savedContent);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PediaContent>> getAll() {
        List<PediaContent> contents = pediaContentService.findAll();
        return ResponseEntity.ok(contents);
    }

    @GetMapping("/view")
    public String viewPediaContent(Model model) {
        List<PediaContent> contents = pediaContentService.findAll();
        model.addAttribute("pediaContents", contents);
        return "pedia_view";  // 템플릿 파일을 반환
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("pediaContent", new PediaContent());
        return "pedia_content_form";
    }

    @PostMapping("/save")
    public String savePediaContent(@ModelAttribute("pediaContent") PediaContent pediaContent) {
        pediaContentService.save(pediaContent);
        return "redirect:/pediacontent/view";
    }

//    @GetMapping("/view")
//    public String viewPediaContentList(Model model) {
//        List<PediaContent> contents = pediaContentService.findAll();
//        model.addAttribute("pediaContents", contents);
//        return "pedia_content_view";
//    }
}