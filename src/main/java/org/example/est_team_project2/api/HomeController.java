package org.example.est_team_project2.api;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.est_team_project2.dao.pedia.PediaContentRepository;
import org.example.est_team_project2.dao.pedia.PediaRepository;
import org.example.est_team_project2.domain.pedia.PediaContent;
import org.example.est_team_project2.dto.board.PostDto;
import org.example.est_team_project2.service.board.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final PostService postService;
    private final PediaContentRepository pediaContentRepository;

    @GetMapping("/")
    public String index(
            @PageableDefault(size = 10) Pageable pageable,
            Model model) {

        Page<PediaContent> pediaContents = pediaContentRepository.findTop8ByStatusOrderByIdDesc(pageable);
        log.info("pediaContents = {}", pediaContents);
        List<PostDto> topPosts = postService.findTopPostsByViews();

        model.addAttribute("pediaContents", pediaContents);
        model.addAttribute("topPosts", topPosts);


        return "index";
    }
}
