package org.example.est_team_project2.api.pedia;


import org.example.est_team_project2.domain.pedia.Pedia;
import org.example.est_team_project2.service.pedia.PediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PediaController {

    private final PediaService service;

    @Autowired
    public PediaController(PediaService service) {
        this.service = service;
    }

//    @GetMapping("/pedia/register")
//    public String showRegisterForm(Model model) {
//        model.addAttribute("pedia", new Pedia());
//        return "registerPedia"; // 등록페이지
//    }

    @PostMapping("/pedia/register")
    public String registerPedia(@ModelAttribute Pedia pedia) {
        service.save(pedia);
        return "redirect:/pedia/list"; // 등록 후 목록 페이지로
    }

    @GetMapping("/pedia/list")
    public String listPedia(Model model) {
        List<Pedia> pediaList = service.findAll();
        model.addAttribute("pediaList", pediaList);
        return "listPedia"; // 목록페이지
    }

}
