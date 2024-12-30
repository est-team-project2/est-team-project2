package org.example.est_team_project2.api;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.est_team_project2.dto.board.PostDto;
import org.example.est_team_project2.service.board.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final PostService postService;

    @GetMapping("/")
    public String index(Model model) {

        List<PostDto> topPosts = postService.findTopPostsByViews();

        model.addAttribute("topPosts", topPosts);

        return "index";
    }
}
