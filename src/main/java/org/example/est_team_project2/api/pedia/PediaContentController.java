package org.example.est_team_project2.api.pedia;

import org.example.est_team_project2.domain.pedia.PediaContent;
import org.example.est_team_project2.dto.Pedia.PediaContentDTO;
import org.example.est_team_project2.service.pedia.PediaContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("/pediacontent")
public class PediaContentController {
    private final PediaContentService pediaContentService;

    @Autowired
    public PediaContentController(PediaContentService pediaContentService) {
        this.pediaContentService = pediaContentService;
    }

    @GetMapping("/register")
    public String showRegisterForm(){
        return "pedia_content";
    }

    @PostMapping("/register")
    public ResponseEntity<PediaContent> register(@RequestBody PediaContentDTO pediaContentDTO) {
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
        return "pedia_view";  // 새로운 템플릿 파일을 반환
    }

    @GetMapping("/pediacontent/new")
    public String showForm(Model model) {
        model.addAttribute("pediaContent", new PediaContent());
        return "pedia_content_form";
    }

    @PostMapping("/pedacontent/save")
    public String savePediaContent(@ModelAttribute("pediaContent") PediaContent pediaContent) {
        pediaContentService.save(pediaContent);
        return "redirect:/pediacontent/view";
    }

    @GetMapping("/pediacontent/view")
    public String ViewPediaContent(Model model) {
        List<PediaContent> contents = pediaContentService.findAll();
        model.addAttribute("pediaContents", contents);
        return "pedia_content_view";
    }
}

