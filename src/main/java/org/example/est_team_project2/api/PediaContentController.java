package org.example.est_team_project2.api;

import org.example.est_team_project2.domain.PediaContent;
import org.example.est_team_project2.service.PediaContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/pediaContent")
public class PediaContentController {
    private final PediaContentService pediaContentService;

    @Autowired
    public PediaContentController(PediaContentService pediaContentService) {
        this.pediaContentService = pediaContentService;
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("pediaContent", new PediaContent());
        return "registerPediaContent";
    }

    @PostMapping("/register")
    public String registerPediaContent(@ModelAttribute("pediaContent") PediaContent pediaContent) {
        pediaContentService.savePediaContent(pediaContent);
        return "redirect:/pediaContent/list";
    }

    @GetMapping("/list")
    public String listPediaContents(Model model, ModelMap modelMap) {
        List<PediaContent> pediaContentList = pediaContentService.getAllPediaContent();
        model.addAttribute("pediaContentList", pediaContentList);
        return "listPediaContents";
    }

}