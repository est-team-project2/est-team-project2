package org.example.est_team_project2.api.pedia;

import org.example.est_team_project2.domain.pedia.PediaContent;
import org.example.est_team_project2.dto.Pedia.PediaContentDTO;
import org.example.est_team_project2.service.pedia.PediaContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pediacontetn")

public class PediaContentController {

    private final PediaContentService pediaContentService;

    @Autowired
    public PediaContentController(PediaContentService pediaContentService) {
        this.pediaContentService = pediaContentService;
    }

    @PostMapping("/register")
    public ResponseEntity<PediaContent> register(@RequestBody PediaContentDTO pediaContentDTO) {
        PediaContent pediaContent = new PediaContent();
        pediaContent.setBreed(pediaContentDTO.getBreed());
        pediaContent.setOrigin(pediaContentDTO.getOrigin());
        pediaContent.setSize(pediaContentDTO.getSize());
        pediaContent.setDetail(pediaContentDTO.getDetail());
        pediaContent.setGeneticDisease(pediaContentDTO.getGeneticDisease());
        pediaContent.setFeature(pediaContentDTO.getFeature());

        PediaContent savedContent = pediaContentService.save(pediaContent);
        return ResponseEntity.ok(savedContent);
    }
}