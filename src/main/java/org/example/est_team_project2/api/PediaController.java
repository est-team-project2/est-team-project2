package org.example.est_team_project2.api;


import org.springframework.ui.Model;
import org.checkerframework.checker.units.qual.A;
import org.example.est_team_project2.domain.Pedia;
import org.example.est_team_project2.service.PediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/pedia")
public class PediaController {
    private final PediaService pediaService;

    @Autowired
    public PediaController(PediaService pediaService) {
        this.pediaService = pediaService;
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("pedia", new Pedia());
        return "pedia/register"; // Thymeleaf 템플릿 이름
    }


    @PostMapping("/register")
    public String registerPedia(@ModelAttribute Pedia pedia) {
        pediaService.savePedia(pedia);
        return "redirect:/pedia/list"; // 등록 후 목록 페이지로 리다이렉트

    }

    @GetMapping("/list")
    public String listPedia(Model model) {
        List<Pedia> pediaList =  pediaService.getAllPedia();
        model.addAttribute("pediaList", pediaList);
        return "listPedia"; // Thymeleaf 템플릿 이름

    }
}